package GUI;

import Model.Player;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.microsoft.azure.storage.table.TableServiceEntity;

import DatabaseLayer.DataBaseConnection;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This Graphical user interface launches the scoreboard of the game where each player's name and score is displayed if the player is registed and not played the score cell will show blank.
 * The score updated score details of the perticular player will be show in the scoreboard
 * @author Sivapalan Siranjiv
 *
 */
public class ScoreBoardGUI {

	private JFrame frame;
	private JTable table;

	DefaultTableModel model;
	Player player;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreBoardGUI window = new ScoreBoardGUI();
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
	public ScoreBoardGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(650, 250, 684, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Calibri", Font.PLAIN, 18));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(SystemColor.textHighlightText);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 138, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));

		Object[] columns = { "Player", "Score" };
		model = new DefaultTableModel();

		JScrollPane pane = new JScrollPane(table);
		table.setModel(model);
		table.setRowHeight(30);

		model.setColumnIdentifiers(columns);
		pane.setBounds(0, 0, 684, 336);
		frame.getContentPane().add(pane);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(0, 336, 684, 132);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(SystemColor.textHighlightText);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setBounds(273, 51, 107, 38);
		panel.add(btnNewButton);
		Object[] row = new Object[2];

		ArrayList Scorelist = new ArrayList();

		DataBaseConnection dbConnection = new DataBaseConnection();
		Scorelist = dbConnection.getScoreData();

		for (int i = 0; i < Scorelist.size(); i++) {
			row[0] = ((TableServiceEntity) Scorelist.get(i)).getPartitionKey();
			row[1] = ((Player) Scorelist.get(i)).getScore();

			model.addRow(row);

		}
		
		frame.setVisible(true);

	}
}
