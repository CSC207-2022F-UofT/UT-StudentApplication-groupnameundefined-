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

@Component
public class MainPanel extends JPanel {

    public void setPanel(String panelName) {
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, panelName);
    }

    public MainPanel() {
        setLayout(new CardLayout());
        setBackground(new Color(128, 128, 255));

        JPanel jpRegister = new RegisterPanel(this);
        add(jpRegister, "RegisterPanel");

        JPanel jpLogin = new LoginPanel(this);
        add(jpLogin, "LoginPanel");

        // JPanel jpHome = new HomePanel(this);
        // add(jpHome, "HomePanel");

        // JPanel jpCreateProfile = new CreateProfilePanel(this);
        // add(jpCreateProfile, "CpPanel");

//        setPanel("LoginPanel");
        setPanel("RegisterPanel");
    }
}
