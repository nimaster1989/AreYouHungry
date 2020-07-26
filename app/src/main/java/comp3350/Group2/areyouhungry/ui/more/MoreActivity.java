package comp3350.Group2.areyouhungry.ui.more;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.ui.all_food.FoodListActivity;
import comp3350.Group2.areyouhungry.ui.favorites.FavouriteFoodListActivity;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        setTitle(getTitle());

        /* For button navigation. */
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){

                    case R.id.navigation_home:
                        Intent home_intent = new Intent(MoreActivity.this, HomeActivity.class);
                        startActivity(home_intent);
                        break;

                    case R.id.navigation_favorites:
                        Intent fav_intent = new Intent(MoreActivity.this, FavouriteFoodListActivity.class);
                        startActivity(fav_intent);
                        break;

                    case R.id.navigation_more:
                        break;
                }
                return false;
            }
        });
    }

    public void allFoodsOnClick(View view){
        Intent all_intent = new Intent(MoreActivity.this, FoodListActivity.class);
        MoreActivity.this.startActivity(all_intent);
    }
}