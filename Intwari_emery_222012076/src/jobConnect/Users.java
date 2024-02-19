package jobConnect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;

public class Users {

    JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPasswordField passwordField;

    Connection con;
    PreparedStatement pst;

    void connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/job_connect_db", "root", "");
            System.out.print("Connected successfully");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Users() {
        initialize();
        connection();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setFont(new Font("Verdana", Font.BOLD, 21));
        frame.setBackground(new Color(192, 192, 192));
        frame.setBounds(100, 100, 883, 541);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("REGISTER");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel.setBounds(327, 11, 202, 64);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("FULL NAME:");
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel_1.setBounds(62, 105, 202, 64);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("Verdana", Font.BOLD, 21));
        textField.setBounds(327, 100, 228, 69);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("EMAIL:");
        lblNewLabel_2.setForeground(new Color(0, 0, 0));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel_2.setBounds(62, 217, 202, 64);
        frame.getContentPane().add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Verdana", Font.BOLD, 21));
        textField_1.setColumns(10);
        textField_1.setBounds(327, 212, 228, 69);
        frame.getContentPane().add(textField_1);

        JLabel lblNewLabel_3 = new JLabel("PASSWORD:");
        lblNewLabel_3.setForeground(new Color(0, 0, 0));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel_3.setBounds(62, 330, 202, 64);
        frame.getContentPane().add(lblNewLabel_3);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Verdana", Font.BOLD, 21));
        passwordField.setBounds(327, 326, 228, 64);
        frame.getContentPane().add(passwordField);

        JLabel lblNewLabel_4 = new JLabel("USER TYPE:");
        lblNewLabel_4.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 22));
        lblNewLabel_4.setBounds(62, 439, 202, 64);
        frame.getContentPane().add(lblNewLabel_4);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setFont(new Font("Verdana", Font.BOLD, 21));
        comboBox.setBounds(327, 439, 228, 60);
        frame.getContentPane().add(comboBox);
        comboBox.addItem("SELECT");
        comboBox.addItem("LABOR");
        comboBox.addItem("ENTREPRENEUR");

        JButton btnNewButton = new JButton("SIGN UP");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullName = textField.getText();
                String email = textField_1.getText();
                String password = new String(passwordField.getPassword());
                String userType = comboBox.getSelectedItem().toString();

                // Check if any of the required fields are empty
                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.");
                } else {
                    try {
                        pst = con.prepareStatement("INSERT INTO users (full_name, email, password, username) VALUES (?, ?, ?, ?)");
                        pst.setString(1, fullName);
                        pst.setString(2, email);
                        pst.setString(3, password);
                        pst.setString(4, fullName);
                        pst.executeUpdate();
                        System.out.println("Data inserted successfully");
                        // Clear input fields after successful insertion
                        textField.setText("");
                        textField_1.setText("");
                        passwordField.setText("");
                        comboBox.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(frame, "Registration successful!");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Failed to register. Please try again.");
                    }
                }
            }
        });
        btnNewButton.setBackground(new Color(0, 128, 192));
        btnNewButton.setForeground(new Color(0, 0, 0));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 22));
        btnNewButton.setBounds(648, 100, 159, 52);
        frame.getContentPane().add(btnNewButton);

        JLabel lblNewLabel_5 = new JLabel("Already have an account?");
        lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 12));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(626, 246, 216, 14);
        frame.getContentPane().add(lblNewLabel_5);

        JButton btnLogIn = new JButton("LOG IN");

        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of the Login class
                LogIn loginWindow = new LogIn();
                loginWindow.frame.setVisible(true); // Make the login window visible
            }
        });

        btnLogIn.setForeground(Color.BLACK);
        btnLogIn.setFont(new Font("Verdana", Font.BOLD, 22));
        btnLogIn.setBackground(new Color(0, 128, 192));
        btnLogIn.setBounds(648, 356, 159, 52);
        frame.getContentPane().add(btnLogIn);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(64, 128, 128));
        panel.setBounds(0, 0, 869, 77);
        frame.getContentPane().add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setForeground(new Color(0, 0, 0));
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(0, 77, 869, 427);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_6 = new JLabel("New label");
        lblNewLabel_6.setBounds(690, 375, 49, 14);
        panel_1.add(lblNewLabel_6);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Users window = new Users();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
