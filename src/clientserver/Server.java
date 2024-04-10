package clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import observers.ConnectionListener;
import observers.ConnectionObserver;

/**
 * Class associated with server
 *
 * @author Kevin Mosekjaer
 */
public class Server implements Runnable { //, ConnectionListener {


	private ServerSocket server;
	private Socket socket;
	private boolean isRunning = false;
	private int port;
	private String name;
	private Network cs;
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	public Server(String name, int port) {
		this.name = name;
		this.port = port;
		start();
	}
	
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}

	protected void connectionActive() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.connected(1));
	}

	public void setIsRunning(boolean value) {
		this.isRunning = value;
	}


	public boolean getIsRunning() {
		return this.isRunning;
	}

	public void start() {
		if(!isRunning) {
			isRunning = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	public void disconnect() {
		try {
			isRunning = false;
			if (socket != null && !socket.isClosed()) {
				socket.close();
				System.out.println("Socket closed in server");
			}
			if (server != null && !server.isClosed()) {
				server.close();
				System.out.println("server closed in server");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Network getReceiveSend() {
		return this.cs;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server started. Waiting for connections...");

			while (isRunning) {
				socket = server.accept();
				cs = new Network(socket);
				connectionActive();
			}
		} catch (IOException e) {
			connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(1));
			disconnect();
		} 
	}

}
