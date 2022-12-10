package frontend.components;

import frontend.exception.ResponseException;
import frontend.schema.TimetableSchema;
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
public class LoginPanel extends JPanel implements ActionListener, InitializablePanel {

	private final WebClient webClient;
	private final UserSchema userSchema;
	private final TimetableSchema timetable;
	private final Logger logger;

	private MainPanel mainPanel;

	private final JLabel title = new JLabel("LOGIN");
	private final JLabel emailLabel = new JLabel("Email: ");

	private final JTextField emailField = new JTextField();

	private final JLabel emailError = new JLabel("");

	private final JLabel passwordLabel = new JLabel("Password: ");

	private final JPasswordField passwordField = new JPasswordField();

	private final JLabel passwordError = new JLabel("");

	private final JButton registerButton = new JButton("Sign Up");

	private final JButton loginButton = new JButton("Sign In");

	private final JLabel successLabel = new JLabel("", SwingConstants.CENTER);


	@Autowired
	public LoginPanel(WebClient webClient, UserSchema userSchema,
					  TimetableSchema timetableSchema, Logger logger) {
		this.webClient = webClient;
		this.userSchema = userSchema;
		this.timetable = timetableSchema;
		this.logger = logger;
	}

	@Override
	public void initialize(MainPanel parent){

		this.mainPanel = parent;


		registerButton.addActionListener(this);
		loginButton.addActionListener(this);

		JPanel jpGrid = new JPanel(new GridLayout(20, 1));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel jp2 = new JPanel(new GridLayout(1, 2));
		jp2.add(emailLabel);
		jp2.add(emailField);

		JPanel jp3 = new JPanel(new BorderLayout());
		jp3.add(emailError, SwingConstants.CENTER);

		JPanel jp4 = new JPanel(new GridLayout(1, 2));
		jp4.add(passwordLabel);
		jp4.add(passwordField);

		JPanel jp5 = new JPanel(new BorderLayout());
		jp5.add(passwordError, BorderLayout.CENTER);

		JPanel buttons = new JPanel(new GridLayout(1, 3));
		buttons.add(registerButton);
		buttons.add(new JPanel());
		buttons.add(loginButton);

		JPanel jpSuccess = new JPanel(new BorderLayout());
		jpSuccess.add(successLabel, BorderLayout.CENTER);

		jpGrid.add(new JPanel());
		jpGrid.add(new JPanel());
		jpGrid.add(new JPanel());
		jpGrid.add(new JPanel());
		jpGrid.add(title);
		jpGrid.add(new JPanel());
		jpGrid.add(jp2);
		jpGrid.add(new JPanel());
		jpGrid.add(jp3);
		jpGrid.add(new JPanel());
		jpGrid.add(jp4);
		jpGrid.add(new JPanel());
		jpGrid.add(jp5);
		jpGrid.add(new JPanel());
		jpGrid.add(buttons);
		jpGrid.add(new JPanel());
		jpGrid.add(new JPanel());
		jpGrid.add(jpSuccess);

		add(jpGrid);

		this.setLayout(new GridLayout(1, 5));
		this.add(new JPanel());
		this.add(new JPanel());
		JPanel vCenter = new JPanel(new BorderLayout());
		vCenter.add(new JPanel());
		vCenter.add(jpGrid, BorderLayout.CENTER);
		this.add(vCenter);
		this.add(new JPanel());
		this.add(new JPanel());


	}

	public void close() {
		this.setVisible(false);
	}

	public void cleanAll() {
		emailField.setText("");
		emailError.setText("");
		passwordField.setText("");
		passwordError.setText("");
		successLabel.setText("");
	}

	public void cleanErrors() {
		emailError.setText("");
		passwordError.setText("");
		successLabel.setText("");
	}

	public void disableRegisterButton() {
		registerButton.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			logger.info("login button clicked");
			this.cleanErrors();
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());

			if (email == null || email.isEmpty() || email.isBlank()) {
				emailError.setText("Email cannot be empty or white spaces");
			} else if (password == null || password.isEmpty() || password.isBlank()) {
				passwordError.setText("Password cannot be empty or white spaces");
			} else {
				Map<String, String> body = new HashMap<>();
				body.put("email", email);
				body.put("password", password);
				Mono<UserSchema> response = webClient.post()
						.uri("/user/login")
						.body(BodyInserters.fromValue(body))
						.retrieve()
						.bodyToMono(UserSchema.class)
						.doOnError(ResponseException.class, exception -> {
							if (exception.hasFieldErrors()) {
								for (Map<String, String> fieldError : exception.getErrors()) {
									logger.error(fieldError.get("message"));
									if (fieldError.get("field").equals("email")) {
										emailError.setText(fieldError.get("message"));
									} else if (fieldError.get("field").equals("password")) {
										passwordError.setText(fieldError.get("message"));
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
					userSchema.setStudentProfile(v.getStudentProfile());
					successLabel.setText("Login Successfully!");
					JOptionPane.showMessageDialog(null,
							"Login successfully!");
					mainPanel.setUserSchema(userSchema);
					if (userSchema.getStudentProfile() == null){
						JOptionPane.showMessageDialog(null, "It looks like you haven't created a student profile yet. To fully utilize the functionality of the app, you will need to provide some basic info regarding your student status.");
						mainPanel.setPanel("CreateProfilePanel");
						this.cleanAll();
						this.close();
					}
					else {
						Mono<TimetableSchema> ttResponse = webClient.get()
								.uri("/timetable/" + userSchema.getStudentProfile().getId())
								.retrieve()
								.bodyToMono(TimetableSchema.class);
						ttResponse.subscribe(t -> {
							timetable.setId(t.getId());
							logger.info(t.getBlocks().toString());
							timetable.setBlocks(t.getBlocks());
							mainPanel.setTimetableSchema(timetable);
							mainPanel.getHomePanel().initialize(mainPanel);
							mainPanel.setPanel("HomePanel");
							this.cleanAll();
							this.close();
							});
					}
				});
			}
		} else if (e.getSource() == registerButton) {
			mainPanel.getRegisterPanel().initialize(mainPanel);
			mainPanel.setPanel("RegisterPanel");
			this.cleanAll();
			this.close();
		}
	}
}
