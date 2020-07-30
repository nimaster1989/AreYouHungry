package comp3350.Group2.areyouhungry.ui.add_food;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;

public class AddFoodActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfood_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.addfood_fba);
        fab.setOnClickListener(new View.OnClickListener(){
            private  String food_name;
            private  boolean favourite;
            private  int portionSize = 0;
            private  int prepTime = 0;
            private  String flavour;
            private  String difficulty;
            private  String ethnicity;
            private  ArrayList<String> str_categorys;
            private  ArrayList<String> str_ingredients;
            private  ArrayList<String> str_directions;

            @Override
            public void onClick(View view) {
                boolean buildFood = true;
                str_categorys = new ArrayList<>();
                str_directions = new ArrayList<>();
                str_ingredients = new ArrayList<>();
                String alertMessage = "";

                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext() /* Activity context */);
                food_name = sharedPreferences.getString("food name", "");
                System.out.println("food name:"+food_name);
                if(food_name.equals("")){
                    alertMessage += "please enter food name\n";
                    buildFood = false;
                }

                favourite  = sharedPreferences.getBoolean("favourite", false);
                System.out.println("favourite:"+favourite);

                String portionSize_Str = sharedPreferences.getString("portion","");
                if(!portionSize_Str.equals("")) portionSize = Integer.parseInt(portionSize_Str);
                System.out.println("portion size:"+portionSize);
                if(portionSize == 0) {
                    alertMessage += "please enter portion size\n";
                    buildFood = false;
                }

                String prepTime_Str = sharedPreferences.getString("preptime","");
                if(!prepTime_Str.equals("")) prepTime = Integer.parseInt(prepTime_Str);
                System.out.println("prep time:"+prepTime);
                if(prepTime == 0){
                    alertMessage += "please enter prepare time\n";
                    buildFood = false;
                }

                flavour = sharedPreferences.getString("flavour","");
                System.out.println("flavour:"+flavour);
                if(flavour.equals("")){
                    alertMessage += "please select flavour\n";
                    buildFood = false;
                }

                difficulty = sharedPreferences.getString("difficulty","");
                System.out.println("difficulty:"+difficulty);
                if(difficulty.equals("")){
                    alertMessage += "please select difficulty\n";
                    buildFood = false;
                }

                ethnicity = sharedPreferences.getString("ethnicity","");
                System.out.println("ethnicity:"+ethnicity);
                if(ethnicity.equals("")){
                    alertMessage += "please select ethnicity\n";
                    buildFood = false;
                }

                Set<String> entries = sharedPreferences.getStringSet("category", null);
                if(entries == null){
                    System.out.println("No entries for category");
                    alertMessage += "please select at least one category \n";
                    buildFood = false;
                }else {
                    String[] selecteds = entries.toArray(new String[] {});
                    str_categorys.clear();
                    Collections.addAll(str_categorys, selecteds);
                    System.out.println(str_categorys.toString());
                }

                str_ingredients.clear();
                String ingredient = sharedPreferences.getString("ingredient","");
                if (ingredient!= null && !ingredient.equals("")) {
                    str_ingredients.add(ingredient);
                }else{
                    alertMessage += "please set at least one ingredients\n";
                    buildFood = false;
                }
                String ingredient2 = sharedPreferences.getString("ingredient2","");
                if (ingredient2!= null && !ingredient2.equals("")) str_ingredients.add(ingredient2);
                String ingredient3 = sharedPreferences.getString("ingredient3","");
                if (ingredient3!= null && !ingredient3.equals("")) str_ingredients.add(ingredient3);
                String ingredient4 = sharedPreferences.getString("ingredient4","");
                if (ingredient4!= null && !ingredient4.equals("")) str_ingredients.add(ingredient4);
                String ingredient5 = sharedPreferences.getString("ingredient5","");
                if (ingredient5!= null && !ingredient5.equals("")) str_ingredients.add(ingredient5);
                System.out.println(str_ingredients.toString());

                str_directions.clear();
                String direction = sharedPreferences.getString("instruction","");
                if (direction!= null && !direction.equals("")) {
                    str_directions.add(direction);
                }else{
                    alertMessage += "please set at least one directions\n";
                    buildFood = false;
                }
                String direction2 = sharedPreferences.getString("instruction2","");
                if (direction2!= null && !direction2.equals("")) str_directions.add(direction2);
                String direction3 = sharedPreferences.getString("instruction3","");
                if (direction3!= null && !direction3.equals("")) str_directions.add(direction3);
                String direction4 = sharedPreferences.getString("instruction4","");
                if (direction4!= null && !direction4.equals("")) str_directions.add(direction4);
                String direction5 = sharedPreferences.getString("instruction5","");
                if (direction5!= null && !direction5.equals("")) str_directions.add(direction5);
                System.out.println(str_directions.toString());



                if(buildFood){
                    System.out.println("start build");
                    AccessFoods af = new AccessFoods();
                    int newId = af.getFoodRow();
                    System.out.println("preptime:"+prepTime);
                    Food newFood = new Food(newId,food_name,portionSize,prepTime,flavour,difficulty,ethnicity);
                    if(af.addFood(newFood) == null){
                        System.out.println("add success");
                    }
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddFoodActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage(alertMessage);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                alertMessage = "";
            }
        });

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
            System.out.println("something change");
            String getStr;
                switch (s) {
                    case "ingredient":
                        getStr = sharedPreferences.getString(s, "");
                        EditTextPreference ep2 = findPreference("ingredient2");
                        if (ep2 != null && !getStr.equals("")) {
                            ep2.setVisible(true);
                        }
                        break;
                    case "ingredient2":
                        getStr = sharedPreferences.getString(s, "");
                        ep2 = findPreference("ingredient2");
                        ep2.setSummary(getStr);
                        EditTextPreference ep3 = findPreference("ingredient3");
                        if (ep3 != null && !getStr.equals("")) {
                            ep3.setVisible(true);
                        }
                        break;
                    case "ingredient3":
                        getStr = sharedPreferences.getString(s, "");
                        ep3 = findPreference("ingredient3");
                        ep3.setSummary(getStr);
                        EditTextPreference ep4 = findPreference("ingredient4");
                        if (ep4 != null && !getStr.equals("")) {
                            ep4.setVisible(true);
                        }
                        break;
                    case "ingredient4":
                        getStr = sharedPreferences.getString(s, "");
                        ep4 = findPreference("ingredient4");
                        ep4.setSummary(getStr);
                        EditTextPreference ep5 = findPreference("ingredient5");
                        if (ep5 != null && !getStr.equals("")) {
                            ep5.setVisible(true);
                        }
                        break;
                    case "instruction":
                        getStr = sharedPreferences.getString(s, "");
                        EditTextPreference inp2 = findPreference("instruction2");
                        if (inp2 != null && !getStr.equals("")) {
                            inp2.setVisible(true);
                        }
                        break;
                    case "instruction2":
                        getStr = sharedPreferences.getString(s, "");
                        inp2 = findPreference("instruction2");
                        inp2.setSummary(getStr);
                        EditTextPreference inp3 = findPreference("instruction3");
                        if (inp3 != null && !getStr.equals("")) {
                            inp3.setVisible(true);
                        }
                        break;
                    case "instruction3":
                        getStr = sharedPreferences.getString(s, "");
                        inp3 = findPreference("instruction3");
                        inp3.setSummary(getStr);
                        EditTextPreference inp4 = findPreference("instruction4");
                        if (inp4 != null && !getStr.equals("")) {
                            inp4.setVisible(true);
                        }
                        break;
                    case "instruction4":
                        getStr = sharedPreferences.getString(s, "");
                        inp4 = findPreference("instruction4");
                        inp4.setSummary(getStr);
                        EditTextPreference inp5 = findPreference("instruction5");
                        if (inp5 != null && !getStr.equals("")) {
                            inp5.setVisible(true);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

//    case "food name":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("")){
//                            getStr = sharedPreferences.getString(s, "");
//                            AddFoodActivity.food_name = getStr;
//                            }
//                        break;
//                    case "portion":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("") && Integer.parseInt(getStr) > 0){
//                            AddFoodActivity.portionSize = Integer.parseInt(getStr);
//                        }
//                        break;
//                    case "preptime":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("") && Integer.parseInt(getStr) > 0){
//                            AddFoodActivity.prepTime = Integer.parseInt(getStr);
//                        }
//                        break;
//                    case "flavour":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("")){
//                            AddFoodActivity.flavour = getStr;
//                        }
//                        break;
//                    case "difficulty":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("")){
//                            AddFoodActivity.difficulty = getStr;
//                        }
//                        break;
//                    case "ethnicity":
//                        getStr = sharedPreferences.getString(s, "");
//                        if(getStr != null && !getStr.equals("")){
//                            AddFoodActivity.ethnicity = getStr;
//                        }
//                        break;
//                    case "favourite":
//                        boolean getFav = sharedPreferences.getBoolean(s,true);
//                        System.out.println("fav?"+favourite);
//                        AddFoodActivity.favourite = getFav;
//                        break;