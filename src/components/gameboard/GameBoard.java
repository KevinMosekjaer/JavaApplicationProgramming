package components.gameboard;

import java.awt.Color;
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
		/*
		empty = new ImageIcon(GameBoard.class.getResource("/assets/clearBlue.png"));
		black = new ImageIcon(GameBoard.class.getResource("/assets/darkBlue.png"));
		red = new ImageIcon(GameBoard.class.getResource("/assets/redBlue.png"));
		 */

		empty = new ImageIcon(GameBoard.class.getResource("/assets/clearBlue.png"));

		Image eImage = empty.getImage(); 
		Image newE = eImage.getScaledInstance(100,100, java.awt.Image.SCALE_SMOOTH);
		empty = new ImageIcon(newE);


		black = new ImageIcon(GameBoard.class.getResource("/assets/darkBlue.png"));

		Image bImage = black.getImage(); 
		Image newB = bImage.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH);
		black = new ImageIcon(newB);


		red = new ImageIcon(GameBoard.class.getResource("/assets/redBlue.png"));

		Image RImage = red.getImage(); 
		Image newR = RImage.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH);
		red = new ImageIcon(newR);


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

	public boolean startGamePrompt() {
		int confirm = JOptionPane.showConfirmDialog(panel, "Start the game?", "Confirm", JOptionPane.YES_NO_OPTION);
		return confirm == JOptionPane.YES_OPTION;
	}

	public boolean startGamePromptOther() {
		int confirm = JOptionPane.showConfirmDialog(panel, "Other player wants to start the game, do you agree?", "Confirm", JOptionPane.YES_NO_OPTION);
		return confirm == JOptionPane.YES_OPTION;
	}

	public void notYourTurn() {
		JOptionPane.showMessageDialog(panel, "Not your turn, please wait until other player places game piece");
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

