package Model;

import com.microsoft.azure.storage.table.TableServiceEntity;

/**
 * This class represents the player detailed to be stored and used in azure storage and other GUIs
 * @author Sivapalan Siranjiv
 *
 */

public class Player extends TableServiceEntity {

	private String name;
	private String email;
	private String score;
	private String password;
	
	/**
	 * Player is constructed in order to work with azure storage
	 * @param name
	 * @param email
	 * @param score
	 * @param password
	 */
	
	public Player(String name, String email, String score, String password) {
		this.partitionKey = name;
		this.rowKey = email;
		this.email = email;
		this.score = score;
		this.password = password;
	}
	
	
	/**
	 * Player is constructed in order to work with azure storage
	 * @param name
	 * @param email
	 */
	public Player(String name, String email) {
		this.partitionKey = name;
		this.rowKey = email;

	}
	
	
	/**
	 * Player is constructed in order to work with azure storage
	 */
	public Player() {}
	
	/**
	 *Player is constructed in order to work with azure storage for registerGUI
	 * @param name
	 * @param email
	 * @param password
	 */
	public Player(String name, String email,String password) {
		super();
		this.name = name;
		this.email = email;

	}
	
	/**
	 * returns name of the player
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * temporarily stores the password to be accessed by azure storage
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * returns email of the player
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 *temporarily stores the password to be accessed by azure storage 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * returns the score of the player
	 * @return
	 */
	public String getScore() {
		return score;
	}
	
	/**
	 *temporarily stores the password to be accessed by azure storage
	 * @param score
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * returns the password of the player
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * temporarily stores the password to be accessed by azure storage
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
