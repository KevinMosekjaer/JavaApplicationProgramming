package components.playerArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Class holding the PlayerArea component to be called into Main
 * 
 * @author Kevin Mosekjaer
 */
public class PlayerArea {
	
	/**
	 * JPanel holding player area components
	 */
	private JPanel panel, rightSide, leftSide, playerColorPanel, namePanel, nextPanel, timerPanel, gameTimerPanel, gamesWonPanel;
	
	/**
	 * JLabels for player area components
	 */
	private JLabel playerColor, next, timer, name, gameTimer, gamesWon;
	
	/**
	 * ImageIcon for game piece color
	 */
	private ImageIcon gamePiece;
	
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
		rightSide = new JPanel();
		leftSide = new JPanel();
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		
		// JPanel for player name
		name = new JLabel("Player "+ playerNumber + ": " + playerName);
		name.setFont(new Font("", Font.BOLD, 20));
		namePanel = new JPanel();
		namePanel.add(name, BorderLayout.WEST);	
		leftSide.add(namePanel, BorderLayout.NORTH);
		
		// JPanel for next move
		next = new JLabel("Next Move:");
		nextPanel = new JPanel(new BorderLayout());
		nextPanel.add(next, BorderLayout.WEST);
		nextPanel.setBorder(new EmptyBorder(5,20,5,5));
		leftSide.add(nextPanel);
		
		// JPanel for player timer
		timer = new JLabel("Turn Time Elapsed:");
		timerPanel = new JPanel(new BorderLayout());
		timerPanel.add(timer, BorderLayout.WEST);
		timerPanel.setBorder(new EmptyBorder(5,20,5,5));
		leftSide.add(timerPanel);

		// JPanel for game timer
		gameTimer = new JLabel("Game Time Elapsed:");
		gameTimerPanel = new JPanel(new BorderLayout());
		gameTimerPanel.add(gameTimer, BorderLayout.WEST);
		gameTimerPanel.setBorder(new EmptyBorder(5,20,5,5));
		leftSide.add(gameTimerPanel);
		
		//JPanel for games won
		gamesWon = new JLabel("Games Won:");
		gamesWonPanel = new JPanel(new BorderLayout());
		gamesWonPanel.add(gamesWon, BorderLayout.WEST);
		gamesWonPanel.setBorder(new EmptyBorder(5,20,5,5));
		leftSide.add(gamesWonPanel);

		if(playerNumber==1) {
			gamePiece = new ImageIcon(PlayerArea.class.getResource("Red_Clear.png"));			
			playerColor = new JLabel(gamePiece);
			playerColor.setText("5");
			playerColor.setFont(new Font("", Font.BOLD, 20));
			playerColor.setForeground(Color.WHITE);		
			playerColor.setHorizontalTextPosition(JLabel.CENTER);
			playerColor.setVerticalTextPosition(JLabel.CENTER);
						
			playerColorPanel = new JPanel(new BorderLayout());
			playerColorPanel.add(playerColor, BorderLayout.CENTER);
	
			rightSide.add(playerColorPanel, BorderLayout.CENTER);
		} else if(playerNumber==2) {
			gamePiece = new ImageIcon(PlayerArea.class.getResource("Black_Clear.png"));
			playerColor = new JLabel(gamePiece);			
			playerColor.setText("3");
			playerColor.setFont(new Font("", Font.BOLD, 20));
			playerColor.setForeground(Color.WHITE);		
			playerColor.setHorizontalTextPosition(JLabel.CENTER);
			playerColor.setVerticalTextPosition(JLabel.CENTER);			
			playerColorPanel = new JPanel(new BorderLayout());
			playerColorPanel.add(playerColor, BorderLayout.CENTER);		
			rightSide.add(playerColorPanel, BorderLayout.CENTER);
		}
		
		panel.add(leftSide, BorderLayout.WEST);
		panel.add(rightSide, BorderLayout.EAST);
				
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
		
	}	
}
