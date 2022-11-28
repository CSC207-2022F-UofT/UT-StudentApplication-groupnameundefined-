package frontend.components;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("User Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(420, 420);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(128, 128, 255));
    }
}
