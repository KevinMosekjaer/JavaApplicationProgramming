package clientserver;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import observers.ConnectionObserver;

/**
 * Class associated with client
 *
 * @author Kevin Mosekjaer
 */
public class Client implements Runnable, ConnectionObserver { 

	/**
	 * socket
	 */
	private Socket socket;
	
	/**
	 * name and address
	 */
	private String name, address;
	
	/**
	 * port number
	 */
	private int port;
	
	/**
	 * is client running
	 */
	private boolean isRunning=false;
	
	/**
	 * Network object
	 */
	private Network cs;
	
	/**
	 * list of connection observers
	 */
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	/**
	 * Constructor starts client
	 * @param name name
	 * @param address address
	 * @param port port
	 */
	public Client(String name, String address, int port) {
		this.name = name;
		this.port = port;
		this.address = address;
		start();
	}

	/**
	 * Function for adding connection observers
	 * @param connectionObserver observer
	 */
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}
	
	/**
	 * Function for connected to server
	 */
	protected void connectionActive() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.connected(2));
	}
	
	/**
	 * Getter for network object
	 * @return network
	 */
	public Network getReceiveSend() {
		return this.cs;
	}

	/**
	 * Function to start client
	 */
	public void start() {
		if(!isRunning) {
			isRunning = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Function for disconnecting from socket
	 */
	public void disconnect() {
		try {
			isRunning = false;
			if (socket != null && !socket.isClosed()) {
				socket.close();
				System.out.println("Disconnect in Client disconnect");
				connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter for socket
	 * @return socket
	 */
	public Socket getSocket() {
		return this.socket;
	}

	/**
	 * Implemented run function for client thread
	 */
	@Override
	public void run() {
		try {
			isRunning = true;
			socket = new Socket(address, port);
			cs = new Network(socket);
			connectionActive();
		} catch (IOException e) {
			//connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(2));
			disconnect();
		} 
	}
	
	/**
	 * Implemented disconnected function from interface
	 * @param c c
	 */
	@Override
	public void disconnected(int c) {
		disconnect();
	}

}
