package jobConnect;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Entrepreneurs {

    protected JFrame frame;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Entrepreneurs window = new Entrepreneurs();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Entrepreneurs() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1041, 509);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblNewLabel_1 = new JLabel("ENTREPRENEURS");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 200, 28);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton_1 = new JButton("CREATE JOBS");

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Create.java class
                Create createWindow = new Create();
                createWindow.frame.setVisible(true);
            }
        });


        btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 17));
        btnNewButton_1.setBounds(847, 1, 180, 76);
        frame.getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("welcome to the hub of job creation");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setToolTipText("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 17));
        lblNewLabel_2.setBounds(266, 50, 458, 20);
        frame.getContentPane().add(lblNewLabel_2);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 849, 77);
        frame.getContentPane().add(panel);

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\user\\Downloads\\d9f8091d8d05420bb4a0d90a503f79c7.jpeg"));
        lblNewLabel_3.setBounds(0, 77, 690, 399);
        frame.getContentPane().add(lblNewLabel_3);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                LogIn loginWindow = new LogIn(); // Create an instance of LogIn class
                loginWindow.frame.setVisible(true); // Make the LogIn frame visible
            }
        });
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 20));
        btnNewButton.setBounds(918, 427, 109, 45);
        frame.getContentPane().add(btnNewButton);

        JButton btnNewButton_2 = new JButton("View applications");
        btnNewButton_2.setFont(new Font("Verdana", Font.BOLD, 18));
        btnNewButton_2.setBounds(825, 295, 211, 65);
        frame.getContentPane().add(btnNewButton_2);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(64, 128, 128));
        panel_1.setBounds(689, 77, 338, 397);
      frame.getContentPane().add(panel_1);

        // Add action listener to the "View Applications" button
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                View_Application viewAppWindow = new View_Application(); // Create an instance of View_Application class
                viewAppWindow.frame.setVisible(true); // Make the View_Application frame visible
            }
        });

      
    }
}
