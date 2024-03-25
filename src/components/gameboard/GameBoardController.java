package components.gameboard;

public class GameBoardController {

	private GameBoardModel model;
	private GameBoard view;
	
	public GameBoardController(GameBoardModel model, GameBoard view) {
		this.model = model;
		this.view = view;
		initializeController();
	}
	
	private void initializeController() {
		for (int i = 0; i < model.COLS; i++) {
            int column = i;
            view.getPlacePieceButton(i).addActionListener(e -> placePiece(column));
        }
	}
	
	public void placePiece(int col) {
        boolean placed = model.placePiece(col);
        if (placed) {
            view.updateGrid(model.getGrid(), model.getCurrentPlayer());
            int winner = model.checkWinner();
            if (winner != 0) {
                view.showWinner(winner);
            } else {
                model.switchPlayer();
            }
        }
    }
	
	
}
