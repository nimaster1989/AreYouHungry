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

    private boolean set_favourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        accessFoods = new AccessFoods();
        foods = new ArrayList<Food>();
        accessFoods.getFoods(foods);

    }

    public void foodAddOnClick(View view) {
        EditText editName = (EditText)findViewById(R.id.editFoodName);
        EditText editRecipe = (EditText)findViewById(R.id.editRecipe);
        String newName =  editName.getText().toString();
        String newRecipe = editRecipe.getText().toString();
        Food newFood = validateFoodData(newName,newRecipe,set_favourite);
        if(newFood != null){
            Snackbar.make(findViewById(R.id.add_constrain), "successfully added!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private Food validateFoodData(String name,String Recipe,Boolean favourite){
        Food addedFood = null;
        if(name.length() == 0){
            Snackbar.make(findViewById(R.id.add_constrain), "food name can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return null;
        }else{
            Iterator<Food> foodIterator = foods.iterator();
            while(foodIterator.hasNext()){
                if(foodIterator.next().getFoodName().equals(name)){
                    Snackbar.make(findViewById(R.id.add_constrain), "food is already in the app ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;}
            }
            String newID = Integer.toString( accessFoods.getFoodRow() + 1);
            System.out.println("foodrow:" +accessFoods.getFoodRow() );
            System.out.println("newID:" +newID);
            if(Recipe.length() == 0){
                addedFood = new Food(newID,name,"",set_favourite);
            }else{
                addedFood = new Food(newID,name,Recipe,set_favourite);
            }
            accessFoods.addFood(addedFood);
        }
        return addedFood;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, FoodListActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
        public void onBackPressed() {
            navigateUpTo(new Intent(this, FoodListActivity.class));
    }

    public void Fav_onClick(View view) {
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            this.set_favourite = true;
        }
    }
}