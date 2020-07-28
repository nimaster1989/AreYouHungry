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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessQuestions;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;


public class PreferredSearchActivity extends AppCompatActivity {
    private AccessQuestions accessQuestions;

    private TextView textViewName;
    private TextView textViewDifficulty;
    private TextView textViewPrepTime;
    private TextView textViewFlavor;
    private TextView textViewServes;
    private TextView textViewEthnicity;
    private ImageView dishImage;
    private Answers answer;
    private Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        answer = (Answers) getIntent().getSerializableExtra("Answers");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_search);
        setTitle(getTitle());
        textViewName = findViewById(R.id.resultName);
        textViewDifficulty = findViewById(R.id.difficultyTextView);
        textViewPrepTime = findViewById(R.id.prepTimeTextView);
        textViewFlavor = findViewById(R.id.flavorTextView);
        textViewServes = findViewById(R.id.portionSizeTextView);
        textViewEthnicity = findViewById(R.id.ethnicityTextView);
        dishImage = findViewById(R.id.image);
        setFood(answer);



        homeButton = findViewById(R.id.returnHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(PreferredSearchActivity.this, HomeActivity.class);
                PreferredSearchActivity.this.startActivity(intent);
            }
        });

    }

        private void setFood(Answers answers){
            Food food = answers.getFood();
            textViewName.setText(food.getFoodName());
            textViewDifficulty.setText("Difficulty: " +food.getDifficulty());
            textViewPrepTime.setText("Preptime: "+food.getPrepTime()+" minutes");
            textViewFlavor.setText("Flavor: "+food.getFlavour());
            textViewServes.setText("Serves: "+answer.getPortionSize()+" people");
            textViewEthnicity.setText("Ethnicity: "+food.getEthnicity());
        }

}