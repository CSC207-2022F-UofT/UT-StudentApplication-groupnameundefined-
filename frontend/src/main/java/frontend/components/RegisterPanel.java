package frontend.components;

import frontend.schema.UserSchema;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class RegisterPanel extends JPanel implements ActionListener {

	private final Logger logger;
	private final WebClient webClient;
	private final UserSchema userSchema;

	private MainPanel mainPanel;

	private static JLabel nameLabel;
	private static JTextField nameField;
	private static JLabel nameError;
	private static JLabel emailLabel;
	private static JTextField emailField;
	private static JLabel emailError;
	private static JLabel passwordLabel;
	private static JPasswordField passwordField;
	private static JLabel passwordError;
	private static JLabel phoneLabel;
	private static JTextField phoneField;
	private static JLabel phoneError;
	private static JButton registerButton;
	private static JLabel successLabel;

	@Autowired
	public RegisterPanel(WebClient webClient, UserSchema userSchema, Logger logger) {
		this.webClient = webClient;
		this.userSchema = userSchema;
		this.logger = logger;
	}

	public void initialize(MainPanel mainPanel) {
		this.mainPanel = mainPanel;

		this.setBounds(0, 0, 400, 400);
		this.setBackground(new Color(128, 128, 255));
		this.setLayout(null);

		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(100, 50, 70, 20);

		nameField = new JTextField();
		nameField.setBounds(110, 75, 170, 20);

		nameError = new JLabel("error");
		nameError.setBounds(115, 95, 170, 20);

		emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(100, 110, 70, 20);

		emailField = new JTextField();
		emailField.setBounds(110, 135, 170, 20);

		emailError = new JLabel("error");
		emailError.setBounds(115, 155, 170, 20);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(100, 170, 70, 20);

		passwordField = new JPasswordField();
		passwordField.setBounds(110, 195, 170, 20);

		passwordError = new JLabel("error");
		passwordError.setBounds(115, 215, 170, 20);

		phoneLabel = new JLabel("Phone: ");
		phoneLabel.setBounds(100, 230, 70, 20);

		phoneField = new JTextField();
		phoneField.setBounds(110, 255, 170, 20);

		phoneError = new JLabel("error");
		phoneError.setBounds(115, 275, 170, 20);

		registerButton = new JButton("Sign Up");
		registerButton.setBounds(155, 295, 80, 30);
		registerButton.addActionListener(this);

		successLabel = new JLabel("Register Successfully!");
		successLabel.setBounds(115, 320, 150, 20);

		this.add(nameLabel);
		this.add(nameField);
		this.add(nameError);
		this.add(emailLabel);
		this.add(emailField);
		this.add(emailError);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(passwordError);
		this.add(phoneLabel);
		this.add(phoneField);
		this.add(phoneError);
		this.add(registerButton);
		this.add(successLabel);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//		mainPanel.setPanel("LoginPanel");
	}
}
