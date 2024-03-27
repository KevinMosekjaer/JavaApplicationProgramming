package components.gamechat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Observer;

/**
 * 
 * @author Kevin Mosekjaer
 */
public class GameChatController implements Observer {
	
	/**
	 * model
	 */
	private GameChatModel model;
	
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

	/**
	 * CLass implementing ActionListener for the chat
	 * 
	 * @author Kevin Mosekjaer
	 */
	class ChatListener implements ActionListener {
		
		/**
		 * 
		 */
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String message = view.getMessageSend();
	        if (!message.isEmpty()) {
	            int player = model.getPlayerNumber();
	            model.addMessage("Player " + player + ": " + message); 
	            view.displayChatMessage(model.getMessage()); 
	            view.resetTextArea();
	        }
	    }
	}

	/**
	 * 
	 */
	@Override
	public void changeTurn(int currentPlayer) {
		boolean currentPlayerTurn = this.model.getPlayerNumber() == currentPlayer;
		if(currentPlayerTurn == true) {
			model.setPlayerNumber(1);
		} else {
			model.setPlayerNumber(2);
		}
	}

	/**
	 * 
	 */
	@Override
	public void gameFinished(int playerNumber) {
		model.addMessage("Game Message: Player " + playerNumber + " Wins!");
		view.displayChatMessage(model.getMessage()); 
        view.resetTextArea();		
	} 
	
}

