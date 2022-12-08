package frontend.components;

import frontend.schema.AptRequestSchema;
import frontend.schema.BlockSchema;
import frontend.schema.TimetableSchema;
import frontend.util.*;
import frontend.util.Calendar;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.*;

public class OutAptMenu extends JScrollPane {

    private ArrayList<AptRequestSchema> OutApts = new ArrayList<AptRequestSchema>();
    private JPanel jpAptList = new JPanel();
    private HomePanel parent;
    private final String[] times = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",  "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00"};
    private final String[] daysOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

    public void updateOutApts(ArrayList<AptRequestSchema> OutApts, WebClient webClient){

        jpAptList.removeAll();
        jpAptList.setLayout(new BoxLayout(jpAptList, BoxLayout.PAGE_AXIS));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        for (AptRequestSchema apt : OutApts){
            JPanel aptLine = new JPanel(new BorderLayout());
            JPanel buttons = new JPanel(new BorderLayout());
            JButton open = new JButton("open");
            buttons.add(open, BorderLayout.CENTER);
            aptLine.add(new JLabel("Sent to" + apt.getTo().getName() + " on " + apt.getTimestamp().toLocalDateTime().toLocalDate().toString()), BorderLayout.WEST);
            aptLine.add(buttons, BorderLayout.EAST);
            jpAptList.add(aptLine);

            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel jpDayOfWeek = new JPanel(new GridLayout(1, 2));
                    JComboBox<String> jcbDaysOfWeek = new JComboBox<String>(daysOfWeek);
                    jcbDaysOfWeek.setSelectedItem(DayOfWeek.of(apt.getAptBlock().getStartDay()).toString());
                    jpDayOfWeek.add(new JLabel("Day of the week"));
                    jpDayOfWeek.add(jcbDaysOfWeek);

                    JComboBox<String> jcbStartTime = new JComboBox<String>(times);
                    System.out.println(CalendarEvent.milliToTime(apt.getAptBlock().getStartMil()).toString());
                    jcbStartTime.setSelectedItem(CalendarEvent.milliToTime(apt.getAptBlock().getStartMil()).toString());
                    JPanel jpStart = new JPanel(new GridLayout(1,2));
                    jpStart.add(new JLabel("Start time:"));
                    jpStart.add(jcbStartTime);

                    JComboBox<String> jcbEndTime= new JComboBox<String>(times);
                    jcbEndTime.setSelectedItem(CalendarEvent.milliToTime(apt.getAptBlock().getEndMil()).toString());
                    JPanel jpEnd = new JPanel(new GridLayout(1,2));
                    jpEnd.add(new JLabel("End time:"));
                    jpEnd.add(jcbEndTime);

                    JTextField jtfLocation = new JTextField();
                    jtfLocation.setText(apt.getAptBlock().getLocation().orElse(""));
                    JPanel jpLocation = new JPanel(new GridLayout(1,2));
                    jpLocation.add(new JLabel("Location"));
                    jpLocation.add(jtfLocation);

                    JLabel jlblMessage = new JLabel("Custom message");
                    JTextArea message = new JTextArea();
                    message.setText(apt.getMessage());

                    JPanel jpNewAptMenu = new JPanel(new GridLayout(10, 1));
                    jpNewAptMenu.add(jpDayOfWeek);
                    jpNewAptMenu.add(jpStart);
                    jpNewAptMenu.add(jpEnd);
                    jpNewAptMenu.add(jpLocation);
                    jpNewAptMenu.add(jlblMessage);
                    jpNewAptMenu.add(message);

                    JPanel jpDialog = new JPanel(new GridLayout(1,3));
                    jpDialog.add(new JPanel());
                    jpDialog.add(jpNewAptMenu);
                    jpDialog.add(new JPanel());
                    String[] options = new String[]{
                            "Exit",
                            "Delete",
                            "Update"
                    };

                    int result = JOptionPane.showOptionDialog(null, jpDialog, "Update Appointment Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (result == 1){
                        Mono<AptRequestSchema> response = webClient.get()
                                .uri("/apt-request/delete/" + apt.getId())
                                .retrieve()
                                .bodyToMono(AptRequestSchema.class);

                        response.subscribe(r -> {
                            OutApts.remove(apt);
                        });
                    }
                    else if(result == 2) {
                        Map<String, Object> body = new HashMap<>();
                        body.put("id", apt.getId());
                        body.put("message", (String) message.getText());
                        body.put("location", jtfLocation.getText());
                        body.put("startDay", DayOfWeek.valueOf((String) jcbDaysOfWeek.getSelectedItem()).getValue());
                        body.put("startMil", TimetableCalendar.toMilli(Objects.requireNonNull(jcbStartTime.getSelectedItem()).toString()));
                        body.put("endDay", DayOfWeek.valueOf((String) jcbDaysOfWeek.getSelectedItem()).getValue());
                        body.put("endMil", TimetableCalendar.toMilli(Objects.requireNonNull(jcbEndTime.getSelectedItem()).toString()));
                        System.out.println(body.toString());
                        Mono<AptRequestSchema> response = webClient.post()
                                .uri("/apt-request/update/")
                                .body(BodyInserters.fromValue(body))
                                .retrieve()
                                .bodyToMono(AptRequestSchema.class);
//                        .doOnError(ResponseException.class, exception -> {
//                            if (exception.hasFieldErrors()) {
//                                for (Map<String, String> fieldError : exception.getErrors()) {
//                                    logger.error(fieldError.get("message"));
//                                    if (fieldError.get("field").equals("program")) {
////										programError.setText(fieldError.get("message"));
//                                    } else if (fieldError.get("field").equals("password")) {
////										passwordError.setText(fieldError.get("message"));
//                                    }
//                                }
//                            } else {
//                                logger.error(exception.getMessage());
//                                jlblStatus.setText(exception.getMessage());
//                            }
//                        })
//                        .onErrorComplete();
                        response.subscribe(r -> {
                            OutApts.remove(apt);
                            OutApts.add(r);
                            updateOutApts(OutApts, webClient);
                        });
                    }
                }
            });
        }
        setViewportView(jpAptList);
    }
    public OutAptMenu(HomePanel parent) {
        this.parent = parent;
        jpAptList.setLayout(new BoxLayout(jpAptList, BoxLayout.PAGE_AXIS));

        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setViewportView(jpAptList);
        setPreferredSize(new Dimension(400,400));
        setVisible(true);
    }
}
