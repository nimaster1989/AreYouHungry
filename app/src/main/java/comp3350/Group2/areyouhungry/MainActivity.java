package comp3350.Group2.areyouhungry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    public static final String dbName="SC";
    //COMP3350  group2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Main activity create");
        super.onCreate(savedInstanceState);

        //startup database
        startUp();

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    private void startUp() {
        Services.createDataAccess(dbName);
    }

    //jump to randomActivity page when click the image button
    public void randomPickOnClick(View view) {
        Intent random_intent = new Intent(MainActivity.this, RandomActivity.class);
        MainActivity.this.startActivity(random_intent);
    }

    public void allFoodsOnClick(View view) {
        Intent all_intent = new Intent(MainActivity.this, AllFoodsActivity.class);
        //Intent all_intent = new Intent(MainActivity.this, FoodListActivity.class);
        MainActivity.this.startActivity(all_intent);
    }
}