package comp3350.Group2.areyouhungry;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import comp3350.Group2.areyouhungry.ui.all_food.FoodListActivity;
import comp3350.Group2.areyouhungry.ui.favorites.FavouriteFoodListActivity;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;
import comp3350.Group2.areyouhungry.ui.random.RandomActivity;

public class MainActivity extends AppCompatActivity {
    public static final String dbName="SC";
    //COMP3350  group2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Main activity create");
        super.onCreate(savedInstanceState);

        //startup database
        startUp();
        Intent home_intent = new Intent(MainActivity.this, HomeActivity.class);
        MainActivity.this.startActivity(home_intent);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_more)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

    }
    private void startUp() {
        Services.createDataAccess(dbName);
    }

}