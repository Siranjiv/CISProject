package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DatabaseLayer.DataBaseConnection;
import rmiInterface.RemoteInterface;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

/**
 * This Graphical user interface launches the login display for player to enter the player's credentials to gain access to play the game.
 * The azure table api is used for authentication of the player
 * The RMI service is used to collect the player's email to assign the score of the player after the player played the game
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class LoginGUI extends JFrame {

	private JFrame frame;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private RemoteInterface myService; // testing

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
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
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(450, 100, 790, 561);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 389, 561);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(LoginGUI.class.getResource("/img/Login_Image.png")));
		lblNewLabel_2.setBounds(90, 136, 200, 200);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(387, 0, 403, 561);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtName = new JTextField();
		txtName.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtName.setBounds(179, 248, 177, 34);
		panel_1.add(txtName);
		txtName.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtPassword.setBounds(179, 306, 177, 34);
		panel_1.add(txtPassword);

		JLabel lblNewLabel = new JLabel("User Name(Email)");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 249, 159, 27);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 310, 159, 25);
		panel_1.add(lblNewLabel_1);

		JButton btnReturn = new JButton("Cancel");
		btnReturn.setForeground(SystemColor.text);
		btnReturn.setBackground(SystemColor.textHighlight);
		btnReturn.setVerticalAlignment(SwingConstants.BOTTOM);
		btnReturn.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnReturn.setBounds(230, 416, 109, 40);
		panel_1.add(btnReturn);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(SystemColor.text);
		btnLogin.setBackground(SystemColor.textHighlight);
		btnLogin.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 18));

		btnLogin.setBounds(60, 416, 109, 40);
		panel_1.add(btnLogin);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0));
		panel_2.setBounds(358, 0, 45, 34);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		lblNewLabel_3.setForeground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 0, 45, 40);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(105, 105, 105));
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 36));
		lblNewLabel_4.setBounds(128, 83, 154, 64);
		panel_1.add(lblNewLabel_4);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtName.getText();
				String password = String.valueOf(txtPassword.getPassword());

				DataBaseConnection dbConnection = new DataBaseConnection();
				dbConnection.Login(email, password);

				if (email.equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Username");
				}

				else if (password.equals("")) {

					JOptionPane.showMessageDialog(null, "Enter Password");
				} else {
					if (dbConnection.Login(email, password)) {
						JOptionPane.showMessageDialog(null, "Login Sucessful");
						try {
							myService = (RemoteInterface) Naming.lookup("rmi://localhost/GameService");// 1
							myService.collectEmail(email);
							myService.login(true);
							frame.dispose();

						} catch (Exception a) {
							System.out.println("A problem occured: " + a.toString());
							a.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Error try Again!!!");
					}

				}

			}
		});
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});

		frame.setVisible(true);
	}
}
