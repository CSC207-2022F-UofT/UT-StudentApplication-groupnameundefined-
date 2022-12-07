package frontend.components;

import frontend.schema.UserSchema;
import org.springframework.web.reactive.function.client.WebClient;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendsPanel extends JPanel implements ActionListener{

    private final WebClient webClient;

    private final UserSchema userSchema;

    DefaultListModel listModel;
    JLabel label;
    JButton delete;
    JButton view;
    JButton add;
    JButton back;
    JList friendlist;

    private MainPanel mainPanel;
    private String message;

    public FriendsPanel(WebClient webClient, UserSchema userSchema, WebClient webClient1, UserSchema userSchema1){
        this.webClient = webClient;
        this.userSchema = userSchema;}

    public void initialize(MainPanel mainPanel){

        this.mainPanel = mainPanel;

        this.setBounds(0, 0, 500, 500);
        this.setBackground(new Color(128, 128, 255));
        this.setLayout(null);

        listModel = new DefaultListModel();

        // Add element of current friend list
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        label = new JLabel();
        label.setSize(500,100);

        delete =new JButton("Delete");
        view =new JButton("View Profile");
        back = new JButton("Back to List");

        friendlist = new JList(listModel);
        friendlist.setBounds(100,100, 75,75);

        delete.addActionListener(this);
        view.addActionListener(this);
        back.addActionListener(this);

        this.add(friendlist);
        this.add(delete);
        this.add(view);
        this.add(back);

        delete.setVisible(true);
        view.setVisible(true);
    }

    /**
     * Sets this panel to be invisible and return nothing.
     */
    public void close() {
        this.setVisible(false);
    }


    public void actionPerformed(ActionEvent e){
        int size = listModel.getSize();
        int index = friendlist.getSelectedIndex();
        Object clicked = e.getSource();

        if(clicked == delete){
            if(size == 0){
                showMessageDialog(null, "No friend to delete!");
            }

        }else if(clicked == view){
            if(size == 0){
                showMessageDialog(null, "No friend to view!");
            }else if(index == -1){
                showMessageDialog(null, "Select a friend to view!");
            }else{
                message = Integer.toString(index);

                // set main panel to view friends panel;
                this.close();
            }
        }

    }

}