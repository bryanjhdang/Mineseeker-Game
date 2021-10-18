package cmpt276.as3.assignment3.model;

public class CellInformation {
    private boolean hasMine;
    private boolean shownCell;
    private boolean mineClickedTwice;

    public CellInformation() {
        hasMine = false;
        shownCell = false;
        mineClickedTwice = false;
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
}
