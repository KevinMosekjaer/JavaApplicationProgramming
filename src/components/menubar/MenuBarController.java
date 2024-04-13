package components.menubar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import observers.ConnectionObserver;
import observers.GameObserver;
import observers.OutgoingObserver;

/**
 * Class holds menu bar controller
 *
 * @author Kevin Mosekjaer
 */
public class MenuBarController implements ConnectionObserver {

	/**
	 * holds menu bar view
	 */
	private MenuBar view;

	/**
	 * holds resrouce bundle
	 */
	private ResourceBundle bundle;

	/**
	 * holds locale
	 */
	private Locale locale;

	/**
	 * List of game observers
	 */
	private List<GameObserver> gameObservers = new ArrayList<>();

	/**
	 * list of connection observers
	 */
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	/**
	 * list of outgoing message observers
	 */
	private List<OutgoingObserver> outgoingObservers = new ArrayList<>();

	/**
	 * boolean variables for remote connections
	 */
	private boolean serverActive = false, clientActive = false, connected = false;

	/**
	 * name of server and client
	 */
	private String serverName, clientName;

	/**
	 * Player number for this entity
	 */
	private int playerNumber;

	/**
	 * Constructor
	 * @param view v
	 */
	public MenuBarController(MenuBar view) {
		this.view = view;
		addMenuListeners();
	}

	/**
	 * Function for adding outgoing observers
	 * @param name n
	 */
	public void addOutgoingObserver(OutgoingObserver name) {
		outgoingObservers.add(name);
	}

	/**
	 * Function for adding connection observers
	 * @param connectionObserver c
	 */
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}

	/**
	 * Function for starting client entity
	 * @param name name
	 * @param address address
	 * @param port port
	 */
	public void clientStart(String name, String address, int port) {
		this.clientName = name;
		connectionObservers.forEach(connectionObserver -> connectionObserver.clientConnect(name, address, port));
	}

	/**
	 * Function for starting server entity
	 * @param name name 
	 * @param port port
	 */
	public void serverStart(String name, int port) {
		this.serverName = name;
		connectionObservers.forEach(connectionObserver -> connectionObserver.serverConnect(name, port));
	}

	/**
	 * Function for client disconnecting through menu
	 */
	public void clientDisconnect() {
		outgoingObservers.forEach(outgoingObserver -> outgoingObserver.disconnectingNow("ds",0,"ds"));
		connectionObservers.forEach(connectionObserver -> connectionObserver.clientDisconnect());
	}

	/**
	 * Function for server disconnecting through menu
	 */
	public void serverDisconnect() {
		outgoingObservers.forEach(outgoingObserver -> outgoingObserver.disconnectingNow("ds",0,"ds"));
		connectionObservers.forEach(connectionObserver -> connectionObserver.serverDisconnect());
	}

	/**
	 * Getter for client/server name
	 * @param num n
	 * @return name
	 */
	public String getName(int num) {
		if(num==1) {
			return serverName;
		} else {
			return clientName;
		}
	}

	/**
	 * Adds observers
	 * @param gameObserver o
	 */
	public void addGameObserver(GameObserver gameObserver) {
		gameObservers.add(gameObserver);
	}

	/**
	 * Contacts observers of change, changes language in menu bar
	 * @param language l
	 */
	public void changeLanguage(String language) {
		gameObservers.forEach(gameObserver -> gameObserver.changeLanguage(language));
		locale = new Locale(language);
		bundle = ResourceBundle.getBundle("messagebundles.MessageBundle", locale);
		view.updateMenuLabels(bundle);
	}


	/**
	 * adds menu listeners
	 */
	private void addMenuListeners() {
		view.addRestartActionListener(e -> restartGame());
		view.addLanguageChangeListener(e -> changeLanguage("en"), "en");
		view.addLanguageChangeListener(e -> changeLanguage("fr"), "fr");
		view.addConnectActionListener(e -> view.getConnectMenu().setVisible(true));
		view.addHostActionListener(e -> view.getHostMenu().setVisible(true));
		view.addDisconnectActionListener(e -> handleDisconnect());
		view.addConnectButtonListener(e -> handleConnect());
		view.addHostButtonListener(e -> handleHost());
		view.addCancelClientButtonListener(e -> resetConnect());
		view.addCancelHostButtonListener(e -> resetHost());
	}

	/**
	 * Function for resetting connect menu
	 */
	private void resetConnect() {
		view.resetConnectName();
		view.resetAddress();
		view.connectResetPort();
		view.resetClientStatus();
		view.getConnectMenu().setVisible(false);
		clientDisconnect();
	}

	/**
	 * Function for resetting host menu
	 */
	private void resetHost() {
		view.resetHostName();
		view.hostResetPort();
		view.resetHostStatus();
		view.getHostMenu().setVisible(false);
		serverDisconnect();
	}

	/**
	 * Function to handle client connection
	 */
	private void handleConnect() {
		if(serverActive) {
			view.serverAlreadyStarted();
			return;
		} else if(clientActive) {
			view.clientAlreadyStarted();
			return;
		} else if(view.getConnectName().length() > 20) {
			view.nameTooLong();
			return;
		} else {
			try {
				System.out.println("Client connection started");
				String name = view.getConnectName();
				String address = view.getAddress();
				int port = view.connectGetPort();
				System.out.println("Name: " + name + "\nAddress: " + address + "\nPort: " + port);
				clientStart(name, address, port);
				view.setClientStatusWaiting();
				clientActive = true;
				this.playerNumber=2;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Function to handle host connection 
	 */
	private void handleHost() {
		if(serverActive) {
			view.serverAlreadyStarted();
			return;
		} else if(clientActive) {
			view.clientAlreadyStarted();
			return;
		} else if(view.getHostName().length() > 20) {
			view.nameTooLong();
			return;
		} else {
			try {
				String name = view.getHostName();
				int port = view.hostGetPort();
				serverStart(name, port);
				view.setHostStatusWaiting();
				serverActive=true;
				this.playerNumber=1;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Function for handling server/client disconnection
	 */
	private void handleDisconnect() {
		if (clientActive) {
			clientDisconnect();
			clientActive = false;
			resetConnect();
			//gameObservers.forEach(gameObserver -> gameObserver.sendChat("Game Message: Network issues, disconnected..."));
		} else if (serverActive) {
			serverDisconnect();
			serverActive = false;
			resetHost();
			//gameObservers.forEach(gameObserver -> gameObserver.sendChat("Game Message: Network issues, disconnected..."));
		}

	}

	/**
	 * Contacts observers of restart
	 */
	private void restartGame() {
		if(!connected) {
			gameObservers.forEach(gameObserver -> gameObserver.restartGame());
		} else if(connected) {
			outgoingObservers.forEach(outgoingObserver -> outgoingObserver.restartOutgoing("rr", this.playerNumber, "request"));
			if(this.playerNumber==1) {
				gameObservers.forEach(gameObserver -> gameObserver.sendChat("Waiting for " + clientName + " to respond..."));
			} else if(this.playerNumber==2) {
				gameObservers.forEach(gameObserver -> gameObserver.sendChat("Waiting for " + serverName + " to respond..."));
			}

		}
	}

	/**
	 * Implemented function for connection from server/client
	 * @param c c
	 */
	@Override
	public void connected(int c) {
		if(c==1) {
			view.resetHostStatus();
			view.setHostStatusConnected();
			connected = true;
			outgoingObservers.forEach(outgoingObserver -> outgoingObserver.nameOutgoing("un", c,serverName));
		} else {
			view.resetClientStatus();
			view.setClientStatusConnected();
			connected = true;
			outgoingObservers.forEach(outgoingObserver -> outgoingObserver.nameOutgoing("un", c, clientName));
		}		
	}

	/**
	 * Implemented function for disconnection from server/client
	 * @param c c
	 */
	@Override
	public void disconnected(int c) {
		connected=false;
		if(c==1) {
			resetHost();
		} else {
			resetConnect();
		}
	}

}
