package main;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import clientserver.Network;
import clientserver.Client;
import clientserver.Server;
import components.gameboard.GameBoardController;
import components.gamechat.GameChatController;
import components.menubar.MenuBarController;
import components.playerarea.PlayerAreaController;
import observers.ConnectionObserver;

/**
 * Class contains main controller for game
 *
 * @author Kevin Mosekjaer
 */
public class GameController implements ConnectionObserver { 

	/**
	 * GameModel model
	 */
	private GameModel model;

	/**
	 * GameView view
	 */
	private GameView view;

	/**
	 * GameChatController chat controller
	 */
	private GameChatController chatController;

	/**
	 * GameBoardController boardController
	 */
	private GameBoardController boardController;

	/**
	 * PlayerAreaController object for player 1 and 2
	 */
	private PlayerAreaController playerController1, playerController2;

	/**
	 * MenuBarController object
	 */
	private MenuBarController menuController;

	/**
	 * Server object
	 */
	private Server server;

	/**
	 * Client object
	 */
	private Client client;

	/** 
	 * socket
	 */
	private Socket socket;

	/** 
	 * network communication object
	 */
	private Network cs;

	/** 
	 * names
	 */
	private String clientName, serverName;

	/**
	 * Constructor for GameController
	 * @param view game view
	 * @param model game model
	 */
	public GameController(GameView view, GameModel model) {
		this.view = view;
		this.model = model;
		//showSplashScreen();
	}

	/**
	 * Function to initialize controllers and initiate game start
	 */
	public void start() {
		view.setVisible(true);
		initializeControllers();
	}

	/**
	 * Splash screen
	 */
	private void showSplashScreen() {
		JWindow splash = new JWindow();
		splash.setSize(750, 750);
		splash.setLocationRelativeTo(null);
		splash.getContentPane().setBackground(Color.decode("#cde3fa"));
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);

		ImageIcon imageIcon = new ImageIcon(GameController.class.getResource("/assets/Connect4_Logo.png"));
		JLabel logo = new JLabel(imageIcon);
		JLabel names = new JLabel("Program by: Kevin Mosekjaer");

		GridBagConstraints layout = new GridBagConstraints();
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.anchor = GridBagConstraints.CENTER;

		panel.add(logo, layout);
		panel.add(names, layout);
		splash.add(panel);
		splash.setVisible(true);

		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		splash.dispose();
	}

	/**
	 * Function to initialize sub controllers
	 */
	public void initializeControllers() {
		chatController = new GameChatController(model.getGameChatModel(), view.getGameChat());
		boardController = new GameBoardController(model.getGameBoardModel(), view.getGameBoard());
		playerController1 = new PlayerAreaController(model.getPlayerAreaModel1(), view.getPlayerArea(1));
		playerController2 = new PlayerAreaController(model.getPlayerAreaModel2(), view.getPlayerArea(2));	
		boardController.addGameObserver(playerController1);
		boardController.addGameObserver(playerController2);
		boardController.addGameObserver(chatController);
		boardController.addGameObserver(boardController);
		menuController = new MenuBarController(view.getMenuBarArea());
		menuController.addGameObserver(boardController);
		menuController.addGameObserver(chatController);
		menuController.addGameObserver(playerController1);
		menuController.addGameObserver(playerController2);
		menuController.addConnectionObserver(this);
	}

	/**
	 * Function to allow communication between server and client
	 * @param c 1 for server, 2 for client
	 */
	public void initializeClientServerComs(int c) {
		if(c==1) { // server connection
			cs = server.getReceiveSend();
			cs.addIncomingObserver(chatController);
			cs.addIncomingObserver(boardController);
			cs.addIncomingObserver(playerController2);
			cs.addConnectionObserver(server);
			chatController.addChatSend(cs);
			menuController.addOutgoingObserver(cs);
			boardController.addOutgoingObserver(cs);
		} else if(c==2) { // client connection
			cs = client.getReceiveSend();
			cs.addIncomingObserver(chatController);
			cs.addIncomingObserver(boardController);
			cs.addIncomingObserver(playerController1);
			cs.addConnectionObserver(client);
			chatController.addChatSend(cs);
			menuController.addOutgoingObserver(cs);
			boardController.addOutgoingObserver(cs);
		} 
		cs.addConnectionObserver(boardController);
		cs.addConnectionObserver(chatController);
		cs.addConnectionObserver(playerController1);
		cs.addConnectionObserver(playerController2);
		menuController.addConnectionObserver(cs);	

	}

	/**
	 * Implemented function for client connect
	 * @param name n
	 * @param address a
	 * @param port p
	 */
	@Override
	public void clientConnect(String name, String address, int port) {
		this.clientName = name;
		client = new Client(name, address, port);		
		client.addConnectionObserver(this);
		client.addConnectionObserver(menuController);
		client.addConnectionObserver(chatController);
		client.addConnectionObserver(boardController);
	}

	/**
	 * Implemented function for server connect
	 * @param name n
	 * @param port p
	 */
	@Override
	public void serverConnect(String name, int port) {
		this.serverName = name;
		server = new Server(name, port);		
		server.addConnectionObserver(this);
		server.addConnectionObserver(menuController);
		server.addConnectionObserver(chatController);
		server.addConnectionObserver(boardController);
	}

	/**
	 * Implemented function for client disconnect
	 */
	@Override
	public void clientDisconnect() {
		if (client != null) {
			client.disconnect();
			client = null;
		}
	}

	/**
	 * Implemented function for server disconnect
	 */
	@Override
	public void serverDisconnect() {
		if (server != null) {
			server.disconnect();
			server = null;
		}
	}

	/**
	 * Implemented function for server/client connection
	 * @param c 1 for server, 2 for client
	 */
	@Override
	public void connected(int c) {
		initializeClientServerComs(c);
		if(c==1) { // 1 means connection from server
			model.getGameChatModel().setMyPlayerNumber(1);
			model.getGameChatModel().setPlayerName(1, menuController.getName(1));
			model.getPlayerAreaModel1().setPlayerName(menuController.getName(1));
			view.getPlayerArea(c).setPlayerName(c, menuController.getName(1));
			model.getGameBoardModel().setThisPlayer(c);
		} else { // 2 means connection from client
			model.getGameChatModel().setMyPlayerNumber(2);
			model.getGameChatModel().setPlayerName(2, menuController.getName(2));
			model.getPlayerAreaModel2().setPlayerName(menuController.getName(2));
			view.getPlayerArea(2).setPlayerName(2, menuController.getName(2));
			model.getGameBoardModel().setThisPlayer(2);
		}
	}

}
