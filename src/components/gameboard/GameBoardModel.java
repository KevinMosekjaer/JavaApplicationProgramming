package components.gameboard;

/**
 * 
 * 
 * @author Kevin Mosekjaer
 */
public class GameBoardModel {
	
	/**
	 * 
	 */
	public static final int ROW = 6, COL=7;
	
	/**
	 * 
	 */
    private int grid[][] = new int[ROW][COL], currentPlayer=1; 
    
    /**
     * 
     */
    boolean ableToPlace= false;
    
    /**
     * Empty constructor
     */
    public GameBoardModel() {
    	
    }
    
    /**
     * 
     * @param col
     * @return
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
     * 
     * @return
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
     * 
     * @param startRow
     * @param startCol
     * @param dRow
     * @param dCol
     * @return
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
     * 
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
     * 
     * @return
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
     * 
     * @return
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
     * 
     * @return
     */
    private boolean checkDiagonal() {
        // Check diagonal (top-left to bottom-right)
        for (int i = 0; i < ROW - 3; i++) {
            for (int j = 0; j < COL - 3; j++) {
                if (checkLine(i, j, 1, 1)) return true;
            }
        }
        // Check diagonal (bottom-left to top-right)
        for (int i = 3; i < ROW; i++) {
            for (int j = 0; j < COL - 3; j++) {
                if (checkLine(i, j, -1, 1)) return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     */
    public int getState(int row, int col) {
        if (row >= 0 && row < ROW && col >= 0 && col < COL) {
            return grid[row][col];
        }
        return -1; 
    }
    
    /**
     * 
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
    /**
     * 
     * @return
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * 
     * @param able
     */
    public void setAbleToPlace(boolean able) {
    	this.ableToPlace = able;
    }
    
    /**
     * 
     * @return
     */
    public boolean getAbleToPlace() {
    	return ableToPlace;
    }
    
}
