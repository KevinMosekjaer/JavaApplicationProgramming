package components.gameChat;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class GameChat {
	
	private JPanel panel;
	
	public GameChat() {
		initializeGameChat();
	}
	
	public JPanel getGameChat() {
		return panel;
	}
	
	private void initializeGameChat() {
		JLabel chat = new JLabel("Chat");
		panel=new JPanel();
		panel.add(chat);
		
		Border chatBorder = BorderFactory.createLineBorder(Color.black);
		panel.setBorder(chatBorder);
	}

}
