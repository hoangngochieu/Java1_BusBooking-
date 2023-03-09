package bus.management;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class AllEmployee extends JInternalFrame {

	private JPanel contentPane;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllEmployee frame = new AllEmployee();
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
	public AllEmployee() {
		setClosable(true);
        setIconifiable(true);
        setResizable(true);
		
		setBounds(0, 0, 1082, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnShow = new JButton("Display Data");
		btnShow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShow.setBounds(10, 10, 148, 38);
		getContentPane().add(btnShow);
		
		tblData = new JTable();
		tblData.setBounds(179, 10, 879, 331);
		getContentPane().add(tblData);
		
		
		
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement st = con.createStatement();
					String query = "select * from employee_details";
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd =rs.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					tblData.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
					
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					model.addRow(colName);
					while(rs.next()) {
						
						String[] row = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
						model.addRow(row);		
						
						}
				
						
					
				} catch (ClassNotFoundException | SQLException e2) {
					e2.printStackTrace();
				}
		
				
				
			}
		});

	}
	}


