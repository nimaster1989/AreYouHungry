package comp3350.Group2.areyouhungry.ui.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;

public class RandomActivity extends AppCompatActivity {

    private AccessFoods accessFoods;
    private ArrayList<Food> foodList;
    private ArrayAdapter<Food> foodArrayAdapter;

    //private int selectedFoodPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("random activity create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        accessFoods = new AccessFoods();

        foodList = new ArrayList<Food>();
        String result = accessFoods.getRandom(foodList);
        if (result != null) {
            System.out.println("accessFoods.getCourses Error");
        } else {
            foodArrayAdapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, foodList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(new StringBuilder().append(foodList.get(position).getFoodID()).append(foodList.get(position).getFoodName()).toString());
                    text2.setText(foodList.get(position).getRecipeLink());

                    return view;
                }
            };
            System.out.println("list view");
            final ListView listView = (ListView) findViewById(R.id.random_pick);
            listView.setAdapter(foodArrayAdapter);

        }

    }
}