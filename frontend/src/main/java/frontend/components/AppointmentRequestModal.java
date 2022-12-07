package frontend.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppointmentRequestModal {
    public static void main(String[] args){
        JFrame frame = new JFrame("Swing Tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAppointmentModal(frame);
        frame.setSize(560, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        frame.setVisible(true);

    }
    private static void createAppointmentModal(final JFrame frame){
        JPanel panel = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel.setLayout(layout);

        JButton button = new JButton("Create Appointment");
        final JDialog modelDialog = createDialog(frame);

        JButton button2 = new JButton("Delete Appointment");
        final JDialog modelDialog2 = createDialog(frame);

        JButton button3 = new JButton("Reschedule Appointment");
        final JDialog modelDialog3 = createDialog(frame);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(true);
                modelDialog2.setVisible(true);
                modelDialog3.setVisible(true);
            }
        });

        panel.add(button);
        panel.add(button2);
        panel.add(button3);



        frame.getContentPane().add(panel, BorderLayout.CENTER);


    }

    private static JDialog createDialog(final JFrame frame){
        final JDialog modelDialog = new JDialog(frame, "Swing Tester",
                Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton okButton = new JButton("Ok");

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(10, 50, 80, 25);
        dialogContainer.add(lblLocation);

        JTextField textField_location = new JTextField();
        textField_location.setBounds(100, 50, 165, 25);
        dialogContainer.add(textField_location);

        JLabel lblTime = new JLabel("Time");
        lblTime.setBounds(10, 20, 80, 25);
        dialogContainer.add(lblTime);

        JTextField textField_time = new JTextField();
        textField_time.setBounds(100, 20, 165, 25);
        dialogContainer.add(textField_time);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(false);
            }
        });

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }
}
