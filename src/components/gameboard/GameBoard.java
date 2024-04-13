package components.gameboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The purpose of this class is to create the game board
 *
 * @author Kevin Mosekjaer
 */
public class GameBoard {

	/**
	 * JPanels for game grid, grid panel, and main panel holding it together
	 */
	private JPanel panel, gameGrid[][], gridPanel, buttonPanel;

	/**
	 * holds place piece button, button
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
	 * JDialog for waiting connection
	 */
	public JDialog waiting;

	/**
	 * Constructor for game board
	 */
	public GameBoard() {
		initializeGameBoard();
	}

	/**
	 * Getter for game board
	 *
	 * @return panel
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
		
		// create empty image icon
		empty = new ImageIcon(GameBoard.class.getResource("/assets/clearBlue.png"));
		Image eImage = empty.getImage(); 
		Image newE = eImage.getScaledInstance(75,75, java.awt.Image.SCALE_SMOOTH);
		empty = new ImageIcon(newE);

		// create black image icon
		black = new ImageIcon(GameBoard.class.getResource("/assets/darkBlue.png"));
		Image bImage = black.getImage(); 
		Image newB = bImage.getScaledInstance(75,75,  java.awt.Image.SCALE_SMOOTH);
		black = new ImageIcon(newB);

		// create red image icon
		red = new ImageIcon(GameBoard.class.getResource("/assets/redBlue.png"));
		Image RImage = red.getImage(); 
		Image newR = RImage.getScaledInstance(75,75,  java.awt.Image.SCALE_SMOOTH);
		red = new ImageIcon(newR);

		// create game board and buttons
		createGameBoard();
		createGameButtons();
		gridPanel.setPreferredSize(new Dimension(0,750));
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
	 * function to create game board buttons
	 */
	private void createGameButtons() {
		buttonPanel = new JPanel(new GridLayout(1, 7));
		placePiece = new JButton[7];
		for (int i = 0; i < 7; i++) {
			button = new JButton("\u2B07");
			placePiece[i] = button;
			buttonPanel.add(button);
		}
		buttonPanel.setPreferredSize(new Dimension(0,50));
		panel.add(buttonPanel);
	}

	/**
	 * Function to reset the game board to empty
	 */
	public void resetGameBoard() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				((JLabel) gameGrid[i][j].getComponent(0)).setIcon(empty);
			}
		}
	}

	/**
	 * Function to update the grid icons
	 * @param row r
	 * @param col c
	 * @param player p
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
	 * Function to display the winner
	 * @param playerNumber num
	 * @return boolean
	 */
	public boolean displayWinner(int playerNumber) {
		if(playerNumber==-1) {
			int restart = JOptionPane.showConfirmDialog(panel, "Tie game, better luck next time!" + " Do you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
			return restart == JOptionPane.YES_OPTION;
		} else {
			int restart = JOptionPane.showConfirmDialog(panel, "Player " + playerNumber + " wins!" + " Do you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
			return restart == JOptionPane.YES_OPTION;
		}
	}
	
	/**
	 * function asking if player wants to play again
	 * @return boolean
	 */
	public boolean playAgain() {
		int restart = JOptionPane.showConfirmDialog(panel, "Do you want to play again?", "Confirm", JOptionPane.YES_NO_OPTION);
		return restart == JOptionPane.YES_OPTION;
	}

	/**
	 * Prompt for starting game
	 * @return boolean
	 */
	public boolean startGamePrompt() {
		int confirm = JOptionPane.showConfirmDialog(panel, "Start the game?", "Confirm", JOptionPane.YES_NO_OPTION);
		return confirm == JOptionPane.YES_OPTION;
	}

	/**
	 * Prompt for other player wanting to start game
	 * @return boolean
	 */
	public boolean startGamePromptOther() {
		int confirm = JOptionPane.showConfirmDialog(panel, "Other player wants to start the game, do you agree?", "Confirm", JOptionPane.YES_NO_OPTION);
		return confirm == JOptionPane.YES_OPTION;
	}

	/**
	 * Error message for not that players turn
	 */
	public void notYourTurn() {
		JOptionPane.showMessageDialog(panel, "Not your turn, please wait until other player places game piece");
	}
	
	/**
	 * Prompt for restarting the game
	 * @param player player
	 * @return boolean
	 */
	public boolean restartGamePrompt(int player) {
		int confirm = JOptionPane.showConfirmDialog(panel, "Player " + player + " wants to retstart, do you agree?", "Confirm", JOptionPane.YES_NO_OPTION);
		return confirm == JOptionPane.YES_OPTION;
	}

	/**
	 * Function returning where piece is to be placed
	 * @param index i
	 * @return button index
	 */
	public JButton getPlacePieceButton(int index) {
		return placePiece[index];
	}

}

