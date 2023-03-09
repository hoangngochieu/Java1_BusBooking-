package bus.management;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBusDetails extends JInternalFrame {
	private JTextField bus_noTF;
	private JTextField bus_sourceTF;
	private JTextField bus_destTF;
	private JTextField priceTF;
	private JTextField timeTF;
	private JTextField seatTF;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBusDetails frame = new AddBusDetails();
					frame.setLocation(300, 300);
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
	public AddBusDetails() {
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(0, 0, 1306, 468);
		getContentPane().setLayout(null);
		
		bus_noTF = new JTextField();
		bus_noTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bus_noTF.setBounds(118, 92, 170, 23);
		getContentPane().add(bus_noTF);
		bus_noTF.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Source:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(30, 138, 105, 23);
		getContentPane().add(lblNewLabel_1_1);
		
		bus_sourceTF = new JTextField();
		bus_sourceTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bus_sourceTF.setColumns(10);
		bus_sourceTF.setBounds(118, 138, 170, 23);
		getContentPane().add(bus_sourceTF);
		
		
		timeTF = new JTextField();
		timeTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timeTF.setColumns(10);
		timeTF.setBounds(472, 183, 197, 23);
		getContentPane().add(timeTF);
		
		
		JLabel lblNewLabel_1_2 = new JLabel("Date:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(30, 183, 105, 23);
		getContentPane().add(lblNewLabel_1_2);
		
		seatTF = new JTextField();
		seatTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		seatTF.setColumns(10);
		seatTF.setBounds(472, 228, 197, 23);
		getContentPane().add(seatTF);
		
		priceTF = new JTextField();
		priceTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		priceTF.setColumns(10);
		priceTF.setBounds(118, 228, 170, 23);
		getContentPane().add(priceTF);

		bus_destTF = new JTextField();
		bus_destTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bus_destTF.setColumns(10);
		bus_destTF.setBounds(472, 139, 197, 23);
		getContentPane().add(bus_destTF);
		
		JComboBox moveCB = new JComboBox();
		moveCB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		moveCB.setBounds(472, 96, 197, 21);
		moveCB.addItem("UP");
		moveCB.addItem("DOWN");
		getContentPane().add(moveCB);	
		getContentPane().add(moveCB);
		
	
		
		JDateChooser departDateDp = new JDateChooser();
		departDateDp.setBounds(118, 183, 170, 23);
		getContentPane().add(departDateDp);
		
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String busnoD = bus_noTF.getText();
				String busmoveD = (String)moveCB.getSelectedItem();
				String bussourceD = bus_sourceTF.getText();
				String busdestD = bus_destTF.getText();
					java.util.Date departDateD = departDateDp.getDate();
					SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String departDate = oDateFormat.format(departDateD);
				String departTimeD = timeTF.getText();
				String priceD = priceTF.getText();
				String seatD = seatTF.getText();
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_details where "
					 + "bus_no ='" + busnoD 
					 + "'and movement ='" + busmoveD 
					 + "'and bus_source ='" + bussourceD 
					 + "'and bus_dest ='" + busdestD 
					 + "'and depart_date= '" + departDate 
					 + "'and depart_time= '" + departTimeD 
					 + "'and price ='" + priceD 
					 + "'and total_seat ='" + seatD + "'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);

					if (rs.next() == true) {
						infoMessage("Already Bus Details is added", "Alert!!");
						clearFieldValue();
					} else {

						String insertQuery = "insert into bus_details values"
										+ "(null,'" + busnoD + "','" + busmoveD + "',"
										+ "'" + bussourceD + "','" + busdestD + "','" + departDate + "',"
										+ "'" + departTimeD + "','" + priceD + "','" + seatD + "')";
						 System.out.println(insertQuery);
						stat.executeUpdate(insertQuery);
						infoMessage("Bus Details is successfully created", "Welcome Bro!!");
						
						String selectQuery1 = "select * from bus_details";
						ResultSet rs1 = stat.executeQuery(selectQuery1);
						  table.setModel(DbUtils.resultSetToTableModel(rs1));
				           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					
					}
					
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(231, 289, 276, 61);
		getContentPane().add(btnNewButton);
		
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Price:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(30, 228, 105, 23);
		getContentPane().add(lblNewLabel_1_2_1);
	
		JLabel lblNewLabel_1_3 = new JLabel("Movement:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(334, 92, 335, 23);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Destination:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(334, 138, 335, 23);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Departure time:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_2.setBounds(334, 183, 335, 23);
		getContentPane().add(lblNewLabel_1_2_2);
	
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Seat:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1_1.setBounds(334, 228, 335, 23);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		
		JLabel lblNewLabel = new JLabel("Add Bus Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(573, 21, 209, 30);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Bus No:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(30, 92, 105, 23);
		getContentPane().add(lblNewLabel_1);
		
		JCheckBox checkBox = new JCheckBox("New check box");
		checkBox.setBounds(527, 93, 142, 21);
		getContentPane().add(checkBox);
		
		JMenuItem menuItem = new JMenuItem("New menu item");
		menuItem.setBounds(487, 92, 182, 24);
		getContentPane().add(menuItem);
		
		JLabel label = new JLabel("New label");
		label.setBounds(544, 97, 125, 13);
		getContentPane().add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(704, 79, 555, 262);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				String busno = (String) model.getValueAt(table.getSelectedRow(),1);
				String movement = (String) model.getValueAt(table.getSelectedRow(),2);
				String source = (String) model.getValueAt(table.getSelectedRow(),3);
				String destination = (String) model.getValueAt(table.getSelectedRow(),4);
				java.util.Date date = (java.util.Date) model.getValueAt(table.getSelectedRow(),5);
				String time = (String) model.getValueAt(table.getSelectedRow(),6);
				String price = (String) model.getValueAt(table.getSelectedRow(),7);
				String seat = (String) model.getValueAt(table.getSelectedRow(),8);
			
				bus_noTF.setText(busno);
				moveCB.setSelectedItem(movement);
				bus_sourceTF.setText(source);
				bus_destTF.setText(destination);
				departDateDp.setDate(date);
				timeTF.setText(time);
				priceTF.setText(price);
				seatTF.setText(seat);
				
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				int id = (int) model.getValueAt(index, 0);	
			
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
							String DeleteQuery = "delete from bus_details where id = '"+id+"'";
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
				
				
				
	
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(802, 372, 105, 30);
		getContentPane().add(btnDelete);
		
		JButton btnResetList = new JButton("Reset");
		btnResetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery1 = "select * from bus_details";
					ResultSet rs1 = stat.executeQuery(selectQuery1);
					  table.setModel(DbUtils.resultSetToTableModel(rs1));
			           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				}
				catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnResetList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnResetList.setBounds(1084, 372, 105, 30);
		getContentPane().add(btnResetList);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String busnoD = bus_noTF.getText();
				String busmoveD = (String)moveCB.getSelectedItem();
				String bussourceD = bus_sourceTF.getText();
				String busdestD = bus_destTF.getText();
					java.util.Date departDateD = departDateDp.getDate();
					SimpleDateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String departDate = oDateFormat.format(departDateD);
				String departTimeD = timeTF.getText();
				String priceD = priceTF.getText();
				String seatD = seatTF.getText();
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int index = table.getSelectedRow();
				int id = (int) model.getValueAt(index, 0);
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_details where "
					 + "bus_no ='" + busnoD 
					 + "'and movement ='" + busmoveD 
					 + "'and bus_source ='" + bussourceD 
					 + "'and bus_dest ='" + busdestD 
					 + "'and depart_date= '" + departDate 
					 + "'and depart_time= '" + departTimeD 
					 + "'and price ='" + priceD 
					 + "'and total_seat ='" + seatD + "'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);

					if (rs.next() == true) {
						infoMessage("Already Bus Details is added", "Alert!!");
						clearFieldValue();
					} else {

						String updateQuery = "UPDATE bus_details SET "
								         + "bus_no ='" + busnoD 
								         + "' ,movement ='" + busmoveD 
										 + "' ,bus_source ='" + bussourceD 
										 + "' ,bus_dest ='" + busdestD 
										 + "' ,depart_date= '" + departDate 
										 + "' ,depart_time= '" + departTimeD 
										 + "' ,price ='" + priceD 
										 + "' ,total_seat ='" + seatD 
										 + "'where id ='"+id+"'";
						 System.out.println(updateQuery);
						stat.executeUpdate(updateQuery);
						infoMessage("Bus Details is successfully Update", "Welcome Bro!!");
						
						String selectQuery1 = "select * from bus_details";
						ResultSet rs1 = stat.executeQuery(selectQuery1);
						  table.setModel(DbUtils.resultSetToTableModel(rs1));
				           table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					
					
					}
					
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(943, 372, 105, 30);
		getContentPane().add(btnUpdate);
	
		
		
	}
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
	public void clearFieldValue()
	{
		bus_noTF.setText("");
		bus_sourceTF.setText("");
		bus_destTF.setText("");
	}
}
