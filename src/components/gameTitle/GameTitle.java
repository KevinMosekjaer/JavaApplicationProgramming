package components.gameTitle;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;

public class GameTitle {
	
	private JPanel panel;
	
	public GameTitle() {
		initializeGameTitle();
	}
	
	public JPanel getGameTitle() {
		return panel;
	}
	
	private void initializeGameTitle() {
		panel=new JPanel();
		
		ImageIcon logo = new ImageIcon(GameTitle.class.getResource("Connect_4_Game_Logo.png"));
		
		JLabel label = new JLabel(logo);
		
		panel.add(label, BorderLayout.NORTH);
		
		
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
	}

}
