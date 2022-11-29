package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

    private static JLabel email;
    private static JTextField emailField;
    private static JLabel emailError;
    private static JLabel password;
    private static JPasswordField passwordField;
    private static JLabel passwordError;
    private static JButton register;
    private static JButton login;
    private static JLabel success;




    LoginPanel(JPanel mainPanel) {

        this.setBounds(0, 0, 500, 500);
        this.setBackground(new Color(128, 128, 255));
        this.setLayout(null);

        email = new JLabel("Email: ");
        email.setBounds(100, 50, 70, 20);

        emailField = new JTextField();
        emailField.setBounds(110, 75, 170, 20);

        emailError = new JLabel("error");
        emailError.setBounds(115, 95, 170, 20);

        password = new JLabel("Password: ");
        password.setBounds(100, 115, 193, 28);

        passwordField = new JPasswordField();
        passwordField.setBounds(110, 140, 170, 20);

        passwordError = new JLabel("error");
        passwordError.setBounds(115, 160, 170, 20);

        register = new JButton("Sign Up");
        register.setBounds(110, 180, 80, 30);

        login = new JButton("Sign In");
        login.setBounds(200, 180, 80, 30);
        login.addActionListener(new LoginPanel(mainPanel));

        success = new JLabel("");
        success.setBounds(135, 210, 150, 20);



        this.add(email);
        this.add(emailField);
        this.add(emailError);
        this.add(password);
        this.add(passwordField);
        this.add(passwordError);
        this.add(register);
        this.add(login);
        this.add(success);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = passwordField.getPassword().toString();
    }
}
