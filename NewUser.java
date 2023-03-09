package bus.management;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.AddResultImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewUser extends JFrame {

	private JPanel contentPane;
	private JTextField firstnameTF;
	private JLabel usernameLB;
	private JTextField usernameTF;
	private JLabel lastnameLB;
	private JTextField lastnameTF;
	private JLabel passwordLB;
	private JTextField passwordTF;
	private JLabel emailLB;
	private JTextField email_idTF;
	private JLabel weburlLB;
	private JTextField weburlTF;
	private JButton registerBtn;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	public NewUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New User Register");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		lblNewLabel.setBounds(381, 39, 290, 48);
		contentPane.add(lblNewLabel);

		JLabel firstnamelLB = new JLabel("First Name:");
		firstnamelLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstnamelLB.setBounds(107, 121, 103, 26);
		contentPane.add(firstnamelLB);

		firstnameTF = new JTextField();
		firstnameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstnameTF.setBounds(228, 118, 209, 29);
		contentPane.add(firstnameTF);
		firstnameTF.setColumns(20);

		usernameLB = new JLabel("UserName:");
		usernameLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		usernameLB.setBounds(107, 207, 103, 26);
		contentPane.add(usernameLB);

		usernameTF = new JTextField();
		usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameTF.setColumns(20);
		usernameTF.setBounds(228, 204, 209, 29);
		contentPane.add(usernameTF);

		lastnameLB = new JLabel("Last Name:");
		lastnameLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lastnameLB.setBounds(500, 121, 113, 26);
		contentPane.add(lastnameLB);

		lastnameTF = new JTextField();
		lastnameTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastnameTF.setColumns(20);
		lastnameTF.setBounds(623, 118, 209, 29);
		contentPane.add(lastnameTF);

		passwordLB = new JLabel("Password:");
		passwordLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordLB.setBounds(502, 207, 83, 26);
		contentPane.add(passwordLB);

		passwordTF = new JTextField();
		passwordTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordTF.setColumns(20);
		passwordTF.setBounds(623, 204, 209, 29);
		contentPane.add(passwordTF);

		emailLB = new JLabel("Email Id:");
		emailLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLB.setBounds(107, 292, 103, 29);
		contentPane.add(emailLB);

		email_idTF = new JTextField();
		email_idTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		email_idTF.setColumns(20);
		email_idTF.setBounds(228, 289, 209, 29);
		contentPane.add(email_idTF);

		weburlLB = new JLabel("Web URL:");
		weburlLB.setFont(new Font("Tahoma", Font.PLAIN, 18));
		weburlLB.setBounds(502, 292, 83, 26);
		contentPane.add(weburlLB);

		weburlTF = new JTextField();
		weburlTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		weburlTF.setColumns(20);
		weburlTF.setBounds(623, 289, 209, 29);
		contentPane.add(weburlTF);

		registerBtn = new JButton("Register");

		registerBtn.addActionListener(new ActionListener() {
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
						infoMessage("Account successfully created", "Welcome Bro!!");
						UserLogin ln = new UserLogin();
						dispose();
						ln.setLocationRelativeTo(null);
						ln.setVisible(true);
						
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});

		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		registerBtn.setBounds(343, 369, 319, 29);
		contentPane.add(registerBtn);

		btnNewButton_2 = new JButton("Already have account ? Sign In");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserLogin u = new UserLogin();
				u.setLocationRelativeTo(null);
				u.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(343, 426, 319, 29);
		contentPane.add(btnNewButton_2);

	}

//
// hàm thông báo đã nhập
	public void infoMessage(String message, String tittle) {
		JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

	}
}
