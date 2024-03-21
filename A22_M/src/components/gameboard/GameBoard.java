
package components.gameboard;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameBoard extends JFrame {
    private int boardSize;
    private int currentPlayer = 1;
    private int numberOfPlayers;
    private static int livingCellNumber = 0;

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private Cell[][] gameBoard;
    private GridLayout grid;

    ImageIcon empty = new ImageIcon(getClass().getResource("/resources/emptycell.png"));
    ImageIcon player1 = new ImageIcon(getClass().getResource("/resources/Picture1.png"));
    ImageIcon player2 = new ImageIcon(getClass().getResource("/resources/Picture2.png"));

    public GameBoard() {
        initializeGameBoard();
    }

	public JPanel getGameBoard() {
		return panel;
	}
	
    private void initializeGameBoard() {
    	
        panel = new JPanel();
        panel.setForeground(Color.blue);
        frame = new JFrame("Connect Four Game");

        setupGameParameters();
        createGameBoard();
        addButtonsToBoard();
        setupFrame();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupGameParameters() {
        numberOfPlayers = 2;
        boardSize = 7;
    }

    private void createGameBoard() {
        gameBoard = new Cell[6][boardSize];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = new Cell();
            }
        }
    }

    private void addButtonsToBoard() {
        buttons = new JButton[6][boardSize];
        grid = new GridLayout(6, boardSize);
        panel.setLayout(grid);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < boardSize; j++) {
                buttons[i][j] = new JButton(empty);
                buttons[i][j].setBackground(Color.blue);
                buttons[i][j].addActionListener(new ButtonClickListener());
                panel.add(buttons[i][j]);
            }
        }
    }

    private void setupFrame() {
        frame.setContentPane(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameBoard());
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int column = -1;

            // Find the column index of the clicked button
            for (int i = 0; i < boardSize; i++) {
                if (clickedButton == buttons[0][i]) {
                    column = i;
                    break;
                }
            }

            // Check if the column is valid and not full
            if (column != -1 && gameBoard[0][column].getCellState() == 0) {
                int row = findLowestEmptyRow(column);
                if (row != -1) {
                    updateGameBoard(row, column);
                    checkForWinner();
                    switchPlayer();
                }
            }
        }

        private int findLowestEmptyRow(int column) {
            for (int i = 5; i >= 0; i--) {
                if (gameBoard[i][column].getCellState() == 0) {
                    return i;
                }
            }
            return -1;
        }

        private void updateGameBoard(int row, int column) {
            gameBoard[row][column].setCellState(currentPlayer);
            livingCellNumber++;
            buttons[row][column].setIcon(currentPlayer == 1 ? player1 : player2);
        }

        private void switchPlayer() {
            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }
        
        private void restartGame() {
            // Reset the game board
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < boardSize; j++) {
                    gameBoard[i][j].setCellState(0);
                    buttons[i][j].setIcon(empty);
                }
            }

            // Reset the current player
            currentPlayer = 1;
            livingCellNumber = 0; 
        }
        
        private void displayWinner(int player) {
            int result = JOptionPane.showConfirmDialog(frame, "Player " + player + " wins! Play again?", "Game Over",  JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                restartGame();
            } else {
                System.exit(0); // Terminate the program if the user doesn't want to play again
            }
        }
        

        private void checkForWinner() {
            // Check horizontal wins
            for (int row = 0; row < 6; row++) {
                for (int col = 0; col < 4; col++) {
                    if (gameBoard[row][col].getCellState() != 0 &&
                        gameBoard[row][col].getCellState() == gameBoard[row][col + 1].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row][col + 2].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row][col + 3].getCellState()) {
                        displayWinner(gameBoard[row][col].getCellState());
                        return;
                    }
                }
            }

            // Check vertical wins
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < boardSize; col++) {
                    if (gameBoard[row][col].getCellState() != 0 &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 1][col].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 2][col].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 3][col].getCellState()) {
                        displayWinner(gameBoard[row][col].getCellState());
                        return;
                    }
                }
            }

            // Check diagonal wins (top-left to bottom-right)
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 4; col++) {
                    if (gameBoard[row][col].getCellState() != 0 &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 1][col + 1].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 2][col + 2].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row + 3][col + 3].getCellState()) {
                        displayWinner(gameBoard[row][col].getCellState());
                        return;
                    }
                }
            }

            // Check diagonal wins (bottom-left to top-right)
            for (int row = 3; row < 6; row++) {
                for (int col = 0; col < 4; col++) {
                    if (gameBoard[row][col].getCellState() != 0 &&
                        gameBoard[row][col].getCellState() == gameBoard[row - 1][col + 1].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row - 2][col + 2].getCellState() &&
                        gameBoard[row][col].getCellState() == gameBoard[row - 3][col + 3].getCellState()) {
                        displayWinner(gameBoard[row][col].getCellState());
                        return;
                    }
                }
            }
        }
    }
}

