package components.menuBar;

import javax.swing.*;

public class MenuBar {
	
	private static JMenuBar menuBar;
	
	private static JMenu file, game, network, language, help;
	
	private static JMenuItem english, french;
	
	
	public MenuBar() {
		initializeMenuBar();
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	private static void initializeMenuBar() {
		menuBar=new JMenuBar();
		
		file = new JMenu("File");
		game = new JMenu("Game");
		network = new JMenu("Network");
		language = new JMenu("Language");
		help = new JMenu("Help");
		
		english = new JMenuItem("English");
		french = new JMenuItem("French");
		
		language.add(english);
		language.add(french);
			
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(language);
		menuBar.add(help);
		
	}
		
}
