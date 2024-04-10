package components.gamechat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import observers.ConnectionListener;
import observers.ConnectionObserver;
import observers.IncomingObserver;
import observers.MenuObserver;
import observers.Observer;
import observers.OutgoingObserver;

/**
 * Class holding game chat controller
 *
 * @author Kevin Mosekjaer
 */
public class GameChatController implements Observer, MenuObserver, IncomingObserver, ConnectionObserver { 

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

	private boolean remote=false;

	private List<OutgoingObserver> outgoing = new ArrayList<>();


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
	 */
	@Override
	public void changeTurn(int currentPlayer) {
		model.setPlayerNumber(currentPlayer);
	}

	/**
	 * implemented game finished function
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
	 */
	@Override
	public void changeLanguage(String language) {
		locale = new Locale(language);
		bundle = ResourceBundle.getBundle("messagebundles.MessageBundle", locale);
		view.updateChatLabels(bundle);
	}

	@Override
	public void gameStart() {
		System.out.println("Inside game start in game chat controller");
		model.addMessage("Game Message: First game has started!");
		view.displayChatMessage(model.getMessage());

	}

	@Override
	public void chatIncoming(int player, String chat) {
		System.out.println("Inside chat incoming in game chat, player: " + player + ", chat: " + chat);
		System.out.println("Player 1 name: " + model.getPlayerName(1));
		System.out.println("Player 2 name: " + model.getPlayerName(2));
		if(!(player == 1 || player == 2)) {
			model.addMessage("Game Message: " + chat);
			view.displayChatMessage(model.getMessage());
		} else {
			model.addMessage(model.getPlayerName(player) + ": " + chat);
			view.displayChatMessage(model.getMessage());
		}
	}

	@Override
	public void nameIncoming(int player, String name) {
		System.out.println("Inside name incoming in chat");
		model.setPlayerName(player, name);
		model.addMessage("Game Message: " + name + " has connected");
		view.displayChatMessage(model.getMessage());
	}

	@Override
	public void restartIncoming(int player, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connected(int c) {
		remote = true;

	}

	@Override
	public void disconnected(int c) {
		// TODO Auto-generated method stub

	}

}

