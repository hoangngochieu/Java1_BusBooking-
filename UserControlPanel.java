package bus.management;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserControlPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserControlPanel frame = new UserControlPanel();
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
	public UserControlPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1334, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(5, 34, 1310, 486);
		contentPane.add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Welcome User");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(1169, 10, 141, 28);
		contentPane.add(lblNewLabel);
		
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
	
		JMenu bustime = new JMenu("Booking");
		bustime.setFont(new Font("Dialog", Font.PLAIN, 14));
		JMenu busfare = new JMenu("Exit");
		busfare.setFont(new Font("Dialog", Font.PLAIN, 14));
	
	
		
		menubar.add(bustime);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Booking");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddBooking abd = new AddBooking();
				desktopPane.add(abd);
				abd.show();
			}
			
		});
		bustime.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("My Booking");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookingDetails abd = new BookingDetails();
				desktopPane.add(abd);
				abd.show();
			}
		});
		bustime.add(mntmNewMenuItem_1);
		menubar.add(busfare);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("LogOut");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserLogin u = new UserLogin();
				u.setLocationRelativeTo(null);
				u.setVisible(true);
			}
		});
		busfare.add(mntmNewMenuItem_2);
	
	}
}
