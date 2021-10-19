package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import cmpt276.as3.assignment3.model.GameManager;
import cmpt276.as3.assignment3.model.MineSeeker;
import cmpt276.as3.assignment3.model.OptionsData;


/**
 * Activity that displays the game
 */
public class GameActivity extends AppCompatActivity {
    private OptionsData option = OptionsData.getInstance();
    private GameManager gameManager = GameManager.getInstance();

    private int numCatsFound = 0;
    private int numScanUsed = 0;
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
        displayGameHistory();
        displayNumCatsFound();
        displayNumScanUsed();
        populateButtons();
    }

    private void displayGameHistory() {
        // Display the text stating the total number of games started.
        gameManager.incrementGamesPlayed();
        TextView totalGamesText = findViewById(R.id.totalGames);
        String numGames = "Games Started: " + gameManager.getGamesPlayed();
        totalGamesText.setText(numGames);

        // Display text stating the best score of completed game for that config.
        TextView bestScoreText = findViewById(R.id.highScore);
        int score = gameManager.getScoreOfCurrentConfig(numRows, numMines);
        String configInfo = "Best Score for " + numRows + "x" + numCols + " - " + numMines + " mines: ";

        if (score == 0) {
            String bestScore = configInfo + "N/A";
            bestScoreText.setText(bestScore);
        } else {
            String bestScore = configInfo + score;
            bestScoreText.setText(bestScore);
        }
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

                // Display a message when accessing each button
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);

                        if (numCatsFound == numMines) {
                            FragmentManager manager = getSupportFragmentManager();
                            MessageFragment dialog = new MessageFragment();
                            gameManager.checkToReplaceScore(numRows, numMines, numScanUsed);

                            dialog.show(manager, "MessageDialog");
                            Log.i("TAG","Just Showed the dialog.");
                        }
                    }
                });

                tableRow.addView(button);
                buttons[row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int row, int col) {
        MediaPlayer catSound = MediaPlayer.create(this, R.raw.cat_meow_sound);
        MediaPlayer scanSound = MediaPlayer.create(this, R.raw.scan_clicked);

        if (catSeeker.checkForMine(row, col) == false) {
            scanSound.start();
            if (catSeeker.isCellScannerLocked(row, col) == false) {
                numScanUsed++;
            }
            catSeeker.setCellScanner(row, col);
            displayNumScanUsed();
            startScanner(row, col);
        }

        if (catSeeker.checkForMine(row, col) == true && catSeeker.isCellRevealed(row, col) == false) {
            catSound.start();
            numCatsFound++;
            displayNumCatsFound();
            buttonRevealCat(row, col);

        } else if (catSeeker.checkForMine(row, col) == true && catSeeker.isCellRevealed(row, col) == true) {
            scanSound.start();
            if (catSeeker.isCellScannerLocked(row, col) == false) {
                numScanUsed++;
            }
            catSeeker.setCellScanner(row, col);
            displayNumScanUsed();

            catSeeker.catClickTwice(row, col);
            buttonRevealCat(row, col);
        }
    }

    private void displayNumCatsFound() {
        TextView foundText = (TextView) findViewById(R.id.numCatsFound);
        String result = "Found " + numCatsFound + " of " + numMines + " Cats";
        foundText.setText(result);
    }

    private void displayNumScanUsed() {
        TextView scanText = (TextView) findViewById(R.id.numScansUsed);
        String result = "# Scans used: " + numScanUsed + "";
        scanText.setText(result);
    }

    private void buttonRevealCat(int row, int col) {
        // Display image after the button is clicked.
        if (catSeeker.checkForMine(row, col) == true && catSeeker.isCellRevealed(row, col) == false) {
            Toast.makeText(GameActivity.this, "Cat found. Well done!", Toast.LENGTH_SHORT)
                    .show();
        }

        Button currentButton = buttons[row][col];
        // Lock the button size
        lockButtonSize();
        catSeeker.revealedCell(row, col);

        // Scale the image to fit inside the button
        int newWidth = currentButton.getWidth();
        int newHeight = currentButton.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat2);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        currentButton.setBackground(new BitmapDrawable(resource, scaledBitmap));

        int scanForMines = 0;
        // Check text in the cat cell if it is click again.
        if (catSeeker.isClickTwice(row, col) == true) {
            scanForMines = catSeeker.numMinesInRowCol(row, col);
            buttons[row][col].setText("" + scanForMines);
            buttons[row][col].setTextColor(0xFFFFFFFF);
        }

        // Change text on other empty revealed cell when one cat is found.
        for (int currRow = 0; currRow < numRows; currRow++) {
            for (int currCol = 0; currCol < numCols; currCol++) {
                if (catSeeker.isEmptyCellRevealed(currRow, currCol) == true
                    || catSeeker.isClickTwice(currRow, currCol) == true) {
                    scanForMines = catSeeker.numMinesInRowCol(currRow, currCol);
                    buttons[currRow][currCol].setText("" + scanForMines);
                    buttons[currRow][currCol].setTextColor(0xFFFFFFFF);
                }
            }
        }
    }

    private void startScanner(int row, int col) {
        Button currentButton = buttons[row][col];
        catSeeker.revealedCell(row, col);
        // Lock the button size
        lockButtonSize();

        // Display the number of mines into the empty cell
        int scanForMines = catSeeker.numMinesInRowCol(row, col);
        currentButton.setBackgroundResource(0);
        currentButton.setText("" + scanForMines);

        currentButton.setTypeface(null, Typeface.BOLD);
        currentButton.setTextColor(0xFFFFFFFF);
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
}