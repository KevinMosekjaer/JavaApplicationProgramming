package observers;

/**
 * Interface for real time connection between server and client
 * 
 * @author Kevin Mosekjaer
 */
public interface ConnectionListener {

	/**
	 * Function for connection between server and client
	 * 
	 * @param c 1 for server, 2 for client
	 */
	public void connected(int c);

	/**
	 * Function for disconnect between server and client
	 * 
	 * @param c 1 for server, 2 for client
	 */
	public void disconnected(int c);
}
