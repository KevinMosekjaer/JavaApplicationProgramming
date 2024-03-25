package components.gameboard;

public class GameBoardModel {
	public static final int ROWS = 6;
    public static final int COLS = 7;
    private int grid[][] = new int[ROWS][COLS]; 
    private int currentPlayer = 1;
    
    public GameBoardModel() {
    	
    }
    
    public boolean placePiece(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][col] == 0) {
                grid[row][col] = currentPlayer;
                return true; // Piece placed
            }
        }
        return false; // Column full
    }
    
    public int checkWinner() {
        // Check horizontal, vertical, and both diagonal wins
        if (checkHorizontal() || checkVertical() || checkDiagonal()) {
            return currentPlayer; // Current player wins
        }
        // Check for draw or continue game
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 0) {
                    return 0; // Game continues
                }
            }
        }
        return -1; // Draw
    }
    
    private boolean checkLine(int startRow, int startCol, int dRow, int dCol) {
        int player = grid[startRow][startCol];
        if (player == 0) return false; // Empty cell
        for (int i = 1; i < 4; i++) {
            if (grid[startRow + dRow * i][startCol + dCol * i] != player) {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkHorizontal() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (checkLine(row, col, 0, 1)) return true;
            }
        }
        return false;
    }
    
    private boolean checkVertical() {
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (checkLine(row, col, 1, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean checkDiagonal() {
        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (checkLine(row, col, 1, 1)) return true;
            }
        }
        // Check diagonal (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (checkLine(row, col, -1, 1)) return true;
            }
        }
        return false;
    }
    
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public int[][] getGrid() {
        return grid;
    }
	
}
