package frontend.components;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(MainPanel mainPanel) {
//        this.setTitle("User Registration");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setResizable(false);
        this.setBounds(0, 0, 250, 250);
        this.setBackground(new Color(128, 128, 255));
        this.add(new JLabel("User Registration"));
        this.add(new JButton("Register"));
        this.add(new JButton("Login"));
        this.setVisible(true);
//        this.getContentPane().setBackground(new Color(128, 128, 255));

    }
}
