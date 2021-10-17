package cmpt276.as3.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * Activity that displays the game
 */
public class GameActivity extends AppCompatActivity {
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 6;
    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    public static Intent gameLaunchIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        populateButtons();
    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(GameActivity.this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < NUM_COLS; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button button = new Button(GameActivity.this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //button.setText(row + "," + col);
                // Make text not clip on the small buttons.
                //button.setPadding(0,0,0,0);
                //lockButtonSize();
                button.setBackgroundResource(R.drawable.house);



                // Display a message when accessing each button
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        // Display image after the button is clicked.
        Button currentButton = buttons[row][col];

        // Lock the button size
        lockButtonSize();
        //currentButton.setBackgroundResource(R.drawable.cat1);

        int newWidth = currentButton.getWidth();
        int newHeight = currentButton.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat1);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        currentButton.setBackground(new BitmapDrawable(resource, scaledBitmap));

        // Change text after clicking.
        currentButton.setText("1");
    }

    private void lockButtonSize() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Button currentButton = buttons[row][col];

                int width = currentButton.getWidth();
                currentButton.setMinWidth(width);
                currentButton.setMaxWidth(width);

                int height = currentButton.getHeight();
                currentButton.setMinHeight(height);
                currentButton.setMaxHeight(height);
            }
        }
    }

    // call getter method to create the game
}