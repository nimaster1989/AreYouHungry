package comp3350.Group2.areyouhungry.ui.on_hand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.ui.add_food.AddFoodActivity;
import comp3350.Group2.areyouhungry.ui.preferred.PreferredActivity;
import comp3350.Group2.areyouhungry.ui.preferred.PreferredSearchActivity;

public class OnhandActivity extends AppCompatActivity {

    public static ArrayList<Food> resultList;
    public static ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.searches, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        foods = new ArrayList<>();
        resultList = new ArrayList<>();
        AccessFoods accessFoods = new AccessFoods();

        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.search_fba);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(resultList.size() == 0){
                    AlertDialog alertDialog = new AlertDialog.Builder(OnhandActivity.this).create();
                    alertDialog.setMessage("no food match");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }else{
                    Intent intent = new Intent(OnhandActivity.this, SearchResultActivity.class);
                    OnhandActivity.this.startActivity(intent);
                }
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

        ArrayList<String> categoryCriterias;
        //food ojbect criterias
        ArrayList<String> prepTimeCriterias;
        ArrayList<String> flavourCriterias;
        ArrayList<String> difficutlyCriterias;
        ArrayList<String> ethnicityCriterias;

        ArrayList<Food> foodsCriteriaResults;
        ArrayList<Food> foodsCategoryResults;
        ArrayList<Food> foodsIngredientResults;
        ArrayList<Food> foodsFinalResult;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
            categoryCriterias = new ArrayList<>();
            prepTimeCriterias = new ArrayList<>();
            flavourCriterias = new ArrayList<>();
            difficutlyCriterias = new ArrayList<>();
            ethnicityCriterias = new ArrayList<>();
            foodsCriteriaResults = new ArrayList<>();
            foodsCategoryResults = new ArrayList<>();
            foodsIngredientResults = new ArrayList<>();
            foodsFinalResult = new ArrayList<>();
            setPreferencesFromResource(R.xml.search_preferences, rootKey);
            MultiSelectListPreference mlp = (MultiSelectListPreference)findPreference("search_on_ingredient");
            if (mlp == null) System.out.println("NullPointerException"+"Blocked is null");
            AccessIngredients accessIngredients = new AccessIngredients();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            accessIngredients.getAllIngredient(ingredients);
            int size = ingredients.size();
            System.out.println(size);
            CharSequence[] entries = new CharSequence[size];
            CharSequence[] entryvalue = new CharSequence[size];
            int i = 0;
            String str_input;
            for (Ingredient ingredient : ingredients){
                entries[i] = (CharSequence) ingredient.getIngredientName();
                entryvalue[i] = (CharSequence)String.valueOf(ingredient.getIngredientID());
                i++;
            }
            mlp.setEntries(entries);
            mlp.setEntryValues(entryvalue);
        }
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
            boolean searchOnCategory = false;
            boolean searchOnTime = false;
            boolean searchOnEthnicity = false;
            boolean searchOnDifficulty = false;
            boolean searchOnFlavour = false;
            switch (s){
                case "search_on_category":
                    searchOnCategory = sharedPreferences.getBoolean(s, false);
                    CheckBoxPreference cb1 = findPreference("Meat");
                    CheckBoxPreference cb2 = findPreference("Vegetable");
                    CheckBoxPreference cb3 = findPreference("Grain");
                    CheckBoxPreference cb4 = findPreference("Dairy");
                    CheckBoxPreference cb5 = findPreference("Fruit");
                    cb1.setVisible(searchOnCategory);
                    cb2.setVisible(searchOnCategory);
                    cb3.setVisible(searchOnCategory);
                    cb4.setVisible(searchOnCategory);
                    cb5.setVisible(searchOnCategory);
                    if(!searchOnCategory)categoryCriterias.clear();
                    break;
                case "Meat":
                case "Vegetable":
                case "Grain":
                case "Dairy":
                case "Fruit":
                    searchOnCategory = sharedPreferences.getBoolean("search_on_category", false);
                    CheckBoxPreference categoryCheckBox = findPreference(s);
                    if(searchOnCategory && categoryCheckBox.isChecked()) categoryCriterias.add(s);
                    else categoryCriterias.remove(s);
                    search_logic_category(categoryCriterias);
                    break;
                case "search_on_time":
                    searchOnTime = sharedPreferences.getBoolean(s, false);
                    CheckBoxPreference cb6 = findPreference("10");
                    CheckBoxPreference cb7 = findPreference("20");
                    CheckBoxPreference cb8 = findPreference("30");
                    CheckBoxPreference cb9 = findPreference("40");
                    cb6.setVisible(searchOnTime);
                    cb7.setVisible(searchOnTime);
                    cb8.setVisible(searchOnTime);
                    cb9.setVisible(searchOnTime);
                    if(!searchOnTime){
                        prepTimeCriterias.clear();
                        cb6.setChecked(false);
                        cb7.setChecked(false);
                        cb8.setChecked(false);
                        cb9.setChecked(false);
                    }
                    break;
                case "10":
                case "20":
                case "30":
                case "40":
                    searchOnTime = sharedPreferences.getBoolean("search_on_time",false);
                    CheckBoxPreference prepTimeCheckBox = findPreference(s);
                    if(searchOnTime && prepTimeCheckBox.isChecked()) prepTimeCriterias.add(s);
                    else prepTimeCriterias.remove(s);
                    break;
                case "search_on_ethnicity":
                    searchOnEthnicity = sharedPreferences.getBoolean(s,false);
                    CheckBoxPreference cb10 = findPreference("Greek");
                    CheckBoxPreference cb11 = findPreference("American");
                    CheckBoxPreference cb12 = findPreference("Japanese");
                    CheckBoxPreference cb13 = findPreference("Chinese");
                    cb10.setVisible(searchOnEthnicity);
                    cb11.setVisible(searchOnEthnicity);
                    cb12.setVisible(searchOnEthnicity);
                    cb13.setVisible(searchOnEthnicity);
                    if(!searchOnEthnicity){
                        ethnicityCriterias.clear();
                        cb10.setChecked(false);
                        cb11.setChecked(false);
                        cb12.setChecked(false);
                        cb13.setChecked(false);
                    }
                    break;
                case "Greek":
                case "American":
                case "Japanese":
                case "Chinese":
                    searchOnEthnicity = sharedPreferences.getBoolean("search_on_ethnicity",false);
                    CheckBoxPreference ethnicityCheckBox = findPreference(s);
                    if(searchOnEthnicity && ethnicityCheckBox.isChecked()) ethnicityCriterias.add(s);
                    else ethnicityCriterias.remove(s);
                    break;
                case "search_on_difficulty":
                    searchOnDifficulty = sharedPreferences.getBoolean(s,false);
                    CheckBoxPreference cb14 = findPreference("Easy");
                    CheckBoxPreference cb15 = findPreference("Medium");
                    CheckBoxPreference cb16 = findPreference("Hard");
                    CheckBoxPreference cb17 = findPreference("Expert");
                    cb14.setVisible(searchOnDifficulty);
                    cb15.setVisible(searchOnDifficulty);
                    cb16.setVisible(searchOnDifficulty);
                    cb17.setVisible(searchOnDifficulty);
                    if(!searchOnDifficulty){
                        difficutlyCriterias.clear();
                        cb14.setChecked(false);
                        cb15.setChecked(false);
                        cb16.setChecked(false);
                        cb17.setChecked(false);
                    }
                    break;
                case"Easy":
                case "Medium":
                case "Hard":
                case "Expert":
                    searchOnDifficulty = sharedPreferences.getBoolean("search_on_difficulty",false);
                    CheckBoxPreference difficultyCheckBox = findPreference(s);
                    if(searchOnDifficulty && difficultyCheckBox.isChecked()) difficutlyCriterias.add(s);
                    else difficutlyCriterias.remove(s);
                    break;
                case "search_on_flavour":
                    searchOnFlavour = sharedPreferences.getBoolean(s,false);
                    CheckBoxPreference cb18 = findPreference("Spicy");
                    CheckBoxPreference cb19 = findPreference("Sweet");
                    CheckBoxPreference cb20 = findPreference("Savory");
                    CheckBoxPreference cb21 = findPreference("Other");
                    cb18.setVisible(searchOnFlavour);
                    cb19.setVisible(searchOnFlavour);
                    cb20.setVisible(searchOnFlavour);
                    cb21.setVisible(searchOnFlavour);
                    if(!searchOnFlavour){
                        flavourCriterias.clear();
                        cb18.setChecked(false);
                        cb19.setChecked(false);
                        cb20.setChecked(false);
                        cb21.setChecked(false);
                    }
                    break;
                case "Spicy":
                case "Sweet":
                case "Savory":
                case "Other":
                    searchOnFlavour = sharedPreferences.getBoolean("search_on_flavour",false);
                    CheckBoxPreference flavourCheckBox = findPreference(s);
                    if(searchOnFlavour && flavourCheckBox.isChecked()) flavourCriterias.add(s);
                    else flavourCriterias.remove(s);
                    break;
                case "search_on_ingredient":
                    Set<String> entries = sharedPreferences.getStringSet("search_on_ingredient", null);
                    String[] selecteds = entries.toArray(new String[]{});
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.clear();
                    Collections.addAll(ingredients, selecteds);
                    System.out.println(ingredients.toString());
                    search_logic_ingredient(ingredients);
                    break;
                default:
                    break;

            }
            search_logic_foodCriteria(prepTimeCriterias,flavourCriterias,difficutlyCriterias,ethnicityCriterias);
            search_logic_answer();
        }

        private void search_logic_foodCriteria(ArrayList<String> prepTimeCriterias,ArrayList<String> flavourCriterias,ArrayList<String> difficutlyCriterias,ArrayList<String> ethnicityCriterias){
            foodsCriteriaResults.clear();
            AccessFoods accessFoods = new AccessFoods();
            ArrayList<Food> foodCriteria = new ArrayList<>();
            accessFoods.foodCriteriaSearch(prepTimeCriterias,flavourCriterias,difficutlyCriterias,ethnicityCriterias,foodCriteria);
            foodsCriteriaResults.addAll(foodCriteria);
        }

        private void search_logic_category(ArrayList<String> categorys){
            foodsCategoryResults.clear();
            System.out.println("category search :"+categorys.toString());
            Set<Food> foodCategorySet = new HashSet<>();
            for(String category:categorys){
                ArrayList<Food> foodCategoryResult = new ArrayList<>();
                AccessFoods accessFoods = new AccessFoods();
                accessFoods.getFoodsByCategory(category,foodCategoryResult);
                foodCategorySet.addAll(foodCategoryResult);
            }
            ArrayList<Food> FoodCategorysResult = new ArrayList<>(foodCategorySet);
            foodsCategoryResults.addAll(FoodCategorysResult);
        }

        private void search_logic_ingredient(ArrayList<String> ingredients){
            foodsIngredientResults.clear();
            System.out.println("ingredient search"+ingredients.toString());
            Set<Food> foodIngredientSet = new HashSet<>();
            for(String ingredient:ingredients){
                ArrayList<Food> foodIngredientResult = new ArrayList<>();
                AccessIngredients accessIngredients = new AccessIngredients();
                accessIngredients.getFoodsByIngredient(ingredient,foodIngredientResult);
                foodIngredientSet.addAll(foodIngredientResult);
            }
            ArrayList<Food> FoodIngredientsResult = new ArrayList<>(foodIngredientSet);
            foodsIngredientResults.addAll(FoodIngredientsResult);
        }

        public void search_logic_answer(){
            foodsFinalResult.clear();
            ArrayList<Food> twoListResult = new ArrayList<>();
            if(!foodsCategoryResults.isEmpty() && !foodsCriteriaResults.isEmpty()){
                System.out.println("intersection");
                twoListResult.addAll(intersection(foodsCategoryResults,foodsCriteriaResults));
            }else if(foodsCategoryResults.isEmpty()){
                twoListResult.addAll(foodsCriteriaResults);
            }else{
                twoListResult.addAll(foodsCategoryResults);
            }
            if(!foodsIngredientResults.isEmpty()){
                foodsFinalResult.addAll(intersection(twoListResult,foodsIngredientResults));
            }else{
                foodsFinalResult.addAll(twoListResult);
            }
            Snackbar.make(this.getView(), foodsFinalResult.size()+" food match", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
            OnhandActivity.resultList.clear();
            OnhandActivity.resultList.addAll(foodsFinalResult);
        }
        public <T> List<T> intersection(List<T> list1, List<T> list2){
            List<T> list = new ArrayList<T>();
            for (T t : list1){
                if(list2.contains(t)){
                    list.add(t);
                }
            }
            return list;
        }
    }

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

}