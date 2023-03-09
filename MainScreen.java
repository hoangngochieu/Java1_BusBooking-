package bus.management;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1018, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUserLogin = new JButton("User Login");
		btnUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserLogin u1 = new UserLogin();
				u1.setLocationRelativeTo(null);
				u1.setVisible(true);
			}
		});
		btnUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUserLogin.setBounds(240, 105, 166, 30);
		contentPane.add(btnUserLogin);
		
		JLabel lblNewLabel = new JLabel("Bus Booking Application");
		lblNewLabel.setBounds(354, 10, 295, 55);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Admin Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin a1 = new AdminLogin();
				a1.setLocationRelativeTo(null);
				a1.setVisible(true);
			}
		});
		btnNewButton.setBounds(616, 105, 147, 30);
		contentPane.add(btnNewButton);
	}

}
