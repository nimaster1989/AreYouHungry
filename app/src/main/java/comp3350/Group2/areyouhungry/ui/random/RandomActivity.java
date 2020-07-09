package comp3350.Group2.areyouhungry.ui.random;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;

public class RandomActivity extends AppCompatActivity {

    private AccessFoods accessFoods;
    private ArrayList<Food> foodList;
    private ArrayAdapter<Food> foodArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                    text1.setText(new StringBuilder().append(foodList.get(position).getFoodName()).toString());


                    return view;
                }
            };
            //Finding the View picked by the random_pick id selected
            final ListView listView = (ListView) findViewById(R.id.random_pick);
            //Creating an onClickListener for clicking on the food selected
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Gets the food selected from the list
                    Food food = (Food) foodList.get(position);
                    Context context = view.getContext();
                    //Change the content of the application
                    Intent intent = new Intent(context, FoodDetailActivity.class);
                    intent.putExtra(FoodDetailFragment.ARG_ITEM_ID, food.getFoodID());
                    context.startActivity(intent);
                }
            });
            listView.setAdapter(foodArrayAdapter);
        }

    }
}