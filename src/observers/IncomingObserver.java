package observers;

/**
 * Observer interface for all incoming messages
 * 
 * @author Kevin Mosekjaer
 */
public interface IncomingObserver {
	
	/**
	 * Function for restart request and accept
	 * @param player p
	 * @param message m
	 */
	default void restartIncoming(int player, String message) { }
				
	/**
	 * Function for game moves incoming
	 * @param player p
	 * @param col c
	 */
	default void gameMoveIncoming(int player, int col) { } 
	
	/**
	 * Function for start game request and accept
	 * @param player p
	 * @param message m
	 */
	default void startGameIncoming(int player, String message) { }
	
	/**
	 * Function for chat messages incoming
	 * @param player p
	 * @param chat c
	 */
	default void chatIncoming(int player, String chat) { }
	
	/**
	 * Function for name incoming
	 * @param player p
	 * @param name n
	 */
	default void nameIncoming(int player, String name) { }	
}
