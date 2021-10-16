package cmpt276.as3.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Options activity to let the user choose board size
 * and number of mines for the next game
 */
public class OptionsActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener{

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
        setUpSpinner(R.id.sizeSpinner);
        setUpSpinner(R.id.mineSpinner);
    }

    private void setUpSpinner(int spinnerId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set Spinner to react to a click
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}