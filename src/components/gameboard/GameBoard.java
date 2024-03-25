package components.gameboard;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * The purpose of this class is to create the game board
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class GameBoard {
	
	/**
	 * JPanels for game grid, grid panel, and main panel holding it together
	 */
	private JPanel panel, gameGrid[][], gridPanel, buttonPanel;
	
	private JButton placePiece[];
	
	/**
	 * JLabel to hold ImageIcons
	 */
	private JLabel box;
	
	/**
	 * ImageIcons for game piece colors
	 */
	private ImageIcon empty, red, black;
	
	/**
	 * Constructor for game board
	 */
	public GameBoard() {
		initializeGameBoard();
	}
	
	/**
	 * Getter for game board
	 * 
	 * @return
	 */
	public JPanel getGameBoard() {
		return panel;
	}
	
	/**
	 * Method to initialize game board by creating the main panel, importing images
	 * and calling method to create the game board
	 */
	private void initializeGameBoard() {
		panel=new JPanel();	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		empty = new ImageIcon(GameBoard.class.getResource("/assets/clearBlue.png"));
		black = new ImageIcon(GameBoard.class.getResource("/assets/darkBlue.png"));
		red = new ImageIcon(GameBoard.class.getResource("/assets/redBlue.png"));
		
		createGameBoard();	
		createGameButtons();
		panel.add(gridPanel);
		panel.setBackground(Color.decode("#cde3fa"));
		
	}
	
	/**
	 * Method to create the empty game board
	 */
	private void createGameBoard() {
		gridPanel = new JPanel(new GridLayout(6,7));
		gameGrid = new JPanel[6][7];
		
		// Creates grid with empty default values
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				gameGrid[i][j]= new JPanel();
				//gameGrid[i][j].setBackground(Color.decode("#EEF632"));
				gameGrid[i][j].setBackground(Color.decode("#3280F6"));
				box = new JLabel(empty);
				gameGrid[i][j].add(box);
				gridPanel.add(gameGrid[i][j]);				
			}
		}
	}
	
	/**
	 * Function to create buttons above game board for placing pieces
	 */
	private void createGameButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7)); 
        placePiece = new JButton[7]; 

        for (int i = 0; i < 7; i++) {
            JButton button = new JButton("\u2B07"); 
            placePiece[i] = button;
            buttonPanel.add(button);
        }
        panel.add(buttonPanel); 
    }
	
	/**
	 * Method to update grid with values 
	 * 
	 * @param row row of game piece to be placed
	 * @param column column of game piece to be placed
	 * @param color color of player
	 */
	/*
	private void updateGrid(int row, int column, ImageIcon color) {
		// Checks if you are able to place item in that section, sets player color if true
		if(((JLabel) gameGrid[row][column].getComponent(0)).getIcon()==empty) {
			((JLabel) gameGrid[row][column].getComponent(0)).setIcon(color);
		// Error popup
		} else {
			JOptionPane.showMessageDialog(panel, "Unable to place in this column");
		}
	}
	*/
	
	public void updateGrid(int[][] gridState, int currentPlayer) {
	    for (int row = 0; row < GameBoardModel.ROWS; row++) {
	        for (int col = 0; col < GameBoardModel.COLS; col++) {
	            ImageIcon icon = switch (gridState[row][col]) {
	                case 1 -> red;
	                case 2 -> black;
	                default -> empty;
	            };
	            ((JLabel) gameGrid[row][col].getComponent(0)).setIcon(icon);
	        }
	    }
	}
	
	public void showWinner(int playerNumber) {
        JOptionPane.showMessageDialog(panel, "Player " + playerNumber + " wins!");
    }
	
	public JButton getPlacePieceButton(int index) {
	    return placePiece[index];
	}
}
