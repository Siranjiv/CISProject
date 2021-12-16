package rmiInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**RMI Interface which has abstact method to exchange data between the client and server
 * Interface used
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public interface RemoteInterface extends Remote {

	/**
	 * If login credentials are correctly entered this method generates cookie(which is dynamic) to access the System 
	 * @param log
	 * @throws RemoteException
	 */
	public void login(boolean log) throws RemoteException;

	/**
	 * method used to return the generated cookie to GUI
	 * @return
	 * @throws RemoteException
	 */
	public String logCookie() throws RemoteException;

	/**
	 * As the user logged to the system this method verifies the cookie and lauches the game
	 * @return
	 * @throws RemoteException
	 */
	public boolean getGameAccess() throws RemoteException;
	
	
/**
 * Logout from the system by changing the cookie to static
 * @param cookie
 * @return
 * @throws RemoteException
 */
	public String logout(String cookie) throws RemoteException;

	/**
	 * Retrive the Email from LoginGUI
	 * @param email
	 * @throws RemoteException
	 */
	public void collectEmail(String email) throws RemoteException;
	
	
/**
 * return the Email to GameGUI
 * @return
 * @throws RemoteException
 */
	public String returnEmail() throws RemoteException;

}
