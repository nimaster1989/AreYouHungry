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
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;


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


    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
            setPreferencesFromResource(R.xml.addfood_preferences, rootKey);

        }

        @Override
        public void onResume(){
            super.onResume();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause(){
            super.onPause();
            // Set up a listener whenever a key changes
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s){
        }
    }
        public void clearValue(){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }
