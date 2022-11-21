import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopNavigation extends JMenuBar implements ActionListener{

    JMenu homeMenu;
    JMenu findMenu;
    JMenu calMenu;
    JMenu settingMenu;
    JMenu aboutMenu;

    JMenuItem accountItem;
    JMenuItem privacyItem;
    JMenuItem helpItem;
    JMenuItem teamItem;

    public TopNavigation(){
        JMenuBar topNavigation = new JMenuBar();

        homeMenu = new JMenu("Home");
        findMenu = new JMenu("Find");
        calMenu = new JMenu("My Calendar");
        settingMenu = new JMenu("Setting");
        aboutMenu = new JMenu("About");

        accountItem = new JMenuItem("Account Setting");
        privacyItem = new JMenuItem("Privacy Setting");
        helpItem = new JMenuItem("User Tutorial");
        teamItem = new JMenuItem("Our Team");

        settingMenu.add(accountItem);
        settingMenu.add(privacyItem);
        aboutMenu.add(helpItem);
        aboutMenu.add(teamItem);

        menuBar.add(homeMenu);
        menuBar.add(findMenu);
        menuBar.add(calMenu);
        menuBar.add(settingMenu);
        menuBar.add(aboutMenu);

        this.setVisible(true);

        accountItem.addActionListener(this);
        privacyItem.addActionListener(this);
        helpItem.addActionListener(this);
        teamItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==accountItem){
            System.out.print("switch to account setting page"); // to be replaced
        }
        if(e.getSource()==privacyItem){
            System.out.print("switch to privacy setting page"); // to be replaced
        }
        if(e.getSource()==helpItem){
            System.out.print("switch to help page"); // to be replaced
        }
        if(e.getSource()==teamItem){
            System.out.print("switch to team page"); // to be replaced
        }
    }

}