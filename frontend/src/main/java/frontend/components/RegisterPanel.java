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
	private static JLabel repeatPasswordLabel;
	private static JPasswordField repeatPasswordField;
	private static JLabel repeatPasswordError;
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
		nameLabel.setBounds(150, 30, 70, 20);

		nameField = new JTextField();
		nameField.setBounds(160, 55, 170, 20);

		nameError = new JLabel("");
		nameError.setBounds(165, 75, 300, 20);

		emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(150, 90, 70, 20);

		emailField = new JTextField();
		emailField.setBounds(160, 115, 170, 20);

		emailError = new JLabel("");
		emailError.setBounds(165, 135, 300, 20);

		passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(150, 150, 70, 20);

		passwordField = new JPasswordField();
		passwordField.setBounds(160, 175, 170, 20);

		passwordError = new JLabel("");
		passwordError.setBounds(165, 195, 300, 20);

		repeatPasswordLabel = new JLabel("Repeat Password: ");
		repeatPasswordLabel.setBounds(150, 210, 70, 20);

		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setBounds(160, 235, 170, 20);

		repeatPasswordError = new JLabel("");
		repeatPasswordError.setBounds(165, 255, 300, 20);

		phoneLabel = new JLabel("Phone: ");
		phoneLabel.setBounds(150, 270, 70, 20);

		phoneField = new JTextField();
		phoneField.setBounds(160, 295, 170, 20);

		phoneError = new JLabel("");
		phoneError.setBounds(165, 315, 300, 20);

		registerButton = new JButton("Sign Up");
		registerButton.setBounds(205, 335, 80, 30);
		registerButton.addActionListener(this);

		successLabel = new JLabel("", SwingConstants.CENTER);
		successLabel.setBounds(45, 365, 400, 20);

		this.add(nameLabel);
		this.add(nameField);
		this.add(nameError);
		this.add(emailLabel);
		this.add(emailField);
		this.add(emailError);
		this.add(passwordLabel);
		this.add(passwordField);
		this.add(passwordError);
		this.add(repeatPasswordLabel);
		this.add(repeatPasswordField);
		this.add(repeatPasswordError);
		this.add(phoneLabel);
		this.add(phoneField);
		this.add(phoneError);
		this.add(registerButton);
		this.add(successLabel);

		this.setVisible(true);
	}

	public void close() {
		this.setVisible(false);
	}

	public void cleanAll() {
		nameField.setText("");
		nameError.setText("");
		emailField.setText("");
		emailError.setText("");
		passwordField.setText("");
		passwordError.setText("");
		repeatPasswordField.setText("");
		repeatPasswordError.setText("");
		phoneField.setText("");
		phoneError.setText("");
		successLabel.setText("");
	}

	public void cleanErrors(){
		nameError.setText("");
		emailError.setText("");
		passwordError.setText("");
		repeatPasswordError.setText("");
		phoneError.setText("");
		successLabel.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		logger.info("register button clicked");
		this.cleanErrors();
		String name = nameField.getText();
		String email = emailField.getText();
		String password = new String(passwordField.getPassword());
		String repeatPassword = new String(repeatPasswordField.getPassword());
		String phone = phoneField.getText();

		if (name == null || name.isEmpty() || name.isBlank()) {
			nameError.setText("Name cannot be empty or white spaces.");
		} else if (email == null || email.isEmpty() || email.isBlank()) {
			emailError.setText("Email cannot be empty or white spaces.");
		} else if (password == null || password.isEmpty() || password.isBlank()) {
			passwordError.setText("Password cannot be empty or white spaces.");
		} else if (repeatPassword == null || repeatPassword.isEmpty() || repeatPassword.isBlank()) {
			repeatPasswordError.setText("Please enter your password twice.");
		} else if (phone == null || phone.isEmpty() || phone.isBlank()) {
			phoneError.setText("Phone cannot be empty or white spaces.");
		} else if (! repeatPassword.equals(password)) {
			repeatPasswordError.setText("Passwords don't match.");
		} else {
			Map<String, String> body = new HashMap<>();
			body.put("name", name);
			body.put("email", email);
			body.put("password", password);
			body.put("phone", phone);
			Mono<UserSchema> response = webClient.post()
					.uri("/user/register")
					.body(BodyInserters.fromValue(body))
					.retrieve()
					.bodyToMono(UserSchema.class)
					.doOnError(ResponseException.class, exception -> {
						if (exception.hasFieldErrors()) {
							for (Map<String, String> fieldError : exception.getErrors()) {
								logger.error(fieldError.get("message"));
								if (fieldError.get("field").equals("name")) {
									nameError.setText(fieldError.get("message"));
								} else if (fieldError.get("field").equals("email")) {
									emailError.setText(fieldError.get("message"));
								} else if (fieldError.get("field").equals("password")) {
									passwordError.setText(fieldError.get("message"));
								} else if (fieldError.get("field").equals("phone")) {
									phoneError.setText(fieldError.get("message"));
								}
							}
						} else {
							logger.error(exception.getMessage());
							successLabel.setText(exception.getMessage());
						}
					})
					.onErrorComplete();

			response.subscribe(v -> {
				userSchema.setId(v.getId());
				userSchema.setName(v.getName());
				userSchema.setEmail(v.getEmail());
				userSchema.setPhone(v.getPhone());
				userSchema.setLoginStatus(v.getLoginStatus());
				userSchema.setJoinedTime(v.getJoinedTime());
				userSchema.setLastActiveTime(v.getLastActiveTime());

				successLabel.setText("Register Successfully!");
				JOptionPane.showMessageDialog(null,
						"Register successfully! Please login again.");
				mainPanel.getLoginPanel().disableRegisterButton();
				mainPanel.setPanel("LoginPanel");
				this.cleanAll();
				this.close();
			});
		}
	}
}
