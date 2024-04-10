package observers;

/**
 * Interface for menu observer
 *
 * @author Kevin Mosekjaer
 */
public interface MenuObserver {

	/**
	 * Abstract function for restart to be implemented
	 */
	void restartGame();

	/**
	 * Abstract function for change language to be implemented
	 * @param language l
	 */
	void changeLanguage(String language);
}
