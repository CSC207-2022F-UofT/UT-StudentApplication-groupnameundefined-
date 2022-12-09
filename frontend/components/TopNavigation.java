package frontend.components;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopNavigation extends JMenuBar implements ActionListener{

    JMenuItem homeMenu;
    JMenuItem findMenu;
    JMenu calMenu;
    JMenu settingMenu;
    JMenu aboutMenu;

    JMenu logoutMenu;



    JMenuItem accountItem;
    JMenuItem privacyItem;
    JMenuItem helpItem;
    JMenuItem teamItem;

    public TopNavigation(MainPanel parent) {


        homeMenu = new JMenu("Home");
        findMenu = new JMenu("Find");
        calMenu = new JMenu("My Calendar");
        settingMenu = new JMenu("Setting");
        aboutMenu = new JMenu("About");
        logoutMenu = new JMenu("Logout");

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem findItem = new JMenuItem("Find");
        JMenuItem calItem = new JMenuItem("My Calendar");
        JMenuItem settingItem = new JMenuItem("Setting");
        JMenuItem logoutItem = new JMenuItem("Logout");

        accountItem = new JMenuItem("Account Setting");
        privacyItem = new JMenuItem("Privacy Setting");
        helpItem = new JMenuItem("User Tutorial");
        teamItem = new JMenuItem("Our Team");

        homeMenu.add(homeItem);
        findMenu.add(findItem);
        calMenu.add(calItem);
        settingMenu.add(settingItem);
        logoutMenu.add(logoutItem);

        this.add(homeMenu);
        this.add(findMenu);
        this.add(calMenu);
        this.add(settingMenu);
        this.add(aboutMenu);
        this.add(logoutMenu);

        this.setVisible(true);

        accountItem.addActionListener(this);
        privacyItem.addActionListener(this);
        helpItem.addActionListener(this);
        teamItem.addActionListener(this);

        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setPanel("HomePanel");
            }
        });
        findItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setPanel("ShowMatchesPanel");
            }
        });
        calItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setPanel("HomePanel");
            }
        });
        settingItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setPanel("SettingsPanel");
            }
        });
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.setPanel("LoginPanel");
            }
        });

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==findMenu){
        }
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
