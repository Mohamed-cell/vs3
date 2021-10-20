import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connection;
	private JTextField textFieldEID;
	private JTextField textFieldFName;
	private JTextField textFieldLName;
	private JTextField textFieldUsername;
	private JTextField textFieldAge;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAge;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTextField textFieldSearch;
	private JComboBox comboBoxSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void reloadTable() {
		
		try {
			
			String query = "select EID, FirstName, LastName, Age from EmployeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs =  pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			textFieldEID.setText(null);
			textFieldFName.setText(null);
			textFieldLName.setText(null);
			textFieldUsername.setText(null);
			passwordField.setText(null);
			textFieldAge.setText(null);
			
			
			rs.close();
			pst.close();
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2);
		}
		
		
		
	}
	

	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadDB = new JButton("Load From Database");
		btnLoadDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String query = "select EID, FirstName, LastName, Age from EmployeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs =  pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
			}
		});
		btnLoadDB.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		btnLoadDB.setFocusable(false);
		btnLoadDB.setBounds(465, 44, 219, 29);
		contentPane.add(btnLoadDB);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(381, 138, 394, 302);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					int row = table.getSelectedRow();
					String empId = table.getModel().getValueAt(row, 0).toString();
					String query = "select * from EmployeeInfo where EID='" + empId + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs =  pst.executeQuery();
					
					while(rs.next()) {
						
						textFieldEID.setText(rs.getString("EID"));
						textFieldFName.setText(rs.getString("FirstName"));
						textFieldLName.setText(rs.getString("LastName"));
						textFieldUsername.setText(rs.getString("Username"));
						passwordField.setText(rs.getString("Password"));
						textFieldAge.setText(rs.getString("Age"));
						
					}
					
					pst.close();
					rs.close();
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
					
				}
				
			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		textFieldEID = new JTextField();
		textFieldEID.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldEID.setBounds(172, 95, 118, 29);
		contentPane.add(textFieldEID);
		textFieldEID.setColumns(10);
		
		textFieldFName = new JTextField();
		textFieldFName.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldFName.setColumns(10);
		textFieldFName.setBounds(172, 138, 118, 29);
		contentPane.add(textFieldFName);
		
		textFieldLName = new JTextField();
		textFieldLName.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldLName.setColumns(10);
		textFieldLName.setBounds(172, 178, 118, 29);
		contentPane.add(textFieldLName);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(172, 221, 118, 29);
		contentPane.add(textFieldUsername);
		
		textFieldAge = new JTextField();
		textFieldAge.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(172, 303, 118, 29);
		contentPane.add(textFieldAge);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 261, 118, 29);
		contentPane.add(passwordField);
		
		lblNewLabel = new JLabel("EID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(56, 104, 82, 20);
		contentPane.add(lblNewLabel);
		
		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblFirstName.setBounds(51, 147, 111, 20);
		contentPane.add(lblFirstName);
		
		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblLastName.setBounds(51, 187, 111, 20);
		contentPane.add(lblLastName);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblUsername.setBounds(51, 230, 111, 20);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblPassword.setBounds(51, 268, 111, 20);
		contentPane.add(lblPassword);
		
		lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblAge.setBounds(51, 312, 111, 20);
		contentPane.add(lblAge);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
						String query = "insert into EmployeeInfo (EID, FirstName, LastName, Username, Password, Age) values (?,?,?,?,?,?)";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, textFieldEID.getText());
						pst.setString(2, textFieldFName.getText());
						pst.setString(3, textFieldLName.getText());
						pst.setString(4, textFieldUsername.getText());
						pst.setString(5, passwordField.getText());
						pst.setString(6, textFieldAge.getText());
						
						
						pst.execute();
						JOptionPane.showMessageDialog(null, "Data Saved!");
						pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
				reloadTable();
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnAdd.setBounds(186, 357, 104, 29);
		contentPane.add(btnAdd);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
						
						String query = "update EmployeeInfo set EID='" + textFieldEID.getText()+ "', FirstName='" + textFieldFName.getText() + 
								"', LastName='" + textFieldLName.getText() + "', Username='" +textFieldUsername.getText() + 
								"', Password='" + passwordField.getText() + "', Age='" + textFieldAge.getText()+ "' where EID='" + textFieldEID.getText() + "'";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "Data Updated!");
						pst.close();
				
				
				} catch (Exception e2) {
					// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2);
				}
				
				reloadTable();
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnEdit.setBounds(186, 397, 104, 29);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Delete", JOptionPane.YES_NO_OPTION);
				if(action == 0) {
					
					try {
						
							String query = "delete from EmployeeInfo where EID='" + textFieldEID.getText() + "'";
							PreparedStatement pst = connection.prepareStatement(query);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Data Removed!");
							pst.close();
				
				
					} catch (Exception e2) {
						// TODO: handle exception
							JOptionPane.showMessageDialog(null, e2);
					}
					
					reloadTable();
						
					}
				
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnDelete.setBounds(189, 433, 101, 29);
		contentPane.add(btnDelete);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"EID", "FirstName", "LastName", "Age"}));
		comboBoxSearch.setBounds(431, 98, 101, 29);
		contentPane.add(comboBoxSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					
					String selected = comboBoxSearch.getSelectedItem().toString();
					
					String query = "select EID, FirstName, LastName, Age from EmployeeInfo where " + selected + "=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldSearch.getText());
					ResultSet rs =  pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
				
				
				
			}
			
		});
		textFieldSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldSearch.setColumns(10);
		textFieldSearch.setBounds(562, 98, 118, 29);
		contentPane.add(textFieldSearch);
	}
}
