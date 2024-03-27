package main;

/**
 * Main class to put all of the game components together and hold main method
 * 
 * @author Kevin Mosekjaer, Matthew Gumienny
 */
public class RunGame {
	
	/**
	 * Constructor
	 */
	public RunGame() {
		
	}

	/**
	 * Main method, calling createGame method
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView();
		GameController controller = new GameController(view, model);
		controller.start();
	}
}
