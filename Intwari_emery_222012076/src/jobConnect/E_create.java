package jobConnect;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;

public class E_create {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_4;
	private JLabel lblNewLabel_5;
	private JTextField textField_5;
	private JButton btnNewButton;
	private JPanel panel;
	private JButton btnCreate;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_create window = new E_create();
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
	public E_create() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.BOLD, 17));
		textField.setBounds(36, 42, 496, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel_1.setBounds(36, 89, 247, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("entrepreneur ID");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel.setBounds(36, 0, 247, 40);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.BOLD, 17));
		textField_1.setColumns(10);
		textField_1.setBounds(36, 128, 496, 47);
		frame.getContentPane().add(textField_1);
		
		lblNewLabel_2 = new JLabel("Company name");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel_2.setBounds(36, 174, 247, 40);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.BOLD, 17));
		textField_2.setColumns(10);
		textField_2.setBounds(36, 218, 496, 47);
		frame.getContentPane().add(textField_2);
		
		lblNewLabel_3 = new JLabel("Job title");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel_3.setBounds(36, 265, 247, 40);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Verdana", Font.BOLD, 17));
		textField_3.setColumns(10);
		textField_3.setBounds(36, 310, 496, 47);
		frame.getContentPane().add(textField_3);
		
		lblNewLabel_4 = new JLabel("Job description");
		lblNewLabel_4.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel_4.setBounds(36, 356, 247, 40);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Verdana", Font.BOLD, 17));
		textField_4.setColumns(10);
		textField_4.setBounds(36, 395, 496, 47);
		frame.getContentPane().add(textField_4);
		
		lblNewLabel_5 = new JLabel("Job salary");
		lblNewLabel_5.setFont(new Font("Verdana", Font.BOLD, 17));
		lblNewLabel_5.setBounds(36, 440, 247, 40);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Verdana", Font.BOLD, 17));
		textField_5.setColumns(10);
		textField_5.setBounds(36, 484, 496, 47);
		frame.getContentPane().add(textField_5);
		
		btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 20));
		btnNewButton.setBounds(977, 547, 109, 45);
		frame.getContentPane().add(btnNewButton);
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Verdana", Font.BOLD, 20));
		btnCreate.setBounds(605, 262, 140, 45);
		frame.getContentPane().add(btnCreate);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(64, 128, 128));
		panel_1.setBounds(533, 42, 553, 550);
		frame.getContentPane().add(panel_1);
	}

}
