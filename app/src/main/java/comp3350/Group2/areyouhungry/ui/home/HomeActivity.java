package comp3350.Group2.areyouhungry.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.ui.favorites.FavouriteFoodListActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;
import comp3350.Group2.areyouhungry.ui.preferred.PreferredActivity;
import comp3350.Group2.areyouhungry.ui.random.RandomActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

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
    }

    //jump to randomActivity page when click the image button
    public void randomPickOnClick(View view) {
        Intent random_intent = new Intent(HomeActivity.this, RandomActivity.class);
        HomeActivity.this.startActivity(random_intent);
    }

    public void preferredPickOnClick(View view) {
        Intent preferred_intent = new Intent(HomeActivity.this, PreferredActivity.class);
        HomeActivity.this.startActivity(preferred_intent);
    }
}