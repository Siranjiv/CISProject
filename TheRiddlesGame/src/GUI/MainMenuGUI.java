package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rmiInterface.RemoteInterface;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

/**
 * This Graphical user interface is the MainMenu of the Game where the player can navigate other GUIs and services
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class MainMenuGUI extends JFrame {

	/**
	 * 
	 */
	private JFrame frame = new JFrame();
	private RemoteInterface myService;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuGUI window = new MainMenuGUI();
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

	String mySessionCookie = "not set";

	public MainMenuGUI() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(550, 150, 559, 566);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		try {
			String inital = null;
			myService = (RemoteInterface) Naming.lookup("rmi://localhost/GameService");// 1
			inital = myService.logCookie();
			System.out.println("initiatal" + inital);
		} catch (Exception e) {
			System.out.println("A problem occured: " + e.toString());
			JOptionPane.showMessageDialog(null, "An error occurred at server. Please check");
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainMenuGUI.class.getResource("/img/MainGUI.png")));
		lblNewLabel.setBounds(0, 0, 545, 336);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 333, 545, 196);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnViewScore = new JButton("View Score");
		btnViewScore.setForeground(new Color(255, 255, 255));
		btnViewScore.setBackground(new Color(51, 153, 102));
		btnViewScore.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnViewScore.setBounds(38, 122, 131, 31);
		panel.add(btnViewScore);

		JButton btnRead = new JButton("Instructions");
		btnRead.setForeground(new Color(255, 255, 255));
		btnRead.setBackground(new Color(51, 153, 102));
		btnRead.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnRead.setBounds(213, 122, 131, 31);
		panel.add(btnRead);

		JButton btnRegister = new JButton("Register");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(51, 153, 102));
		btnRegister.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnRegister.setBounds(391, 122, 131, 31);
		panel.add(btnRegister);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setForeground(new Color(255, 255, 255));
		btnSignOut.setBackground(new Color(51, 153, 102));
		btnSignOut.setBounds(391, 49, 131, 31);
		panel.add(btnSignOut);
		btnSignOut.setFont(new Font("Calibri", Font.PLAIN, 18));

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setForeground(new Color(255, 255, 255));
		btnStartGame.setBackground(new Color(51, 153, 102));
		btnStartGame.setBounds(213, 49, 131, 31);
		panel.add(btnStartGame);
		btnStartGame.setFont(new Font("Calibri", Font.PLAIN, 18));

		JButton btnSignin = new JButton("Sign In");
		btnSignin.setForeground(new Color(255, 255, 255));
		btnSignin.setBounds(38, 49, 131, 31);
		panel.add(btnSignin);
		btnSignin.setBackground(new Color(51, 153, 102));
		btnSignin.setFont(new Font("Calibri", Font.PLAIN, 18));
		
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();

				MainMenuGUI.this.dispose();
				String result = null;
				try {

					result = myService.logCookie();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Result: " + result);

			}
		});
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (myService.getGameAccess() == true) {
						GameGUI game = new GameGUI();
					} else {
						JOptionPane.showMessageDialog(null, "Please Login to play the Game !!!");

					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					System.out.println("The logout error");
					e1.printStackTrace();
				}


			}
		});
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					System.out.println(myService.logCookie());
					String result = myService.logout(mySessionCookie);

					JOptionPane.showMessageDialog(null, "You have Sign Out");
					System.out.println("Logout: " + result);
					System.out.println(myService.logCookie());
				} catch (Exception ex) {
					System.out.println("A problem occured: " + ex.toString());
					ex.printStackTrace();
				}

			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();

			}
		});
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInstructionGUI Instruction = new GameInstructionGUI();
			}
		});
		btnViewScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreBoardGUI register = new ScoreBoardGUI();

			}
		});
	}
}
