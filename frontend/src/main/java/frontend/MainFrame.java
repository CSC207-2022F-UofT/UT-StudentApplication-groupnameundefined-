package frontend;

import java.awt.*;

import javax.swing.*;

import frontend.components.MainPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import frontend.components.TopNavigation;

import reactor.core.publisher.Mono;

@Component
public class MainFrame extends JFrame {

	@Autowired
	private WebClient webClient;

	@Autowired
	private Logger logger;

	@Autowired
	private MainPanel mainPanel;

	public void initialize() {
		setTitle("UT Student Application");
		setSize(1400, 800);
		setMinimumSize(new Dimension(500, 430));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

		mainPanel.initialize();
		add(mainPanel);
		
		pack();

		//        Mono<String> response = webClient.get().uri("/users").exchangeToMono(r -> {
		//            return r.bodyToMono(String.class);
		//        });
		//        response.subscribe(value -> logger.info(value));
	}
}
