package comp3350.Group2.areyouhungry.ui.preferred;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import comp3350.Group2.areyouhungry.R;


/* This is the page that loads when the user selects. */
public class PreferredActivity extends AppCompatActivity  {
    private String kindOfFood;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred);

    }

}
