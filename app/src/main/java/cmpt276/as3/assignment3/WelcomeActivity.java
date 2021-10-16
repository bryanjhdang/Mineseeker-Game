package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Initial activity displayed at startup.
 * Displays welcome message and an option to move to the main menu.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMenuButton();
    }

    private void setMenuButton() {
        Button menuBtn = findViewById(R.id.menuButton);
        menuBtn.setOnClickListener(view -> {
            Intent i = MenuActivity.menuLaunchIntent(WelcomeActivity.this);
            startActivity(i);
            finish();
        });
    }
}