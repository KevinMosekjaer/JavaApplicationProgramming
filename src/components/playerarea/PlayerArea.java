package components.playerarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class holding the PlayerArea component to be called into Main
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class PlayerArea {
	
	/**
	 * JPanel holding player area components
	 */
	private JPanel panel, rightSide, leftSide, playerColorPanel, namePanel, nextPanel, timerPanel, gameTimerPanel, gamesWonPanel;
	
	/**
	 * JLabels for player area components
	 */
	private JLabel playerColor, name;//, next, timer, name, gameTimer, gamesWon;
	
	/**
	 * 
	 */
	private JTextArea nextSection, timerSection, gameTimerSection, gamesWonSection;
	
	/**
	 * 
	 */
	int playerNumber;
	
	/**
	 * 
	 */
	String playerName;
	
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
		this.playerNumber = playerNumber;
		this.playerName = playerName;
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
		
		name = new JLabel("Player " + playerNumber + ": " + playerName);
        name.setFont(new Font("", Font.BOLD, 20));
        namePanel = new JPanel();
        namePanel.add(name, BorderLayout.WEST);
        namePanel.setBackground(Color.WHITE); // color
        leftSide.add(namePanel, BorderLayout.NORTH);
		

        nextSection = setupPlayerAreaSection("Next Move: ");
        timerSection = setupPlayerAreaSection("Turn Time Elapsed: ");
        timerSection.setText("00:00");
        gameTimerSection = setupPlayerAreaSection("Game Time Elapsed: ");
        gameTimerSection.setText("00:00");
        gamesWonSection = setupPlayerAreaSection("Games Won: ");
        
        setupPlayerColorSection(playerNumber);

		
		// Adds everything together
		panel.add(leftSide, BorderLayout.WEST);
		panel.add(rightSide, BorderLayout.EAST);
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
		panel.setBackground(Color.WHITE); // color
	}	
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	private JTextArea setupPlayerAreaSection(String title) {
	    JLabel titleLabel = new JLabel(title);
	    titleLabel.setFont(new Font("", Font.BOLD, 14));
	    JTextArea text = new JTextArea();
	    text.setEditable(false);
	    text.setFocusable(false);
	    text.setBackground(Color.WHITE); // color
	    JPanel sectionPanel = new JPanel();
	    sectionPanel.add(titleLabel, BorderLayout.WEST);
	    sectionPanel.add(text, BorderLayout.WEST);
	    sectionPanel.setBackground(Color.WHITE); // color
	    leftSide.add(sectionPanel, BorderLayout.WEST);
	    return text; 
	}
	
	/**
	 * 
	 * @param playerNumber
	 */
	private void setupPlayerColorSection(int playerNumber) {
        String colorPath = playerNumber == 1 ? "/assets/Red_Clear.png" : "/assets/Black_Clear.png";
        gamePiece = new ImageIcon(PlayerArea.class.getResource(colorPath));
        
        playerColor = new JLabel(gamePiece);
        playerColor.setText("0");
        playerColor.setFont(new Font("", Font.BOLD, 20));
        playerColor.setForeground(Color.WHITE);        
        playerColor.setHorizontalTextPosition(JLabel.CENTER);
        playerColor.setVerticalTextPosition(JLabel.CENTER);
        
        playerColorPanel = new JPanel(new BorderLayout());
        playerColorPanel.add(playerColor, BorderLayout.CENTER); 
        playerColorPanel.setBackground(Color.WHITE); // color
        rightSide.add(playerColorPanel, BorderLayout.CENTER);
    }
	
	/**
	 * 
	 * @param playerNumber
	 * @param playerName
	 */
	public void setPlayerName(int playerNumber, String playerName) {
        name.setText("Player " + playerNumber + ": " + playerName);
    }
	
	/**
	 * 
	 * @param playerName
	 */
	public void updateNextMove(String playerName) {
	    nextSection.setText(playerName);
	}

	/**
	 * 
	 * @param time
	 */
	public void updateGameTimer(String time) {
	    gameTimerSection.setText(time);
	}
	
	/**
	 * 
	 * @param text
	 * @param time
	 */
	public void initialTimerSetText(JTextArea text, String time) {
		text.setText(time);
	}
	
	
	/*
	public void updateGameTimer(String time) {
		gameTimerSection.setText("Game Time Elapsed: " + time);
	}
	
	public void initialTimerSetText(JTextArea text, String time) {
		timerSection.setText("Turn Time Elapsed: " + time);
	}
	*/

	/**
	 * 
	 * @param time
	 */
	public void updateTurnTimer(String time) {
	    timerSection.setText(time);
	}

	/**
	 * 
	 * @param won
	 */
	public void updateGamesWon(int won) {
	    gamesWonSection.setText("" + won);
	}
	
	/**
	 * 
	 * @param pieces
	 */
	public void updatePiecesPlaced(int pieces) {
		playerColor.setText("" + pieces);
	}

}
