package comp3350.Group2.areyouhungry.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.ui.add_food.AddActivity;
import comp3350.Group2.areyouhungry.ui.favorites.FavouriteFoodListActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;
import comp3350.Group2.areyouhungry.ui.preferred.PreferredActivity;


public class HomeActivity extends AppCompatActivity {
    private Button addFoodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){

                    case R.id.navigation_home:
                        break;

                    case R.id.navigation_favorites:
                        Intent fav_intent = new Intent(HomeActivity.this, FavouriteFoodListActivity.class);
                        startActivity(fav_intent);
                        break;

                    case R.id.navigation_more:
                        Intent more_intent = new Intent(HomeActivity.this, MoreActivity.class);
                        startActivity(more_intent);
                        break;
                }
                return false;
            }
        });
        addFoodButton = findViewById(R.id.add_food_button);
        addFoodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addFoodOnClick();
            }
        });
    }


    public void preferredPickOnClick(View view){
        Intent preferred_intent = new Intent(HomeActivity.this, PreferredActivity.class);
        HomeActivity.this.startActivity(preferred_intent);
    }

    public void addFoodOnClick(){
        Intent intent = new Intent(this, AddActivity.class);
        this.startActivity(intent);
    }
}