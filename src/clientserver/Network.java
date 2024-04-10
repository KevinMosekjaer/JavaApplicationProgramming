package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import observers.IncomingObserver;
import observers.OutgoingObserver;

public class Network implements OutgoingObserver {

	protected Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private Queue<String> messageQueue = new LinkedList<>();
	private List<IncomingObserver> incomingObservers = new ArrayList<>();
	private Thread outgoing,incoming;

	public Network(Socket socket) {
		this.socket=socket;
		initializeReadWrite();
		messageIncomingThread();
		messageOutgoingThread();
	}

	private void initializeReadWrite() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void messageIncomingThread() {
		System.out.println("Inside message incoming");
		incoming = new Thread(new Runnable() {
			@Override
			public void run() {
				String message;
				try {
					while((message = reader.readLine()) != null) {
						decryptMessage(message);				
					}
				} catch(IOException e) {
					// maybe add ability to reconnect
					//e.printStackTrace();
					System.out.println("Message incoming crash");
				} 
			}
		});
		incoming.start();
	}


	private void messageOutgoingThread() {
		outgoing = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!Thread.currentThread().isInterrupted()) {
						String message = messageQueue.poll();
						if (message != null) {
							writer.println(message);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Message outgoing crash");
				}
			}
		});
		outgoing.start();
	}

	public void messageOutgoing(String message) {
		System.out.println("Inside message Outgoing: " + message);
		messageQueue.add(message);
		System.out.println("Message sent to outgoing thread");

	}

	private void decryptMessage(String message) {
		System.out.println("Inside decrypt message");
		String messageParts[] = message.split(";", 2);
		String firstSection = messageParts[0];
		char first[] = firstSection.toCharArray();
		String type = "" +  first[0] + first[1];
		int player = Integer.parseInt(String.valueOf(first[2]));
		System.out.println("Player number: " + player);
		String messageSent = messageParts[1];

		switch(type) {
		case "cm":
			System.out.println("message before decrypt: " + message);
			chatIncoming(player, messageSent);
			System.out.println("Sending to chat, message after decrypt: player: " + player + ", message: " + messageSent);
			break;
		case "un":
			System.out.println("message before decrypt: " + message);
			nameIncoming(player, messageSent);
			System.out.println("Sending to name, message after decrypt: player: " + player + ", message: " + messageSent);
			break;
		case "gm":
			System.out.println("message before decrypt: " + message);
			char move[] = messageSent.toCharArray();
			int col = Integer.parseInt(String.valueOf(move[0]));
			gameMoveIncoming(player,col);
			System.out.println("Sending to game, message after decrypt: player: " + player + ", col: " + col);
			break;
		case "rr":
			System.out.println("message before decrypt: " + message);
			restartIncoming(player, messageSent);
			System.out.println("Sending to restart, message after decrypt: player: " + player + ", message: " + messageSent);
			break;
		case "sg":
			System.out.println("Inside start game decrypt");
			System.out.println("message before decrypt: " + message);
			startGameIncoming(player, messageSent);
			break;
		default:
			return;
		}
	}

	private String encryptMessage(String type, int player, String message) {
		System.out.println("Inside encrypt message");
		String messageOutgoing = type + player + ";" + message;
		return messageOutgoing;
	}

	public void addIncomingObserver(IncomingObserver incomingObserver) {
		incomingObservers.add(incomingObserver);
	}

	/* Incoming messages */	
	private void nameIncoming(int player, String name) {
		System.out.println("Inside name incoming, player: " + player + ", name: " + name);
		incomingObservers.forEach(incomingObserver -> incomingObserver.nameIncoming(player, name));
	}

	private void chatIncoming(int player, String chat) {
		System.out.println("Inside chat incoming");
		incomingObservers.forEach(incomingObserver -> incomingObserver.chatIncoming(player, chat));
	}

	private void gameMoveIncoming(int player, int col) {
		System.out.println("Inside game incoming");
		incomingObservers.forEach(incomingObserver -> incomingObserver.gameMoveIncoming(player, col));
	}

	private void restartIncoming(int player, String message) {
		System.out.println("Inside restart incoming");
		incomingObservers.forEach(incomingObserver -> incomingObserver.restartIncoming(player, message));
	}

	private void startGameIncoming(int player, String message) {
		System.out.println("Inside start game incoming");
		incomingObservers.forEach(incomingObserver -> incomingObserver.startGameIncoming(player,message));
	}

	/* Outgoing messages */
	@Override
	public void nameOutgoing(String type, int player, String message) {
		System.out.println("Inside name Outgoing");
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	@Override
	public void restartOutgoing(String type, int player, String message) {
		System.out.println("Inside restart Outgoing");
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	@Override
	public void gameMoveOutgoing(String type, int player, int col) {
		System.out.println("Inside game move Outgoing");
		String moveMessage = String.valueOf(col);
		String fullMessage = encryptMessage(type,player,moveMessage);
		messageOutgoing(fullMessage);
	}

	@Override
	public void chatOutgoing(String type, int player, String message) {
		System.out.println("Inside chat Outgoing");
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);
	}

	@Override
	public void startGameOutgoing(String type, int player, String message) {
		System.out.println("Inside Start game outgoing in network");
		String fullMessage = encryptMessage(type, player, message);
		messageOutgoing(fullMessage);		
	}

}

