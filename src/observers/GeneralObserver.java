package observers;

public interface GeneralObserver {
	
	/**
	 * Abstract function for restart to be implemented
	 */
	void restartGame();

	/**
	 * Function for change language to be implemented
	 * @param language l
	 */
	void changeLanguage(String language);
	
	/**
	 * Function for players changing turns
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
	 * Function for game start
	 */
	void gameStart();

}
