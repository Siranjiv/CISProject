package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmiInterface.RemoteInterface;

/**
 * This service is used to verify if the player is logged in the system and for functions with the login gui to retrive current player details
 * 
 * As for verification of the current player the dynamic and static variables which represent the cookies will be static initially
 * when the player is logged, then the "sessionCookie" variable changes which result being dynamic that makes the player having access to the game
 * when the player logout the "sessionCookie" becomes static again being assign by the same variable as "currentCookie"
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class GameService extends UnicastRemoteObject implements RemoteInterface {


	private static final long serialVersionUID = 1L;
	private String sessionCookie = "123";
	private String currentCookie = "123";
	private String Email = null;
	private String username = null;


	public GameService() throws RemoteException {

	}

	/**
	 * Implementation of remote interface method
	 */
	public boolean getGameAccess() throws RemoteException {
		if (sessionCookie.equals(currentCookie)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Implementation of remote interface method
	 */
	public void collectEmail(String email) throws RemoteException
	{
		Email = email;

	}

	/**
	 * Implementation of remote interface method
	 */
	public String returnEmail() throws RemoteException {
		return Email;
	}

	/**
	 * Implementation of remote interface method
	 */
	public void login(boolean log) throws RemoteException {

		if (log = true) {
			sessionCookie = "123 working" + Math.random();

		} else {
			sessionCookie = "false and no";
		}

	}

	/**
	 * Implementation of remote interface method
	 */
	public String logCookie() throws RemoteException {

		return sessionCookie;
	}

	/**
	 * Implementation of remote interface method
	 */
	public String logout(String cookie) throws RemoteException {
		sessionCookie = "abc";
		currentCookie = "abc";
		return "logout successful";
	}

}
