package clientserver;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import observers.ConnectionListener;
import observers.ConnectionObserver;

/**
 * Class associated with client
 *
 * @author Kevin Mosekjaer
 */
public class Client implements Runnable{ 

	private Socket socket;
	private String name, address;
	private int port;
	private boolean isRunning=false;
	private Network cs;
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();



	public Client(String name, String address, int port) {
		this.name = name;
		this.port = port;
		this.address = address;
		start();
		System.out.println("In client constructor");
	}

	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}
	
	protected void connectionActive() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.connected(2));
	}
	
	public Network getReceiveSend() {
		return this.cs;
	}

	public void start() {
		System.out.println("In client start function");
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
				System.out.println("Socket closed in client");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return this.socket;
	}

	@Override
	public void run() {
		System.out.println("In client run function above try");
		try {
			isRunning = true;
			socket = new Socket(address, port);
			cs = new Network(socket);
			connectionActive();
		} catch (IOException e) {
			connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(2));
			disconnect();
		} 
	}

}
