package main;

import javax.swing.*;
import components.gameboard.GameBoard;
import components.gamechat.GameChat;
import components.gametitle.GameTitle;
import components.menuBar.MenuBar;
import components.playerarea.PlayerArea;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Main class to put all of the game components together and hold main method
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class RunGame {
	
	/**
	 * JFrame holding all components for the program
	 */
	private static JFrame frame;
	
	/**
	 * JPanels to manage other components to help with formatting
	 */
	private static JPanel rightSide, leftSide, all;
	
	/**
	 * ImageIcon for tab bar/game icon
	 */
	private static ImageIcon icon;
	
	/**
	 * Function creating the game and putting the components together
	 */
	private static void createGame() {
		frame=new JFrame();
		rightSide = new JPanel();
		leftSide = new JPanel();
		all = new JPanel(new BorderLayout());
		icon = new ImageIcon(RunGame.class.getResource("/assets/Connect4_Logo.png"));
		frame.setIconImage(icon.getImage());
		
		// Set preferred size and orientation
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));		
		rightSide.setPreferredSize(new Dimension(500,0));
	
		// Calling other component constructors
		MenuBar menu = new MenuBar();
		GameTitle title = new GameTitle();
		GameBoard board = new GameBoard();
		PlayerArea player1 = new PlayerArea(1, "Kevin");
		PlayerArea player2 = new PlayerArea(2, "Mateusz");
		GameChat chat = new GameChat();
		
		frame.setJMenuBar(menu.getMenuBar());
		
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
		frame.add(all);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Main method, calling createGame method
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		createGame();
	}
}
