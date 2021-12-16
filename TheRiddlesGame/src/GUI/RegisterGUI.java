package GUI;

import Model.Player;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import DatabaseLayer.DataBaseConnection;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This Graphical user interface launches the registation display for player to enter player's details.
 * The email and name entered by player should be unique.If either email or name is already registered it cannot be used again, since email is unique and name is kept unique to 
 * manage the scores, this helps the  players can view the scores in ScoreBoardGUI with ease 
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class RegisterGUI {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	Player player;
	private JPasswordField txtCpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
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
	public RegisterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(350, 100, 863, 613);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		JLabel Name = new JLabel("Name");
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setFont(new Font("Calibri", Font.PLAIN, 18));
		Name.setBounds(406, 162, 56, 38);
		frame.getContentPane().add(Name);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(406, 230, 56, 38);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(406, 304, 74, 35);
		frame.getContentPane().add(lblNewLabel_1);

		txtName = new JTextField();
		txtName.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtName.setBounds(588, 162, 221, 38);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtEmail.setBounds(588, 230, 221, 38);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtPassword.setBounds(588, 302, 221, 38);
		frame.getContentPane().add(txtPassword);
		frame.setVisible(true);// change

		JButton btnReg = new JButton("Register");
		btnReg.setBackground(SystemColor.textHighlight);
		btnReg.setForeground(SystemColor.text);
		btnReg.setVerticalAlignment(SwingConstants.BOTTOM);
		btnReg.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String name = txtName.getText();
					String email = txtEmail.getText();
					String password = String.valueOf(txtPassword.getPassword());
					String Cpassword = String.valueOf(txtCpassword.getPassword());

					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "Add A Name");
					}

					else if (password.equals("")) {
						JOptionPane.showMessageDialog(null, "Add A Password");
					} else if (email.equals("")) {
						JOptionPane.showMessageDialog(null, "Add Email");
					} else if (!password.equals(Cpassword))
					{
						JOptionPane.showMessageDialog(null, "Recheck your Password Again");
					} else {

						player = new Player(name, email, password);

						DataBaseConnection dbConnection = new DataBaseConnection();

						if (dbConnection.verifyUname(name)) {
							JOptionPane.showMessageDialog(null, "Name already Registered");

						} else if (dbConnection.verfyEmail(email)) {
							JOptionPane.showMessageDialog(null, "Email already Registered");
						} else {
							dbConnection.setDataRecords(name, email, password);
							JOptionPane.showMessageDialog(null, "Registeration Complete!!!");
							frame.dispose();
						}
					}

				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Error Check Again!!!");
				}

			}
		});
		btnReg.setBounds(457, 473, 99, 38);
		frame.getContentPane().add(btnReg);

		JButton btnReturn = new JButton("Cancel");
		btnReturn.setBackground(SystemColor.textHighlight);
		btnReturn.setForeground(SystemColor.text);
		btnReturn.setVerticalAlignment(SwingConstants.BOTTOM);
		btnReturn.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnReturn.setBounds(665, 473, 99, 38);
		frame.getContentPane().add(btnReturn);

		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(406, 380, 151, 32);
		frame.getContentPane().add(lblNewLabel_2);

		txtCpassword = new JPasswordField();
		txtCpassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtCpassword.setBounds(588, 377, 221, 38);
		frame.getContentPane().add(txtCpassword);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 355, 613);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(RegisterGUI.class.getResource("/img/Login_Image.png")));
		lblNewLabel_3.setBounds(67, 135, 215, 237);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sign Up");
		lblNewLabel_4.setForeground(new Color(105, 105, 105));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblNewLabel_4.setBounds(556, 62, 151, 44);
		frame.getContentPane().add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 0));
		panel_1.setBounds(830, 0, 33, 32);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("X");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		lblNewLabel_5.setForeground(SystemColor.window);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_5.setBounds(0, 0, 33, 32);
		panel_1.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 18));
	}
}
