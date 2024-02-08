package components.playerArea;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * @author mosek
 *
 */
public class PlayerArea {
	
	/**
	 * 
	 */
	private JPanel panel;
	
	/**
	 * 
	 * @param playerNumber
	 * @param playerName
	 */
	public PlayerArea(int playerNumber, String playerName) {
		initializePlayerArea(playerNumber, playerName);
	}
	
	/**
	 * 
	 * @return panel holding playerArea
	 */
	public JPanel getPlayerArea() {
		return panel;
	}
	
	/**
	 * 
	 * @param playerNumber
	 * @param playerName
	 */
	private void initializePlayerArea(int playerNumber, String playerName) {	
		panel=new JPanel();
		JLabel name = new JLabel("Player "+ playerNumber + ": " + playerName);
		panel.add(name);
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
		
	}
	
}
