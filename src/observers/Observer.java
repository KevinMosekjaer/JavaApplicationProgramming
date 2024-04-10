package observers;

/**
 * Class holds abstract functions to be implemented
 *
 * @author Kevin Mosekjaer
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

	/**
	 * Abstract function for game start
	 */
	void gameStart();

}
