package jobConnect;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

public class J_Sprofile {

    JFrame frame;
    private JTextField textField;
    private JLabel lblNewLabel_1;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private JLabel lblNewLabel_2;
    private JPanel panel;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    J_Sprofile window = new J_Sprofile();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public J_Sprofile() {
        initialize();
        connection();
    }

    private void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/job_connect_db", "Intwari", "222012076");
            System.out.println("Connected successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1050, 576);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Job Seeker ID");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel.setBounds(48, 78, 179, 45);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.BOLD, 16));
        textField.setBounds(48, 126, 412, 28);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblUserId = new JLabel("User ID");
        lblUserId.setFont(new Font("Verdana", Font.BOLD, 18));
        lblUserId.setBounds(48, 156, 179, 45);
        frame.getContentPane().add(lblUserId);

        JLabel lblNewLabel_1_1 = new JLabel("Experience");
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(48, 225, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Education");
        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1_1.setBounds(48, 307, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Contact");
        lblNewLabel_1_1_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1_1_1.setBounds(48, 375, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1_1_1);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("Languages");
        lblNewLabel_1_1_1_2.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1_1_2.setBounds(48, 453, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1_1_2);

        lblNewLabel_1 = new JLabel("Profile");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 133, 28);
        frame.getContentPane().add(lblNewLabel_1);

        JButton view = new JButton("Back");
        view.setFont(new Font("Verdana", Font.BOLD, 20));
        view.setBounds(927, 484, 109, 45);
        frame.getContentPane().add(view);

        JButton view1 = new JButton("View");
        view1.setFont(new Font("Verdana", Font.BOLD, 19));
        view1.setBounds(879, 0, 157, 76);
        frame.getContentPane().add(view1);
        
        lblNewLabel_2 = new JLabel("If you like you can customise your profile");
        lblNewLabel_2.setToolTipText("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 17));
        lblNewLabel_2.setBounds(295, 51, 412, 20);
        frame.getContentPane().add(lblNewLabel_2);
        
        panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(1, -1, 1035, 77);
        frame.getContentPane().add(panel);
        
        btnUpdate = new JButton("UPDATE");
        btnUpdate.setFont(new Font("Verdana", Font.BOLD, 19));
        btnUpdate.setBounds(609, 276, 157, 76);
        frame.getContentPane().add(btnUpdate);
        
        btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Check if data has been viewed before deleting
                if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty() ||
                        textField_3.getText().isEmpty() || textField_4.getText().isEmpty() ||
                        textField_5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "You must first view the data.");
                } else {
                    // Perform delete operation
                    try {
                        String query = "DELETE FROM job_seekers WHERE job_seeker_id=?";
                        pst = con.prepareStatement(query);
                        pst.setString(1, textField.getText()); // Job seeker ID
                        int deleted = pst.executeUpdate();
                        if(deleted > 0) {
                            JOptionPane.showMessageDialog(frame, "Data deleted successfully.");
                            // Clear text fields after deletion
                            textField_1.setText("");
                            textField_2.setText("");
                            textField_3.setText("");
                            textField_4.setText("");
                            textField_5.setText("");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to delete data.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
        	}
        });
        btnDelete.setFont(new Font("Verdana", Font.BOLD, 19));
        btnDelete.setBounds(838, 276, 157, 76);
        frame.getContentPane().add(btnDelete);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_1.setColumns(10);
        textField_1.setBounds(48, 199, 412, 28);
        frame.getContentPane().add(textField_1);
        
        textField_2 = new JTextField();
        textField_2.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_2.setColumns(10);
        textField_2.setBounds(48, 281, 412, 28);
        frame.getContentPane().add(textField_2);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_3.setColumns(10);
        textField_3.setBounds(48, 347, 412, 28);
        frame.getContentPane().add(textField_3);
        
        textField_4 = new JTextField();
        textField_4.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_4.setColumns(10);
        textField_4.setBounds(48, 423, 412, 28);
        frame.getContentPane().add(textField_4);
        
        textField_5 = new JTextField();
        textField_5.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_5.setColumns(10);
        textField_5.setBounds(48, 501, 412, 28);
        frame.getContentPane().add(textField_5);

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                Job_Seekers jobSeekersWindow = new Job_Seekers(); // Create an instance of Job_Seekers class
                jobSeekersWindow.frame.setVisible(true); // Make the Job_Seekers frame visible
            }
        });

        view1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jobSeekerID = textField.getText();
                if(jobSeekerID.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your ID.");
                } else {
                    try {
                        String query = "SELECT * FROM job_seekers WHERE job_seeker_id=?";
                        pst = con.prepareStatement(query);
                        pst.setString(1, jobSeekerID);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            // Retrieve data from the result set and set it to text fields
                            textField_1.setText(rs.getString("user_id"));
                            textField_2.setText(rs.getString("experience"));
                            textField_3.setText(rs.getString("education"));
                            textField_4.setText(rs.getString("contact_information"));
                            textField_5.setText(rs.getString("languages"));
                            // Enable update and delete buttons after viewing the data
                            btnUpdate.setEnabled(true);
                            btnDelete.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(frame, "No data found for the given ID.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Add action listener to the update button
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if data has been viewed before updating
                if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty() ||
                        textField_3.getText().isEmpty() || textField_4.getText().isEmpty() ||
                        textField_5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "You must first view the data.");
                } else {
                    // Perform update operation
                    try {
                        String query = "UPDATE job_seekers SET user_id=?, experience=?, education=?, contact_information=?, languages=? WHERE job_seeker_id=?";
                        pst = con.prepareStatement(query);
                        pst.setString(1, textField_1.getText());
                        pst.setString(2, textField_2.getText());
                        pst.setString(3, textField_3.getText());
                        pst.setString(4, textField_4.getText());
                        pst.setString(5, textField_5.getText());
                        pst.setString(6, textField.getText()); // Job seeker ID
                        int updated = pst.executeUpdate();
                        if(updated > 0) {
                            JOptionPane.showMessageDialog(frame, "Data updated successfully.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Failed to update data.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
