package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.gameboard.GameBoard;
import components.gamechat.GameChat;
import components.gamechat.GameChatController;
import components.gametitle.GameTitle;
import components.menuBar.MenuBar;
import components.playerarea.PlayerArea;

public class GameView extends JFrame {

	/**
	 * JPanels to manage other components to help with formatting
	 */
	private static JPanel rightSide, leftSide, all;
	
	/**
	 * ImageIcon for tab bar/game icon
	 */
	private static ImageIcon icon;
	
	private GameChat chat;
	
	private GameBoard board;
	
	private PlayerArea player1, player2;
	
	public GameView() {
		initializeGame();
	}
	
	/**
	 * Function creating the game and putting the components together
	 */
	private void initializeGame() {
		//frame=new JFrame();
		rightSide = new JPanel();
		leftSide = new JPanel();
		all = new JPanel(new BorderLayout());
		icon = new ImageIcon(RunGame.class.getResource("/assets/Connect4_Logo.png"));
		this.setIconImage(icon.getImage());
		
		// Set preferred size and orientation
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));		
		rightSide.setPreferredSize(new Dimension(500,0));
	
		// Initialize all component views
		MenuBar menu = new MenuBar();
		GameTitle title = new GameTitle();
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

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}
	
	public GameChat getGameChat() {
		return this.chat;
	}
	
	public GameBoard getGameBoard() {
		return this.board;
	}
	
	public PlayerArea getPlayerArea(int playerNumber) {
		if(playerNumber == 1) {
			return this.player1;
		} else {
			return this.player2;
		}
	}
}
