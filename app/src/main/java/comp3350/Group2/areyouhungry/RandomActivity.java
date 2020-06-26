package comp3350.Group2.areyouhungry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import comp3350.Group2.areyouhungry.R;

public class RandomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("random activity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
    }

}