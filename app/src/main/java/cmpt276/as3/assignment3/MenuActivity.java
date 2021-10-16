package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Menu activity to navigate between playing a game,
 * options, and help screen
 */
public class MenuActivity extends AppCompatActivity {

    public static Intent launchIntent(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        displayAllButtons();
    }

    public void displayAllButtons() {
        setGameButton();
        setOptionsButton();
        setHelpButton();
    }

    public void setGameButton() {
        Button gameBtn = findViewById(R.id.gameButton);
        gameBtn.setOnClickListener(view -> {
            Intent i = GameActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }

    public void setOptionsButton() {
        Button optionsBtn = findViewById(R.id.optionsButton);
        optionsBtn.setOnClickListener(view -> {
            Intent i = OptionsActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }

    public void setHelpButton() {
        Button helpBtn = findViewById(R.id.helpButton);
        helpBtn.setOnClickListener(view -> {
            Intent i = HelpActivity.launchIntent(MenuActivity.this);
            startActivity(i);
        });
    }
}