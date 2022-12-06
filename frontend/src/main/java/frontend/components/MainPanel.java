package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import frontend.components.TopNavigation;

@Component
@Getter
public class MainPanel extends JPanel {

	private final LoginPanel loginPanel;
	private final RegisterPanel registerPanel;
	private final FriendsPanel friendsPanel;

	@Autowired
	public MainPanel(LoginPanel loginPanel, RegisterPanel registerPanel, FriendsPanel friendsPanel) {
		this.loginPanel = loginPanel;
		this.registerPanel = registerPanel;
		this.friendsPanel = friendsPanel;
	}

	public void setPanel(String panelName) {
		CardLayout cl = (CardLayout) this.getLayout();
		cl.show(this, panelName);
	}

	public void initialize() {
		setLayout(new CardLayout());
		setBackground(new Color(128, 128, 255));
		TopNavigation topbar = new TopNavigation();
		this.add(topbar);

		add(loginPanel, "LoginPanel");
		add(registerPanel, "RegisterPanel");
		add(friendsPanel, "FriendsPanel");

		loginPanel.initialize(this);
		setPanel("LoginPanel");
	}

}
