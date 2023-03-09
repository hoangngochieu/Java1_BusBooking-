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

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTF;
	private JTextField passwordTF;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblNewLabel.setBounds(225, 49, 182, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(94, 104, 135, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(94, 134, 135, 22);
		contentPane.add(lblNewLabel_2);
		
		usernameTF = new JTextField();
		usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameTF.setBounds(198, 101, 180, 25);
		contentPane.add(usernameTF);
		usernameTF.setColumns(10);
		
		passwordTF = new JTextField();
		passwordTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordTF.setBounds(198, 136, 180, 25);
		contentPane.add(passwordTF);
		passwordTF.setColumns(10);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTF.getText();
				String password = passwordTF.getText();
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
						dispose();
						UserControlPanel ucp = new UserControlPanel();
						ucp.setLocationRelativeTo(null);
						ucp.setVisible(true);
					}
					else {
						infoMessage("User account or password incorrect"," Alert!!");
					}
					
				} catch (Exception e2) {
					
				}	
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(198, 182, 180, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Create Account");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				NewUser nu = new NewUser();
				nu.setLocationRelativeTo(null);
				nu.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(198, 224, 180, 31);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Admin Login");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminLogin a1 = new AdminLogin();
				a1.setLocationRelativeTo(null);
				a1.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(401, 0, 163, 30);
		contentPane.add(btnNewButton_3);
		
	}
	
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
}
