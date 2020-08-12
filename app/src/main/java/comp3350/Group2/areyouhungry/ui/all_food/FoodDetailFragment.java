package comp3350.Group2.areyouhungry.ui.all_food;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
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
import comp3350.Group2.areyouhungry.objects.Ingredient;


public class FoodDetailFragment extends Fragment{


    public static final String ARG_ITEM_ID = "item_id";

    private Food mFood;
    private AccessFoods accessFoods;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final TextView rootView = (TextView) inflater.inflate(R.layout.food_detail, container, false);
        if (mFood != null){
            Food theFood = accessFoods.getFoodByID(mFood.getFoodID());
            String imageURL = accessFoods.getImagebyFood(mFood.getFoodID());

            if(imageURL != null){
                Glide.with(this)
                        .load(imageURL).centerCrop()
                        .into(new CustomTarget<Drawable>(Resources.getSystem().getDisplayMetrics().widthPixels,Resources.getSystem().getDisplayMetrics().heightPixels/2){

                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition){
                                rootView.setCompoundDrawablesWithIntrinsicBounds(null, resource, null, null);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder){
                                rootView.setCompoundDrawablesWithIntrinsicBounds(null, placeholder, null, null);
                            }
                        });

            }

            AccessIngredients ai = new AccessIngredients();
            AccessDirections ad = new AccessDirections();

            List<Direction> directions = new ArrayList<>();
            List<Ingredient> ingredients = new ArrayList<>();


            SpannableString foodTitle = new SpannableString("Info:\n");
            foodTitle.setSpan(new StyleSpan(Typeface.BOLD), 0, foodTitle.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            foodTitle.setSpan(new AbsoluteSizeSpan(64), 0, foodTitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            String foodInfo = "Portion size: " + theFood.getPortionSize() + "\nPrep Time: " + theFood.getPrepTime() + " minutes\nFlavour: " + theFood.getFlavour() + "\nDifficulty: " + theFood.getDifficulty() + "\nEthnicity: " + theFood.getEthnicity() + "\n\n";

            ad.getDirection(theFood, directions);
            ai.getIngredient(theFood, ingredients);
            String theIngredients = "";

            SpannableString ingredientsTitle = new SpannableString("Ingredients: \n");
            ingredientsTitle.setSpan(new StyleSpan(Typeface.BOLD), 0, ingredientsTitle.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            ingredientsTitle.setSpan(new AbsoluteSizeSpan(64), 0, ingredientsTitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            for(int i = 0; i<ingredients.size(); i++){
                Ingredient currIngredient = ingredients.get(i);
                theIngredients = theIngredients + currIngredient.getMeasurement() + " " + currIngredient.getIngredientName() + "\n";
            }

            String theDirections = "";
            SpannableString directionsTitle = new SpannableString("\nDirections: \n");
            directionsTitle.setSpan(new StyleSpan(Typeface.BOLD), 0, directionsTitle.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            directionsTitle.setSpan(new AbsoluteSizeSpan(64), 0, directionsTitle.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            for(int i = 0; i<directions.size(); i++){
                Direction currDirection = directions.get(i);
                theDirections = theDirections + currDirection.getStepNumber() + ". "+currDirection.getDirectionDescription()+ "\n\n";
            }

            CharSequence finalText = TextUtils.concat(foodTitle, foodInfo, ingredientsTitle, theIngredients, directionsTitle, theDirections);
            ((TextView) rootView.findViewById(R.id.food_detail)).setText(finalText);
        }

        return rootView;
    }
}