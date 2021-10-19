package cmpt276.as3.assignment3.model;

public class GameScore {
    private int gamesPlayed;

    // Make GameScore class into a Singleton
    private static GameScore instance;
    private GameScore() {
        // Private to prevent anyone from instantiating
    }

    /**
     * Method to retrieve the class without accessing
     * it by the constructor
     * @return the only instance of the class
     */
    public static GameScore getInstance() {
        if(instance == null) {
            instance = new GameScore();
        }
        return instance;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void resetAllGames() {
        // reset all
    }
}
