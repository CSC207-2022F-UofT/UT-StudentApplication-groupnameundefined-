package frontend.components;

import frontend.exception.ResponseException;
import frontend.schema.UserSchema;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

@Component
public class HabitSettingPanel extends JPanel implements ActionListener {


//    private final Logger logger;
//    private final WebClient webClient;
//    private final UserSchema userSchema;
    private MainPanel mainPanel;

    private static JButton submitButton;
    private static JSlider collaborateSlider;
    private static JSlider talkativeSlider;
    private static JLabel collaborateLabel;
    private static JLabel talkativeLabel;


    @Autowired
    public HabitSettingPanel() {
    }

    public void initialize(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        this.setBounds(0, 0, 500, 500);
        this.setBackground(new Color(128, 128, 255));
        this.setLayout(null);

        talkativeLabel = new JLabel();
        collaborateLabel = new JLabel();

        collaborateSlider = new JSlider(1, 5, 4);
        collaborateSlider.setBounds(150, 90, 70, 20);
        collaborateSlider.setPaintTrack(true);
        collaborateSlider.setPaintTicks(true);
        collaborateSlider.setPaintLabels(true);
        collaborateSlider.setMajorTickSpacing(3);
        collaborateSlider.setMinorTickSpacing(1);

        talkativeSlider = new JSlider(1, 5, 4);
        talkativeSlider.setBounds(160, 115, 170, 20);
        talkativeSlider.setPaintTrack(true);
        talkativeSlider.setPaintTicks(true);
        talkativeSlider.setPaintLabels(true);
        talkativeSlider.setMajorTickSpacing(3);
        talkativeSlider.setMinorTickSpacing(1);

        collaborateLabel.setText("Your collaborate index is " + collaborateSlider.getValue() + "5 means you enjoy collaborating with others a lot, 0 means the least");
        talkativeLabel.setText("Your talkative index is " + talkativeSlider.getValue() + "5 means you're very talkative, 0 means you are the least talkative");
        talkativeLabel.setBounds(160, 115, 170, 20);
        collaborateLabel.setBounds(160, 115, 170, 20);

        submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(this);

        // add slider to panel
        this.add(collaborateLabel);
        this.add(collaborateSlider);
        this.add(talkativeLabel);
        this.add(talkativeSlider);
        this.add(submitButton);

        this.setVisible(true);
    }


    public void cleanAll() {
        this.collaborateSlider.setValue(3);
        this.talkativeSlider.setValue(3);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            Integer collaborate = collaborateSlider.getValue();
            Integer talkative = talkativeSlider.getValue();
            System.out.print("Payload habit" + "collaborate:" + collaborate + "talkative:" + talkative); // to be replaced
        }
//        if(e.getSource() == submitButton){
//            Integer collaborate = collaborateSlider.getValue();
//            Integer talkative = talkativeSlider.getValue();
//            Map<String, Integer> payload = new HashMap<>();
//            payload.put("collaborate", collaborate);
//            payload.put("talkative", talkative);
//
//            Mono<HabitSchema> response = webClient.post()
//                    .uri("/habit/create")
//                    .body(BodyInserters.fromValue(payload))
//                    .retrieve()
//                    .bodyToMono(HabitSchema.class)
//                    .onErrorComplete();
//
//        }
    }
}
