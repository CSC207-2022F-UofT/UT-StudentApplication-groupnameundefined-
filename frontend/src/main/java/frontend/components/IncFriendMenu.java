package frontend.components;

import frontend.schema.FriendRequestSchema;
import frontend.schema.BlockSchema;
import frontend.schema.FriendRequestSchema;
import frontend.schema.TimetableSchema;
import frontend.util.CalendarEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.*;

public class IncFriendMenu extends JScrollPane {

    private ArrayList<FriendRequestSchema> IncFriends = new ArrayList<FriendRequestSchema>();
    private JPanel jpFriendList = new JPanel();
    private HomePanel parent;

    public void updateIncFriends(ArrayList<FriendRequestSchema> IncFriends, WebClient webClient){

        jpFriendList.removeAll();
        jpFriendList.setLayout(new BoxLayout(jpFriendList, BoxLayout.PAGE_AXIS));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        for (FriendRequestSchema friend : IncFriends){
            JPanel friendLine = new JPanel(new BorderLayout());
            JPanel buttons = new JPanel(new BorderLayout());
            JButton open = new JButton("open");
            buttons.add(open, BorderLayout.CENTER);
            friendLine.add(new JLabel("From " + friend.getFrom().getName() + " on " + friend.getTimestamp().toLocalDateTime().toLocalDate().toString()), BorderLayout.WEST);
            friendLine.add(buttons, BorderLayout.EAST);
            jpFriendList.add(friendLine);

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel jpFriend = new JPanel(new GridLayout(1, 2));
                    jpFriend.add(new JLabel("Sent from :"));
                    jpFriend.add(new JLabel(friend.getFrom().getName()));

                    JPanel jpTimeSent = new JPanel(new GridLayout(1, 2));
                    jpTimeSent.add(new JLabel("Date sent:"));
                    jpTimeSent.add(new JLabel(friend.getTimestamp().toLocalDateTime().toLocalDate().toString()));

                    JLabel jlblMessage = new JLabel("Message:");
                    JTextArea message = new JTextArea(friend.getMessage());

                    JPanel jpNewFriendMenu = new JPanel(new GridLayout(10, 1));
                    jpNewFriendMenu.add(jpFriend);
                    jpNewFriendMenu.add(jpTimeSent);
                    jpNewFriendMenu.add(jlblMessage);
                    jpNewFriendMenu.add(message);

                    JPanel jpDialog = new JPanel(new BorderLayout());
                    jpDialog.add(jpNewFriendMenu, BorderLayout.CENTER);
                    String[] options = new String[]{
                            "Exit",
                            "Decline",
                            "Accept"
                    };

                    int result = JOptionPane.showOptionDialog(null, jpDialog, "Send Appointment Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (result == 1){
                        Mono<FriendRequestSchema> res = webClient.get()
                                .uri("/friend-request/deny/" + friend.getId())
                                .retrieve()
                                .bodyToMono(FriendRequestSchema.class);
                    }
                    else if (result == 2) {
                        Mono<FriendRequestSchema> response = webClient.get()
                                .uri("/friend-request/approve/" + friend.getId())
                                .retrieve()
                                .bodyToMono(FriendRequestSchema.class);
                        response.subscribe(r -> {
                            IncFriends.remove(friend);
                            updateIncFriends(IncFriends, webClient);
                        });
                    }
                }
            });
        }
        setViewportView(jpFriendList);
    }
    public IncFriendMenu(HomePanel parent) {
        this.parent = parent;
        jpFriendList.setLayout(new BoxLayout(jpFriendList, BoxLayout.PAGE_AXIS));

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(jpFriendList);
        setPreferredSize(new Dimension(400,400));
        setVisible(true);
    }
}
