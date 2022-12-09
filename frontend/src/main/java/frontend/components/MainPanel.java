package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Set;

import frontend.schema.TimetableSchema;
import frontend.schema.UserSchema;
import frontend.components.TopNavigation;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Getter
@Setter
public class MainPanel extends JPanel {

	private UserSchema userSchema;
	private TimetableSchema timetableSchema;
	private final LoginPanel loginPanel;
	private final RegisterPanel registerPanel;
	private final CreateProfilePanel createProfilePanel;
	private final HomePanel homePanel;
	private final ShowMatchesPanel showMatchesPanel;
	private final SettingsPanel settingsPanel;


	@Autowired
	public MainPanel(LoginPanel loginPanel, RegisterPanel registerPanel,
					 CreateProfilePanel createProfilePanel, HomePanel homePanel,
					 ShowMatchesPanel showMatchesPanel, SettingsPanel settingsPanel) {
		this.loginPanel = loginPanel;
		this.registerPanel = registerPanel;
		this.createProfilePanel = createProfilePanel;
		this.homePanel = homePanel;
		this.showMatchesPanel = showMatchesPanel;
		this.settingsPanel = settingsPanel;
	}

	public void setPanel(String panelName) {
		CardLayout cl = (CardLayout) this.getLayout();
		cl.show(this, panelName);
	}

	public void initialize() {
		setLayout(new CardLayout());
		setBackground(new Color(128, 128, 255));

		add(loginPanel, "LoginPanel");
		add(registerPanel, "RegisterPanel");
		add(createProfilePanel, "CreateProfilePanel");
		add(homePanel, "HomePanel");
		add(showMatchesPanel, "ShowMatchesPanel");
		add(settingsPanel, "SettingsPanel");
		loginPanel.initialize(this);
		createProfilePanel.initialize(this);
		showMatchesPanel.initialize(this);
		settingsPanel.initialize(this);
		setPanel("LoginPanel");
	}

}
