package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import cmpt276.as3.assignment3.model.Game;
import cmpt276.as3.assignment3.model.GameManager;

/**
 * Menu activity to navigate between playing a game,
 * options, and help screen
 */
public class MenuActivity extends AppCompatActivity {
    private static GameManager gameManager = GameManager.getInstance();
    // Create Json instance
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Intent launchIntent(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeInitialBars();

        // Read information from the existing Json file
        readFromFile(gson);

        setContentView(R.layout.activity_main_menu);
        displayAllButtons();
    }

    /**
     * Method to read information about the previous game states from the JSon file into the ArrayList of Game.
     */
    private static void readFromFile(Gson gson) {
        ArrayList<Game> tempList;

        try {
            // Read from Json file
            Reader readList = Files.newBufferedReader(Paths.get(".\\gameConfigList.json"));
            tempList = gson.fromJson(readList, new TypeToken<ArrayList<Game>>() {}.getType());
            if (tempList.size() != 0) {
                gameManager.copyConfigList(tempList);
            }
            readList.close();

            // Read the number of game played from a different JSon file.
            Reader readGamesPlayed = Files.newBufferedReader(Paths.get(".\\gameNumGames.json"));
            int numGamesPlayed = gson.fromJson(readGamesPlayed, new TypeToken<Integer>() {}.getType());
            if (numGamesPlayed != 0) {
                gameManager.setGamesPlayed(numGamesPlayed);
            }
            readGamesPlayed.close();

        } catch (IOException ioe) {
            Log.i("TAG", "Cannot read information from the file.");
        }
    }


    @Override
    public void onBackPressed() {
        writeToFile(gson);
        super.onBackPressed();
    }

    private void writeToFile(Gson gson) {
        try {
            // Start writing from the ArrayList into json file
            Writer writeList = new FileWriter(".\\gameConfigList.json");
            gson.toJson(gameManager.getGameConfigList(), writeList);
            writeList.close();

            // Save the number of games played into different file.
            Writer writeNumGames = new FileWriter(".\\gameNumGames.json");
            gson.toJson(gameManager.getGamesPlayed(), writeNumGames);
            writeNumGames.close();

        } catch (IOException ioe) {
            Log.i("TAG", "Cannot read information from the file.");
        }
    }

    // https://www.youtube.com/watch?v=jOWW95u15S0&ab_channel=TechProjects
    private void removeInitialBars() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void displayAllButtons() {
        setGameButton();
        setOptionsButton();
        setHelpButton();
    }

    public void setGameButton() {
        Button gameBtn = findViewById(R.id.gameButton);
        gameBtn.setTextColor(0xFF000000);
        gameBtn.setOnClickListener(view -> {
            Intent i = GameActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }

    public void setOptionsButton() {
        Button optionsBtn = findViewById(R.id.optionsButton);
        optionsBtn.setTextColor(0xFF000000);
        optionsBtn.setOnClickListener(view -> {
            Intent i = OptionsActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }

    public void setHelpButton() {
        Button helpBtn = findViewById(R.id.resetButton);
        helpBtn.setTextColor(0xFF000000);
        helpBtn.setOnClickListener(view -> {
            Intent i = HelpActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }
}