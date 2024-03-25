package components.playerarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants;

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
	
	private JTextArea nextSection, timerSection, gameTimerSection, gamesWonSection;
	
	private JTextArea next, timer, gameTimer, gamesWon;
	
	int playerNumber;
	
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
		//rightSide.setBackground(Color.WHITE);
		//leftSide.setBackground(Color.WHITE);
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		
		name = new JLabel("Player " + playerNumber + ": " + playerName);
        name.setFont(new Font("", Font.BOLD, 20));
        namePanel = new JPanel();
        namePanel.add(name, BorderLayout.WEST);
        namePanel.setBackground(Color.WHITE); // color
        leftSide.add(namePanel, BorderLayout.NORTH);
		
        /*
		next = setupPlayerAreaSection("Next Move: ");
        timer = setupPlayerAreaSection("Turn Time Elapsed: ");
        gameTimer = setupPlayerAreaSection("Game Time Elapsed: ");
        gamesWon = setupPlayerAreaSection("Games Won: ");
        */
        
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
	
	/*
	private JTextArea setupPlayerAreaSection(String title) {
        JTextArea textArea = new JTextArea(title);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(new Color(0, 0, 0, 0)); // Making background transparent
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setFont(new Font("", Font.PLAIN, 14));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textArea, BorderLayout.WEST);
        panel.setBorder(new EmptyBorder(5, 20, 5, 5));
        leftSide.add(panel);

        return textArea;
    }
    */
	
	private JTextArea setupPlayerAreaSection(String title) {
	    JLabel titleLabel = new JLabel(title);
	    titleLabel.setFont(new Font("", Font.BOLD, 14));
	    //titleLabel.setBackground(Color.WHITE);
	    JTextArea text = new JTextArea();
	    text.setEditable(false);
	    text.setFocusable(false);
	    text.setBackground(Color.WHITE); // color
	    JPanel sectionPanel = new JPanel();
	    sectionPanel.add(titleLabel);
	    sectionPanel.add(text);
	    sectionPanel.setBackground(Color.WHITE); // color
	    leftSide.add(sectionPanel, BorderLayout.WEST);
	    return text; 
	}
	
	private void setupPlayerColorSection(int playerNumber) {
        String colorPath = playerNumber == 1 ? "/assets/Red_Clear.png" : "/assets/Black_Clear.png";
        gamePiece = new ImageIcon(PlayerArea.class.getResource(colorPath));
        
        playerColor = new JLabel(gamePiece);
        //String piecesPlaced = playerNumber == 1 ? "5" : "4";
        //playerColor.setText(piecesPlaced);
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
	
	public void setPlayerName(int playerNumber, String playerName) {
        name.setText("Player " + playerNumber + ": " + playerName);
    }
	
	public void updateNextMove(String playerName) {
	    nextSection.setText(playerName);
	}

	public void updateGameTimer(String time) {
	    gameTimerSection.setText(time);
	}
	
	public void initialTimerSetText(JTextArea text, String time) {
		text.setText(time);
	}

	public void updateTurnTimer(String time) {
	    timerSection.setText(time);
	}

	public void updateGamesWon(int won) {
	    gamesWonSection.setText("" + won);
	}
	
	public void updatePiecesPlaced(int pieces) {
		
	}
	
}
