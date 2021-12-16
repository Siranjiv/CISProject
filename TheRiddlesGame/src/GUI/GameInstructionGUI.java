package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Color;


/**
 * This Graphical user interface lauches the instuction of the game for the player
 * read and understand about the game
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class GameInstructionGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameInstructionGUI window = new GameInstructionGUI();
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
	public GameInstructionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(650, 200, 744, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Calibri", Font.PLAIN, 13));
		textPane.setBounds(0, 0, 744, 428);
		frame.getContentPane().add(textPane);
		textPane.setEditable(false);

		textPane.setText("\t\t\t!!!Welcome to MathRiddles!!!" + "\n\n" + "------To Start the Game--------" + "\n"
				+ "*If you are a new player you must Register using the \"Register\" button first*" + "\n\n"
				+ "*In order to play the game you must login using the \"Login\" button then press the \"Start Game\" button\r\n"
				+ "to lauch the game.*" + "\n\n" + "*After playing the game you can Logout using the \"Logout\" Button*"
				+ "\n\n" + "---------Check Scores--------\r\n"
				+ "*You can check your scores using the \"ScoreBoard\" button*\r\n" + "\n"
				+ "--------How to play the Game--------\r\n" + "*You will be given Riddle questions to solve*\r\n"
				+ "*You have to enter your answer in the textbox and Click \"Enter\"*"
				+ "\n\n----------Game Rules----------\r\n" 
				+"*The time duration will be 4 minutes and 30 seconds* " + "\n"
				+ "*If you get the correct answer you get 10 points*\r\n"
				+ "*For each incorrect answers your points will be deducted by one*" + "\n\n"
				+ "\t\t\t!!!!Have Fun Playing!!!!"

		);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 428, 744, 111);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
				JButton btnClose = new JButton("Close");
				btnClose.setForeground(SystemColor.textHighlightText);
				btnClose.setBackground(new Color(30, 144, 255));
				btnClose.setVerticalAlignment(SwingConstants.BOTTOM);
				btnClose.setFont(new Font("Calibri", Font.PLAIN, 20));
				btnClose.setBounds(297, 50, 119, 39);
				panel.add(btnClose);
				/**
				 * Button to close the frame
				 */
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
		frame.setVisible(true);
	}

}
