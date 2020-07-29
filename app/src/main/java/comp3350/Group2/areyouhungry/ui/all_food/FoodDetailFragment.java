package comp3350.Group2.areyouhungry.ui.all_food;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodDirection;
import comp3350.Group2.areyouhungry.objects.FoodIngredient;
import comp3350.Group2.areyouhungry.objects.Ingredient;

/* A fragment representing a single Food detail screen.
   This fragment is either contained in a FoodListActivity
   in two-pane mode (on tablets) or a FoodDetailActivity
   on handsets. */
public class FoodDetailFragment extends Fragment{
    /* The fragment argument representing the item ID that this fragment
       represents. */

    public static final String ARG_ITEM_ID = "item_id";

    private Food mFood;
    private AccessFoods accessFoods;

    /* Mandatory empty constructor for the fragment manager to instantiate the
       fragment (e.g. upon screen orientation changes). */

    public FoodDetailFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)){
            accessFoods = new AccessFoods();
            Map<String,Food> Food_map = new HashMap<String,Food>();
            accessFoods.getMap(Food_map);
            mFood = Food_map.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null){
                appBarLayout.setTitle(mFood.getFoodName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        TextView rootView = (TextView) inflater.inflate(R.layout.food_detail, container, false);
        if (mFood != null){

            // imageID works by grabbing a "name", "defType", and package. Make sure the 1st parameter or "name" is an image that exists in the res/drawable folder
            // example: spaghetti.jpg image in drawable folder, "name" will be spaghetti, placeholder.png "name" would be ramen. We can somehow link imageID to FOOD or whatever class later.
            int imageID = getResources().getIdentifier("spaghetti", "drawable", getContext().getPackageName());
            Drawable foodImage = ContextCompat.getDrawable(getContext(), imageID);

            // Method below may need changing depending on dimensions of the users device. I made it so that the width is streched to the width of the device.
            // An issue is that aspect ratio may/will not be the same after resizing
            foodImage.setBounds(0, 0, Resources.getSystem().getDisplayMetrics().widthPixels, 500);
            rootView.setCompoundDrawables(null, foodImage, null, null);
            Food theFood = accessFoods.getFoodByID(mFood.getFoodID());

            AccessIngredients ai = new AccessIngredients();
            AccessDirections ad = new AccessDirections();

            List<Direction> directions = new ArrayList<>();
            List<Ingredient> ingredients = new ArrayList<>();

            ad.getDirection(theFood, directions);
            ai.getIngredient(theFood, ingredients);
            String test = "Ingredients: \n";
            for(int i = 0; i<ingredients.size(); i++){
                Ingredient currIngredient = ingredients.get(i);
                test = test + currIngredient.getMeasurement() + " " + currIngredient.getIngredientName() + "\n";
            }

            test = test + "\n\nDirections: \n";

            for(int i = 0; i<directions.size(); i++){
                Direction currDirection = directions.get(i);
                test = test + currDirection.getStepNumber() + ". "+currDirection.getDirectionDescription()+ "\n";
            }

            ((TextView) rootView.findViewById(R.id.food_detail)).setText(test);
        }else{
            System.out.println("FoodDetailFragment oncreate view mFood is null");
        }

        return rootView;
    }
}