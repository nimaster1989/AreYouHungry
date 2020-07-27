package comp3350.Group2.areyouhungry.ui.preferred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;


public class PreferredSearchActivity extends AppCompatActivity {
    private AccessFoods accessFoods;
    private ArrayList<Food> foodList;
    private ArrayAdapter<Food> foodArrayAdapter;
    private Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Answers answers = (Answers) getIntent().getSerializableExtra("Answers");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_search);
        setTitle(getTitle());
        homeButton = findViewById(R.id.returnHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(PreferredSearchActivity.this, HomeActivity.class);
                PreferredSearchActivity.this.startActivity(intent);
            }
        });

    }


}