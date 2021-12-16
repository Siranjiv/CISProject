package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.Naming;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DatabaseLayer.DataBaseConnection;
import Engine.GameEngine;
import rmiInterface.RemoteInterface;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**This Graphical User Interface Launches the game for the player to play
 *By RMI service the email of the current player is retrived from LoginGUI to assign the score of the player
 * 
 * @author Siranjiv Sivapalan
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class GameGUI extends JFrame {

	private JFrame frame;
	private JTextField txtValue;
	private JLabel lblNewLabel;
	private JLabel lblValue;
	private JLabel lblQuestion;
	private ImageIcon ii;
	private RemoteInterface myService;

	GameEngine myGame = null;
	URL currentGame = null;
	private int counter = 0;
	private Boolean isIt = false;
	private int minute = 0;
	private int second = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI window = new GameGUI();
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
	public GameGUI() {
		initialize(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String player) {
		frame = new JFrame();
		frame.setBounds(450, 100, 817, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		myGame = new GameEngine(player);
		currentGame = myGame.nextGame();

		ii = new ImageIcon(currentGame);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		btnClose.setBounds(1063, 635, 97, 25);
		frame.getContentPane().add(btnClose);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 0, 817, 96);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblTime = new JLabel("Time :");
		lblTime.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Calibri", Font.BOLD, 19));
		lblTime.setBounds(22, 40, 59, 25);
		panel.add(lblTime);
		lblTime.setForeground(SystemColor.textHighlightText);

		JLabel lblSecond = new JLabel("00");
		lblSecond.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecond.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSecond.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSecond.setFont(new Font("Calibri", Font.BOLD, 19));
		lblSecond.setBounds(109, 35, 33, 30);
		panel.add(lblSecond);
		lblSecond.setForeground(SystemColor.textHighlightText);

		JLabel lblMinute = new JLabel("00");
		lblMinute.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinute.setFont(new Font("Calibri", Font.BOLD, 19));
		lblMinute.setBounds(80, 35, 33, 30);
		panel.add(lblMinute);
		lblMinute.setForeground(SystemColor.textHighlightText);

		lblNewLabel = new JLabel("Score :");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel.setBounds(640, 32, 56, 33);
		panel.add(lblNewLabel);

		lblValue = new JLabel("00");
		lblValue.setForeground(SystemColor.textHighlightText);
		lblValue.setVerticalAlignment(SwingConstants.BOTTOM);
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setFont(new Font("Calibri", Font.BOLD, 19));
		lblValue.setBounds(699, 32, 47, 33);
		panel.add(lblValue);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.windowText);
		panel_2.setBounds(784, 0, 33, 33);
		panel.add(panel_2);
		panel_2.setLayout(null);
		

		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		lblNewLabel_2.setForeground(SystemColor.textHighlightText);
		lblNewLabel_2.setBounds(0, 0, 32, 33);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 18));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.text);
		panel_1.setBounds(0, 98, 817, 584);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblQuestion = new JLabel("");
		lblQuestion.setBounds(24, 48, 337, 425);
		panel_1.add(lblQuestion);
		lblQuestion.setIcon(ii);

		txtValue = new JTextField();
		txtValue.setBounds(459, 343, 240, 34);
		panel_1.add(txtValue);
		txtValue.setColumns(10);

		JButton btnEnter = new JButton("Enter");
		btnEnter.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEnter.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnEnter.setForeground(SystemColor.textHighlightText);
		btnEnter.setBackground(SystemColor.windowText);
		btnEnter.setBounds(524, 409, 114, 39);
		panel_1.add(btnEnter);

		JLabel lblNewLabel_1 = new JLabel("Guess the Number?");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 26));
		lblNewLabel_1.setBounds(470, 208, 229, 78);
		panel_1.add(lblNewLabel_1);

		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int solution = Integer.parseInt(txtValue.getText());
					boolean correct = myGame.checkSolution(currentGame, solution);
					int score = myGame.getScore();
					String email;
					String uname;
					myService = (RemoteInterface) Naming.lookup("rmi://localhost/GameService");
					DataBaseConnection dbConnection = new DataBaseConnection();
					email = myService.returnEmail();
					uname = dbConnection.retriveKeys(email);

					if (correct) {
						currentGame = myGame.nextGame();
						ii = new ImageIcon(currentGame);
						lblQuestion.setIcon(ii);

						JOptionPane.showMessageDialog(null, "Well Done!! You Got it Right!");
						lblValue.setText(String.valueOf(score));
						dbConnection.ModifyScore(uname, email, Integer.toString(score));

					} else { 
						if (score == 0) {
							JOptionPane.showMessageDialog(null, "You Got It Wrong! Try again!!!");
						} else {

							lblValue.setText(String.valueOf(score));
							dbConnection.ModifyScore(uname, email, Integer.toString(score));
							JOptionPane.showMessageDialog(null, "You Got It Wrong! Try again!!!");
						}
					}
					if (myGame.getcount() == 6) {

						frame.dispose();
					}
				} catch (Exception a) {
					JOptionPane.showMessageDialog(null, "Error Check Again!!!");
				}
			}

		});

		/**
		 * Referened and modified from youtube tutorial to put timer for the game
		 * finimies, 2016. Java Countdown Timer + source code!! simple example (NetBeans 8.1). YouTube. Available at: https://www.youtube.com/watch?v=Euexl32lB8w [Accessed December 1, 2021]. 
		 */
		Timer timer = new Timer(); 
		counter = 210; 

		TimerTask task = new TimerTask() {
			public void run() {
				second = counter % 60;
				minute = counter / 60;

				lblMinute.setText("0" + Integer.toString(minute)); // the timer lable to counter.

				lblSecond.setText(Integer.toString(second));
				counter--;
				if (counter == -1) {
					timer.cancel();
					frame.dispose();
				} else if (isIt) {
					timer.cancel();
					isIt = false;
				}
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000); 

		frame.setVisible(true);

	}
}
