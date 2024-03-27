package main;

/**
 * 
 * @author mosek
 *
 */
public interface Observer {
	
	/**
	 * Abstract function for players changing turns
	 * 
	 * @param currentPlayer num
	 */
	void changeTurn(int currentPlayer);
	
	/**
	 * Function for when game has finished
	 * 
	 * @param playerNumber num
	 */
	void gameFinished(int playerNumber);

}
