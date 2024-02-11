package components.gameTitle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * 
 * 
 * @author mosek
 *
 */
public class GameTitle {
	
	/**
	 * JPanel holding the game title
	 */
	private JPanel panel;
	
	/**
	 * Constructor for game title
	 */
	public GameTitle() {
		initializeGameTitle();
	}
	
	/**
	 * Getter returning the JPanel holding the game title
	 * 
	 * @return JPanel title
	 */
	public JPanel getGameTitle() {
		return panel;
	}
	
	/**
	 * Function creating the game title component and adding to a JPanel
	 */
	private void initializeGameTitle() {
		panel=new JPanel();
		
		ImageIcon logo = new ImageIcon(GameTitle.class.getResource("Connect_4_Game_Logo.png"));
		
		JLabel label = new JLabel(logo);
		
		panel.add(label, BorderLayout.NORTH);
		
		
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
	}

}
