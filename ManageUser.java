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
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageUser extends JInternalFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField firstnameTF;
	private JTextField usernameTF;
	private JTextField email_idTF;
	private JTextField lastnameTF;
	private JTextField passwordTF;
	private JTextField weburlTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageUser frame = new ManageUser();
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
	public ManageUser() {
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1310, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(611, 80, 677, 289);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				String firstname = (String) model.getValueAt(table.getSelectedRow(),1);
				String lastname = (String) model.getValueAt(table.getSelectedRow(),2);
				String username = (String) model.getValueAt(table.getSelectedRow(),3);
				String password = (String) model.getValueAt(table.getSelectedRow(),4);
				String email = (String) model.getValueAt(table.getSelectedRow(),5);
				String url = (String) model.getValueAt(table.getSelectedRow(),6);
			
				firstnameTF.setText(firstname);
				lastnameTF.setText(lastname);
				usernameTF.setText(username);
				passwordTF.setText(password);
				email_idTF.setText(email);
				weburlTF.setText(url);
			
				
				
			}
		});
	

		scrollPane.setViewportView(table);
	
		
		
		JButton btnNewButton = new JButton("Create User Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = firstnameTF.getText();
				String lastname = lastnameTF.getText();
				String username = usernameTF.getText();
				String password = passwordTF.getText();
				String email = email_idTF.getText();
				String weburl = weburlTF.getText();

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from user_details where username ='" + username
							+ "' and password ='" + password + "'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);

					if (rs.next() == true) {
						infoMessage("Account already exists", "Alert!!");

					} else {

						String insertQuery = "insert into user_details values(null,'" + firstname + "','" + lastname
								+ "','" + username + "','" + password + "','" + email + "','" + weburl + "')";
						// System.out.println(insertQuery);
						stat.executeUpdate(insertQuery);
						infoMessage("Account successfully created", "!!");
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(166, 357, 224, 57);
		contentPane.add(btnNewButton);
		
		firstnameTF = new JTextField();
		firstnameTF.setBounds(127, 108, 120, 27);
		contentPane.add(firstnameTF);
		firstnameTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(27, 108, 101, 27);
		contentPane.add(lblNewLabel);
		
		usernameTF = new JTextField();
		usernameTF.setColumns(10);
		usernameTF.setBounds(127, 194, 120, 27);
		contentPane.add(usernameTF);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(27, 194, 101, 27);
		contentPane.add(lblNewLabel_1);
		
		email_idTF = new JTextField();
		email_idTF.setColumns(10);
		email_idTF.setBounds(127, 281, 120, 27);
		contentPane.add(email_idTF);
		
		JLabel lblNewLabel_2 = new JLabel("Email Id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(27, 281, 101, 27);
		contentPane.add(lblNewLabel_2);
		
		lastnameTF = new JTextField();
		lastnameTF.setColumns(10);
		lastnameTF.setBounds(445, 108, 120, 27);
		contentPane.add(lastnameTF);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(341, 108, 113, 27);
		contentPane.add(lblNewLabel_4);
		
		passwordTF = new JTextField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(445, 194, 120, 27);
		contentPane.add(passwordTF);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(341, 194, 113, 27);
		contentPane.add(lblNewLabel_1_1);
		
		weburlTF = new JTextField();
		weburlTF.setColumns(10);
		weburlTF.setBounds(445, 281, 120, 27);
		contentPane.add(weburlTF);
		
		JLabel lblNewLabel_2_1 = new JLabel("Web URL");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(341, 281, 113, 27);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("User Accout Details");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1.setBounds(611, 43, 224, 27);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Create User Accout ");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4_1_1.setBounds(210, 43, 224, 27);
		contentPane.add(lblNewLabel_4_1_1);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery1 = "select * from user_details";
					
					ResultSet rs1 = stat.executeQuery(selectQuery1);
					
					ResultSetMetaData rsmd =rs1.getMetaData();
					
					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					table.setModel(new DefaultTableModel(null,colName));
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
				
					for (int i = 0; i < cols; i++) 
						colName[i]= rsmd.getColumnName(i+1);
					
					model.setColumnIdentifiers(colName);
					
					
					while(rs1.next()) {
						
						String[] row = {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7)};
						model.addRow(row);		
						
						}
				

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(660, 393, 139, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						String firstname = firstnameTF.getText();
						String lastname = lastnameTF.getText();
						String username = usernameTF.getText();
						String password = passwordTF.getText();
						String email = email_idTF.getText();
						String weburl = weburlTF.getText();
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						int index = table.getSelectedRow();
						String id =  (String) model.getValueAt(index, 0);
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
							Connection con = DriverManager.getConnection(databaseURL, "root", "");
							Statement stat = con.createStatement();
							String selectQuery = "select * from user_details where "
									
							 + "username ='" + username 		
							 + "'and password ='" + password
							 + "'and firstname ='" + firstname
							 + "'and lastname ='" + lastname
							 + "'and email_id ='" + email 
							 + "'and web_url ='" + weburl +"'";
							System.out.println(selectQuery);
							ResultSet rs = stat.executeQuery(selectQuery);

							if (rs.next() == true) {
								infoMessage("Already User details is added", "Alert!!");
								
							} else {

								String updateQuery = "UPDATE user_details SET "
										 + "firstname ='" + firstname 
										         + "' ,lastname ='" + lastname 
												 + "' ,username ='" + username 
												 + "' ,password ='" + password 
												 + "' ,email_id= '" + email 
												 + "' ,web_url= '" + weburl 
												 + "'where id ='"+id+"'";
								 System.out.println(updateQuery);
								stat.executeUpdate(updateQuery);
								infoMessage("Bus Details is successfully Update", "Welcome Bro!!");
								
								String selectQuery1 = "select * from user_details";
								ResultSet rs1 = stat.executeQuery(selectQuery1);
								
							
							
							}
							
						} catch (Exception e2) {
							System.out.println(e2);
						}
					}
				});
		
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setBounds(881, 393, 139, 32);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Delete");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

						DefaultTableModel model = (DefaultTableModel)table.getModel();
						int index = table.getSelectedRow();
						String id = (String) model.getValueAt(index, 0);	
					
						if (table.getSelectedRowCount()==1)
						{
							//model.removeRow(table.getSelectedRow());
							System.out.println(table.getSelectedRow()+1);
							try 
								{
									Class.forName("com.mysql.cj.jdbc.Driver");
									String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
									Connection con = DriverManager.getConnection(databaseURL, "root", "");
									Statement stat = con.createStatement();
									String DeleteQuery = "delete from user_details where id = '"+id+"'";
									System.out.println(DeleteQuery);
									stat.executeUpdate(DeleteQuery);
									model.removeRow(index);
									
					
								}
							
							
							catch (ClassNotFoundException | SQLException | NullPointerException e2) 
								{
							 		System.out.println(e2);
			
								}
						}
						else
						{
							if (table.getSelectedRowCount()==0)
								//if table is empty (no data)
							{
								infoMessage("Alert!","Table is Empty !");
							} 
							else 
								// if table is not empty but row is not selected or multiple row is selected
							{
								infoMessage("Alert!","Please select single row for delete !");					
							}
						}
						
					}
					
				});
						
						
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2.setBounds(1098, 393, 139, 32);
		contentPane.add(btnNewButton_1_2);
	}
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
}
