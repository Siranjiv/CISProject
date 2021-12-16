package Engine;

import java.net.URL;

/**This class represents to main controller for the game
 * 
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class GameEngine {

	String Player = null;
	
	/**
	 * Each players have their own engine when playing the game
	 * @param player
	 */

	public GameEngine(String player) {

		Player = player;
	}

	int counter = 0;
	int score = 0;
	
	/**
	 * retrives the game level based on the answer given by player
	 * @return
	 */

	public URL nextGame() {
		if (counter == 0) {
			counter = counter + 1;
			return GameEngine.class.getResource("/Level_1_Ans_32.jpg");

		} else if (counter == 1) {
			counter = counter + 1;
			return GameEngine.class.getResource("/Level_2_Ans_6.jpg");

		} else if (counter == 2) {
			counter = counter + 1;
			return GameEngine.class.getResource("/Level_3_Ans_25.jpg");

		} else if (counter == 3) {
			counter = counter + 1;
			return GameEngine.class.getResource("/Level_4_Ans_14.jpg");
		} else {
			counter = counter + 1;
			return GameEngine.class.getResource("/Level_5_Ans_5.jpg");
		}

	}
	
	/**
	 * Checks if the parameter answer is the solution of the game URL.if so score is increased by 10 else reduced by 1 
	 * @param game
	 * @param answer
	 * @return
	 */

	public boolean checkSolution(URL game, int answer) {
		if (counter == 1) {

			if (answer == 32) {
				score = score + 10;
				return true;
			} else {
				return false;
			}

		} else if (counter == 2) {
			if (answer == 6) {
				score = score + 10;
				return true;
			} else {
				score = score - 1;
				return false;
			}

		} else if (counter == 3) {
			if (answer == 25) {
				score = score + 10;
				return true;
			} else {
				score = score - 1;
				return false;
			}

		} else if (counter == 4) {
			if (answer == 14) {
				score = score + 10;
				return true;
			} else {
				score = score - 1;
				return false;
			}

		} else if (counter == 5) {
			if (answer == 5) {
				score = score + 10;
				return true;
			} else {
				score = score - 1;
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * Retrives the score.
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Retrives the counter to check which question is answered
	 * @return
	 */
	public int getcount() {

		return counter;
	}

}
