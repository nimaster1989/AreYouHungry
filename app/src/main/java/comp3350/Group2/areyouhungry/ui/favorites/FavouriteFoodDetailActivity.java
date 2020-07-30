package comp3350.Group2.areyouhungry.ui.favorites;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;

/* An activity representing a single FavouriteFood detail screen. This
   activity is only used on narrow width devices. On tablet-size devices,
   item details are presented side-by-side with a list of items
   in a FavouriteFoodListActivity. */

public class FavouriteFoodDetailActivity extends AppCompatActivity{

    private String curr_id = null;
    private AccessFoods accessFoods;
    FloatingActionButton fab;
    Food curr_food = null;
    User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritefood_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        accessFoods = new AccessFoods();

        curr_id = getIntent().getStringExtra(FavouriteFoodDetailFragment.ARG_ITEM_ID);
        if (curr_id != null){
            curr_food = accessFoods.getFoodByID(curr_id);
        }

        currUser = MainActivity.currentUser;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        if(this.curr_food != null ){
            Boolean userFav = accessFoods.getFoodFavouriteByUser(MainActivity.currentUser,curr_food);
            curr_food.setFavourite(userFav);
            curr_food.setFavourite(accessFoods.getFoodFavouriteByUser(MainActivity.currentUser,curr_food));
            if(curr_food.getFavourite()){
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
                            .setAction("Action", null).setAnchorView(R.id.nav_view).show();
                } else{
                    curr_food.setFavourite(true);
                    accessFoods.setFoodFavouriteByUser(currUser,curr_id,true);
                    fab.setImageDrawable(getDrawable(R.drawable.ic_baseline_favorite_24));
                    Snackbar.make(view, "You like this food!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).setAnchorView(R.id.nav_view).show();
                }
            }
        }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null){
            Bundle arguments = new Bundle();
            arguments.putString(FavouriteFoodDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(FavouriteFoodDetailFragment.ARG_ITEM_ID));
            FavouriteFoodDetailFragment fragment = new FavouriteFoodDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.favouritefood_detail_container, fragment)
                    .commit();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){

                    case R.id.navigation_home:
                        Intent home_intent = new Intent(FavouriteFoodDetailActivity.this, HomeActivity.class);
                        startActivity(home_intent);
                        break;

                    case R.id.navigation_favorites:
                        Intent favor_intent = new Intent(FavouriteFoodDetailActivity.this, FavouriteFoodListActivity.class);
                        startActivity(favor_intent);
                        break;

                    case R.id.navigation_more:
                        Intent more_intent = new Intent(FavouriteFoodDetailActivity.this, MoreActivity.class);
                        startActivity(more_intent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            navigateUpTo(new Intent(this, FavouriteFoodListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}