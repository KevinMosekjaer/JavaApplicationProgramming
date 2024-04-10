package components.menubar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import observers.ClientServerConnection;
import observers.ConnectionListener;
import observers.ConnectionObserver;
import observers.MenuObserver;
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
	 * holds list of observers
	 */
	private List<MenuObserver> observers = new ArrayList<>();
	
	private List<ConnectionObserver> connectionObservers = new ArrayList<>();

	private List<OutgoingObserver> outgoing = new ArrayList<>();

	private boolean serverActive = false, clientActive = false;

	private String serverName, clientName;

	/**
	 * Constructor
	 * @param view v
	 */
	public MenuBarController(MenuBar view) {
		this.view = view;
		addMenuListeners();
	}

	public void addNameSend(OutgoingObserver name) {
		outgoing.add(name);
	}
	
	public void addConnectionObserver(ConnectionObserver connectionObserver) {
		connectionObservers.add(connectionObserver);
	}

	public void clientStart(String name, String address, int port) {
		this.clientName = name;
		connectionObservers.forEach(connectionObserver -> connectionObserver.clientConnect(name, address, port));
	}

	public void serverStart(String name, int port) {
		this.serverName = name;
		connectionObservers.forEach(connectionObserver -> connectionObserver.serverConnect(name, port));
	}

	public void clientDisconnect() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.clientDisconnect());
	}

	public void serverDisconnect() {
		connectionObservers.forEach(connectionObserver -> connectionObserver.serverDisconnect());
	}

	public String getName(int num) {
		if(num==1) {
			return serverName;
		} else {
			return clientName;
		}
	}

	/**
	 * Adds observers
	 * @param observer o
	 */
	public void addObserver(MenuObserver observer) {
		observers.add(observer);
	}

	/**
	 * Contacts observers of change, changes language in menu bar
	 * @param language l
	 */
	public void changeLanguage(String language) {
		observers.forEach(observer -> observer.changeLanguage(language));
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

	private void resetConnect() {
		view.resetConnectName();
		view.resetAddress();
		view.connectResetPort();
		view.resetClientStatus();
	}

	private void resetHost() {
		view.resetHostName();
		view.hostResetPort();
		view.resetHostStatus();
	}

	private void handleConnect() {
		if(serverActive) {
			view.serverAlreadyStarted();
			return;
		} else if(clientActive) {
			view.clientAlreadyStarted();
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
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

	}

	private void handleHost() {
		if(serverActive) {
			view.serverAlreadyStarted();
			return;
		} else if(clientActive) {
			view.clientAlreadyStarted();
			return;
		} else {
			try {
				System.out.println("Server connection started");
				String name = view.getHostName();
				int port = view.hostGetPort();
				System.out.println("Name: " + name + "\nPort: " + port);
				serverStart(name, port);
				view.setHostStatusWaiting();
				serverActive=true;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleDisconnect() {
		if (clientActive) {
			clientDisconnect();
			clientActive = false;
			resetConnect();
		} else if (serverActive) {
			serverDisconnect();
			serverActive = false;
			resetHost();
		}
	}

	/**
	 * Contacts observers of restart
	 */
	private void restartGame() {
		observers.forEach(MenuObserver::restartGame);
	}

	@Override
	public void connected(int c) {
		if(c==1) {
			System.out.println("Inside connected in menu bar: server");
			view.resetHostStatus();
			view.setHostStatusConnected();
			outgoing.forEach(nameSend -> nameSend.nameOutgoing("un", c,serverName));
		} else {
			System.out.println("Inside connected in menu bar: client");
			view.resetClientStatus();
			view.setClientStatusConnected();
			outgoing.forEach(nameSend -> nameSend.nameOutgoing("un", c, clientName));
		}		
	}

	@Override
	public void disconnected(int c) {
		// TODO Auto-generated method stub

	}

}
