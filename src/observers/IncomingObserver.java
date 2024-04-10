package observers;

/**
 * Observer for all incoming messages
 * 
 * @author Kevin Mosekjaer
 */
public interface IncomingObserver {
	
	/**
	 * Function for restart request and accept
	 * @param player p
	 * @param message m
	 */
	default public void restartIncoming(int player, String message) { }
			
	
	/**
	 * Function for game moves incoming
	 * @param player p
	 * @param col c
	 */
	default public void gameMoveIncoming(int player, int col) { } 
	
	/**
	 * Function for start game request and accept
	 * @param player p
	 * @param message m
	 */
	default public void startGameIncoming(int player, String message) { }
	
	/**
	 * Function for chat messages incoming
	 * @param player p
	 * @param chat c
	 */
	default public void chatIncoming(int player, String chat) { }
	
	/**
	 * Function for name incoming
	 * @param player p
	 * @param name n
	 */
	default public void nameIncoming(int player, String name) { }	
}
