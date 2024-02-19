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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Job_Seekers {

    protected JFrame frame;
    private JLabel lblNewLabel_1;
    private JButton btnNewButton;
    private JButton btnNewButton_3;
    private JTable table;
    private Connection con;
    private JPanel panel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Job_Seekers window = new Job_Seekers();
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
    public Job_Seekers() {
        initialize();
        connection();
        fetchData();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1050, 552);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblNewLabel_1 = new JLabel("HOMEPAGE");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 133, 28);
        frame.getContentPane().add(lblNewLabel_1);

        JButton btnNewButton_1 = new JButton("Send proposal");
        btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 19));
        btnNewButton_1.setBounds(851, 0, 190, 76);
        frame.getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("welcome to your first step of getting employed");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setToolTipText("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 17));
        lblNewLabel_2.setBounds(266, 50, 458, 20);
        frame.getContentPane().add(lblNewLabel_2);

        btnNewButton = new JButton("PROFILE");
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 19));
        btnNewButton.setBounds(0, 0, 157, 76);
        frame.getContentPane().add(btnNewButton);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 1035, 82);
        frame.getContentPane().add(panel);

        btnNewButton_3 = new JButton("Back");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                LogIn usersWindow = new LogIn(); // Create an instance of Users class
                usersWindow.frame.setVisible(true); // Make the Users frame visible
            }
        });
        btnNewButton_3.setFont(new Font("Verdana", Font.BOLD, 20));
        btnNewButton_3.setBounds(927, 470, 109, 45);
        frame.getContentPane().add(btnNewButton_3);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 174, 766, 138);
        frame.getContentPane().add(scrollPane);
        
        table = new JTable();
        table.setFont(new Font("Verdana", Font.BOLD, 13));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
            },
            new String[] {
                "COMPANY NAME", "Job title", "Job salary"
            }
        ));
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(64, 128, 128));
        panel_1.setBounds(0, 87, 1036, 428);
        frame.getContentPane().add(panel_1);
        table.getColumnModel().getColumn(0).setPreferredWidth(110);
        table.getColumnModel().getColumn(1).setPreferredWidth(95);

        // Add action listener to PROFILE button
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                frame.dispose();
                // Open the J_Sprofile frame
                J_Sprofile profileWindow = new J_Sprofile();
                profileWindow.frame.setVisible(true);
            }
        });

        // Add action listener to "Send proposal" button
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(frame, "Please select a job.");
                } else {
                    String jobTitle = (String) table.getValueAt(selectedRow, 1);
                    // Open the Submit_Proposal window with the selected job title
                    Submit_Proposal submitProposalWindow = new Submit_Proposal(jobTitle);
                    submitProposalWindow.frame.setVisible(true);
                    frame.dispose(); // Close the Job_Seekers window
                }
            }
        });
    }

    private void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/job_connect_db", "root", "");
            System.out.println("Connected successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchData() {
        if (con != null) {
            try {
                PreparedStatement pst = con.prepareStatement("SELECT company_name, job_title, job_salary FROM entrepreneurs");
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    String companyName = rs.getString("company_name");
                    String jobTitle = rs.getString("job_title");
                    double jobSalary = rs.getDouble("job_salary");

                    model.addRow(new Object[]{companyName, jobTitle, jobSalary});
                }

                pst.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is null. Cannot fetch data.");
        }
    }
}
