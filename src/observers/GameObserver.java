package observers;

/**
 * Observer interface for game functionality
 * 
 * @author Kevin Mosekjaer
 */
public interface GameObserver {
	
	/**
	 * Abstract function for restart to be implemented
	 */
	default void restartGame() { }

	/**
	 * Function for change language to be implemented
	 * @param language l
	 */
	default void changeLanguage(String language) { }
	
	/**
	 * Function for players changing turns
	 *
	 * @param currentPlayer num
	 */
	default void changeTurn(int currentPlayer) { }

	/**
	 * Function for when game has finished
	 *
	 * @param playerNumber num
	 */
	default void gameFinished(int playerNumber) { }

	/**
	 * Function for game start
	 */
	default void gameStart() { }
	
	/**
	 * Function to send chat messages 
	 * @param message m
	 */
	default void sendChat(String message) { }

}
