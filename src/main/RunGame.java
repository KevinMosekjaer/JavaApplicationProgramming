package main;

import javax.swing.*;

import components.*;
import components.gameBoard.GameBoard;
import components.gameChat.GameChat;
import components.gameTitle.GameTitle;
import components.menuBar.MenuBar;
import components.playerArea.PlayerArea;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class RunGame {
	
	private static JFrame frame;
	
	private static void createGame() {
		frame=new JFrame();
		MenuBar menu = new MenuBar();
		GameTitle title = new GameTitle();
		GameBoard board = new GameBoard();
		PlayerArea player = new PlayerArea();
		GameChat chat = new GameChat();
				
		frame.setLayout(new FlowLayout());
		frame.setPreferredSize(new Dimension(960,540));		
		
		frame.setJMenuBar(menu.getMenuBar());
		frame.add(chat.getGameChat());
		
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		createGame();
	}

}
