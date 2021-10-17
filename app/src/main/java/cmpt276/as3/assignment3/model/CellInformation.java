package cmpt276.as3.assignment3.model;

public class CellInformation {
    private boolean hasMine;
    private boolean shownCell;

    public CellInformation() {
        hasMine = false;
        shownCell = false;
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
}
