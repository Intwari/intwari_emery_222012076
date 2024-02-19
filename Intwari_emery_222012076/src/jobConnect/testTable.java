package jobConnect;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.DefaultFocusTraversalPolicy;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Result;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class testTable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testTable frame = new testTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	/**
	 * Create the frame.
	 */
	DefaultTableModel model;
	 Connection con;
	 PreparedStatement pst;
	 private JTextField full_name;
	 private JTextField id;
	 private JTextField email;
	 private JButton UPdate;
	 
	 void connection() {
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con =  DriverManager.getConnection("jdbc:mysql://localhost/job_connect_db","root", "");
	         System.out.print("Connected succesfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	private void addNewUsers() {
		int cc;
		try {
			pst = con.prepareStatement("SELECT * FROM users");
		ResultSet Rs = pst.executeQuery();
		java.sql.ResultSetMetaData RSMD= Rs.getMetaData(); 
		cc = RSMD.getColumnCount();
		DefaultTableModel dataInTable = (DefaultTableModel)table.getModel();
		dataInTable.setRowCount(0);
		while (Rs.next()) {
			Vector v2  = new Vector();
			
			v2.add(Rs.getString("user_id"));
			v2.add(Rs.getString("email"));
			v2.add(Rs.getString("full_name"));
			dataInTable.addRow(v2);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public testTable() {
		
		connection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 791);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			
				System.out.println("data clicked");
              model = (DefaultTableModel)table.getModel();
             int selectedIndex = table.getSelectedRow();
				int ids = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
				id.setText(model.getValueAt(selectedIndex, 0).toString());
				full_name.setText(model.getValueAt(selectedIndex, 1).toString());
				email.setText(model.getValueAt(selectedIndex, 2).toString());
		/*    	 String fullName,emailq,idq;
		    	 fullName = full_name.getText();
		    	 emailq = email.getText();
		    	 idq  =id.getText();
		    	  
		    try {
				pst = con.prepareStatement("UPDATE  users SET full_name=?,email=? WHERE id=?) ");
				pst.setString(1, fullName);
				pst.setString(2, emailq);
				pst.setString(3, idq);
				pst.executeUpdate();
				System.out.println("Data inserted well");
				full_name.setText("");
				email.setText("");
				id.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				*/
			}
		});
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setToolTipText("");
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Full name", "Email", "User Type"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		addNewUsers();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		table.setBounds(120, 154, 619, 176);
		contentPane.add(table);
		
		full_name = new JTextField();
		full_name.setBounds(120, 486, 96, 20);
		contentPane.add(full_name);
		full_name.setColumns(10);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(126, 415, 96, 20);
		contentPane.add(id);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(120, 538, 96, 20);
		contentPane.add(email);
		
		UPdate = new JButton("UPdate");
		UPdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				    	 String fullName,emailq,idq;
		    	 fullName = full_name.getText();
		    	 emailq = email.getText();
		    	 idq  =id.getText();
		    	  
		    try {
				pst = con.prepareStatement("UPDATE  users SET full_name=?,email=? WHERE user_id =? ");
				pst.setString(1, fullName);
				pst.setString(2, emailq);
				pst.setString(3, idq);
				pst.executeUpdate();
				System.out.println("Data inserted well");
				full_name.setText("");
				email.setText("");
				id.setText("");
				addNewUsers();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		UPdate.setBounds(120, 617, 89, 23);
		contentPane.add(UPdate);
	}
	
 
}
