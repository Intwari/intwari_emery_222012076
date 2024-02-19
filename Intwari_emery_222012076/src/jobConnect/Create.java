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
import java.sql.SQLException;

public class Create extends JFrame {

    protected JFrame frame;
    private JLabel lblNewLabel_1;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost/job_connect_db";
    private static final String USERNAME = "Intwari";
    private static final String PASSWORD = "222012076";

    // Establishing database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Create window = new Create();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Create() {
        initialize();
    }

    private void initialize() {
        setTitle("Create Jobs");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed
        setLocationRelativeTo(null);

        frame = new JFrame();
        frame.setBounds(100, 100, 880, 507);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblNewLabel_1 = new JLabel("Create Job Details");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 200, 28);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblCompanyName = new JLabel("Company Name");
        lblCompanyName.setFont(new Font("Verdana", Font.BOLD, 18));
        lblCompanyName.setBounds(47, 78, 179, 45);
        frame.getContentPane().add(lblCompanyName);

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.BOLD, 16));
        textField.setColumns(10);
        textField.setBounds(47, 126, 412, 51);
        frame.getContentPane().add(textField);

        JLabel lblJobTitle = new JLabel("Job Title");
        lblJobTitle.setFont(new Font("Verdana", Font.BOLD, 18));
        lblJobTitle.setBounds(47, 177, 179, 45);
        frame.getContentPane().add(lblJobTitle);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_1.setColumns(10);
        textField_1.setBounds(47, 222, 412, 51);
        frame.getContentPane().add(textField_1);

        JLabel lblNewLabel_1_1 = new JLabel("Job Description");
        lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(47, 274, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_2.setColumns(10);
        textField_2.setBounds(47, 322, 412, 51);
        frame.getContentPane().add(textField_2);

        JLabel lblNewLabel_1_1_1 = new JLabel("Job Salary");
        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.BOLD, 18));
        lblNewLabel_1_1_1.setBounds(47, 373, 179, 45);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Verdana", Font.BOLD, 16));
        textField_3.setColumns(10);
        textField_3.setBounds(47, 421, 412, 51);
        frame.getContentPane().add(textField_3);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get values from text fields
                String companyName = textField.getText();
                String jobTitle = textField_1.getText();
                String jobDescription = textField_2.getText();
                String jobSalary = textField_3.getText();

                // Check if any of the text fields are empty
                if (companyName.isEmpty() || jobTitle.isEmpty() || jobDescription.isEmpty() || jobSalary.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                } else {
                    // Save to database
                    saveToDatabase(companyName, jobTitle, jobDescription, jobSalary);
                }
            }
        });
        createButton.setFont(new Font("Verdana", Font.BOLD, 20));
        createButton.setBounds(571, 274, 109, 45);
        frame.getContentPane().add(createButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                Entrepreneurs entrepreneursWindow = new Entrepreneurs(); // Create an instance of Entrepreneurs class
                entrepreneursWindow.frame.setVisible(true); // Make the Entrepreneurs frame visible
            }
        });
        backButton.setFont(new Font("Verdana", Font.BOLD, 20));
        backButton.setBounds(761, 420, 109, 51);
        frame.getContentPane().add(backButton);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 870, 472);
        frame.getContentPane().add(panel);
    }

    private void saveToDatabase(String companyName, String jobTitle, String jobDescription, String jobSalary) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO entrepreneurs (company_name, job_title, job_description, job_salary) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, companyName);
                pstmt.setString(2, jobTitle);
                pstmt.setString(3, jobDescription);

                // Convert jobSalary to a double and truncate to fit within the column constraint
                double salary = Double.parseDouble(jobSalary);
                salary = Math.min(salary, 9999999.99); // Ensure it doesn't exceed 10 digits
                pstmt.setDouble(4, salary);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Job details created successfully!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to create job details.");
        }
    }
}
