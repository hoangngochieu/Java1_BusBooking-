package bus.management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class AdminControlPanel extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminControlPanel frame = new AdminControlPanel();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminControlPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1339, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel lblNewLabel = new JLabel("Welcome: Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(1171, 10, 144, 22);
		contentPane.add(lblNewLabel);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(5, 34, 1310, 468);
		contentPane.add(desktopPane);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu managebus = new JMenu("Manage Bus");
		JMenu employee = new JMenu("Employee");
		employee.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JMenu mnManage = new JMenu("Bus");
		mnManage.setFont(new Font("Dialog", Font.PLAIN, 14));
		menubar.add(mnManage);
		
		JMenuItem mntmAddBusDetails = new JMenuItem("Add/Edit Bus Details");
		mntmAddBusDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBusDetails abd = new AddBusDetails();
				desktopPane.add(abd);
				abd.show();
			}
		});
		mntmAddBusDetails.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnManage.add(mntmAddBusDetails);
		
		JMenuItem mntmAllBusDetails = new JMenuItem("All Bus Details");
		mntmAllBusDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllBusDetails all = new AllBusDetails();
				desktopPane.add(all);
				all.show();
				
			}
		});
		mntmAllBusDetails.setFont(new Font("Dialog", Font.PLAIN, 14));
		mnManage.add(mntmAllBusDetails);
		menubar.add(employee);
		
		JMenuItem add_1 = new JMenuItem("Add/Edit Employee");
		add_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee emp = new AddEmployee();
				desktopPane.add(emp);
				emp.show();
			}
		});
		add_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		employee.add(add_1);
		JMenu manageadmin = new JMenu("Booking");
		manageadmin.setFont(new Font("Dialog", Font.PLAIN, 14));
		menubar.add(manageadmin);
		
		JMenuItem mntmAd = new JMenuItem("Add/Edit Booking Details");
		mntmAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBooking all = new AddBooking();
				desktopPane.add(all);
				all.show();
				
			}
		});
		mntmAd.setFont(new Font("Dialog", Font.PLAIN, 14));
		manageadmin.add(mntmAd);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("All Booking Details");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				BookingDetails u = new BookingDetails();
				desktopPane.add(u);
				u.show();
			}
		});
		mntmNewMenuItem.setFont(new Font("Dialog", Font.PLAIN, 14));
		manageadmin.add(mntmNewMenuItem);
		
		JMenu manageadmin_1 = new JMenu("Admin");
		manageadmin_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		menubar.add(manageadmin_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manager User");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageUser u = new ManageUser();
				desktopPane.add(u);
				u.show();
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		manageadmin_1.add(mntmNewMenuItem_1);
		
		JMenu manageadmin_1_2 = new JMenu("Revenue");
		manageadmin_1_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		menubar.add(manageadmin_1_2);
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Detailed Revenue");
		mntmNewMenuItem_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DetailedRevenue u = new DetailedRevenue();
				desktopPane.add(u);
				u.show();
			}
		});
		mntmNewMenuItem_1_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		manageadmin_1_2.add(mntmNewMenuItem_1_2);
		
		JMenu manageadmin_1_1 = new JMenu("Exit");
		manageadmin_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		menubar.add(manageadmin_1_1);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("LogOut");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				AdminLogin u = new AdminLogin();
				u.setLocationRelativeTo(null);
				u.setVisible(true);
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		manageadmin_1_1.add(mntmNewMenuItem_1_1);
	
	}
}
