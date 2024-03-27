package components.menubar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class holding the MenuBar component to be called into main
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
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
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		save.setBorder(new EmptyBorder(0,2,0,30));
		file.add(save);
		file.add(load);
		
		// Sub menu for Game
		restart = new JMenuItem("Restart");
		option = new JMenuItem("Options");
		restart.setBorder(new EmptyBorder(0,2,0,19));
		game.add(restart);
		game.add(option);
		

		// Sub menu for network
		host = new JMenuItem("Host");
		connect = new JMenuItem("Connect");
		disconnect = new JMenuItem("Disconnect");
		host.setBorder(new EmptyBorder(0,2,0,45));
		network.add(host);
		network.add(connect);
		network.add(disconnect);
		
		// Sub menu options for language
		english = new JMenuItem("English");
		french = new JMenuItem("French");
		language.add(english);
		english.setBorder(new EmptyBorder(0,2,0,23));
		language.add(french);
		
		// Sub menu for help
		how = new JMenuItem("How to Play");
		about = new JMenuItem("About");
		how.setBorder(new EmptyBorder(0,2,0,0));
		help.add(how);
		help.add(about);

		// Borders to make things more even
		file.setBorder(new EmptyBorder(5,25,5,25));
	    game.setBorder(new EmptyBorder(5,20,5,20));
	    network.setBorder(new EmptyBorder(5,18,5,18));
	    language.setBorder(new EmptyBorder(5,10,5,10));
	    help.setBorder(new EmptyBorder(5,25,5,30));
				
	    // Adds menu bar options
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(language);
		menuBar.add(help);	
	}		
}
