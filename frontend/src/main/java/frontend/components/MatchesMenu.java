package frontend.components;

import frontend.schema.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MatchesMenu extends JScrollPane {

    private ArrayList<StudentProfileSchema> Matches = new ArrayList<StudentProfileSchema>();
    private JPanel jpMatchesList = new JPanel();
    private ShowMatchesPanel parent;

    public void updateMatches(ArrayList<StudentProfileSchema> Matches, WebClient webClient){

        jpMatchesList.removeAll();
        jpMatchesList.setLayout(new BoxLayout(jpMatchesList, BoxLayout.PAGE_AXIS));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        for (StudentProfileSchema match : Matches){
            JPanel matchLine = new JPanel(new BorderLayout());
            JPanel buttons = new JPanel(new BorderLayout());
            JButton open = new JButton("See Profile");
            buttons.add(open, BorderLayout.CENTER);
            matchLine.add(new JLabel(match.getUserName()));
            matchLine.add(buttons, BorderLayout.EAST);
            jpMatchesList.add(matchLine);

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel jpID = new JPanel(new GridLayout(1, 2));
                    jpID.add(new JLabel("Name"));
                    jpID.add(new JLabel(match.getUserName()));

                    JPanel jpProgram = new JPanel(new GridLayout(1, 2));
                    jpProgram.add(new JLabel("Program:"));
                    jpProgram.add(new JLabel(match.getProgram()));

                    JPanel jpCollege = new JPanel(new GridLayout(1,2));
                    jpCollege.add(new JLabel("College:"));
                    jpCollege.add(new JLabel(match.getCollege()));

                    JPanel jpEnrolment = new JPanel(new GridLayout(1,2));
                    jpEnrolment.add(new JLabel("Enrolled since:"));
                    jpEnrolment.add(new JLabel(match.getEnrolmentYear().toString()));

                    SocialMediaProfileSchema matchSocials = match.getSocialMediaProfile();
                    JPanel jpIg = new JPanel(new GridLayout(1, 2));
                    if (matchSocials.getInstagramId() != null){
                        jpIg.add(new JLabel("Instagram Id:"));
                        jpIg.add(new JLabel("@" + matchSocials.getInstagramId()));
                    }

                    JPanel jpFb = new JPanel(new GridLayout(1, 2));
                    if (matchSocials.getFacebookId() != null){
                        jpFb.add(new JLabel("Faceboook Link:"));
                        jpFb.add(new JLabel("www.facebook.com/" + matchSocials.getInstagramId()));
                    }
                    HabitSchema matchHabits = match.getHabit();

                    JPanel jpTalk = new JPanel(new GridLayout(1, 2));
                    jpTalk.add(new JLabel("Talkativeness"));
                    JSlider jsTalkativeness = new JSlider(JSlider.HORIZONTAL, 1, 5, matchHabits.getTalkative());
                    jsTalkativeness.setMajorTickSpacing(1);
                    jsTalkativeness.setPaintTicks(true);
                    jsTalkativeness.setSnapToTicks(true);
                    jsTalkativeness.setEnabled(false);
                    JPanel jst = new JPanel(new GridLayout(1, 3));
                    jst.add(new JLabel("Not talkative"));
                    jst.add(jsTalkativeness);
                    jst.add(new JLabel(" Very talkative"));
                    jpTalk.add(jst);

                    JPanel jpCollab = new JPanel(new GridLayout(1, 2));
                    jpCollab.add(new JLabel("Collaborativeness"));
                    JSlider jsCollaborate = new JSlider(JSlider.HORIZONTAL, 1, 5, matchHabits.getCollaborative());
                    jsCollaborate.setMajorTickSpacing(1);
                    jsCollaborate.setPaintTicks(true);
                    jsCollaborate.setSnapToTicks(true);
                    jsCollaborate.setEnabled(false);
                    JPanel jsc = new JPanel(new GridLayout(1, 3));
                    jsc.add(new JLabel("Not Collaborative"));
                    jsc.add(jsCollaborate);
                    jsc.add(new JLabel(" Very Collaborative"));
                    jpCollab.add(jsc);

                    JLabel jlblMessage = new JLabel("Message to send:");
                    JTextArea message = new JTextArea();

                    JPanel jpNewMatchMenu = new JPanel(new GridLayout(12, 1));
                    jpNewMatchMenu.add(jpID);
                    jpNewMatchMenu.add(jpProgram);
                    jpNewMatchMenu.add(jpCollab);
                    jpNewMatchMenu.add(jpEnrolment);
                    jpNewMatchMenu.add(jpFb);
                    jpNewMatchMenu.add(jpIg);
                    jpNewMatchMenu.add(jpTalk);
                    jpNewMatchMenu.add(jpCollab);
                    jpNewMatchMenu.add(jlblMessage);
                    jpNewMatchMenu.add(message);

                    JPanel jpDialog = new JPanel(new BorderLayout());
                    jpDialog.add(jpNewMatchMenu, BorderLayout.CENTER);
                    String[] options = new String[]{
                            "Exit",
                            "Send Friend Request"
                    };

                    int result = JOptionPane.showOptionDialog(null, jpDialog, "Send Friend Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (result == 1) {
                        Map<String, Object> body = new HashMap<>();
                        body.put("fromId", parent.getUserSchema().getId());
                        body.put("toId", match.getUserId());
                        body.put("message", (String) message.getText());
                        Mono<AptRequestSchema> response = webClient.post()
                                .uri("/friend-request/create/")
                                .body(BodyInserters.fromValue(body))
                                .retrieve()
                                .bodyToMono(AptRequestSchema.class);
                        response.subscribe(r -> {
                            Matches.remove(match);
                            updateMatches(Matches, webClient);
                        });
                    }
                }
            });
        }
        setViewportView(jpMatchesList);
    }
    public MatchesMenu(ShowMatchesPanel parent) {
        this.parent = parent;
        jpMatchesList.setLayout(new BoxLayout(jpMatchesList, BoxLayout.PAGE_AXIS));

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(jpMatchesList);
        setPreferredSize(new Dimension(400,800));
        setVisible(true);
    }
}

