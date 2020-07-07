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
    public static final String dbName="BF";
    //COMP3350  group2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Main activity create");
        super.onCreate(savedInstanceState);

        //startup database
        startUp();

        //startUp home activity
        Intent home_intent = new Intent(MainActivity.this, HomeActivity.class);
        MainActivity.this.startActivity(home_intent);
    }
    private void startUp() {
        Services.createDataAccess(dbName);
    }

}