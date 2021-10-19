package cmpt276.as3.assignment3.model;

public class Game {
    private int rows;
    private int mineNum;
    private int highScore;

    public Game(int rows, int mineNum) {
        this.rows = rows;
        this.mineNum = mineNum;
        this.highScore = 0;
    }

    public int getRows() {
        return rows;
    }

    public int getMineNum() {
        return mineNum;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
