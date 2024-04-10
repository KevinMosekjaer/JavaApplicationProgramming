package main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import components.gameboard.GameBoard;
import components.gamechat.GameChat;
import components.gametitle.GameTitle;
import components.menubar.MenuBar;
import components.playerarea.PlayerArea;

/**
 * Class holds main view for game
 *
 * @author Kevin Mosekjaer
 */
public class GameView extends JFrame {

	/**
	 * JPanels to manage other components to help with formatting
	 */
	private static JPanel rightSide, leftSide, all;

	/**
	 * ImageIcon for tab bar/game icon
	 */
	private static ImageIcon icon;

	/**
	 * GameChat object
	 */
	private GameChat chat;

	/**
	 * GameBoard object
	 */
	private GameBoard board;

	/**
	 * Player area objects
	 */
	private PlayerArea player1, player2;

	/**
	 * Menu bar object
	 */
	private MenuBar menu;

	/**
	 * Game title object
	 */
	private GameTitle title;

	/**
	 * Constructor for GameView
	 */
	public GameView() {
		initializeGame();
	}

	/**
	 * Function creating the game and putting the components together
	 */
	private void initializeGame() {
		rightSide = new JPanel();
		leftSide = new JPanel();
		all = new JPanel(new BorderLayout());
		icon = new ImageIcon(RunGame.class.getResource("/assets/Connect4_Logo.png"));
		this.setIconImage(icon.getImage());

		// Set preferred size and orientation
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		rightSide.setPreferredSize(new Dimension(400,0));

		// Initialize all component views
		menu = new MenuBar();
		title = new GameTitle();
		board = new GameBoard();
		player1 = new PlayerArea(1, "");
		player2 = new PlayerArea(2, "");
		chat = new GameChat();

		// Set menu bar in JFrame
		this.setJMenuBar(menu.getMenuBar());

		// Adding right side components
		rightSide.add(player1.getPlayerArea());
		rightSide.add(player2.getPlayerArea());
		rightSide.add(chat.getGameChat());

		// Adding left side components
		leftSide.add(title.getGameTitle());
		leftSide.add(board.getGameBoard());

		// Adding it all together
		all.add(rightSide, BorderLayout.EAST);
		all.add(leftSide, BorderLayout.CENTER);
		this.add(all);

		this.setResizable(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Getter for game chat object
	 * @return game chat
	 */
	public GameChat getGameChat() {
		return this.chat;
	}

	/**
	 * Getter for game board
	 * @return game board
	 */
	public GameBoard getGameBoard() {
		return this.board;
	}

	/**
	 * Getter for player objects
	 * @param playerNumber number
	 * @return player area
	 */
	public PlayerArea getPlayerArea(int playerNumber) {
		if(playerNumber == 1) {
			return this.player1;
		} else {
			return this.player2;
		}
	}

	/**
	 * Getter for menu bar
	 * @return menu bar
	 */
	public MenuBar getMenuBarArea() {
		return this.menu;
	}
}
