package bus.management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AllBusDetails extends JInternalFrame {
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllBusDetails frame = new AllBusDetails();
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
	public AllBusDetails() {
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		setBounds(0, 0, 1310, 468);
		getContentPane().setLayout(null);
		
		JButton btnShow = new JButton("Reset");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShow.setBounds(254, 381, 109, 36);
		getContentPane().add(btnShow);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 50, 1168, 315);
		getContentPane().add(scrollPane);
		
		tblData = new JTable();
		scrollPane.setViewportView(tblData);
		
		JButton btnOrderByBus = new JButton("Order By Bus");
		btnOrderByBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement st = con.createStatement();
					String query = "select * from bus_details order by bus_no";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					tblData.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					
					while(rs.next()) {
						
						String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
						model.addRow(row);		
						
						}
				
						
				
				} catch (ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnOrderByBus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrderByBus.setBounds(401, 381, 145, 36);
		getContentPane().add(btnOrderByBus);
		
		JButton btnOrderByDate = new JButton("Order By Date");
		btnOrderByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement st = con.createStatement();
					String query = "select * from bus_details order by depart_date";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					tblData.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					
					while(rs.next()) {
						
						String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
						model.addRow(row);		
						
						}
				
						
				
				} catch (ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnOrderByDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrderByDate.setBounds(585, 381, 181, 36);
		getContentPane().add(btnOrderByDate);
		
		
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement st = con.createStatement();
					String query = "select * from bus_details";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					tblData.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					while(rs.next()) {
						
						String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)};
						model.addRow(row);		
						
						}
				
						
				
				} catch (ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
		
				
				
			}
		});

	}

}
