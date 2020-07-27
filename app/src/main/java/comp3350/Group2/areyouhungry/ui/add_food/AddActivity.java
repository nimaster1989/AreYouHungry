package comp3350.Group2.areyouhungry.ui.add_food;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.all_food.FoodListActivity;

public class AddActivity extends AppCompatActivity {

    private AccessFoods accessFoods;
    private ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        accessFoods = new AccessFoods();
        foods = new ArrayList<Food>();
        accessFoods.getFoods(foods);
    }

    public void foodAddOnClick(View view){
        EditText editName = (EditText)findViewById(R.id.editFoodName);
        EditText editRecipe = (EditText)findViewById(R.id.editRecipe);
        String newName =  editName.getText().toString();
        String newRecipe = editRecipe.getText().toString();
        CheckBox favCheck = (CheckBox) findViewById(R.id.fav_checkBox);
        boolean set_favourite = favCheck.isChecked();
        Food newFood = validateFoodData(newName,newRecipe,set_favourite);
        if(newFood != null){
            addCategory(newFood);
            Snackbar.make(findViewById(R.id.add_constrain), "successfully added!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void addCategory(Food newFood){
        CheckBox checkbox;
        checkbox = (CheckBox) findViewById(R.id.meat_checkBox);
        if (checkbox.isChecked()){
            addCategoryHelper(newFood,"Meat");
        }
        checkbox = (CheckBox) findViewById(R.id.vegetable_checkBox);
        if (checkbox.isChecked()){
            addCategoryHelper(newFood,"Vegetable");
        }
        checkbox = (CheckBox) findViewById(R.id.grain_checkBox);
        if (checkbox.isChecked()){
            addCategoryHelper(newFood,"Grain");
        }
        checkbox = (CheckBox) findViewById(R.id.dairy_checkBox);
        if (checkbox.isChecked()){
            addCategoryHelper(newFood,"Dairy");
        }
        checkbox = (CheckBox) findViewById(R.id.fruit_checkBox);
        if (checkbox.isChecked()){
            addCategoryHelper(newFood,"Fruit");
        }
    }

    private void addCategoryHelper(Food newFood,String categoryName){
        accessFoods.addFoodCategory(newFood,categoryName);
    }

    private Food validateFoodData(String name,String Recipe,Boolean favourite){
        Food foodToAdd = null;
        if(name.length() == 0){
            Snackbar.make(findViewById(R.id.add_constrain), "food name can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return null;
        }else{
            /* Creates a food object. */
            int newID =  accessFoods.getFoodRow() + 1;
            //TODO change to new constructor here
            if(Recipe.length() == 0){
                foodToAdd = new Food(newID,name,favourite);
            }else{
                foodToAdd = new Food(newID,name,favourite);
            }

            /* Check if the new food is duplicate. */
            if(accessFoods.checkDuplicate(foodToAdd)){
                Snackbar.make(findViewById(R.id.add_constrain), "food is already in the app ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return null;
            }

            /* Now its safe to add food. */
            accessFoods.addFood(foodToAdd);
        }
        return foodToAdd;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            navigateUpTo(new Intent(this, FoodListActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
        public void onBackPressed(){
            navigateUpTo(new Intent(this, FoodListActivity.class));
    }



}