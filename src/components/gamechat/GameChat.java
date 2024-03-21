package components.gamechat;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class holding game chat component to be called in main
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class GameChat {
	
	/**
	 * JPanels holding main panel and others for formatting
	 */
    private JPanel panel, top, bottom, center;//, chatArea;
    
    private JTextArea chatArea;
    
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
        chatArea = new JTextArea();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        chatArea.setBackground(Color.WHITE);
        chatArea.setEditable(false);
        center.setBackground(Color.WHITE);
        
        center.add(chatArea, BorderLayout.WEST);
        panel.add(center, BorderLayout.CENTER);
        
        
        // Bottom section   
        textBox = new JTextField();
        send = new JButton("Send");       
        bottom.add(send, BorderLayout.EAST);
        bottom.add(textBox, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH); 
        
        leftSide = BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK);
        panel.setBorder(leftSide);
    }
    
    /**
     * Function to display chat messages
     * 
     * @param message m
     */
    public void displayChatMessage(String message) {
    	chatArea.append(message + "\n");
    }

    /**
     * Resets text area after sending message
     */
    public void resetTextArea() {
    	textBox.setText("");
    }
    
    /**
     * Getter for message to be sent
     * 
     * @return message to be sent
     */
    public String getMessageSend() {    	
    	return textBox.getText();
    }
    
    
    
    /**
     * Setting up action listener
     * 
     * @param e e
     */
    public void addChatSendListener(ActionListener e) {
    	send.addActionListener(e);
    }
    
     
}
