package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

/**
 * This is the server class which has to run in order to start the server
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */
public class Server {

	public static void main(String[] args) {
		System.out.println("Attempting to start the Server...");
		try {
			GameService myHello = new GameService();
			Registry reg = LocateRegistry.createRegistry(1099);
			reg.rebind("GameService", myHello);
			JOptionPane.showMessageDialog(null, "Service started. Welcome to the Game Service!");
			System.out.println("Service started. Welcome to the Game Service!");

		} catch (RemoteException e) {
			System.out.println("An error occured: " + e.toString());
			JOptionPane.showMessageDialog(null, "An error occurred at server. Please check");
			e.printStackTrace();
		}
	}
}
