package bus.management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Label;
import javax.swing.JSplitPane;


public class DetailedRevenue extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailedRevenue frame = new DetailedRevenue();
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
	public DetailedRevenue() {
		
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		
		
		
		setBounds(0, 0, 1310, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		JLabel lblNewLabel = new JLabel("Revenue per bus");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(64, 243, 214, 52);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select distinct(bus_no) from bus_booking";
			// System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while (rs.next()) {
				comboBox_1.addItem(rs.getString("bus_no"));
			}

		} catch (Exception e) {
		}
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBounds(289, 262, 156, 21);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Start Date");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(216, 136, 116, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(332, 138, 156, 19);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("End Date");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(599, 136, 116, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(715, 138, 156, 19);
		contentPane.add(dateChooser_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 371, 403, 71);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
	
		Button button = new Button("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
		java.util.Date startdate1 = dateChooser.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sdf.format(startdate1);
		
		java.util.Date enddate1 = dateChooser_1.getDate();
		String enddate = sdf.format(enddate1);
		
				String busno = (String) comboBox_1.getSelectedItem();
				
			

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select bus_no, SUM(paid) from bus_booking where bus_no='" + busno +
							"' and journey_date between '"+startdate+"' and '"+enddate+"'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					

					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					
					while(rs.next()) {
						
						String[] row = {rs.getString(1),rs.getString(2)};
						model.addRow(row);		
						
						}
				

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
		});
	
		button.setFont(new Font("Dialog", Font.PLAIN, 18));
		button.setBounds(349, 294, 118, 71);
		contentPane.add(button);
		
		JLabel lblRevenue = new JLabel("Revenue");
		lblRevenue.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRevenue.setBounds(488, 30, 156, 52);
		contentPane.add(lblRevenue);
		
		JLabel lblTotalRevenue = new JLabel("Total Revenue ");
		lblTotalRevenue.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTotalRevenue.setBounds(639, 243, 214, 52);
		contentPane.add(lblTotalRevenue);
		
		Button button_1 = new Button("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				java.util.Date startdate1 = dateChooser.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String startdate = sdf.format(startdate1);
				
				java.util.Date enddate1 = dateChooser_1.getDate();
				String enddate = sdf.format(enddate1);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select SUM(paid) from bus_booking where journey_date between '"+startdate+"' and '"+enddate+"'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					

					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table_1.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					
					while(rs.next()) {
						
						String[] row = {rs.getString(1)};
						model.addRow(row);		
						
						}
				

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
		});
	
	
		button_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		button_1.setBounds(927, 294, 116, 71);
		contentPane.add(button_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(640, 372, 403, 71);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
	
	}
}
