package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import cmpt276.as3.assignment3.model.CellInformation;
import cmpt276.as3.assignment3.model.MineSeeker;
import cmpt276.as3.assignment3.model.OptionsData;


/**
 * Activity that displays the game
 */
public class GameActivity extends AppCompatActivity {
    private OptionsData option = OptionsData.getInstance();
    private int numMines;
    private int numRows;
    private int numCols;
    Button[][] buttons;
    private MineSeeker catSeeker;

    public static Intent launchIntent(Context c) {
        Intent intent = new Intent(c, GameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeInitialBars();
        setContentView(R.layout.activity_game);

        getOptionForGrid();
        populateButtons();
    }

    private void getOptionForGrid() {
        numMines = option.getMineNum();
        numRows = option.getRowNum();
        numCols = option.getColumnNum();
        buttons = new Button[numRows][numCols];
        catSeeker = new MineSeeker(numMines, numRows, numCols);
    }

    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

        for (int row = 0; row < numRows; row++) {
            // Populate the rows
            TableRow tableRow = new TableRow(GameActivity.this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            // Populate the number of buttons in each row.
            for (int col = 0; col < numCols; col++) {
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button button = new Button(GameActivity.this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                //button.setBackgroundResource(R.drawable.house);

                if (catSeeker.checkForMine(row, col) == true) {
                    button.setText("MINE");
                }

                // Display a message when accessing each button
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                        //updateNumMines(FINAL_ROW, FINAL_COL);
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
        catSeeker.revealedCell(row, col);

        // Lock the button size
        lockButtonSize();

        if (catSeeker.checkForMine(row, col) == true) {
            // Scale the image to fit inside the button
            int newWidth = currentButton.getWidth();
            int newHeight = currentButton.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat1);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            currentButton.setBackground(new BitmapDrawable(resource, scaledBitmap));

            for (int currRow = 0; currRow < numRows; currRow++) {
                for (int currCol = 0; currCol < numCols; currCol++) {
                    if (catSeeker.isEmptyCellRevealed(currRow, currCol) == true) {
                        int scanForMines = catSeeker.numMinesInRowCol(currRow, currCol);
                        buttons[currRow][currCol].setText("" + scanForMines);

                        buttons[currRow][currCol].setTextColor(0xFFFFFFFF);
                    }
                }
            }

        } else {
            // It is an empty cell, start the scanner
            currentButton.setBackgroundResource(0);

            int scanForMines = catSeeker.numMinesInRowCol(row, col);
            currentButton.setText("" + scanForMines);

            currentButton.setPadding(0,0,0,0);
            currentButton.setTextSize(22);
            currentButton.setTypeface(null, Typeface.BOLD);
            currentButton.setTextColor(0xFFFFFFFF);
        }
    }

    private void lockButtonSize() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
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

    // https://www.youtube.com/watch?v=jOWW95u15S0&ab_channel=TechProjects
    private void removeInitialBars() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // call getter method to create the game
}