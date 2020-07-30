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

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;

public class FoodDetailActivity extends AppCompatActivity{

    private String curr_id = null;
    private AccessFoods accessFoods;
    FloatingActionButton fab;
    Food curr_food = null;
    User currUser;

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
            AccessIngredients ai = new AccessIngredients();
            ArrayList<Ingredient> ins = new ArrayList<Ingredient>();
            ai.getIngredient(curr_food,ins);
            AccessDirections ad = new AccessDirections();
            ArrayList<Direction> ds = new ArrayList<Direction>();
            ad.getDirection(curr_food,ds);
        }

        currUser = MainActivity.currentUser;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        if(this.curr_food != null ){
            Boolean userFav = accessFoods.getFoodFavouriteByUser(MainActivity.currentUser,curr_food);
            curr_food.setFavourite(userFav);
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
                        accessFoods.setFoodFavouriteByUser(currUser,curr_id,false);
                        fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_border_24));
                        Snackbar.make(view, "You unlike this food!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else{
                        curr_food.setFavourite(true);
                        accessFoods.setFoodFavouriteByUser(currUser,curr_id,true);
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
            navigateUpTo(new Intent(this, FoodListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


