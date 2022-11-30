package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private static MainPanel mainPanel;
    private static JLabel emailLabel;
    private static JTextField emailField;
    private static JLabel emailError;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JLabel passwordError;
    private static JButton registerButton;
    private static JButton loginButton;
    private static JLabel successLabel;

    LoginPanel(JPanel mainPanel) {

        this.setBounds(0, 0, 500, 500);
        this.setBackground(new Color(128, 128, 255));
        this.setLayout(null);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(100, 50, 70, 20);

        emailField = new JTextField();
        emailField.setBounds(110, 75, 170, 20);

        emailError = new JLabel("error");
        emailError.setBounds(115, 95, 170, 20);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(100, 115, 193, 28);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 140, 170, 20);

        passwordError = new JLabel("error");
        passwordError.setBounds(115, 160, 170, 20);

        registerButton = new JButton("Sign Up");
        registerButton.setBounds(110, 180, 80, 30);

        loginButton = new JButton("Sign In");
        loginButton.setBounds(200, 180, 80, 30);
        loginButton.addActionListener(this);

        successLabel = new JLabel("");
        successLabel.setBounds(135, 210, 150, 20);


        this.add(emailLabel);
        this.add(emailField);
        this.add(emailError);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(passwordError);
        this.add(registerButton);
        this.add(loginButton);
        this.add(successLabel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if ( ) {
                successLabel.setText("Login Successfully.");
            } else {
                successLabel.setText("Login Failed.");
            }
        } else if (e.getSource() == registerButton) {
            mainPanel.setPanel("RegisterPanel");
        } else {

        }
    }
}
