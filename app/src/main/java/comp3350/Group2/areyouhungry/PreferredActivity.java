package comp3350.Group2.areyouhungry;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;

public class PreferredActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("prefered activity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred);

        Spinner foodSpinner = (Spinner) findViewById(R.id.foodSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.foodSpinner));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(myAdapter);



    }
}
