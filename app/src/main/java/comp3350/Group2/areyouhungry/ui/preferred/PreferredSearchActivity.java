package comp3350.Group2.areyouhungry.ui.preferred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessQuestions;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;


public class PreferredSearchActivity extends AppCompatActivity{
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
    private Button recipeButton;

    private String foodId = "1"; //Placeholder variable. change it when we have our SQL query. if SQL query returns an integer, turn into string

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

        recipeButton = findViewById(R.id.viewRecipeButton);
        recipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();

                Intent intent = new Intent(context, FoodDetailActivity.class);

                // Takes the argument (FoodDetailFragment.ARG_ITEM_ID, and FOODID value (string))
                intent.putExtra(FoodDetailFragment.ARG_ITEM_ID, foodId);
                context.startActivity(intent);
            }
        });

    }

        private void setFood(Answers answers){
            Food food = answers.getFoodBasedOnAnswers();
            foodId = food.getFoodID();
            textViewName.setText(food.getFoodName());
            textViewDifficulty.setText("Difficulty: " +food.getDifficulty());
            textViewPrepTime.setText("Preptime: "+food.getPrepTime()+" minutes");
            textViewFlavor.setText("Flavor: "+food.getFlavour());
            textViewServes.setText("Serves: "+answer.getPortionSize()+"-"+(Integer.parseInt(answer.getPortionSize())+1) +" people");
            textViewEthnicity.setText("Ethnicity: "+food.getEthnicity());
        }

}