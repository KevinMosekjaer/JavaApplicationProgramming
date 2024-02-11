package components.menuBar;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class holding the MenuBar component to be called into main
 * 
 * @author Kevin Mosekjaer
 */
public class MenuBar {
	
	/**
	 * Menu bar holding options
	 */
	private static JMenuBar menuBar;
	
	/**
	 * Menu options
	 */
	private static JMenu file, game, network, language, help;
	
	/**
	 * Sub menu options
	 */
	private static JMenuItem english, french, host, connect, disconnect, how, about, restart, save, load, option;
	
	/**
	 * Constructor for MenuBar
	 */
	public MenuBar() {
		initializeMenuBar();
	}
	
	/**
	 * Getter for the menu bar
	 * 
	 * @return JMenuBar menu
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	/**
	 * Method creating the menu bar, menu options, and sub menu
	 */
	private static void initializeMenuBar() {
		menuBar=new JMenuBar();
		
		file = new JMenu("File");
		game = new JMenu("Game");
		network = new JMenu("Network");
		language = new JMenu("Language");
		help = new JMenu("Help");
		
		// Sub menu options for file
		save = new JMenuItem("Save Game");
		load = new JMenuItem("Load Game");
		file.add(save);
		file.add(load);
		
		// Sub menu for Game
		restart = new JMenuItem("Restart Game");
		option = new JMenuItem("Game Options");
		game.add(restart);
		game.add(option);
		

		// Sub menu for network
		host = new JMenuItem("Host");
		connect = new JMenuItem("Connect");
		disconnect = new JMenuItem("Disconnect");
		network.add(host);
		network.add(connect);
		network.add(disconnect);
		
		// Sub menu options for language
		english = new JMenuItem("English");
		french = new JMenuItem("French");
		language.add(english);
		language.add(french);
		
		// Sub menu for help
		how = new JMenuItem("How to Play");
		about = new JMenuItem("About");
		help.add(how);
		help.add(about);
		
		
		int vertical = 5, horizontal = 20;
	    Border menuBorder = BorderFactory.createEmptyBorder(vertical, horizontal, vertical, horizontal);
	    file.setBorder(menuBorder);
	    game.setBorder(menuBorder);
	    network.setBorder(menuBorder);
	    language.setBorder(menuBorder);
	    help.setBorder(menuBorder);
				
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(language);
		menuBar.add(help);
		
	}
		
}
