package components.gamechat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.GameModel;

/**
 * 
 * @author Kevin Mosekjaer
 */
public class GameChatController {
	
	/**
	 * model
	 */
	private GameChatModel model;
	//private GameModel model;
	
	/**
	 * view
	 */
	private GameChat view;
	
	/**
	 * Constructor
	 * 
	 * @param model model
	 * @param view view
	 */
	public GameChatController(GameChatModel model, GameChat view) {
		this.model=model;
		this.view=view;
		this.view.addChatSendListener(new ChatListener());
	}
	
	/*
	public GameChatController(GameModel model, GameChat view) {
		this.model=model;
		this.view=view;
		this.view.addChatSendListener(new ChatListener());
	}
	*/
	
	/**
     * Getter for player number of message
     * 
     * @return player number
     */
    public int getPlayerNumber() {
    	return 1;
    }
    
	
	/**
	 * CLass implementing ActionListener for the chat
	 * 
	 * @author Kevin Mosekjaer
	 */
	class ChatListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String message = view.getMessageSend();
	        if (!message.isEmpty()) {
	            int player = getPlayerNumber();
	            model.addMessage("Player " + player + ": " + message); 
	            view.displayChatMessage(model.getMessage()); 
	            view.resetTextArea();
	        }
	    }
	}
}

