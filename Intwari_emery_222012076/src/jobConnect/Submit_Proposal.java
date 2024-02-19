package jobConnect;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Submit_Proposal {

    protected JFrame frame;
    private JLabel lblNewLabel_1;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;
    private JLabel lblNewLabel;
    private JTextField textField;
    private JTextField textField_1;
    private Connection con;
    private JLabel lblNewLabel_3;
    private JTextField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Submit_Proposal window = new Submit_Proposal(null);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Submit_Proposal(String jobTitle) {
        initialize();
        textField.setText(jobTitle); // Set the job title in the text field
        
        lblNewLabel_3 = new JLabel("Full Name :");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_3.setBounds(47, 105, 157, 45);
        frame.getContentPane().add(lblNewLabel_3);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Verdana", Font.BOLD, 15));
        textField_2.setBounds(251, 121, 458, 45);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1050, 535);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblNewLabel_1 = new JLabel("Submit proposal");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 180, 28);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Detailed proposal can earn you a good job");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setToolTipText("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 17));
        lblNewLabel_2.setBounds(266, 50, 458, 20);
        frame.getContentPane().add(lblNewLabel_2);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 1035, 77);
        frame.getContentPane().add(panel);

        btnNewButton_2 = new JButton("Submit proposal");
        btnNewButton_2.setFont(new Font("Verdana", Font.BOLD, 19));
        btnNewButton_2.setBounds(361, 430, 217, 62);
        frame.getContentPane().add(btnNewButton_2);

        btnNewButton_3 = new JButton("Back");
        btnNewButton_3.setFont(new Font("Verdana", Font.BOLD, 20));
        btnNewButton_3.setBounds(926, 447, 109, 45);
        frame.getContentPane().add(btnNewButton_3);

        lblNewLabel = new JLabel("Job title :");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel.setBounds(47, 203, 157, 45);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Verdana", Font.BOLD, 15));
        textField.setBounds(251, 203, 458, 43);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblProposal = new JLabel("Proposal  :");
        lblProposal.setHorizontalAlignment(SwingConstants.CENTER);
        lblProposal.setFont(new Font("Verdana", Font.BOLD, 18));
        lblProposal.setBounds(47, 332, 157, 45);
        frame.getContentPane().add(lblProposal);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.BOLD, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(251, 296, 458, 116);
        frame.getContentPane().add(textField_1);

        // Add action listener to the "Submit proposal" button
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the proposal text
                String proposal = textField_1.getText();
                // Get the labor name
                String laborName = textField_2.getText();
                
                // Check if the labor name is empty
                if (laborName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your name");
                    return;
                }
                
                // Call a method to save the proposal to the database
                saveProposal(proposal, laborName);
                // Show a message dialog indicating success
                JOptionPane.showMessageDialog(frame, "Proposal successfully sent");
            }
        });

        // Add action listener to the "Back" button
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                Job_Seekers jobSeekersWindow = new Job_Seekers(); // Create an instance of Job_Seekers class
                jobSeekersWindow.frame.setVisible(true); // Make the Job_Seekers frame visible
            }
        });
    }

    private void saveProposal(String proposal, String laborName) {
        try {
            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost/Job_Connect_db", "Intwari", "222012076");
            
            // Create a prepared statement to insert the proposal into the database
            PreparedStatement pst = con.prepareStatement("INSERT INTO applications (job_title, proposal, labor_name) VALUES (?, ?, ?)");
            // Set the job title (assuming it's already set in the text field)
            pst.setString(1, textField.getText());
            // Set the proposal
            pst.setString(2, proposal);
            // Set the labor name
            pst.setString(3, laborName);
            
            // Execute the update
            int rowsAffected = pst.executeUpdate();
            
            // Check if the proposal was successfully inserted
            if (rowsAffected > 0) {
                System.out.println("Proposal submitted successfully!");
            } else {
                System.out.println("Failed to submit proposal.");
            }
            
            // Close the prepared statement
            pst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close the connection
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
