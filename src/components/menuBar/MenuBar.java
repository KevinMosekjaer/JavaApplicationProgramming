package components.menuBar;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

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
		
		
		int vertical = 5, horizontal = 20;
	    Border menuBorder = BorderFactory.createEmptyBorder(vertical, horizontal, vertical, horizontal);
	    file.setBorder(menuBorder);
	    game.setBorder(menuBorder);
	    network.setBorder(menuBorder);
	    language.setBorder(menuBorder);
	    help.setBorder(menuBorder);
	    
	    
				
		GridLayout menuLayout = new GridLayout();	
		//menuBar.setLayout(new GridLayout());
		//menuBar.setLayout(new FlowLayout());
				
		menuBar.add(file);
		menuBar.add(game);
		menuBar.add(network);
		menuBar.add(language);
		menuBar.add(help);
		
	}
		
}
