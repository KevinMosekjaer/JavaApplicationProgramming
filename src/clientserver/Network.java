package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import observers.ConnectionObserver;
import observers.IncomingObserver;
import observers.OutgoingObserver;

/**
 * Class responsible for the incoming and outgoing messages between the client and server
 * 
 * @author Kevin Mosekjaer
 */
public class Network implements OutgoingObserver, ConnectionObserver {

	/**
	 * Socket used for communication
	 */
	private Socket socket;

	/**
	 * Reader to read incoming messages 
	 */
	private BufferedReader reader;

	/**
	 * Writer to send outgoing messages 
	 */
	private PrintWriter writer;

	/**
	 * Queue of messages to be sent
	 */
	private Queue<String> messageQueue = new LinkedList<>();

	/**
	 * list for incoming observer interface
	 */
	private List<IncomingObserver> incomingObservers = new ArrayList<>();

	/**
	 * list for connection observers interface
	 */
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	/**
	 * Threads for messages
	 */
	private Thread outgoing,incoming;

	/**
	 * Constructor establishing communication abilities between server/client
	 * @param socket s
	 */
	public Network(Socket socket) {
		this.socket=socket;
		initializeReadWrite();
		messageIncomingThread();
		messageOutgoingThread();
	}

	/**
	 * Function to add connection observers
	 * @param connectionObserver observer
	 */
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}

	/** 
	 * Function to add incoming message observers
	 * @param incomingObserver observer
	 */
	public void addIncomingObserver(IncomingObserver incomingObserver) {
		incomingObservers.add(incomingObserver);
	}

	/** 
	 * Function to initialize the reader and writer
	 */
	private void initializeReadWrite() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
		}
	}

	/**
	 * Function running thread that receives messages 
	 */
	private void messageIncomingThread() {
		incoming = new Thread(new Runnable() {	
			/**
			 * Implemented run function for incoming thread
			 */
			@Override
			public void run() {
				String message;
				try {
					if(socket != null) {
						while((message = reader.readLine()) != null) {
							decryptMessage(message);				
						}
					}
				} catch(IOException e) {
					System.out.println("Disconnect in network incoming run");
					connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(0));
				}
			}
		});
		incoming.start();
	}

	/**
	 * Function running thread that sends messages 
	 */
	private void messageOutgoingThread() {
		outgoing = new Thread(new Runnable() {
			/**
			 * Implemented run function for outgoing thread
			 */
			@Override
			public void run() {
				try {
					if(socket != null) {
						while (!Thread.currentThread().isInterrupted()) {
							String message = messageQueue.poll();
							if (message != null) {
								writer.println(message);
							}					
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		outgoing.start();
	}

	/**
	 * function adding messages to outgoing message queue to be sent
	 * @param message message to be sent
	 */
	private void messageOutgoing(String message) {
		messageQueue.add(message);
	}

	/**
	 * Function to decrypt messages sent by the other user
	 * @param message message to be decrypted
	 */
	private void decryptMessage(String message) {
		String messageParts[] = message.split(";", 2); // splits message from info about message
		String firstSection = messageParts[0];
		char first[] = firstSection.toCharArray();
		String type = "" +  first[0] + first[1]; // first 2 characters == message type
		int player = Integer.parseInt(String.valueOf(first[2])); // third character is player number
		String messageSent = messageParts[1]; // actual message

		switch(type) {
		case "cm": // chat message
			chatIncoming(player, messageSent);
			break;
		case "un": // user name
			nameIncoming(player, messageSent);
			break;
		case "gm": // game move
			char move[] = messageSent.toCharArray();
			int col = Integer.parseInt(String.valueOf(move[0]));
			gameMoveIncoming(player,col);
			break;
		case "rr": // restart request
			restartIncoming(player, messageSent);
			break;
		case "sg": // start game
			startGameIncoming(player, messageSent);
			break;
		case "ds": // disconnect 
			System.out.println("Disconnect in network decrypt");
			connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(player));
			break;
		default:
			return;
		}
	}

	/**
	 * Function for encrypt messages to be sent to other user
	 * @param type type of message
	 * @param player sent by this player
	 * @param message the message
	 * @return full message
	 */
	private String encryptMessage(String type, int player, String message) {
		String messageOutgoing = type + player + ";" + message;
		return messageOutgoing;
	}

	/**
	 * Function sending incoming name to observers
	 * @param player p
	 * @param name name
	 */
	private void nameIncoming(int player, String name) {
		incomingObservers.forEach(incomingObserver -> incomingObserver.nameIncoming(player, name));
	}

	/**
	 * Function sending incoming chat message to observers
	 * @param player p
	 * @param chat chat message
	 */
	private void chatIncoming(int player, String chat) {
		incomingObservers.forEach(incomingObserver -> incomingObserver.chatIncoming(player, chat));
	}

	/**
	 * Function sending incoming game moves to observers
	 * @param player p
	 * @param col c
	 */
	private void gameMoveIncoming(int player, int col) {
		incomingObservers.forEach(incomingObserver -> incomingObserver.gameMoveIncoming(player, col));
	}

	/**
	 * Function sending restart request to observers
	 * @param player p
	 * @param message message
	 */
	private void restartIncoming(int player, String message) {
		incomingObservers.forEach(incomingObserver -> incomingObserver.restartIncoming(player, message));
	}

	/**
	 * Function sending start game to observers
	 * @param player p
	 * @param message m
	 */
	private void startGameIncoming(int player, String message) {
		incomingObservers.forEach(incomingObserver -> incomingObserver.startGameIncoming(player,message));
	}

	/**
	 * Function for sending name
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	@Override
	public void nameOutgoing(String type, int player, String message) {
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	/**
	 * Function for restarting game
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	@Override
	public void restartOutgoing(String type, int player, String message) {
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	/**
	 * Function for game moves
	 * @param type t
	 * @param player p
	 * @param col c
	 */
	@Override
	public void gameMoveOutgoing(String type, int player, int col) {
		String moveMessage = String.valueOf(col);
		String fullMessage = encryptMessage(type,player,moveMessage);
		messageOutgoing(fullMessage);
	}

	/**
	 * Function for chat messages sent
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	@Override
	public void chatOutgoing(String type, int player, String message) {
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	/**
	 * Function for initial start game
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	@Override
	public void startGameOutgoing(String type, int player, String message) {
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);		
	}

	/**
	 * Function for let other machine know they are going to disconnect
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	@Override
	public void disconnectingNow(String type, int player, String message) {
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);	
	}

	/**
	 * Implemented function for if server disconnects from menu
	 */
	@Override
	public void serverDisconnect() {
		System.out.println("Server disconnect in network");
		connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(1));
	}

	/**
	 * Implemented function for if client disconnects from menu
	 */
	@Override
	public void clientDisconnect() {
		System.out.println("client disconnect in network");
		connectionObservers.forEach(connectionObserver -> connectionObserver.disconnected(2));
	}

}

