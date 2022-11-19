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
public class LoginPanel extends JPanel {

    private WebClient webClient;

    private final JLabel jlblUsername = new JLabel("Username");
    private final JLabel jlblPassword = new JLabel("Password");

    private final JTextField jtfUsername = new JTextField(15);
    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final JButton jbtCancel = new JButton("Cancel");

    private final JLabel jlblStatus = new JLabel(" ");

    public LoginPanel(final MainPanel parent){
//      Initialize as JPanel using BorderLayout as LayoutManager
        super(new BorderLayout());

//        Initialize subpanels
        JPanel labels = new JPanel(new GridLayout(2, 2));
        labels.add(jlblUsername);
        labels.add(jlblPassword);

        JPanel inputFields = new JPanel(new GridLayout(2, 2));
        inputFields.add(jtfUsername);
        inputFields.add(jpfPassword);

        JPanel form = new JPanel();
        form.add(labels);
        form.add(inputFields);

        JPanel buttons = new JPanel();
        buttons.add(jbtOk);
        buttons.add(jbtCancel);

//        Organize subpanels in LoginPanel
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        add(jlblStatus, BorderLayout.NORTH);
        jlblStatus.setForeground(Color.RED);
        jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

//        Listens for when we click jbtOk
        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Make API request here

//                Mono<String> response = webClient.get().uri("/users").exchangeToMono(r -> {
//                    return r.bodyToMono(String.class);
//                });
//
//                if (response.equals("success")){
//                    cookie.set(...)
//                    setVisible(false);
//                }

                if (Arrays.equals("stackoverflow".toCharArray(), jpfPassword.getPassword())
                        && "stackoverflow".equals(jtfUsername.getText())) {
                    parent.setPanel("CpPanel");
                } else {
                    jlblStatus.setText("Invalid username or password");
                }
            }
        });

    }
}
