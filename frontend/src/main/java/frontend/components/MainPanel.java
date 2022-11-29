package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import frontend.components.TopNavigation;

@Component
public class MainPanel extends JPanel {

    public void setPanel(String panelName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, panelName);
    }

    public MainPanel() {

        setLayout(new CardLayout());
        setBackground(new Color(128, 128, 255));


        JPanel test = new JPanel();
        TopNavigation nav = new TopNavigation();
//        test.add(nav);
        test.setLayout(new BorderLayout());
        test.add(nav, BorderLayout.NORTH);
        this.add(test, "Panel");

        setPanel("Panel");

        // add(test, "Panel");
        // JPanel jpLogin = new LoginPanel(this);
        // add(jpLogin, "LoginPanel");

        // JPanel jpHome = new HomePanel(this);
        // add(jpHome, "HomePanel");

        // JPanel jpCreateProfile = new CreateProfilePanel(this);
        // add(jpCreateProfile, "CpPanel");

        // JPanel jpCreateProfile = new CreateProfilePanel(this);
        // add(jpCreateProfile, "CpPanel");


    }
}
