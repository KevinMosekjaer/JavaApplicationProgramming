package main;

import javax.swing.*;

import components.*;
import components.gameBoard.GameBoard;
import components.gameChat.GameChat;
import components.gameTitle.GameTitle;
import components.menuBar.MenuBar;
import components.playerArea.PlayerArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class RunGame {
	
	private static JFrame frame;
	
	private static void createGame() {
		frame=new JFrame();
		JPanel rightSide = new JPanel();
		JPanel leftSide = new JPanel();
		JPanel all = new JPanel(new BorderLayout());
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		
		rightSide.setPreferredSize(new Dimension(300,0));
			
		MenuBar menu = new MenuBar();
		GameTitle title = new GameTitle();
		GameBoard board = new GameBoard();
		PlayerArea player1 = new PlayerArea(1, "Kevin");
		PlayerArea player2 = new PlayerArea(2, "Mateusz");
		GameChat chat = new GameChat();
				
		frame.setPreferredSize(new Dimension(960,540));		
		
		frame.setJMenuBar(menu.getMenuBar());
		
		rightSide.add(player1.getPlayerArea());
		rightSide.add(player2.getPlayerArea());
		rightSide.add(chat.getGameChat());
		
		leftSide.add(title.getGameTitle());
		leftSide.add(board.getGameBoard());
		
		all.add(rightSide, BorderLayout.EAST);
		all.add(leftSide, BorderLayout.CENTER);
		
		frame.add(all);
		
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createGame();
	}

}
