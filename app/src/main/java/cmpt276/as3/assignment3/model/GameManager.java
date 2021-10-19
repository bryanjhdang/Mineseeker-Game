package cmpt276.as3.assignment3.model;

import java.util.ArrayList;

public class GameManager {

    private int gamesStarted = 0;
    ArrayList<Game> gameConfigList = new ArrayList<>();
    private int[] rowOptions = new int[] {4, 5, 6};
    private int[] numMineOptions = new int[] {6, 10, 15, 20};

    // Make GameScore class into a Singleton
    private static GameManager instance;
    private GameManager() {
        // Private to prevent anyone from instantiating

        // Create all instances of game configurations in gameConfigList
        for (int row = 0; row < rowOptions.length; row++) {
            for (int mine = 0; mine < numMineOptions.length; mine++) {
                gameConfigList.add(new Game(rowOptions[row], numMineOptions[mine]));
            }
        }
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
        gamesStarted++;
    }

    public int getGamesPlayed() {
        return gamesStarted;
    }

    public void setGamesPlayed(int numGames) {
        gamesStarted = numGames;
    }

    public ArrayList<Game> getGameConfigList() {
        return gameConfigList;
    }

    /**
     * Retrieves the index of the corresponding game configuration,
     * and then checks if the new score is lower than the current one
     * @param rows
     * @param mineNum
     * @param newScore
     */
    public void checkToReplaceScore(int rows, int mineNum, int newScore) {
        Game currGameConfig = getGameOfCurrentConfig(rows, mineNum);
        int currScore = currGameConfig.getHighScore();

        if(currScore == 0 || newScore < currScore) {
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
        gamesStarted = 0;

        // Reset high scores
        for(int idx = 0; idx < gameConfigList.size(); idx++) {
            gameConfigList.get(idx).setHighScore(0);
        }
    }

    /**
     * Copy the list of game config
     */
    public void copyConfigList(ArrayList<Game> tempList){
        for (int item = 0; item < tempList.size(); item++) {
            int scorePerConfig = tempList.get(item).getHighScore();

            gameConfigList.get(item).setHighScore(scorePerConfig);
        }
    }
}
