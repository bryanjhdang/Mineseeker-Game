package cmpt276.as3.assignment3.model;

/**
 * Class to store the options that the user sets before playing a game,
 * and to retrieve options before creating a new game.
 */
public class OptionsData {
    private int rowNum;
    private int columnNum;
    private int mineNum;

    // Make OptionsData class into a Singleton
    private static OptionsData instance;
    private OptionsData() {
        // Private to prevent anyone from instantiating
    }

    /**
     * Method to retrieve the class without accessing
     * it by the constructor
     * @return the only instance of the class
     */
    public static OptionsData getInstance() {
        if(instance == null) {
            instance = new OptionsData();
        }
        return instance;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public int getMineNum() {
        return mineNum;
    }

    public void setMineNum(int mineNum) {
        this.mineNum = mineNum;
    }
}
