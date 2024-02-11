package components.gameBoard;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard {
	
	private JPanel panel;
	
	private JPanel board[][];
	
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
	
	private void gameBoardGrid() {
		int rows = 6, columns = 7;
		
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				
			}
		}
		
	}

}
