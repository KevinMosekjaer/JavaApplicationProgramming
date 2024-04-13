package components.playerarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
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
	private JPanel panel, rightSide, leftSide, top, playerColorPanel, namePanel;

	/**
	 * JLabels for player area components
	 */
	private JLabel playerColor, name,titleLabel;

	/**
	 * text section
	 */
	private JTextArea nextSection, timerSection, gameTimerSection, gamesWonSection;

	/**
	 * player number
	 */
	private int playerNumber;

	/**
	 * player name
	 */
	private String playerName;

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
	 * @param playerNumber num
	 * @param playerName name
	 */
	private void initializePlayerArea(int playerNumber, String playerName) {
		panel=new JPanel(new BorderLayout());
		rightSide = new JPanel();
		leftSide = new JPanel();
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.setBorder(new EmptyBorder(5,0,10,0));
		top.setBackground(Color.WHITE);


		name = new JLabel("Player " + playerNumber + ": " + playerName);
		name.setFont(new Font("", Font.BOLD, 20));
		namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		namePanel.add(name);
		namePanel.setBackground(Color.WHITE); 
		top.add(namePanel);

		nextSection = setupPlayerAreaSection("Next Move: ");
		timerSection = setupPlayerAreaSection("Turn Time Elapsed: ");
		timerSection.setText("00:00");
		gameTimerSection = setupPlayerAreaSection("Game Time Elapsed: ");
		gameTimerSection.setText("00:00");
		gamesWonSection = setupPlayerAreaSection("Games Won: ");

		setupPlayerColorSection(playerNumber);


		// Adds everything together
		panel.add(top, BorderLayout.NORTH);
		panel.add(leftSide, BorderLayout.WEST);
		panel.add(rightSide, BorderLayout.EAST);

		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
		panel.setBackground(Color.WHITE); 
	}

	/**
	 * Sets up player area text section
	 * @param title title
	 * @return text
	 */
	private JTextArea setupPlayerAreaSection(String title) {
		titleLabel = new JLabel();
		titleLabel.setText(title);
		titleLabel.setFont(new Font("", Font.BOLD, 14));
		JTextArea text = new JTextArea(); 
		text.setEditable(false);
		text.setFocusable(false);
		text.setBackground(Color.WHITE); 
		JPanel sectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sectionPanel.add(titleLabel);
		sectionPanel.add(text); 
		sectionPanel.setBackground(Color.WHITE);
		sectionPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT); 
		leftSide.add(sectionPanel); 
		return text;
	}


	/**
	 * Sets up player color section
	 * @param playerNumber num
	 */
	private void setupPlayerColorSection(int playerNumber) {
		String colorPath = playerNumber == 1 ? "/assets/Red_Clear.png" : "/assets/Black_Clear.png";
		gamePiece = new ImageIcon(PlayerArea.class.getResource(colorPath));
		playerColor = new JLabel(gamePiece);
		playerColor.setText("0");
		playerColor.setFont(new Font("", Font.BOLD, 20));
		playerColor.setForeground(Color.WHITE);
		playerColor.setHorizontalTextPosition(SwingConstants.CENTER);
		playerColor.setVerticalTextPosition(SwingConstants.CENTER);
		playerColorPanel = new JPanel(new BorderLayout());
		playerColorPanel.add(playerColor);
		playerColorPanel.setBackground(Color.WHITE); // color
		rightSide.add(playerColorPanel);
	}

	/**
	 * Sets player name
	 * @param playerNumber num
	 * @param playerName name
	 */
	public void setPlayerName(int playerNumber, String playerName) {
		name.setText("Player " + playerNumber + ": " + playerName);
	}

	/**
	 * Updates next move
	 * @param playerName name
	 */
	public void updateNextMove(String playerName) {
		nextSection.setText(playerName);
	}

	/**
	 * Updates game timer
	 * @param time t
	 */
	public void updateGameTimer(String time) {
		gameTimerSection.setText(time);
	}

	/**
	 * Set initial time
	 * @param text te
	 * @param time ti
	 */
	public void initialTimerSetText(JTextArea text, String time) {
		text.setText(time);
	}

	/**
	 * updates turn timer
	 * @param time t
	 */
	public void updateTurnTimer(String time) {
		timerSection.setText(time);
	}

	/**
	 * updates games won
	 * @param won w
	 */
	public void updateGamesWon(int won) {
		gamesWonSection.setText("" + won);
	}

	/**
	 * updates pieces placed
	 * @param pieces p
	 */
	public void updatePiecesPlaced(int pieces) {
		playerColor.setText("" + pieces);
	}

	/**
	 * Function to update labels
	 * @param bundle b
	 */
	public void updatePlayerLabels(ResourceBundle bundle) {
		nextSection.setText(bundle.getString("next"));
		timerSection.setText(bundle.getString("turnTime"));
		gameTimerSection.setText(bundle.getString("gameTime"));
		gamesWonSection.setText(bundle.getString("gamesWon"));
		name.setText(bundle.getString("player") + ": " + playerNumber);
	}


}
