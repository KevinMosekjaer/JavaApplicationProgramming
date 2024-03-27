package components.gameboard;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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
	
	/**
	 * 
	 */
	private JButton placePiece[], button;
	
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
				gameGrid[i][j].setBackground(Color.decode("#3280F6"));
				box = new JLabel(empty);
				gameGrid[i][j].add(box);
				gridPanel.add(gameGrid[i][j]);				
			}
		}
	}
	
	/**
	 * 
	 */
	private void createGameButtons() {
        buttonPanel = new JPanel(new GridLayout(1, 7)); 
        placePiece = new JButton[7]; 

        for (int i = 0; i < 7; i++) {
            button = new JButton("\u2B07"); 
            placePiece[i] = button;
            buttonPanel.add(button);
        }
        panel.add(buttonPanel); 
    }
	
	/**
	 * 
	 */
	public void resetGameBoard() {
	    for (int i = 0; i < 6; i++) {
	        for (int j = 0; j < 7; j++) {
	            ((JLabel) gameGrid[i][j].getComponent(0)).setIcon(empty);
	        }
	    }
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @param player
	 */
	public void updateGridIcon(int row, int col, int player) {
	    ImageIcon icon = switch (player) {
	        case 1 -> red;
	        case 2 -> black;
	        default -> empty;
	    };
	    ((JLabel)gameGrid[row][col].getComponent(0)).setIcon(icon);
	}

	/**
	 * 
	 * @param playerNumber
	 * @return
	 */
	public boolean displayWinner(int playerNumber) {
		int restart = JOptionPane.showConfirmDialog(panel, "Player " + playerNumber + " wins!" + " Do you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
		return restart == JOptionPane.YES_OPTION;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean playAgain() {
		int restart = JOptionPane.showConfirmDialog(panel, "Do you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
		return restart == JOptionPane.YES_OPTION;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public JButton getPlacePieceButton(int index) {
		return placePiece[index];
	}	
	
}

