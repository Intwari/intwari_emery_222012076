package jobConnect;

import java.sql.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn {

    public JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    private JComboBox<String> comboBox;
    private Connection connection;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIn window = new LogIn();
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
    public LogIn() {
        initialize();
        connectToDatabase();
    }

    // Establish connection to the database
    private void connectToDatabase() {
        try {
            // Modify the connection URL, username, and password as per your database configuration
            String url = "jdbc:mysql://localhost:3306/job_Connect_db";
            String username = "Intwari";
            String password = "222012076";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 883, 559);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Please fill your information here");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel.setBounds(184, 11, 457, 64);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Verdana", Font.BOLD, 22));
        lblEmail.setBounds(71, 156, 202, 64);
        frame.getContentPane().add(lblEmail);

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.BOLD, 21));
        textField.setBounds(327, 156, 202, 64);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setFont(new Font("Verdana", Font.BOLD, 22));
        lblPassword.setBounds(71, 305, 202, 64);
        frame.getContentPane().add(lblPassword);

        JLabel lblUserType = new JLabel("User Type:");
        lblUserType.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserType.setFont(new Font("Verdana", Font.BOLD, 22));
        lblUserType.setBounds(71, 433, 202, 64);
        frame.getContentPane().add(lblUserType);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.BOLD, 21));
        passwordField.setBounds(327, 310, 202, 59);
        frame.getContentPane().add(passwordField);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Verdana", Font.BOLD, 21));
        comboBox.setBounds(327, 433, 202, 64);
        comboBox.addItem("SELECT");
        comboBox.addItem("LABOR");
        comboBox.addItem("ENTREPRENEUR");
        frame.getContentPane().add(comboBox);

        JButton loginButton = new JButton("LOG IN");
        loginButton.setBackground(new Color(0, 128, 255));
        loginButton.setFont(new Font("Verdana", Font.BOLD, 21));
        loginButton.setBounds(638, 163, 144, 50);
        frame.getContentPane().add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) comboBox.getSelectedItem();

                if (userType.equals("SELECT")) {
                    JOptionPane.showMessageDialog(frame, "Please select your user type");
                    return;
                }

                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE email = ?");
                    statement.setString(1, email);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Email exists, check password
                        String storedPassword = resultSet.getString("password");
                        if (password.equals(storedPassword)) {
                            // Password matches, open corresponding window
                            if (userType.equals("LABOR")) {
                                // Open the Job_Seekers window
                                Job_Seekers jobSeekersWindow = new Job_Seekers();
                                jobSeekersWindow.frame.setVisible(true);
                                frame.dispose(); // Close the login window
                            } else if (userType.equals("ENTREPRENEUR")) {
                                // Open the Entrepreneurs window
                                Entrepreneurs entrepreneursWindow = new Entrepreneurs();
                                entrepreneursWindow.frame.setVisible(true);
                                frame.dispose(); // Close the login window
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Wrong password");
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Unknown email, please register");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 869, 77);
        frame.getContentPane().add(panel);
        
      
        
        JLabel lblNewLabel_5 = new JLabel("Don't have an account?");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 12));
        lblNewLabel_5.setBounds(593, 261, 216, 14);
        frame.getContentPane().add(lblNewLabel_5);
        
        
        JButton register = new JButton("Register");
        register.setBackground(new Color(0, 128, 255));
        register.setFont(new Font("Verdana", Font.BOLD, 20));
        register.setBounds(638, 320, 136, 44);
        frame.getContentPane().add(register);
        
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Users window for registration
                Users usersWindow = new Users();
                usersWindow.frame.setVisible(true);
            }
        });
    }
}
