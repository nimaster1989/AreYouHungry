package comp3350.Group2.areyouhungry;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.Group2.areyouhungry.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {
    public static final String dbName="BF";
    //COMP3350  group2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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