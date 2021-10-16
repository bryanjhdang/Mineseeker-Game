package cmpt276.as3.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import cmpt276.as3.assignment3.model.OptionsData;

/**
 * Options activity to let the user choose board size
 * and number of mines for the next game
 */
public class OptionsActivity extends AppCompatActivity {
    final int SIZE_OPTIONS = 3;
    final int MINE_OPTIONS = 4;
    final String TAG = "TAG_MSG";
    private OptionsData optionsData = OptionsData.getInstance();

    public static Intent optionsLaunchIntent(Context c) {
        Intent intent = new Intent(c, OptionsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        displayAllSpinners();
    }

    /**
     * Calling method to create both size and mineNum Spinner
     */
    private void displayAllSpinners() {
        setUpSpinner(R.id.sizeSpinner, R.array.size);
        setUpSpinner(R.id.mineSpinner, R.array.mines);
    }

    /**
     * Create a Spinner and fill it using a string array in strings.xml
     * @param spinnerId is the spinner to fill
     * @param stringArrayId is the array of strings to fill the Spinner
     */
    private void setUpSpinner(int spinnerId, int stringArrayId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, stringArrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Return the index in the array of strings that matches with the String parameter
     * @param arraySize
     * @param arrayId is the array of strings to check with
     * @param chosenItem is the String being checked
     * @return
     */
    private int getStringArrayIdx(int arraySize, int arrayId, String chosenItem) {
        int chosenIdx = 0;
        for(int i = 0; i < arraySize; i++) {
            String currOption = getResources().getStringArray(arrayId)[i];
            if(chosenItem.equals(currOption)) {
                chosenIdx = i;
            }
        }
        return chosenIdx;
    }

    /**
     * Calling method to use Singleton class' setters for both mines and size
     */
    private void setOptions() {
        setSizeOptions();
        setMineOptions();
    }

    /**
     * Use the setters from the Singleton class to set the values for rows
     * and columns depending on the Spinner option chosen
     */
    private void setSizeOptions() {
        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);
        String chosenSize = sizeSpinner.getSelectedItem().toString();
        int sizeIdx = getStringArrayIdx(SIZE_OPTIONS, R.array.size, chosenSize);

        switch(sizeIdx) {
            case 0:
                optionsData.setRowNum(4);
                optionsData.setColumnNum(6);
                break;
            case 1:
                optionsData.setRowNum(5);
                optionsData.setColumnNum(10);
                break;
            case 2:
                optionsData.setRowNum(6);
                optionsData.setColumnNum(15);
                break;
            default:
                Log.d(TAG, "Something went wrong with reading the size index");
                break;
        }
    }

    /**
     * Use the setters from the Singleton class to set the value for
     * the number of mines depending on the Spinner option chosen
     */
    private void setMineOptions() {
        Spinner mineSpinner = findViewById(R.id.mineSpinner);
        String chosenMineNum = mineSpinner.getSelectedItem().toString();
        int mineNumIdx = getStringArrayIdx(MINE_OPTIONS, R.array.mines, chosenMineNum);

        switch(mineNumIdx) {
            case 0:
                optionsData.setMineNum(6);
                break;
            case 1:
                optionsData.setMineNum(10);
                break;
            case 2:
                optionsData.setMineNum(15);
                break;
            case 3:
                optionsData.setMineNum(20);
                break;
            default:
                Log.d(TAG, "Something went wrong with reading the mine index");
                break;
        }
    }

    /**
     *  Call the setters of the Singleton class to save the options
     *  when leaving the options activity
     */
    @Override
    public void onBackPressed() {
        setOptions();
        super.onBackPressed();
    }
}