package frontend.components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopNavigation extends JMenuBar implements ActionListener{

    JMenu homeMenu;
//    JMenu findMenu;
//    JMenu calMenu;
//    JMenu settingMenu;
//    JMenu aboutMenu;

    JMenu logoutMenu;

    JMenu habitsetMenu;

    JMenuItem accountItem;
    JMenuItem privacyItem;
    JMenuItem helpItem;
    JMenuItem teamItem;

    public TopNavigation(){



        homeMenu = new JMenu("Home");
//        findMenu = new JMenu("Find");
//        calMenu = new JMenu("My Calendar");
//        settingMenu = new JMenu("Setting");
//        aboutMenu = new JMenu("About");
        logoutMenu = new JMenu("Logout");
        habitsetMenu = new JMenu("Habit Set");

        accountItem = new JMenuItem("Account Setting");
        privacyItem = new JMenuItem("Privacy Setting");
        helpItem = new JMenuItem("User Tutorial");
        teamItem = new JMenuItem("Our Team");

//        settingMenu.add(accountItem);
//        settingMenu.add(privacyItem);
//        aboutMenu.add(helpItem);
//        aboutMenu.add(teamItem);

        this.add(homeMenu);
//        this.add(findMenu);
//        this.add(calMenu);
//        this.add(settingMenu);
//        this.add(aboutMenu);
        this.add(logoutMenu);
        this.add(habitsetMenu);

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
        if(e.getSource()==habitsetMenu){

        }
    }

}