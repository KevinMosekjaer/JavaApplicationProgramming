package observers;

/**
 * Observer interface for Client and Server connection
 * 
 * @author Kevin Mosekjaer
 */
public interface ConnectionObserver {
	
	/**
	 * Function for starting client connection
	 * @param name n
	 * @param address a
	 * @param port p
	 */
	default void clientConnect(String name, String address, int port) { }

	/**
	 * Function for starting server connection
	 * @param name n
	 * @param port p
	 */
	default void serverConnect(String name, int port) { }
	
	/**
	 * Function for client disconnecting
	 */
	default void clientDisconnect() { }

	/**
	 * Function for server disconnecting
	 */
	default void serverDisconnect() { }
		
	/**
	 * Function for connection between server and client
	 * @param c 1 for server, 2 for client
	 */
	default void connected(int c) { }

	/**
	 * Function for disconnect between server and client
	 * @param c 1 for server, 2 for client
	 */
	default void disconnected(int c) { }
	
}
