package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cmpt276.as3.assignment3.model.OptionsData;

/**
 * Options activity to let the user choose board size
 * and number of mines for the next game
 */
public class OptionsActivity extends AppCompatActivity {

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

    private void displayAllSpinners() {
        setUpSpinner(R.id.sizeSpinner, R.array.size);
        setUpSpinner(R.id.mineSpinner, R.array.mines);
    }

    private void setUpSpinner(int spinnerId, int stringArrayId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, stringArrayId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setOptions() {
        setSizeOptions();
        setMineOptions();
    }

    private void setSizeOptions() {
        final int SIZE_OPTIONS = 3;
        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);
        String chosenSize = sizeSpinner.getSelectedItem().toString();
        int chosenSizeIdx = 0;

        for(int i = 0; i < SIZE_OPTIONS; i++) {
            String currSizeOption = getResources().getStringArray(R.array.size)[i];
            if(chosenSize.equals(currSizeOption)) {
                chosenSizeIdx = i;
            }
        }

        switch(chosenSizeIdx) {
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

    private void setMineOptions() {
        final int MINE_OPTIONS = 4;
        Spinner mineSpinner = findViewById(R.id.mineSpinner);
        String chosenMineNum = mineSpinner.getSelectedItem().toString();
        int chosenMineNumIdx = 0;

        for(int i = 0; i < MINE_OPTIONS; i++) {
            String currMineNumOption = getResources().getStringArray(R.array.mines)[i];
            if(chosenMineNum.equals(currMineNumOption)) {
                chosenMineNumIdx = i;
            }
        }

        switch(chosenMineNumIdx) {
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

    @Override
    public void onBackPressed() {
        setOptions();
        super.onBackPressed();
    }
}