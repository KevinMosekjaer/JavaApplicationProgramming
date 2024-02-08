package components.gameBoard;

import javax.swing.*;

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
		
	}

}
