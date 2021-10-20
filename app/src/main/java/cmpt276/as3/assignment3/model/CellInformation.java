package cmpt276.as3.assignment3.model;

/**
 * Class to store the information of one cell on the grid.
 * Show whether the cell has mine, cell has been revealed yet.
 */
public class CellInformation {
    private boolean hasMine;
    private boolean shownCell;
    private boolean mineClickedTwice;
    private boolean cellScanner;

    public CellInformation() {
        hasMine = false;
        shownCell = false;
        mineClickedTwice = false;
        cellScanner = false;
    }

    public boolean getMineInformation() {
        return hasMine;
    }

    public void setMineInformation(boolean foundMine) {
        hasMine = foundMine;
    }

    public boolean getCellInformation() {
        return shownCell;
    }

    public void setCellInformation(boolean isShown) {
        shownCell = isShown;
    }

    public boolean isMineClickedTwice() {
        return mineClickedTwice;
    }

    public void setMineClickedTwice(boolean mineClickedTwice) {
        this.mineClickedTwice = mineClickedTwice;
    }

    public boolean getCellScanner() {
        return cellScanner;
    }

    public void setCellScanner(boolean cellScanner) {
        this.cellScanner = cellScanner;
    }
}
