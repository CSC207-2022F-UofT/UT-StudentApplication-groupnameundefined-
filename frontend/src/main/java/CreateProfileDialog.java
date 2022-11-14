import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class CreateProfileDialog extends JDialog {

    private final String[] Programs = {"CS", "Engineering", "Psychology"};
    private final JLabel jlblEnrolmentYear = new JLabel("Enrolment Year");
    private final JLabel jlblProgramOfStudy = new JLabel("ProgramOfStudy");

    private final JComboBox<String> jcbProgramOfStudy= new JComboBox<>(Programs);
//    AutoCompletion.enable(jcbProgramOfStudy);

    private final JPasswordField jpfPassword = new JPasswordField();

    private final JButton jbtOk = new JButton("Login");
    private final JButton jbtCancel = new JButton("Cancel");

    private final JLabel jlblStatus = new JLabel(" ");

}
