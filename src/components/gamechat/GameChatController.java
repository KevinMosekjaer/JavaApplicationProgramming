package components.gamechat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import observers.ConnectionObserver;
import observers.GameObserver;
import observers.IncomingObserver;
import observers.OutgoingObserver;

/**
 * Class holding game chat controller
 *
 * @author Kevin Mosekjaer
 */
public class GameChatController implements GameObserver, IncomingObserver, ConnectionObserver { 

	/**
	 * model
	 */
	private GameChatModel model;

	/**
	 * view
	 */
	private GameChat view;

	/**
	 * resource bundle
	 */
	private ResourceBundle bundle;

	/**
	 * locale
	 */
	private Locale locale;

	/**
	 * variable for if remote or not
	 */
	private boolean remote=false, disconnectSent=false;;

	/**
	 * List of outgoing observers
	 */
	private List<OutgoingObserver> outgoing = new ArrayList<>();


	/**
	 * Constructor
	 * @param model model
	 * @param view view
	 */
	public GameChatController(GameChatModel model, GameChat view) {
		this.model=model;
		this.view=view;
		this.view.addChatSendListener(new ChatListener());
	}

	/**
	 * Function to add outgoing observers
	 * @param chat c
	 */
	public void addChatSend(OutgoingObserver chat) {
		outgoing.add(chat);
	}

	/**
	 * CLass implementing ActionListener for the chat
	 *
	 * @author Kevin Mosekjaer
	 */
	class ChatListener implements ActionListener {

		/**
		 * implemented action performed function for chat
		 * @param e e
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = view.getMessageSend();
			if (!message.isEmpty()) {
				if(remote) {
					int player = model.getMyPlayerNumber();
					model.addMessage(model.getPlayerName(player) + ": " + message);
					view.displayChatMessage(model.getMessage());
					view.resetTextArea();
					outgoing.forEach(out -> out.chatOutgoing("cm", player, message));
				} else {
					model.addMessage("Player " + model.getPlayerNumber() + ": " + message);
					view.displayChatMessage(model.getMessage());
					view.resetTextArea();
				}
			}
		}
	}

	/**
	 * implemented change turn function
	 * @param currentPlayer player
	 */
	@Override
	public void changeTurn(int currentPlayer) {
		model.setPlayerNumber(currentPlayer);
	}

	/**
	 * implemented game finished function
	 * @param playerNumber num
	 */
	@Override
	public void gameFinished(int playerNumber) {
		if(remote) {
			model.addMessage("Game Message: " + model.getPlayerName(playerNumber) + " Wins!");
		} else {
			model.addMessage("Game Message: Player " + playerNumber + " Wins!");
		}	
		view.displayChatMessage(model.getMessage());
		view.resetTextArea();
	}

	/**
	 * implemented restart game function
	 */
	@Override
	public void restartGame() {
		model.addMessage("Game Message: Game has restarted!");
		view.displayChatMessage(model.getMessage());

	}

	/**
	 * implemented change language function
	 * @param language l
	 */
	@Override
	public void changeLanguage(String language) {
		locale = new Locale(language);
		bundle = ResourceBundle.getBundle("messagebundles.MessageBundle", locale);
		view.updateChatLabels(bundle);
	}

	/**
	 * Implemented function for game starting
	 */
	@Override
	public void gameStart() {
		model.addMessage("Game Message: First game has started!");
		view.displayChatMessage(model.getMessage());
	}

	/**
	 * Implemented function for sending chat messages to chat
	 * @param message m
	 */
	@Override
	public void sendChat(String message) {
		model.addMessage("Game Message: " + message);
		view.displayChatMessage(model.getMessage());
	}

	/**
	 * Implemented function for chat incoming from other user
	 * @param player p
	 * @param chat c
	 */
	@Override
	public void chatIncoming(int player, String chat) {
		if(!(player == 1 || player == 2)) {
			model.addMessage("Game Message: " + chat);
			view.displayChatMessage(model.getMessage());
		} else {
			model.addMessage(model.getPlayerName(player) + ": " + chat);
			view.displayChatMessage(model.getMessage());
		}
	}

	/**
	 * Implemented function for name incoming from server/client
	 * @param player p
	 * @param name n
	 */
	@Override
	public void nameIncoming(int player, String name) {
		model.setPlayerName(player, name);
		model.addMessage("Game Message: " + name + " has connected");
		view.displayChatMessage(model.getMessage());
	}

	/**
	 * Implemented function for connection to server/client
	 * @param c c
	 */
	@Override
	public void connected(int c) {
		remote = true;
	}
	
	/**
	 * Implemented function for disconnection from client/server
	 * @param c c
	 */
	@Override
	public void disconnected(int c) {
		if(disconnectSent==false) {
			disconnectSent=true;
			model.addMessage("Game Message: Network issues, disconnected...");
			view.displayChatMessage(model.getMessage());
		} else {
			return;
		}
	}

}

