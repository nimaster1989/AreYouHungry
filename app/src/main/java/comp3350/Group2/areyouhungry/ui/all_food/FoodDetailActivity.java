package comp3350.Group2.areyouhungry.ui.all_food;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;

/* This is the page that appears when you double click a food item for
   more details. */
public class FoodDetailActivity extends AppCompatActivity {

    private String curr_id = null;
    private AccessFoods accessFoods;
    private ArrayList<Food> foodList;
    FloatingActionButton fab;
    Food curr_food = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        accessFoods = new AccessFoods();

        /* Get current food id. */
        curr_id = getIntent().getStringExtra(FoodDetailFragment.ARG_ITEM_ID);
        if (curr_id != null){
            curr_food = accessFoods.getFoodByID(curr_id);
        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if(this.curr_food != null ){
            curr_food.setFavourite(accessFoods.getFoodFavouriteByUser(MainActivity.currentUser,curr_food));
            if(this.curr_food.getFavourite()){
                fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
            }else{
                fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
            }
        }
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (curr_food != null){
                    if (curr_food.getFavourite()){
                        curr_food.setFavourite(false);
                        accessFoods.setFoodFavourite(curr_id,false);
                        accessFoods.setFoodFavouriteByUser(MainActivity.currentUser,curr_id,false);
                        fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                        Snackbar.make(view, "You unlike this food!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else{
                        curr_food.setFavourite(true);
                        accessFoods.setFoodFavourite(curr_id,true);
                        accessFoods.setFoodFavouriteByUser(MainActivity.currentUser,curr_id,true);
                        fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                        Snackbar.make(view, "You like this food!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });

        /* Show the Up button in the action bar. */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /* The variable savedInstanceState is non-null when there is fragment state
           saved from previous configurations of this activity
           (e.g. when rotating the screen from portrait to landscape).
           In this case, the fragment will automatically be re-added
           to its container so we don"t need to manually add it. */

        if (savedInstanceState == null){
            /* Create the detail fragment and add it to the activity
               using a fragment transaction. */
            Bundle arguments = new Bundle();
            arguments.putString(FoodDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(FoodDetailFragment.ARG_ITEM_ID));
            FoodDetailFragment fragment = new FoodDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.food_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){

            /* This ID represents the Home or Up button. In the case of this
               activity, the Up button is shown. */

            navigateUpTo(new Intent(this, FoodListActivity.class));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


