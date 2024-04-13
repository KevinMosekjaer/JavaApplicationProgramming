package clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import observers.ConnectionObserver;

/**
 * Class associated with server
 *
 * @author Kevin Mosekjaer
 */
public class Server implements Runnable, ConnectionObserver { 

	/**
	 * server socket
	 */
	private ServerSocket server;
	
	/**
	 * socket
	 */
	private Socket socket;
	
	/**
	 * is server running
	 */
	private boolean isRunning = false;
	
	/**
	 * port number
	 */
	private int port;
	
	/**
	 * name for server/host user 
	 */
	private String name;
	
	/**
	 * Network object
	 */
	private Network cs;
	
	/**
	 * list of connection observers
	 */
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	/**
	 * Constructor starting server, initializing values
	 * @param name name 
	 * @param port port
	 */
	public Server(String name, int port) {
		this.name = name;
		this.port = port;
		start();
	}
	
	/**
	 * Function to add connection observers
	 * @param connectionObserver o
	 */
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}

	/**
	 * Function letting observers know that server has connected to client
	 */
	private void connectionActive() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.connected(1));
	}

	/**
	 * Setter for if server thread is running
	 * @param value value
	 */
	public void setIsRunning(boolean value) {
		this.isRunning = value;
	}

	/**
	 * Getter for if server thread is running
	 * @return value 
	 */
	public boolean getIsRunning() {
		return this.isRunning;
	}

	/**
	 * Function for starting server
	 */
	public void start() {
		if(!isRunning) {
			isRunning = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Function for closing server
	 */
	public void disconnect() {
		try {
			isRunning = false;
			if (socket != null && !socket.isClosed()) { // if true close socket, call disconnect function for observers
				socket.close();
				System.out.println("Disconnect in server disconnect");
				connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Getter for network communication object
	 * @return network object
	 */
	public Network getReceiveSend() {
		return this.cs;
	}

	/**
	 * Implemented run function for server thread
	 */
	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			while (isRunning) { 
				socket = server.accept();
				cs = new Network(socket);
				connectionActive();
			}
		} catch (IOException e) {
			//connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(1));
			disconnect();
			
		}
	}
	
	/**
	 * Implemented disconnect function from connection observer interface
	 * @param c c
	 */
	@Override
	public void disconnected(int c) {
		disconnect();
	}
	
}
