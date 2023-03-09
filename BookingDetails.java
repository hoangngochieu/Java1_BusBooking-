package bus.management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BookingDetails extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingDetails frame = new BookingDetails();
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
	public BookingDetails() {
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		setBounds(0, 0, 1308, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 10, 1079, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_booking";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					ResultSetMetaData rsmd = rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table.setModel(new DefaultTableModel(null,colName));

					DefaultTableModel model = (DefaultTableModel) table.getModel();
			

				
					for (int i = 0; i < cols; i++)
						colName[i] = rsmd.getColumnName(i + 1);

					model.setColumnIdentifiers(colName);
					

					while (rs.next()) {

						 String[] row = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)};
						model.addRow(row);
					}

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
			
		});
		btnNewButton.setBounds(334, 369, 115, 38);
		contentPane.add(btnNewButton);
		
		JButton btnOrderByDate = new JButton("Order By Date");
		btnOrderByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_booking order by journey_date";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					ResultSetMetaData rsmd = rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table.setModel(new DefaultTableModel(null,colName));

					DefaultTableModel model = (DefaultTableModel) table.getModel();
			

				
					for (int i = 0; i < cols; i++)
						colName[i] = rsmd.getColumnName(i + 1);

					model.setColumnIdentifiers(colName);
					

					while (rs.next()) {

						 String[] row = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
						model.addRow(row);
					}

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
			
		});
		btnOrderByDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrderByDate.setBounds(833, 369, 175, 38);
		contentPane.add(btnOrderByDate);
		
		JButton btnOrderByBus = new JButton("Order By Bus ");
		btnOrderByBus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_booking order by bus_no";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					ResultSetMetaData rsmd = rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table.setModel(new DefaultTableModel(null,colName));

					DefaultTableModel model = (DefaultTableModel) table.getModel();
			

				
					for (int i = 0; i < cols; i++)
						colName[i] = rsmd.getColumnName(i + 1);

					model.setColumnIdentifiers(colName);
					

					while (rs.next()) {

						 String[] row = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)};
						model.addRow(row);
					}

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
			
		});
		btnOrderByBus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnOrderByBus.setBounds(543, 369, 183, 38);
		contentPane.add(btnOrderByBus);

	}
}
