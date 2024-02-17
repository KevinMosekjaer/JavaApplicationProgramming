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
	private JPanel panel, gameGrid[][], gridPanel;
	
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
		empty = new ImageIcon(GameBoard.class.getResource("/assets/clearBlue.png"));
		black = new ImageIcon(GameBoard.class.getResource("/assets/darkBlue.png"));
		red = new ImageIcon(GameBoard.class.getResource("/assets/redBlue.png"));
		
		createGameBoard();		
		panel.add(gridPanel);
		panel.setBackground(Color.decode("#cde3fa"));
		
		// temp values in game board
		updateGrid(5,6,red);
		updateGrid(5,5,black);
		updateGrid(4,5,red);
		updateGrid(5,3,red);
		updateGrid(5,0,red);
		updateGrid(5,2,red);
		updateGrid(5,4,black);
		updateGrid(4,3,black);
		updateGrid(5,1,black);
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
	 * Method to update grid with values 
	 * 
	 * @param row row of game piece to be placed
	 * @param column column of game piece to be placed
	 * @param color color of player
	 */
	private void updateGrid(int row, int column, ImageIcon color) {
		// Checks if you are able to place item in that section, sets player color if true
		if(((JLabel) gameGrid[row][column].getComponent(0)).getIcon()==empty) {
			((JLabel) gameGrid[row][column].getComponent(0)).setIcon(color);
		// Error popup
		} else {
			JOptionPane.showMessageDialog(panel, "Unable to place in this column");
		}
	}
}
