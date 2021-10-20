package cmpt276.as3.assignment3.model;

/**
 * Class that handles the game logic of the Game Activity.
 * Distribute the mines randomly and handle the scanner for each cell.
 */
public class MineSeeker {

    private int numMines;
    private int numRows;
    private int numCols;
    private CellInformation[][] gameBoard;

    public MineSeeker(int numMines, int numRows, int numCols) {
        this.numMines = numMines;
        this.numRows = numRows;
        this.numCols = numCols;
        gameBoard = new CellInformation[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                CellInformation newCell = new CellInformation();
                gameBoard[row][col] = newCell;
            }
        }
        randMinesPosition();
    }

    /**
     * Method randomly places the number of mines into the game board.
     */
    private void randMinesPosition() {
        int countMines = 0;

        while (countMines < numMines) {
            int randRow = (int) (Math.random() * numRows);
            int randCol = (int) (Math.random() * numCols);

            if (gameBoard[randRow][randCol].getMineInformation() == false) {
                gameBoard[randRow][randCol].setMineInformation(true);
                countMines++;
            }
        }
    }

    /**
     * Methods that handle the cell status when it is revealed to the player.
     */
    public void revealedCell(int currentRow, int currentCol) {
        gameBoard[currentRow][currentCol].setCellInformation(true);
    }

    public boolean isCellRevealed(int currentRow, int currentCol) {
        if (gameBoard[currentRow][currentCol].getCellInformation() == true) {
            return true;
        }
        return false;
    }

    public boolean isEmptyCellRevealed(int currentRow, int currentCol) {
        if (gameBoard[currentRow][currentCol].getMineInformation() == false
                && gameBoard[currentRow][currentCol].getCellInformation() == true) {
            return true;
        }
        return false;
    }

    public boolean checkForMine(int row, int col) {
        if (gameBoard[row][col].getMineInformation() == true) {
            return true;
        }
        return false;
    }

    public void catClickTwice(int currentRow, int currentCol) {
        gameBoard[currentRow][currentCol].setMineClickedTwice(true);
    }

    public boolean isClickTwice(int currentRow, int currentCol) {
        if (gameBoard[currentRow][currentCol].isMineClickedTwice() == true) {
            return true;
        }
        return false;
    }

    public void setCellScanner(int currentRow, int currentCol) {
        gameBoard[currentRow][currentCol].setCellScanner(true);
    }

    public boolean isCellScannerLocked(int currentRow, int currentCol) {
        if (gameBoard[currentRow][currentCol].getCellScanner() == true) {
            return true;
        }
        return false;
    }

    /**
     * Class works as a scanner to return the number of mines in that row and column.
     */
    public int numMinesInRowCol(int currentRow, int currentCol) {
        int countMines = 0;

        // First scan horizontally of the currentRow for mines.
        for (int col = 0; col < numCols; col++) {
            if (gameBoard[currentRow][col].getMineInformation() == true
                && gameBoard[currentRow][col].getCellInformation() == false) {
                countMines++;
            }
        }

        // Then scan vertically of the currentColumn.
        for (int row = 0; row < numRows; row++) {
            if (row != currentRow) {
                if (gameBoard[row][currentCol].getMineInformation() == true
                        && gameBoard[row][currentCol].getCellInformation() == false) {
                    countMines++;
                }
            }
        }
        return countMines;
    }
}
