package frontend.components;

import frontend.schema.AptRequestSchema;
import frontend.schema.BlockSchema;
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

public class IncAptMenu extends JScrollPane {

    private ArrayList<AptRequestSchema> IncApts = new ArrayList<AptRequestSchema>();
    private JPanel jpAptList = new JPanel();
    private HomePanel parent;

    public void updateIncApts(ArrayList<AptRequestSchema> IncApts, WebClient webClient){

        jpAptList.removeAll();
        jpAptList.setLayout(new BoxLayout(jpAptList, BoxLayout.PAGE_AXIS));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        for (AptRequestSchema apt : IncApts){
            JPanel aptLine = new JPanel(new BorderLayout());
            JPanel buttons = new JPanel(new BorderLayout());
            JButton open = new JButton("open");
            buttons.add(open, BorderLayout.CENTER);
            aptLine.add(new JLabel("From " + apt.getFrom().getName() + " on " + apt.getTimestamp().toLocalDateTime().toLocalDate().toString()), BorderLayout.WEST);
            aptLine.add(buttons, BorderLayout.EAST);
            jpAptList.add(aptLine);

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel jpFriend = new JPanel(new GridLayout(1, 2));
                    jpFriend.add(new JLabel("Sent from :"));
                    jpFriend.add(new JLabel(apt.getFrom().getName()));

                    JPanel jpTimeSent = new JPanel(new GridLayout(1, 2));
                    jpTimeSent.add(new JLabel("Date sent:"));
                    jpTimeSent.add(new JLabel(apt.getTimestamp().toLocalDateTime().toLocalDate().toString()));

                    JPanel jpLocation = new JPanel(new GridLayout(1,2));
                    jpLocation.add(new JLabel("Location at:"));
                    jpLocation.add(new JLabel(apt.getAptBlock().getLocation().orElse("null")));

                    JPanel jpWhen = new JPanel(new GridLayout(1,2));
                    jpWhen.add(new JLabel("Appointment time:"));
                    BlockSchema ab = apt.getAptBlock();
                    jpWhen.add(new JLabel(DayOfWeek.of(ab.getStartDay()).toString() + " from " +
                            CalendarEvent.milliToTime(ab.getStartMil()).toString() + " to " + CalendarEvent.milliToTime(ab.getEndMil())));

                    JLabel jlblMessage = new JLabel("Message:");
                    JTextArea message = new JTextArea(apt.getMessage());

                    JPanel jpNewAptMenu = new JPanel(new GridLayout(10, 1));
                    jpNewAptMenu.add(jpFriend);
                    jpNewAptMenu.add(jpTimeSent);
                    jpNewAptMenu.add(jpLocation);
                    jpNewAptMenu.add(jpWhen);
                    jpNewAptMenu.add(jlblMessage);
                    jpNewAptMenu.add(message);

                    JPanel jpDialog = new JPanel(new BorderLayout());
                    jpDialog.add(jpNewAptMenu, BorderLayout.CENTER);
                    String[] options = new String[]{
                            "Exit",
                            "Decline",
                            "Accept"
                    };

                    int result = JOptionPane.showOptionDialog(null, jpDialog, "Send Appointment Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (result == 1){
                        Mono<AptRequestSchema> response = webClient.get()
                                .uri("/apt-request/deny/" + apt.getId())
                                .retrieve()
                                .bodyToMono(AptRequestSchema.class);
                    }
                    else if (result == 2) {
                        Mono<AptRequestSchema> response = webClient.get()
                                .uri("/apt-request/approve/" + apt.getId())
                                .retrieve()
                                .bodyToMono(AptRequestSchema.class);
                        response.subscribe(r -> {
                            IncApts.remove(apt);
                            TimetableSchema timetable = parent.getTimetable();
                            Set<BlockSchema> tBlocks = timetable.getBlocks();
                            tBlocks.add(apt.getAptBlock());
                            timetable.setBlocks(tBlocks);
                            parent.getStudentCalendar().updateCal(timetable, parent.getUserSchema(), parent.getWebClient());
                            updateIncApts(IncApts, webClient);
                        });
                    }
                }
            });
        }
        setViewportView(jpAptList);
    }
    public IncAptMenu(HomePanel parent) {
        this.parent = parent;
        jpAptList.setLayout(new BoxLayout(jpAptList, BoxLayout.PAGE_AXIS));

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(jpAptList);
        setPreferredSize(new Dimension(400,400));
        setVisible(true);
    }
}
