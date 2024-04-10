package components.gameboard;

/**
 * Class for game board model
 *
 * @author Kevin Mosekjaer
 */
public class GameBoardModel {

	/**
	 * final rows and columns
	 */
	public static final int ROW = 6, COL=7;

	/**
	 * grid, current players
	 */
	private int grid[][] = new int[ROW][COL], currentPlayer, thisPlayer;

	/**
	 * if player is able to place pieces
	 */
	private boolean ableToPlace= false, hasStarted=false;

	/**
	 * Empty constructor
	 */
	public GameBoardModel() {

	}

	/**
	 * Places piece in grid
	 * @param col c
	 * @return boolean
	 */
	public boolean placePiece(int col) {
		for (int i = ROW - 1; i >= 0; i--) {
			if (grid[i][col] == 0) {
				grid[i][col] = currentPlayer;
				return true;
			}
		}
		return false;
	}

	/**
	 * Functions calling other functions to check if player has won
	 * @return continue or tie
	 */
	public int checkWinner() {
		if (checkHorizontal() || checkVertical() || checkDiagonal()) {
			return currentPlayer;
		}
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (grid[i][j] == 0) {
					return 0;
				}
			}
		}
		return -1;
	}

	/**
	 * Checks if there are 4 in a row starting from a certain location
	 *
	 * @param startRow start row
	 * @param startCol start col
	 * @param row row
	 * @param col col
	 * @return boolean
	 */
	private boolean checkLine(int startRow, int startCol, int row, int col) {
		int player = grid[startRow][startCol];
		if (player == 0) return false;
		for (int i = 1; i < 4; i++) {
			if (grid[startRow + row * i][startCol + col * i] != player) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Function to reset grid
	 */
	public void resetGrid() {
		for (int i = 0; i< ROW; i++) {
			for (int j = 0; j < COL; j++) {
				grid[i][j] = 0;
			}
		}
		currentPlayer = 1;
	}

	/**
	 * Function to check if can place horizontally
	 * @return bolean
	 */
	private boolean checkHorizontal() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL - 3; j++) {
				if (checkLine(i, j, 0, 1)) return true;
			}
		}
		return false;
	}

	/**
	 * Function to check if can place vertically
	 * @return boolean
	 */
	private boolean checkVertical() {
		for (int i = 0; i < ROW - 3; i++) {
			for (int j = 0; j < COL; j++) {
				if (checkLine(i, j, 1, 0)) return true;
			}
		}
		return false;
	}

	/**
	 * Function to check if can place diagonally
	 * @return boolean
	 */
	private boolean checkDiagonal() {
		for (int i = 0; i < ROW - 3; i++) {
			for (int j = 0; j < COL - 3; j++) {
				if (checkLine(i, j, 1, 1)) return true;
			}
		}
		for (int i = 3; i < ROW; i++) {
			for (int j = 0; j < COL - 3; j++) {
				if (checkLine(i, j, -1, 1)) return true;
			}
		}
		return false;
	}

	/**
	 * Gets state of grid cell
	 * @param row r
	 * @param col c
	 * @return value
	 */
	public int getState(int row, int col) {
		if (row >= 0 && row < ROW && col >= 0 && col < COL) {
			return grid[row][col];
		}
		return -1;
	}

	/**
	 * Function for switching player
	 */
	public void switchPlayer() {
		currentPlayer = (currentPlayer == 1) ? 2 : 1;
	}
	/**
	 * Getter for current player
	 * @return player
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int player) {
		this.currentPlayer = player;
	}

	/**
	 * Setter for able to place
	 * @param able a
	 */
	public void setAbleToPlace(boolean able) {
		this.ableToPlace = able;
	}

	/**
	 * Getter for able to place
	 * @return able a
	 */
	public boolean getAbleToPlace() {
		return ableToPlace;
	}


	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}

	/**
	 * Getter for able to place
	 * @return able a
	 */
	public boolean getHasStarted() {
		return hasStarted;
	}

	public int getThisPlayer() {
		return thisPlayer;
	}


	public void setThisPlayer(int player) {
		this.thisPlayer = player;
	}
}
