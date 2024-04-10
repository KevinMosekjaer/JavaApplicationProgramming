package observers;

/**
 * Interface for outgoing messages
 * 
 * @author Kevin Mosekjaer
 */
public interface OutgoingObserver {

	/**
	 * Function for name outgoing
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	public void nameOutgoing(String type, int player, String message);
	
	/**
	 * Function for restart outgoing
	 * @param message m
	 */
	public void restartOutgoing(String type, int player, String message);
	
	/**
	 * Function for game move outgoing
	 * @param type t
	 * @param player p
	 * @param col c
	 */
	public void gameMoveOutgoing(String type, int player, int col);
	
	/**
	 * Function for chat outgoing
	 * @param type t
	 * @param player p
	 * @param message m
	 */
	public void chatOutgoing(String type, int player, String message);	
	
	/**
	 * Function for start game outgoing
	 * @param type type
	 * @param player player
	 * @param message messgae
	 */
	public void startGameOutgoing(String type, int player, String message);
	
}
