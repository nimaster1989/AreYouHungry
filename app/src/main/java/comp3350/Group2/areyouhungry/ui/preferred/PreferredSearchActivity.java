package comp3350.Group2.areyouhungry.ui.preferred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;


public class PreferredSearchActivity extends AppCompatActivity{
    private AccessFoods accessFoods;
    private User currUser;

    private TextView textViewName;
    private TextView textViewDifficulty;
    private TextView textViewPrepTime;
    private TextView textViewFlavor;
    private TextView textViewServes;
    private TextView textViewEthnicity;
    private ImageView dishImage;
    private Answers answer;
    private AccessFoods accessFood;
    private Button homeButton;
    private Button recipeButton;
    private FloatingActionButton likeButton;
    private FloatingActionButton dislikeButton;
    private String foodId = "1";
    private boolean liked;
    private boolean disliked;
    private Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        accessFoods = new AccessFoods();
        currUser = MainActivity.currentUser;

        answer = (Answers) getIntent().getSerializableExtra("Answers");
        accessFood = new AccessFoods();
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
        food = setFood(answer);


        homeButton = findViewById(R.id.returnHomeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreferredSearchActivity.this, HomeActivity.class);
                PreferredSearchActivity.this.startActivity(intent);
            }
        });

        recipeButton = findViewById(R.id.viewRecipeButton);
        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, FoodDetailActivity.class);
                intent.putExtra(FoodDetailFragment.ARG_ITEM_ID, foodId);
                context.startActivity(intent);
            }
        });

        likeButton = findViewById(R.id.like);
        likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                liked = accessFoods.getFoodLikedByUser(currUser,food);
                if(!liked) {
                    accessFoods.setFoodLikedByUser(currUser, food.getFoodID(), true);
                    accessFoods.setFoodDislikedByUser(currUser, food.getFoodID(), false);
                    Snackbar.make(view, "You Liked This Food!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "You've Already Liked This Food!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        dislikeButton = findViewById(R.id.dislike);
        dislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                disliked = accessFoods.getFoodDislikedByUser(currUser,food);
                if(!disliked) {
                    accessFoods.setFoodDislikedByUser(currUser, food.getFoodID(), true);
                    accessFoods.setFoodLikedByUser(currUser, food.getFoodID(), false);
                    Snackbar.make(view, "You Disliked This Food!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Snackbar.make(view, "You've Already Disliked This Food!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

        private Food setFood(Answers answers){
            Food food = answers.getFoodBasedOnAnswers();
            foodId = food.getFoodID();
            textViewName.setText(food.getFoodName());
            textViewDifficulty.setText("Difficulty: " +food.getDifficulty());
            textViewPrepTime.setText("Preptime: "+food.getPrepTime()+" minutes");
            textViewFlavor.setText("Flavor: "+food.getFlavour());
            textViewServes.setText("Serves: "+answer.getPortionSize()+"-"+(Integer.parseInt(answer.getPortionSize())+1) +" people");
            textViewEthnicity.setText("Ethnicity: "+food.getEthnicity());
            if(foodId.equals("1")){
                dishImage.setImageResource(R.drawable.food1);
            }else if(foodId.equals("2")){
                dishImage.setImageResource(R.drawable.food2);
            }else if(foodId.equals("3")){
                dishImage.setImageResource(R.drawable.food3);
            }
            else if(foodId.equals("4")){
                dishImage.setImageResource(R.drawable.food4);
            }
            else if(foodId.equals("5")){
                dishImage.setImageResource(R.drawable.food5);
            }
            else if(foodId.equals("6")){
                dishImage.setImageResource(R.drawable.food6);
            }

            return food;
        }
}
