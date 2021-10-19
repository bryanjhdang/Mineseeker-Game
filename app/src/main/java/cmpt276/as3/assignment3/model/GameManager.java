package cmpt276.as3.assignment3.model;

import java.util.ArrayList;

public class GameManager {
    final static int ROW_OPTION_ONE = 4;
    final static int ROW_OPTION_TWO = 5;
    final static int ROW_OPTION_THREE = 6;
    final static int MINE_OPTION_ONE = 6;
    final static int MINE_OPTION_TWO = 10;
    final static int MINE_OPTION_THREE = 15;
    final static int MINE_OPTION_FOUR = 20;

    private int gamesPlayed;
    ArrayList<Game> gameConfigList = new ArrayList<>();

    // Make GameScore class into a Singleton
    private static GameManager instance;
    private GameManager() {
        // Private to prevent anyone from instantiating

        // Create all instances of game configurations in gameConfigList
        gameConfigList.add(new Game(ROW_OPTION_ONE, MINE_OPTION_ONE));
        gameConfigList.add(new Game(ROW_OPTION_ONE, MINE_OPTION_TWO));
        gameConfigList.add(new Game(ROW_OPTION_ONE, MINE_OPTION_THREE));
        gameConfigList.add(new Game(ROW_OPTION_ONE, MINE_OPTION_FOUR));
        gameConfigList.add(new Game(ROW_OPTION_TWO, MINE_OPTION_ONE));
        gameConfigList.add(new Game(ROW_OPTION_TWO, MINE_OPTION_TWO));
        gameConfigList.add(new Game(ROW_OPTION_TWO, MINE_OPTION_THREE));
        gameConfigList.add(new Game(ROW_OPTION_TWO, MINE_OPTION_FOUR));
        gameConfigList.add(new Game(ROW_OPTION_THREE, MINE_OPTION_ONE));
        gameConfigList.add(new Game(ROW_OPTION_THREE, MINE_OPTION_TWO));
        gameConfigList.add(new Game(ROW_OPTION_THREE, MINE_OPTION_THREE));
        gameConfigList.add(new Game(ROW_OPTION_THREE, MINE_OPTION_FOUR));
    }

    /**
     * Method to retrieve the class without accessing
     * it by the constructor
     * @return the only instance of the class
     */
    public static GameManager getInstance() {
        if(instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Retrieves the index of the corresponding game configuration,
     * and then checks if the new score is greater than the current one
     * @param rows
     * @param mineNum
     * @param newScore
     */
    public void checkToReplaceScore(int rows, int mineNum, int newScore) {
        Game currGameConfig = getGameOfCurrentConfig(rows, mineNum);
        int currScore = currGameConfig.getHighScore();

        if(newScore > currScore) {
            currGameConfig.setHighScore(newScore);
        }
    }

    /**
     * Returns the high score of the current configuration
     * @param rows
     * @param mineNum
     * @return
     */
    public int getScoreOfCurrentConfig(int rows, int mineNum) {
        Game currGame = getGameOfCurrentConfig(rows, mineNum);
        return currGame.getHighScore();
    }

    /**
     * Returns the Game representing the current configuration
     * @param rows
     * @param mineNum
     * @return
     */
    private Game getGameOfCurrentConfig(int rows, int mineNum) {
        for(int idx = 0; idx < gameConfigList.size(); idx++) {
            Game currGameConfig = gameConfigList.get(idx);
            if(rows == currGameConfig.getRows() &&
               mineNum == currGameConfig.getMineNum()) {
                return currGameConfig;
            }
        }
        return null;
    }

    /**
     * Reset the total amount of games played and the
     * high scores of each configuration
     */
    public void resetAllGames() {
        gamesPlayed = 0;

        // Reset high scores
        for(int idx = 0; idx < gameConfigList.size(); idx++) {
            gameConfigList.get(idx).setHighScore(0);
        }
    }
}
