package jobConnect;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class View_Application {

    public JFrame frame;
    private JLabel lblNewLabel_1;
    private JButton btnNewButton_3;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel_1;
    private JPanel panel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View_Application window = new View_Application();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View_Application() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1050, 551);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        lblNewLabel_1 = new JLabel("proposal sent");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 19));
        lblNewLabel_1.setBounds(417, 0, 165, 28);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Here is the jobs applied for");
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
        
        btnNewButton_3 = new JButton("Back");
        btnNewButton_3.setFont(new Font("Verdana", Font.BOLD, 20));
        btnNewButton_3.setBounds(927, 466, 109, 45);
        frame.getContentPane().add(btnNewButton_3);
        
        panel_2 = new JPanel();
        panel_2.setBackground(new Color(64, 128, 128));
        panel_2.setBounds(0, 87, 1035, 424);
        frame.getContentPane().add(panel_2);
        
        scrollPane = new JScrollPane();
        panel_2.add(scrollPane);
        
        table = new JTable();
        table.setFont(new Font("Verdana", Font.BOLD, 13));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null},
                {null, null},
            },
            new String[] {
                "APPLICANT NAMES", "JOB TITLE"
            }
        ));
        

        
        // Add action listener to the "Back" button
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                Entrepreneurs entrepreneursWindow = new Entrepreneurs(); // Create an instance of Entrepreneurs class
                entrepreneursWindow.frame.setVisible(true); // Make the Entrepreneurs frame visible
            }
        });
        
        displayApplications();
    }

    private void displayApplications() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Job_Connect_db", "Intwari", "222012076");
            String query = "SELECT labor_name, job_title FROM applications";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String laborName = rs.getString("labor_name");
                String jobTitle = rs.getString("job_title");
                model.addRow(new Object[]{laborName, jobTitle});
            }
            
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
