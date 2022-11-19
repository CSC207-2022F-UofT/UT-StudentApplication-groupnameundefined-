package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HomePanel extends JPanel {

    private WebClient webClient;

    private final JLabel jlblHomeLabel = new JLabel("Home");

    public HomePanel(final MainPanel parent){
//      Initialize as JPanel using BorderLayout as LayoutManager
        super(new BorderLayout());

                        // Make API request here

//                Mono<String> response = webClient.get().uri("/users").exchangeToMono(r -> {
//                    return r.bodyToMono(String.class);
//                });
//
//                if (response.equals("no profile")){
//                    initialze createprof dialog
//                }

        JDialog cpDialog = new CreateProfileDialog(parent, true);
        cpDialog.setVisible(true);
        add(jlblHomeLabel);
    }
}
