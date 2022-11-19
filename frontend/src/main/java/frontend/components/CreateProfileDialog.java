package frontend.components;

import javax.swing.*;

public class CreateProfileDialog extends JDialog {

    private final JLabel jlblNotice = new JLabel("It seems like you haven't created a student profile yet." +
                                                  "Without one, your functionality on the app will be limited." +
                                                  "Would you like to create a student profile?");

    private final JButton jbCreateProfile = new JButton("Create Profile");

    public CreateProfileDialog(final MainPanel parent, final boolean modal){
        JPanel p1 = new JPanel();
        p1.add(jlblNotice);
        p1.add(jbCreateProfile);

    }
}
