package bus.management;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.toedter.calendar.JDateChooser;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Label;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBooking extends JInternalFrame {

	private JTable table_1;
	public JFrame BookingFormFrame;
	public JPanel BookingFormPanel;
	public JLabel l1, l2, l3, l4, l5, l6, l7, l8, l11, l12, l13, l14, l15, l16, l17, l18, seatnoL, idnoL, fnameL,
			lnameL;
	private GridLayout gl;
	public JTextField t1, t2, t3, t4;
	private JComboBox<String> sourceCB, destinationCB;
	java.util.Date fromDate;
	JDateChooser from;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBooking frame = new AddBooking();
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
	public AddBooking() {
		
		setClosable(true);
		setResizable(true);
		setIconifiable(true);

		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setBounds(0, 0, 1310, 486);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Booking Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(186, 20, 214, 29);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Source:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(47, 54, 80, 24);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Destination:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(47, 126, 98, 24);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Date:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(295, 54, 67, 25);
		getContentPane().add(lblNewLabel_3);

		sourceCB = new JComboBox<String>();
		sourceCB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sourceCB.setBounds(148, 55, 110, 29);
		getContentPane().add(sourceCB);

		destinationCB = new JComboBox<String>();
		destinationCB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		destinationCB.setBounds(148, 127, 110, 29);
		getContentPane().add(destinationCB);

		JDateChooser from = new JDateChooser();
		from.setBounds(374, 55, 132, 29);
		getContentPane().add(from);

		Label label = new Label("Booking Details...........");
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		label.setBounds(543, 30, 289, 32);
		getContentPane().add(label);

		Label label_1 = new Label("Research Result............");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		label_1.setBounds(47, 186, 225, 40);
		getContentPane().add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 231, 459, 216);
		getContentPane().add(scrollPane);

		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = table_1.getSelectedRow();
				TableModel model = table_1.getModel();
				String bus_no = (String) model.getValueAt(index, 1);
				String movement = (String) model.getValueAt(index, 2);
				String bus_source = (String) model.getValueAt(index, 3);
				String bus_dest = (String) model.getValueAt(index, 4);
				String depart_date = model.getValueAt(index, 5).toString();
				String depart_time = (String) model.getValueAt(index, 6);
				String price = (String) model.getValueAt(index, 7);
				String avail_seat = (String) model.getValueAt(index, 8);

				BookingFormFrame = new JFrame();
				BookingFormPanel = new JPanel();
				gl = new GridLayout(0, 4);
				l1 = new JLabel("Bus No");
				l11 = new JLabel(bus_no);
				l2 = new JLabel("Movement");
				l12 = new JLabel(movement);
				l3 = new JLabel("Bus Source");
				l13 = new JLabel(bus_source);
				l4 = new JLabel("Bus Dest");
				l14 = new JLabel(bus_dest);
				l5 = new JLabel("Depart Date");
				l15 = new JLabel(depart_date);
				l6 = new JLabel("Depart Time");
				l16 = new JLabel(depart_time);
				l7 = new JLabel("Price/seat");
				l17 = new JLabel(price);
				l8 = new JLabel("Available Seat");
				l18 = new JLabel(avail_seat);
				seatnoL = new JLabel("Number of seats");
				idnoL = new JLabel("ID Seat");
				fnameL = new JLabel("First Name");
				lnameL = new JLabel("Last Name");
				t1 = new JTextField(20);
				
				t2 = new JTextField(20);
				t3 = new JTextField(20);
				t4 = new JTextField(20);
				

				BookingFormFrame.getContentPane().add(BookingFormPanel);
				BookingFormPanel.setLayout(gl);

				BookingFormPanel.add(l1);
				BookingFormPanel.add(l11);
				BookingFormPanel.add(l2);
				BookingFormPanel.add(l12);
				BookingFormPanel.add(l3);
				BookingFormPanel.add(l13);
				BookingFormPanel.add(l4);
				BookingFormPanel.add(l14);
				BookingFormPanel.add(l5);
				BookingFormPanel.add(l15);
				BookingFormPanel.add(l6);
				BookingFormPanel.add(l16);
				BookingFormPanel.add(l7);
				BookingFormPanel.add(l17);
				BookingFormPanel.add(l8);
				BookingFormPanel.add(l18);

				BookingFormPanel.add(seatnoL);
				BookingFormPanel.add(t1);
				BookingFormPanel.add(idnoL);
				BookingFormPanel.add(t2);
				BookingFormPanel.add(fnameL);
				BookingFormPanel.add(t3);
				BookingFormPanel.add(lnameL);
				BookingFormPanel.add(t4);

				int result = JOptionPane.showConfirmDialog(BookingFormFrame, BookingFormPanel, "Booking Form",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {

					
					String seatno = t1.getText();
					int paid = Integer.parseInt(seatno)*200;
					String idno = t2.getText();
					String first_tname = t3.getText();
					String last_name = t4.getText();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm";
						Connection con = DriverManager.getConnection(databaseURL, "root", "");
						Statement stat = con.createStatement();
						String selectQuery = "select * from bus_booking where bus_no ='" + bus_no
								+ "' and journey_date='" + depart_date+ "' and movement='" + movement + "' and id_seat='" + idno + "'";
						System.out.println(selectQuery);
						ResultSet rs = stat.executeQuery(selectQuery);
						// System.out.println(rs.next());
						if (rs.next())
							infoMessage("Already Booked", "ALert !!");
						else {
							String insertQuery = "insert into bus_booking values(null,'"
									+ depart_date + "','" + bus_no + "','" + movement+ "','" + seatno + "','" + idno + "','" + first_tname
									+ "','" + last_name +"','" +paid+ "')";
							System.out.println(insertQuery);
							int i = stat.executeUpdate(insertQuery);
							if (i == 1) {

								int remaining_seat = Integer.parseInt(avail_seat) - Integer.parseInt(seatno);

								String updateQuery = "update bus_details set total_seat='" + remaining_seat
										+ "' where bus_no='" + bus_no + "' and movement='" + movement
										+ "' and depart_date='" + depart_date + "'";
								int j = stat.executeUpdate(updateQuery);
								if (j == 1) {

									infoMessage("Booking Done", "Alert !!");

									String selectQuery1 = "select * from bus_booking ORDER BY bus_no";
											
									ResultSet rs1 = stat.executeQuery(selectQuery1);
									
									  table1.setModel(DbUtils.resultSetToTableModel(rs1));
							           table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
								
								}
							}

						}
					} catch (Exception e2) {
						System.out.println(e2);
					}

				}

			}
		});
		scrollPane.setViewportView(table_1);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(526, 0, 11, 447);
		getContentPane().add(verticalStrut);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select distinct(bus_source) from bus_details";
			// System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while (rs.next()) {
				sourceCB.addItem(rs.getString("bus_source"));
			}

		} catch (Exception e) {
		}

		// set database bus destination no in JcomboBox
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(databaseURL, "root", "");
			Statement stat = con.createStatement();
			String selectQuery = "select distinct(bus_dest) from bus_details";
			// System.out.println(selectQuery);
			ResultSet rs = stat.executeQuery(selectQuery);
			while (rs.next()) {
				destinationCB.addItem(rs.getString("bus_dest"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSearch.setBounds(374, 127, 132, 29);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String source = (String) sourceCB.getSelectedItem();
				String destination = (String) destinationCB.getSelectedItem();
				fromDate = from.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fromDateFormated = sdf.format(fromDate);

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from bus_details where bus_source='" + source + "' and bus_dest='"
							+ destination + "' and depart_date='" + fromDateFormated + "'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);
					

					  table_1.setModel(DbUtils.resultSetToTableModel(rs));
			           table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				

				} catch (ClassNotFoundException | SQLException | NullPointerException e2) {
					System.out.println(e2);

				}
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(536, 81, 702, 271);
		getContentPane().add(scrollPane_1);

		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane_1.setViewportView(table1);
		
		
		
		int index = table_1.getSelectedRow();
		TableModel model = table_1.getModel();
		
		
		
		JButton btnDeleteRow = new JButton("Delete Row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				DefaultTableModel model = (DefaultTableModel)table1.getModel();
				int index = table1.getSelectedRow();
				int booking_id = (int) model.getValueAt(index, 0);	
			
							if (table1.getSelectedRowCount()==1)
				{
					//	model.removeRow(table1.getSelectedRow());
						System.out.println(table1.getSelectedRow()+1);
					try 
						{
							Class.forName("com.mysql.cj.jdbc.Driver");
							String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
							Connection con = DriverManager.getConnection(databaseURL, "root", "");
							Statement stat = con.createStatement();
							String DeleteQuery = "delete from bus_booking where booking_id = '"+booking_id+"'";
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
					if (table1.getSelectedRowCount()==0)
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
		btnDeleteRow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeleteRow.setBounds(596, 386, 156, 29);
		getContentPane().add(btnDeleteRow);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
						Connection con = DriverManager.getConnection(databaseURL, "root", "");
						Statement stat = con.createStatement();
						String selectQuery1 = "select * from bus_booking ORDER BY bus_no";
						
						ResultSet rs1 = stat.executeQuery(selectQuery1);
						
						  table1.setModel(DbUtils.resultSetToTableModel(rs1));
				           table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					}
				catch (Exception e1) {
				}
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReset.setBounds(1023, 386, 156, 29);
		getContentPane().add(btnReset);
		
		
	

	}

	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
}
