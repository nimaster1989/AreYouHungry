package comp3350.Group2.areyouhungry.ui.add_food;

import androidx.annotation.NonNull;
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

        System.out.println("add activity create");
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
        System.out.println("add onclick");
        EditText editName = (EditText)findViewById(R.id.editFoodName);
        EditText editRecipe = (EditText)findViewById(R.id.editRecipe);
        String newName =  editName.getText().toString();
        String newRecipe = editRecipe.getText().toString();
        System.out.println("get from form: "+newName+" , "+newRecipe);
        Food newFood = validateFoodData(newName,newRecipe);
        if(newFood != null){
            if(set_favourite){
                newFood.setFavourite(true);
            }
            if(accessFoods.addFood(newFood) == null){
                System.out.println("add success create");
                Snackbar.make(findViewById(R.id.add_constrain), "successfully added!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                accessFoods.getFoods(foods);
            }
        }
        set_favourite = false;
    }

    private Food validateFoodData(String name,String Recipe){
        if(name.length() == 0){
            Snackbar.make(findViewById(R.id.add_constrain), "food name can not be empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return null;
        }else{
            Iterator<Food> foodIterator = foods.iterator();
            while(foodIterator.hasNext()){
                if(foodIterator.next().foodName.equals(name)){
                    Snackbar.make(findViewById(R.id.add_constrain), "food is already in the app ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return null;}
            }
            if(Recipe.length() == 0){
                if(foods.size() < 10) {
                    return new Food("00" + (foods.size() + 1),name);
                }else{
                    return new Food("0" + (foods.size() + 1),name);
                }
            }else{
                if(foods.size() < 10) {
                    return new Food("00" + (foods.size()+1),name,Recipe);
                }else{
                    return new Food("0" + (foods.size()+1),name,Recipe);
                }
            }
        }
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