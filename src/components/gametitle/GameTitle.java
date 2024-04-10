package components.gametitle;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class holding the Game logo/title to be called in main
 *
 * @author Kevin Mosekjaer
 */
public class GameTitle {

	/**
	 * JPanel holding the game title
	 */
	private JPanel panel;

	/**
	 * ImageIcon for game logo
	 */
	private ImageIcon logo;

	/**
	 * JLabel holding logo
	 */
	private JLabel label;

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
		logo = new ImageIcon(GameTitle.class.getResource("/assets/Connect4_Logo.png"));
		label = new JLabel(logo);
		panel.add(label, BorderLayout.NORTH);
		panel.setBackground(Color.decode("#cde3fa"));
	}
}
