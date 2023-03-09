package bus.management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField adminuserTF;
	private JTextField adminpassTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 362);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblAdminLogin.setBounds(227, 51, 182, 30);
		contentPane.add(lblAdminLogin);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(102, 107, 135, 22);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(102, 137, 135, 22);
		contentPane.add(lblNewLabel_2);

		adminuserTF = new JTextField();
		adminuserTF.setColumns(10);
		adminuserTF.setBounds(206, 109, 180, 25);
		contentPane.add(adminuserTF);

		adminpassTF = new JTextField();
		adminpassTF.setColumns(10);
		adminpassTF.setBounds(206, 139, 180, 25);
		contentPane.add(adminpassTF);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = adminuserTF.getText();
				String password = adminpassTF.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String databaseURL = "jdbc:mysql://localhost:3306/busm?useUnicode=yes&characterEncoding=UTF-8";
					Connection con = DriverManager.getConnection(databaseURL, "root", "");
					Statement stat = con.createStatement();
					String selectQuery = "select * from admin_details where username ='" + username
							+ "' and password ='" + password + "'";
					System.out.println(selectQuery);
					ResultSet rs = stat.executeQuery(selectQuery);

					if (rs.next() == true) {
						//infoMessage("Welcome...", "Alert!!");
						dispose();
						AdminControlPanel acp = new AdminControlPanel();
						acp.setLocationRelativeTo(null);
						acp.setVisible(true);
					}
					else {
						infoMessage("User account or password incorrect"," Alert!!");
					}
					
				} catch (Exception e2) {
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(206, 184, 180, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("User Login");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserLogin u1 = new UserLogin();
				u1.setLocationRelativeTo(null);
				u1.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(429, 0, 135, 31);
		contentPane.add(btnNewButton_3);
	}
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
}
