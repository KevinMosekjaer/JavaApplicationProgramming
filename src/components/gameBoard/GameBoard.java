package components.gameBoard;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard {
	
	private JPanel panel;
	
	public GameBoard() {
		initializeGameBoard();
	}
	
	public JPanel getGameBoard() {
		return panel;
	}
	
	private void initializeGameBoard() {
		panel=new JPanel();
	
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
	}

}
