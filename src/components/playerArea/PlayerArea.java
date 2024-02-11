package components.playerArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class holding the PlayerArea component to be called into Main
 * 
 * @author Kevin Mosekjaer
 */
public class PlayerArea {
	
	/**
	 * JPanel holding player area component
	 */
	private JPanel panel;
	
	/**
	 * JLabel for player Color
	 */
	private JLabel playerColor;
	
	/**
	 * 
	 */
	//private ImageIcon gamePiece;
	
	/**
	 * Constructor for player area
	 * 
	 * @param playerNumber player number
	 * @param playerName name of player
	 */
	public PlayerArea(int playerNumber, String playerName) {
		initializePlayerArea(playerNumber, playerName);
	}
	
	/**
	 * Getter for JPanel holding player area component
	 * 
	 * @return panel holding playerArea
	 */
	public JPanel getPlayerArea() {
		return panel;
	}
	
	/**
	 * Method initializing the player area component into the JPanel
	 * 
	 * @param playerNumber
	 * @param playerName
	 */
	private void initializePlayerArea(int playerNumber, String playerName) {	
		panel=new JPanel(new BorderLayout());
		JPanel rightSide = new JPanel();
		JPanel leftSide = new JPanel();
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		
		// JPanel for player name
		JLabel name = new JLabel("Player "+ playerNumber + ": " + playerName);
		JPanel namePanel = new JPanel(new BorderLayout());
		namePanel.add(name, BorderLayout.WEST);	
		leftSide.add(namePanel, BorderLayout.NORTH);
		
	
		JPanel nextTimer = new JPanel(new BorderLayout());
		
		
		// JPanel for next move
		JLabel next = new JLabel("Next Move:");
		JPanel nextPanel = new JPanel(new BorderLayout());
		nextPanel.add(next, BorderLayout.WEST);
		//panel.add(nextPanel);
		nextTimer.add(nextPanel, BorderLayout.NORTH);
		
		// JPanel for player timer
		JLabel timer = new JLabel("Turn Time Elapsed:");
		JPanel timerPanel = new JPanel(new BorderLayout());
		timerPanel.add(timer, BorderLayout.WEST);
		//panel.add(timerPanel, BorderLayout.CENTER);
		nextTimer.add(timerPanel, BorderLayout.SOUTH);
		
		
		leftSide.add(nextTimer, BorderLayout.CENTER);
		
		
		JPanel gameTimerWon = new JPanel(new BorderLayout());
		
		// JPanel for game timer
		JLabel gameTimer = new JLabel("Game Time Elapsed:");
		JPanel gameTimerPanel = new JPanel(new BorderLayout());
		gameTimerPanel.add(gameTimer, BorderLayout.WEST);
		//panel.add(gameTimerPanel);
		gameTimerWon.add(gameTimerPanel, BorderLayout.NORTH);
		
		//JPanel for games won
		JLabel gamesWon = new JLabel("Games Won:");
		JPanel gamesWonPanel = new JPanel(new BorderLayout());
		gamesWonPanel.add(gamesWon, BorderLayout.WEST);
		//panel.add(gamesWonPanel, BorderLayout.SOUTH);
		gameTimerWon.add(gamesWonPanel, BorderLayout.SOUTH);
		
		
		leftSide.add(gameTimerWon, BorderLayout.SOUTH);

		
		if(playerNumber==1) {
			ImageIcon gamePiece = new ImageIcon(PlayerArea.class.getResource("Connect4_Only_Red.png"));
			playerColor = new JLabel(gamePiece);
			JPanel pc = new JPanel();
			pc.add(playerColor);
			rightSide.add(pc, BorderLayout.CENTER);
		} else if(playerNumber==2) {
			ImageIcon gamePiece = new ImageIcon(PlayerArea.class.getResource("Connect4_Only_Black.png"));
			playerColor = new JLabel(gamePiece);
			JPanel pc = new JPanel();
			pc.add(playerColor);
			rightSide.add(pc, BorderLayout.CENTER);
		}

		
		panel.add(leftSide, BorderLayout.WEST);
		panel.add(rightSide, BorderLayout.EAST);
		
		
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
		
	}
	
}
