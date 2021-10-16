package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Help activity to display game descriptions, authors, and
 * sources for images and resources
 */
public class HelpActivity extends AppCompatActivity {

    public static Intent helpLaunchIntent(Context c) {
        Intent intent = new Intent(c, HelpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView test = findViewById(R.id.descriptionText);
        test.setText("hello");
    }

    // make it so that the text is for each of the description upon creation
}