package cmpt276.as3.assignment3.model;

import java.util.ArrayList;

/**
 * Class that stores a collection of games for 12 configurations.
 * Handles holding the highest score of each game configuration,
 * as well as the number of games started between launches.
 */
public class GameManager {

    private int gamesStarted = 0;
    ArrayList<Game> gameConfigList = new ArrayList<>();

    // Make GameScore class into a Singleton
    private static GameManager instance;
    private GameManager() {
        // Private to prevent anyone from instantiating

        // Create all instances of 12 game configurations in gameConfigList
        int[] rowOptions = new int[] {4, 5, 6};
        int[] numMineOptions = new int[] {6, 10, 15, 20};

        for (int rowOption : rowOptions) {
            for (int numMineOption : numMineOptions) {
                gameConfigList.add(new Game(rowOption, numMineOption));
            }
        }
    }

    /**
     * Method to retrieve the class without accessing
     * it by the constructor
     * @return the only instance of the class
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void incrementGamesPlayed() {
        gamesStarted++;
    }

    public int getGamesPlayed() {
        return gamesStarted;
    }

    public void setGamesPlayed(int numGames) {
        gamesStarted = numGames;
    }

    /**
     * Retrieves the index of the corresponding game configuration,
     * and then checks if the new score is lower than the current one.
     * Set new best game score for that configuration if needed.
     */
    public void checkToReplaceScore(int rows, int mineNum, int newScore) {
        Game currGameConfig = getGameOfCurrentConfig(rows, mineNum);
        int currScore = currGameConfig.getHighScore();

        if (currScore == 0 || newScore < currScore) {
            currGameConfig.setHighScore(newScore);
        }
    }

    /**
     * Returns the best game score of the current configuration.
     */
    public int getScoreOfCurrentConfig(int rows, int mineNum) {
        Game currGame = getGameOfCurrentConfig(rows, mineNum);
        return currGame.getHighScore();
    }

    /**
     * Returns the Game representing the current configuration
     */
    private Game getGameOfCurrentConfig(int rows, int mineNum) {
        for (int idx = 0; idx < gameConfigList.size(); idx++) {
            Game currGameConfig = gameConfigList.get(idx);
            if (rows == currGameConfig.getRows() &&
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
        gamesStarted = 0;
        // Reset high scores
        for(int idx = 0; idx < gameConfigList.size(); idx++) {
            gameConfigList.get(idx).setHighScore(0);
        }
    }
}
