package comp3350.Group2.areyouhungry.ui.add_food;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;

public class AddFoodActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfood_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.adds, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        clearValue();
        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.addfood_fba);
        fab.setOnClickListener(new View.OnClickListener(){
            private  String food_name;
            private  boolean favourite;
            private  int portionSize = 0;
            private  int prepTime = 0;
            private  String flavour;
            private  String difficulty;
            private  String ethnicity;
            private  String imageURL;
            private  ArrayList<String> str_categorys;
            @Override

            public void onClick(View view){
                Intent next = new Intent(AddFoodActivity.this, AddNextActivity.class);
                startActivity(next);
            }
            /*
            public void onClick(View view){

                boolean buildFood = true;
                str_categorys = new ArrayList<>();
                AlertDialog alertDialog = new AlertDialog.Builder(AddFoodActivity.this).create();
                String alertMessage = "";
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                food_name = sharedPreferences.getString("food name", "");
                if(food_name.equals("")){
                    alertMessage += "Please enter a food name.\n";
                    buildFood = false;
                }
                favourite  = sharedPreferences.getBoolean("favourite", false);

                String portionSize_Str = sharedPreferences.getString("portion","");
                if(TextUtils.isDigitsOnly(portionSize_Str)){
                    portionSize = Integer.parseInt(portionSize_Str);
                }
                if(portionSize <= 0){
                    alertMessage += "Please enter a portion size.\n";
                    buildFood = false;
                }

                String prepTime_Str = sharedPreferences.getString("preptime","");
                if(TextUtils.isDigitsOnly(prepTime_Str)){
                    prepTime = Integer.parseInt(prepTime_Str);
                }
                if(prepTime <= 0){
                    alertMessage += "Please enter a preparation time.\n";
                    buildFood = false;
                }

                flavour = sharedPreferences.getString("flavour","");
                if(flavour.equals("")){
                    alertMessage += "Please enter a flavour type.\n";
                    buildFood = false;
                }

                difficulty = sharedPreferences.getString("difficulty","");
                if(difficulty.equals("")){
                    alertMessage += "Please select a difficulty.\n";
                    buildFood = false;
                }

                ethnicity = sharedPreferences.getString("ethnicity","");
                if(ethnicity.equals("")){
                    alertMessage += "Please enter a food ethnicity.\n";
                    buildFood = false;
                }

                Set<String> entries = sharedPreferences.getStringSet("category", null);
                if(entries == null){
                    alertMessage += "Please select at least one category.\n";
                    buildFood = false;
                }else{
                    String[] selecteds = entries.toArray(new String[]{});
                    str_categorys.clear();
                    Collections.addAll(str_categorys, selecteds);

                }
                //add ingredients and instructions arraylist here


                imageURL = sharedPreferences.getString("imageurl", "");
                if(buildFood){
                    AccessFoods af = new AccessFoods();
                    int newId = af.getFoodRow() + 1;
                    Food newFood = new Food(newId,food_name,portionSize,prepTime,flavour,difficulty,ethnicity);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.apply();

                    Bundle bundle = new Bundle();
                    Intent next = new Intent(AddFoodActivity.this, AddNextActivity.class);
                    bundle.putSerializable("foodObj", newFood);
                    bundle.putBoolean("foodFav",favourite);
                    bundle.putStringArrayList("foodCats",str_categorys);
                    bundle.putString("foodImg",imageURL);
                    next.putExtras(bundle);
                    startActivity(next);
                    finish();
                }else{
                    alertDialog.setTitle("Error:");
                    alertDialog.setMessage(alertMessage);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
            */
        });



    }


    //This allows you to use the back button on the top left to go to home page
    public boolean onOptionsItemSelected(MenuItem item){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
        return true;
    }

    @Override
    public void onBackPressed(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }


    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.addfood_preferences, rootKey);

        }

        @Override
        public void onResume() {
            super.onResume();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
//            String getStr;
//                switch (s){
//                    case "ingredient":
//                        getStr = sharedPreferences.getString(s, "");
//                        EditTextPreference ep2 = findPreference("ingredient2");
//                        if (ep2 != null && !getStr.equals("")){
//                            ep2.setVisible(true);
//                        }
//                        break;
//                    case "ingredient2":
//                        getStr = sharedPreferences.getString(s, "");
//                        ep2 = findPreference("ingredient2");
//                        ep2.setSummary(getStr);
//                        EditTextPreference ep3 = findPreference("ingredient3");
//                        if (ep3 != null && !getStr.equals("")){
//                            ep3.setVisible(true);
//                        }
//                        break;
//                    case "ingredient3":
//                        getStr = sharedPreferences.getString(s, "");
//                        ep3 = findPreference("ingredient3");
//                        ep3.setSummary(getStr);
//                        EditTextPreference ep4 = findPreference("ingredient4");
//                        if (ep4 != null && !getStr.equals("")){
//                            ep4.setVisible(true);
//                        }
//                        break;
//                    case "ingredient4":
//                        getStr = sharedPreferences.getString(s, "");
//                        ep4 = findPreference("ingredient4");
//                        ep4.setSummary(getStr);
//                        EditTextPreference ep5 = findPreference("ingredient5");
//                        if (ep5 != null && !getStr.equals("")){
//                            ep5.setVisible(true);
//                        }
//                        break;
//                    case "instruction":
//                        getStr = sharedPreferences.getString(s, "");
//                        EditTextPreference inp2 = findPreference("instruction2");
//                        if (inp2 != null && !getStr.equals("")){
//                            inp2.setVisible(true);
//                        }
//                        break;
//                    case "instruction2":
//                        getStr = sharedPreferences.getString(s, "");
//                        inp2 = findPreference("instruction2");
//                        inp2.setSummary(getStr);
//                        EditTextPreference inp3 = findPreference("instruction3");
//                        if (inp3 != null && !getStr.equals("")){
//                            inp3.setVisible(true);
//                        }
//                        break;
//                    case "instruction3":
//                        getStr = sharedPreferences.getString(s, "");
//                        inp3 = findPreference("instruction3");
//                        inp3.setSummary(getStr);
//                        EditTextPreference inp4 = findPreference("instruction4");
//                        if (inp4 != null && !getStr.equals("")){
//                            inp4.setVisible(true);
//                        }
//                        break;
//                    case "instruction4":
//                        getStr = sharedPreferences.getString(s, "");
//                        inp4 = findPreference("instruction4");
//                        inp4.setSummary(getStr);
//                        EditTextPreference inp5 = findPreference("instruction5");
//                        if (inp5 != null && !getStr.equals("")){
//                            inp5.setVisible(true);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//            }
        }
    }
        public void clearValue(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }
