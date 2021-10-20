package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Main Menu Activity to navigate between playing a game, options, and help screen.
 */
public class MenuActivity extends AppCompatActivity {

    public static Intent launchIntent(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeInitialBars();
        setContentView(R.layout.activity_main_menu);
        displayAllButtons();
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