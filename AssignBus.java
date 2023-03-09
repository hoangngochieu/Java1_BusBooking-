package bus.management;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;

public class AssignBus extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignBus frame = new AssignBus();
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
	public AssignBus() {
		
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1082, 468);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Assign Bus");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(477, 103, 167, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(318, 173, 151, 30);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bus No:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(318, 249, 137, 30);
		getContentPane().add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(578, 173, 175, 29);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBounds(578, 249, 175, 29);
		getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(375, 328, 101, 30);
		getContentPane().add(btnNewButton);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBounds(594, 328, 101, 30);
		getContentPane().add(btnReset);
		// set database eid in JcomboBox 
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select eid from employee_details";
			//System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while(rs.next()) {
				comboBox.addItem(Integer.toString(rs.getInt("eid")));
			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}
	
		
		// set database bus no in JcomboBox 
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select bus_no from bus_details";
			//System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while(rs.next()) {
				comboBox_1.addItem(rs.getString("bus_no"));
			}

		}
		catch (Exception e) {
			// TODO: handle exception
		}
	
}}
