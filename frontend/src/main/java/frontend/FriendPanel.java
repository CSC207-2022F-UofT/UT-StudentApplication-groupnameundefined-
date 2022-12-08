package frontend;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class FriendPanel extends JPanel{
    public void TabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainPanel = new JPanel();

        JScrollPane panel1 = new  JScrollPane();
        tabbedPane.addTab("Incoming Requests", panel1);
        panel1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollPane panel2 = new  JScrollPane();
        tabbedPane.addTab("Outgoing Request", panel2);
        panel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JScrollPane panel3 = new  JScrollPane();
        tabbedPane.addTab("Blocked", panel3);
        panel3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
    }

}

