package frontend.components;

import frontend.schema.AptRequestSchema;
import frontend.schema.FriendRequestSchema;
import frontend.schema.TimetableSchema;
import frontend.schema.UserSchema;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

@Getter
@Component
public class HomePanel extends JPanel implements InitializablePanel {

	private final WebClient webClient;
	private final UserSchema userSchema;
	private final TimetableSchema timetable;
	private final Logger logger;

	private final JMenu jmOutgoingAptRequests = new JMenu();
	private final JMenu jmIncomingAptRequests = new JMenu();

	private final TimetableCalendar studentCalendar = new TimetableCalendar();
	private final IncAptMenu incAptMenu = new IncAptMenu(this);
	private final OutAptMenu outAptMenu = new OutAptMenu(this);

	private final IncFriendMenu incFriendsMenu = new IncFriendMenu(this);

	@Autowired
	public HomePanel(
			WebClient webClient, UserSchema userSchema,
			TimetableSchema timetableSchema, Logger logger
	) {
		this.webClient = webClient;
		this.userSchema = userSchema;
		this.timetable = timetableSchema;
		this.logger = logger;
	}

	@Override
	public void initialize(MainPanel parent) {
		Mono<ArrayList<AptRequestSchema>> responseTo = webClient.get()
				.uri("/apt-request/to/" + parent.getUserSchema().getId().intValue() + "?status=PENDING")
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<>() {
				});
		responseTo.subscribe(r -> {
			incAptMenu.updateIncApts(r, webClient);
		});
		Mono<ArrayList<AptRequestSchema>> responseFrom = webClient.get()
				.uri("/apt-request/from/" + parent.getUserSchema().getId())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<>() {
				});
		responseFrom.subscribe(r -> {
			outAptMenu.updateOutApts(r, webClient);
		});
		JPanel jpCalendar = new JPanel();
		jpCalendar.setLayout(new BorderLayout());
		jpCalendar.setPreferredSize(new Dimension(1000, 1000));
		studentCalendar.updateCal(timetable, userSchema, webClient);
		jpCalendar.add(studentCalendar);

		JPanel jpIncomingAptRequests = new JPanel();
		jpIncomingAptRequests.add(jmIncomingAptRequests);

		JPanel jpOutgoingAptRequests = new JPanel();
		jpOutgoingAptRequests.add(jmOutgoingAptRequests);

		JTabbedPane tabbedPane = new JTabbedPane();

		ImageIcon icon = new ImageIcon("images/middle.gif");

		JComponent incApt = new JPanel();
		incApt.setLayout(new BorderLayout());
		incApt.add(incAptMenu);

		JComponent outApt = new JPanel();
		outApt.setLayout(new BorderLayout());
		outApt.add(outAptMenu);

		JComponent incFriends = new JPanel();
		incFriends.setLayout(new BorderLayout());
		incFriends.add(incFriendsMenu);

		tabbedPane.addTab("Outgoing Appointments", icon, outApt,
				"All outgoing appointments"
		);
		tabbedPane.addTab("Incoming Appointments", icon, incApt,
				"All incoming appointments"
		);

		tabbedPane.addTab("Incoming Friend Requests", icon, incFriends, "All Incoming Friend Requests");

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Mono<ArrayList<AptRequestSchema>> responseTo = webClient.get()
						.uri("/apt-request/to/" + parent.getUserSchema().getId().intValue() + "?status=PENDING")
						.retrieve()
						.bodyToMono(new ParameterizedTypeReference<>() {
						});
				responseTo.subscribe(r -> {
					incAptMenu.updateIncApts(r, webClient);
				});
				Mono<ArrayList<AptRequestSchema>> responseFrom = webClient.get()
						.uri("/apt-request/from/" + parent.getUserSchema().getId())
						.retrieve()
						.bodyToMono(new ParameterizedTypeReference<>() {
						});
				responseFrom.subscribe(r -> {
					outAptMenu.updateOutApts(r, webClient);
				});
				Mono<ArrayList<FriendRequestSchema>> responseFriend = webClient.get()
						.uri("/friend-request/to/" + parent.getUserSchema().getId().intValue() + "?status=PENDING")
						.retrieve()
						.bodyToMono(new ParameterizedTypeReference<>() {
						});
				responseFriend.subscribe(s -> {
					incFriendsMenu.updateIncFriends(s, webClient);
				});
			}
		});

		setLayout(new MigLayout("wrap 7, fill"));
		add(new TopNavigation(parent), "span 7 1, grow");
		add(jpCalendar, "span 6 6, grow");
		add(tabbedPane, "span 1 6, grow");

	}
}
