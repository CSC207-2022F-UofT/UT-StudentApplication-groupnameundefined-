import java.awt.*;
import javax.swing.*;

public class MainPage extends JFrame{
    private PassWordDialog passDialog;

    public MainPage() {
        passDialog = new PassWordDialog(this, true);
        passDialog.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MainPage();
                frame.getContentPane().setBackground(Color.BLACK);
                frame.setTitle("Logged In");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                JPanel p = new JPanel();
                JTextField text = new JTextField(15);
                frame.add(p);
                p.add(text);
                frame.setVisible(true);
            }
        });
    }
}

