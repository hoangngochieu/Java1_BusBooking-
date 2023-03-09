package bus.management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import java.awt.Font;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddEmployee extends JInternalFrame {
	private JTextField firstnameTF;
	private JTextField lastnameTF;
	private JTextField phoneno1TF;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
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
	public AddEmployee() {
		
	
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 15));
		setBounds(0, 0, 1310, 468);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(152, 51, 206, 39);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(41, 139, 123, 22);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Phone No:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(41, 256, 123, 22);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Last Name:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(41, 197, 123, 22);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Bus no:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(41, 312, 123, 22);
		getContentPane().add(lblNewLabel_1_3);
		
		JComboBox BusnoCB = new JComboBox();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select distinct(bus_no) from bus_details";
			// System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while (rs.next()) {
				BusnoCB.addItem(rs.getString("bus_no"));
			}

		} catch (Exception e) {
		}

	
		BusnoCB.setBounds(152, 316, 199, 21);
		getContentPane().add(BusnoCB);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String firstname=firstnameTF.getText();
			        String lastname=lastnameTF.getText();
			        String phoneno1=phoneno1TF.getText();
			    	String bus_no = (String) BusnoCB.getSelectedItem();
			        
			        try
			        {
			        	Class.forName("com.mysql.cj.jdbc.Driver");
			            String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";			            Connection con = DriverManager.getConnection(databaseURL, "root", "");
			            Statement stat = con.createStatement();    
			            String selectQuery = "select * from employee_details where phoneno1	='"+phoneno1+"'";
			            System.out.println(selectQuery);
			            ResultSet rs=stat.executeQuery(selectQuery);
			         //   System.out.println(rs.next());
			           if(rs.next())
			           {
			             infoMessage("Already Inserted", "ALert !!");
			            
			           }
			           else
			           {
			          String insertQuery = "insert into employee_details values(null,'" + firstname + "','" + lastname + "','" + phoneno1 + "','" + bus_no + "')";
			           
			          stat.executeUpdate(insertQuery);
			           infoMessage("information is inserted", "Welcome Bro !!");
			       
			          
			               
			           
			        }
			        }
			        catch(Exception e2)
			                {
			                    System.out.println(e2);
			                }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(391, 161, 109, 134);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
						Connection con = DriverManager.getConnection(databaseURL, "root", "");
						Statement stat = con.createStatement();
						String selectQuery1 = "select * from employee_details";
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
								
								String[] row = {rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5)};
								model.addRow(row);		
								
								}
						

						} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
							System.out.println(e2);

						}
					}
				});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(1127, 160, 109, 31);
		getContentPane().add(btnNewButton_1);
		
		firstnameTF = new JTextField();
		firstnameTF.setBounds(152, 140, 199, 27);
		getContentPane().add(firstnameTF);
		firstnameTF.setColumns(10);
		
		lastnameTF = new JTextField();
		lastnameTF.setColumns(10);
		lastnameTF.setBounds(152, 198, 199, 27);
		getContentPane().add(lastnameTF);
		
		phoneno1TF = new JTextField();
		phoneno1TF.setColumns(10);
		phoneno1TF.setBounds(152, 255, 199, 27);
		getContentPane().add(phoneno1TF);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(608, 145, 430, 244);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				String firstname = (String) model.getValueAt(table.getSelectedRow(),1);
				String lastname = (String) model.getValueAt(table.getSelectedRow(),2);
				String phone1 = (String) model.getValueAt(table.getSelectedRow(),3);
				String bus_no = (String) model.getValueAt(table.getSelectedRow(),4);
				
				firstnameTF.setText(firstname);
				lastnameTF.setText(lastname);
				phoneno1TF.setText(phone1);
				BusnoCB.setSelectedItem(bus_no);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				String id = (String) model.getValueAt(index, 0);	
			
							if (table.getSelectedRowCount()==1)
				{
					//	model.removeRow(table.getSelectedRow());
					System.out.println(table.getSelectedRow()+1);
					try 
						{
							Class.forName("com.mysql.cj.jdbc.Driver");
							String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
							Connection con = DriverManager.getConnection(databaseURL, "root", "");
							Statement stat = con.createStatement();
							String DeleteQuery = "delete from employee_details where eid = '"+id+"'";
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
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setBounds(1127, 227, 109, 31);
		getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Update");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstname = firstnameTF.getText();
				String lastname = lastnameTF.getText();
				String phoneno1 = phoneno1TF.getText();
				String bus_no = (String) BusnoCB.getSelectedItem();
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				String id = (String) model.getValueAt(index, 0);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from employee_details where "
							 + "firstname ='" + firstname 
							 + "'and lastname ='" + lastname 
							 + "'and phoneno1 ='" + phoneno1 
							 + "'and bus_no ='" + bus_no+"'"; 
					ResultSet rs = stat.executeQuery(selectQuery);

					if (rs.next() == true) {
						infoMessage("Already Bus Details is added", "Alert!!");
						//clearFieldValue();
					} else {

						String updateQuery = "UPDATE employee_details SET "
								         + "firstname ='" + firstname 
								         + "' ,lastname ='" + lastname 
										 + "' ,phoneno1 ='" + phoneno1 
										 + "' ,bus_no ='" + bus_no
										 + "'where eid ='"+id+"'";
						 System.out.println(updateQuery);
						stat.executeUpdate(updateQuery);
						infoMessage("Employee Details is successfully Update", "Welcome Bro!!");
						
						String selectQuery1 = "select * from employee_details";
						ResultSet rs1 = stat.executeQuery(selectQuery1);
					
					
					
					}
			}
				catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1_1.setBounds(1127, 291, 109, 31);
		getContentPane().add(btnNewButton_1_1_1);
		
		JLabel lblEmployeeDetails = new JLabel("Employee details");
		lblEmployeeDetails.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmployeeDetails.setBounds(608, 51, 206, 39);
		getContentPane().add(lblEmployeeDetails);
		
		


        setResizable(true);
        
        
	}
	public void infoMessage(String message, String tittle) {
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);
    }
}
