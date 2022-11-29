package frontend.components;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(MainPanel mainPanel) {
        this.setBounds(0, 0, 250, 250);
        this.setBackground(new Color(128, 128, 255));
        JButton register = new JButton("Sign Up");
        register.setBounds(50, 400, 80, 30);
        JButton login = new JButton("Sign In");
        login.setBounds(100, 400, 80, 30);
        this.add(register);
        this.add(login);
        this.setVisible(true);

    }
}
