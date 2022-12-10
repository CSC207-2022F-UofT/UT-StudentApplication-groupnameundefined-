package frontend.components;

import frontend.schema.AptRequestSchema;
import frontend.schema.StudentProfileSchema;
import frontend.schema.TimetableSchema;
import frontend.schema.UserSchema;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class ShowMatchesPanel extends JPanel implements InitializablePanel, ActionListener {

	private final WebClient webClient;
	private final UserSchema userSchema;
	private final TimetableSchema timetable;
	private final Logger logger;
	private final JButton jbHabits = new JButton("By habits");
	private final JButton jbCourses = new JButton("By Courses");
	private final JButton jbBoth = new JButton("By Both");
	private final MatchesMenu matchesMenu = new MatchesMenu(this);

	@Autowired
	public ShowMatchesPanel(
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
		JPanel jpMid = new JPanel(new GridLayout(4, 1));
		JLabel jlblTitle = new JLabel("Find Matches");
		jlblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlblTitle.setSize(150, 150);
		JPanel buttons = new JPanel(new BorderLayout(30, 100));

		jbBoth.addActionListener(this);
		jbHabits.addActionListener(this);
		jbCourses.addActionListener(this);

		buttons.add(new JPanel(), BorderLayout.NORTH);
		buttons.add(jbHabits, BorderLayout.WEST);
		buttons.add(jbCourses, BorderLayout.CENTER);
		buttons.add(jbBoth, BorderLayout.EAST);
		buttons.add(new JPanel(), BorderLayout.SOUTH);


		jpMid.add(jlblTitle);
		jpMid.add(buttons);
		jpMid.add(matchesMenu);
		jpMid.add(new JPanel());


		JPanel matchPanel = new JPanel(new GridLayout(1, 3));
		matchPanel.add(new JPanel());
		matchPanel.add(jpMid);
		matchPanel.add(new JPanel());

		setLayout(new MigLayout("wrap 7, fill"));
		add(new TopNavigation(parent), "span 7 1, grow");
		add(matchPanel, "span 6 6, grow");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String matchBy;
		if (e.getSource() == jbHabits) {
			matchBy = new String("HABIT");
		} else if (e.getSource() == jbCourses) {
			matchBy = new String("COURSE");
		} else {
			matchBy = new String("BOTH");
		}
		Mono<ArrayList<StudentProfileSchema>> response = webClient.get()
				.uri("/student-profile/match/"
						+ userSchema.getStudentProfile().getId().intValue()
						+ "?criteria=" + matchBy
				)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<>() {
				});
		response.subscribe(r -> {
			logger.info(r.toString());
			matchesMenu.updateMatches(r, this.webClient);
		});
	}
}
