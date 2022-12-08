package frontend.components;

import frontend.schema.*;
import frontend.util.CalendarEvent;
import frontend.util.Calendar;
import frontend.util.WeekCalendar;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.util.*;

public class TimetableCalendar extends JPanel {

    private WeekCalendar cal = new WeekCalendar(new ArrayList<CalendarEvent>());
    private final String[] times = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",  "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00"};
    private final String[] daysOfWeek = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

    static public long toMilli(String time){
        return Integer.parseInt(time.substring(0,2)) * 3600000L + Integer.parseInt(time.substring(3)) * 1800000L;
    }

    public void updateCal(TimetableSchema timetable, UserSchema userSchema, WebClient webClient){
        ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
        for (BlockSchema block: timetable.getBlocks()){
//            rn backend doesnt set type on blocks
            if (block.getType().equals("SEC")){
                events.add(new CalendarEvent(block.getStartDay(), block.getStartMil(), block.getEndMil(), block.getSection().map(x -> x.getCourse().getName()).orElse("Misc Course"), Color.PINK));
            } else if (block.getType().equals("APT")) {
                events.add(new CalendarEvent(block.getStartDay(), block.getStartMil(), block.getEndMil(), block.getLocation().orElse("Misc Location"), Color.CYAN));
            }
            else{
                System.out.println("Not section or Apt");
            }
        }
        removeAll();
        this.cal = new WeekCalendar(events);
        cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
        cal.addCalendarEmptyClickListener(e -> {

            Mono<ArrayList<UserSchema>> response = webClient.get()
                    .uri("/user/" + userSchema.getId() + "/friends")
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<>() {
                    });
            response.subscribe(r -> {
                Map<String, Long> friendIds = new HashMap<String, Long>();
                ArrayList<String> friends = new ArrayList<String>();
                for (UserSchema user : r) {
                    friends.add(user.getName());
                    friendIds.put(user.getName(), user.getId());
                }

                JPanel jpSelectFriend = new JPanel(new GridLayout(1, 2));
                JComboBox<String> jcbFriends = new JComboBox<String>((friends.toArray(new String[0])));
                jpSelectFriend.add(new JLabel("Send to:"));
                jpSelectFriend.add(jcbFriends);

                JPanel jpDayOfWeek = new JPanel(new GridLayout(1, 2));
                JComboBox<String> jcbDaysOfWeek = new JComboBox<String>(daysOfWeek);
                jcbDaysOfWeek.setSelectedItem(e.getDateTime().getDayOfWeek().toString());
                jpDayOfWeek.add(new JLabel("Day of the week"));
                jpDayOfWeek.add(jcbDaysOfWeek);

                JComboBox<String> jcbStartTime = new JComboBox<String>(times);
                jcbStartTime.setSelectedItem(Calendar.roundTime(e.getDateTime().toLocalTime(), 30).toString());
                JPanel jpStart = new JPanel(new GridLayout(1, 2));
                jpStart.add(new JLabel("Start time:"));
                jpStart.add(jcbStartTime);

                JComboBox<String> jcbEndTime = new JComboBox<String>(times);
                jcbEndTime.setSelectedItem(Calendar.roundTime(e.getDateTime().toLocalTime().plusHours(1), 30).toString());
                JPanel jpEnd = new JPanel(new GridLayout(1, 2));
                jpEnd.add(new JLabel("End time:"));
                jpEnd.add(jcbEndTime);

                JTextField jtfLocation = new JTextField();
                JPanel jpLocation = new JPanel(new GridLayout(1, 2));
                jpLocation.add(new JLabel("Location"));
                jpLocation.add(jtfLocation);

                JLabel jlblMessage = new JLabel("Custom message");
                JTextArea message = new JTextArea();

                JPanel jpNewAptMenu = new JPanel(new GridLayout(10, 1));
                jpNewAptMenu.add(jpSelectFriend);
                jpNewAptMenu.add(jpDayOfWeek);
                jpNewAptMenu.add(jpStart);
                jpNewAptMenu.add(jpEnd);
                jpNewAptMenu.add(jpLocation);
                jpNewAptMenu.add(jlblMessage);
                jpNewAptMenu.add(message);

                JPanel jpDialog = new JPanel(new GridLayout(1, 3));
                jpDialog.add(new JPanel());
                jpDialog.add(jpNewAptMenu);
                jpDialog.add(new JPanel());
                String[] options = new String[]{
                        "Cancel",
                        "Send"
                };

                int result = JOptionPane.showOptionDialog(null, jpDialog, "Send Appointment Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (result > 0) {
                    Map<String, Object> body = new HashMap<>();
                    body.put("fromId", (Long) userSchema.getId());
                    body.put("toId", (Long) friendIds.get(jcbFriends.getSelectedItem()));
                    body.put("message", (String) message.getText());
                    body.put("location", jtfLocation.getText());
                    body.put("startDay", DayOfWeek.valueOf((String) jcbDaysOfWeek.getSelectedItem()).getValue());
                    body.put("startMil", toMilli((String) Objects.requireNonNull(jcbStartTime.getSelectedItem())));
                    body.put("endDay", DayOfWeek.valueOf((String) jcbDaysOfWeek.getSelectedItem()).getValue());
                    body.put("endMil", toMilli((String) Objects.requireNonNull(jcbEndTime.getSelectedItem())));
                    System.out.println(body.toString());
                    Mono<AptRequestSchema> aptResponse = webClient.post()
                            .uri("/apt-request/create")
                            .body(BodyInserters.fromValue(body))
                            .retrieve()
                            .bodyToMono(AptRequestSchema.class);
                    aptResponse.subscribe(t -> {
                        System.out.println(t.getTo());
                    });
                    System.out.println(e.getDateTime());
                    System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
                }
            });
        });

        cal.addCalendarEventClickListener(e -> System.out.println(e.getCalendarEvent()));
        cal.addCalendarEmptyClickListener(e -> {
            System.out.println(e.getDateTime());
            System.out.println(Calendar.roundTime(e.getDateTime().toLocalTime(), 30));
        });
        add(cal, BorderLayout.CENTER);

    }
    public TimetableCalendar() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 1000));
        add(cal, BorderLayout.CENTER);
        setSize(1000, 900);
        setVisible(true);
    }
}
