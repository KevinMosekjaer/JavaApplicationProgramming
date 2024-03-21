package components.gamechat;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class holding game chat component to be called in main
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class GameChat {
	
	/**
	 * JPanels holding main panel and others for formatting
	 */
    private JPanel panel, top, bottom, center, chatArea;
    
    /**
     * text box to write messages
     */
    private JTextField textBox;
    
    /**
     * button to send messages to chat
     */
    private JButton send;
    
    /**
     * Holds title, variable to add messages
     */
    private JLabel chat, newMessage;
    
    /**
     * Holds scrollable chat
     */
    private JScrollPane scrollChat;
    
    /**
     * Borders to separate sections
     */
    private Border leftSide, underTitle;

    /**
     * Constructor initializing game chat
     */
    public GameChat() {
        initializeGameChat();
    }

    /**
     * Getter for JPanel holding the game chat component
     * 
     * @return
     */
	public JPanel getGameChat() {
		return panel;
	}

	/**
	 * Method to create the game chat section
	 */
    private void initializeGameChat() {
        panel = new JPanel(new BorderLayout());
        top = new JPanel();
        bottom = new JPanel(new BorderLayout());
        center = new JPanel(new BorderLayout());
        
        // Top area
        chat = new JLabel("Chat");
        top.add(chat);
        underTitle = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        top.setBorder(underTitle);
        panel.add(top, BorderLayout.NORTH);
        
        // Middle section
        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        
        // Temp messages
        chatMessage("Hello",1);
        chatMessage("Good Luck!",2);
        chatMessage("No",1);
        chatMessage("Yes!",2);
        chatMessage("Toxic behaviour will result in a 30 day ban.",3);
        scrollChat = new JScrollPane(chatArea);
        scrollChat.setBorder(null);
        center.add(scrollChat, BorderLayout.WEST);
        panel.add(center, BorderLayout.CENTER);
        
        
        // Bottom section   
        textBox = new JTextField("Enter a message!");
        send = new JButton("Send");       
        bottom.add(send, BorderLayout.EAST);
        bottom.add(textBox, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH); 
        
        leftSide = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK);
        panel.setBorder(leftSide);
    }
    
    /**
     * Method to add messages to the chat area
     * 
     * @param message message to be added
     * @param player who sent it
     */
    private void chatMessage(String message, int player) {
    	if(player == 1) {
    		newMessage = new JLabel("Player 1: " + message);
        	chatArea.add(newMessage);
    	} else if(player == 2) {
    		newMessage = new JLabel("Player 2: " + message);
        	chatArea.add(newMessage);
    	} else {
    		newMessage = new JLabel("Game message: " + message);
        	chatArea.add(newMessage);
    	}
    }
}
