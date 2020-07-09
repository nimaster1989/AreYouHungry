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


//This is the page that loads when the user selects
public class PreferredActivity extends AppCompatActivity implements OnItemSelectedListener {
    private Spinner foodSpinner;
    private String kindOfFood;
    private Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred);

        setTitle(getTitle());

        foodSpinner = (Spinner) findViewById(R.id.foodSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.foodSpinner));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(myAdapter);
        foodSpinner.setOnItemSelectedListener(this);

        searchButton = (Button) findViewById(R.id.preferredSearch);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                searchFood();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
        kindOfFood = foodSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void searchFood(){
        Intent intent = new Intent(this, PreferredSearchActivity.class);
        intent.putExtra("KIND_OF_FOOD", kindOfFood);
        startActivity(intent);
    }
}
