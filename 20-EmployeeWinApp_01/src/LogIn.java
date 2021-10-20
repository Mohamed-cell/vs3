import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class LogIn {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
		
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textUsername = new JTextField();
		textUsername.setBounds(270, 57, 143, 32);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(162, 66, 98, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(162, 131, 98, 23);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(270, 128, 143, 32);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon("F:\\Users\\Salar\\eclipse-workspace-swing-2021\\20-EmployeeWinApp_01\\img\\tap.png"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String query = "select * from EmployeeInfo where Username=? and Password=?";
				try {
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textUsername.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					
					int counter = 0;
					while (rs.next()) {
						counter++;
					}
					
					if (counter == 0) {
						
						JOptionPane.showMessageDialog(null, "Wrong Username and Password");
						
					} else if(counter == 1){
						
						JOptionPane.showMessageDialog(null, "Username and Password are Correct!");
						frame.dispose();
						EmployeeInfo emp = new EmployeeInfo();
						emp.setVisible(true);

					}else if(counter > 1) {
						
						JOptionPane.showMessageDialog(null, "Deplicated Username and Password...");
					}
					
					pst.close();
					rs.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogin.setBounds(195, 187, 117, 32);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Please, Enter Username and Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(51, 21, 373, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("F:\\Users\\Salar\\eclipse-workspace-swing-2021\\20-EmployeeWinApp_01\\img\\login.png"));
		lblNewLabel_1.setBounds(0, 66, 155, 141);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
