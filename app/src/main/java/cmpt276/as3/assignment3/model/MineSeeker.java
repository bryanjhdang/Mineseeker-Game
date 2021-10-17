package cmpt276.as3.assignment3.model;


/**
 * Class that handles the game logic.
 */
public class MineSeeker {
    private OptionsData option = OptionsData.getInstance();
    private int numMines;
    private int numRows;
    private int numCols;
    private CellInformation[][] gameBoard;

    public MineSeeker() {
        numMines = option.getMineNum();
        numRows = option. getRowNum();
        numCols = option.getColumnNum();
        gameBoard = new CellInformation[numRows][numMines];

        randMinesPosition();
    }

    /**
     * Class to randomly place the number of mines into the gameboard
     */
    private void randMinesPosition() {
        int countMines = 0;
        int totalCells = numRows * numCols;

        while (countMines < numMines) {
            int randPosition = (int) ((Math.random() * (totalCells - 0)) + 0;
            int randRow = randPosition / numRows;
            int randCol = randPosition % numCols;

            if (gameBoard[randRow][randCol].getMineInformation() == false) {
                gameBoard[randRow][randCol].setMineInformation(true);
                countMines++;
            }
        }
    }

    /**
     * Class to change the cell status when it is revealed to the user.
     */
    public void revealedCell(int currentRow, int currentCol) {
        gameBoard[currentRow][currentCol].setCellInformation(true);
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

        // Then scan horizontally of the currentCol
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
