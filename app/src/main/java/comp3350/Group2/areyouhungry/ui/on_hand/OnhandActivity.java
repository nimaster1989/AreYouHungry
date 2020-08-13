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

public class OnhandActivity extends AppCompatActivity{

    public static ArrayList<Food> resultList;
    public static ArrayList<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.searches, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("Search");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        foods = new ArrayList<>();
        resultList = new ArrayList<>();

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
        /* Food object criteria's. */
        ArrayList<String> totalTimeCriterias;
        ArrayList<String> flavourCriterias;
        ArrayList<String> difficutlyCriterias;
        ArrayList<String> ethnicityCriterias;
        ArrayList<String> ingredientsCriterias;
        ArrayList<String> ingredientsTextCriterias;
        ArrayList<Food> foodsCriteriaResults;
        ArrayList<Food> foodsCategoryResults;
        ArrayList<Food> foodsIngredientResults;
        ArrayList<Food> foodsFinalResult;

        ArrayList<Ingredient> allIngredient;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
            categoryCriterias = new ArrayList<>();
            totalTimeCriterias = new ArrayList<>();
            flavourCriterias = new ArrayList<>();
            difficutlyCriterias = new ArrayList<>();
            ethnicityCriterias = new ArrayList<>();
            ingredientsCriterias = new ArrayList<>();
            ingredientsTextCriterias = new ArrayList<>();
            foodsCriteriaResults = new ArrayList<>();
            foodsCategoryResults = new ArrayList<>();
            foodsIngredientResults = new ArrayList<>();
            foodsFinalResult = new ArrayList<>();
            allIngredient = new ArrayList<>();
            setPreferencesFromResource(R.xml.search_preferences, rootKey);
            MultiSelectListPreference mlp = (MultiSelectListPreference)findPreference("search_on_ingredient");
            AccessIngredients accessIngredients = new AccessIngredients();
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            accessIngredients.getAllIngredient(ingredients);
            /* Assign to class variable. */
            allIngredient = ingredients;
            /* Set up ingredient options view for search. */
            int size = ingredients.size();
            CharSequence[] entries = new CharSequence[size];
            CharSequence[] entryvalue = new CharSequence[size];
            int i = 0;
            for (Ingredient ingredient : ingredients){
                entries[i] = ingredient.getIngredientName();
                entryvalue[i] = String.valueOf(ingredient.getIngredientID());
                i++;
            }
            mlp.setEntries(entries);
            mlp.setEntryValues(entryvalue);
        }
        public void onResume(){
            super.onResume();
            /* Set up a listener whenever a key changes. */
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause(){
            super.onPause();
            /* Set up a listener whenever a key changes. */
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
                    if(!searchOnCategory){
                        categoryCriterias.clear();
                        cb1.setChecked(false);
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                        cb4.setChecked(false);
                        cb5.setChecked(false);
                    }
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
                    CheckBoxPreference cb6 = findPreference("15");
                    CheckBoxPreference cb7 = findPreference("30");
                    CheckBoxPreference cb8 = findPreference("45");
                    CheckBoxPreference cb9 = findPreference("60");
                    CheckBoxPreference cb10 = findPreference("100");
                    cb6.setVisible(searchOnTime);
                    cb7.setVisible(searchOnTime);
                    cb8.setVisible(searchOnTime);
                    cb9.setVisible(searchOnTime);
                    cb10.setVisible(searchOnTime);
                    if(!searchOnTime){
                        totalTimeCriterias.clear();
                        cb6.setChecked(false);
                        cb7.setChecked(false);
                        cb8.setChecked(false);
                        cb9.setChecked(false);
                        cb10.setChecked(false);
                    }
                    break;
                case "15":
                case "30":
                case "45":
                case "60":
                case "100":
                    searchOnTime = sharedPreferences.getBoolean("search_on_time",false);
                    CheckBoxPreference totalTimeCheckBox = findPreference(s);
                    if(searchOnTime && totalTimeCheckBox.isChecked()) totalTimeCriterias.add(s);
                    else totalTimeCriterias.remove(s);
                    break;
                case "search_on_ethnicity":
                    searchOnEthnicity = sharedPreferences.getBoolean(s,false);
                    CheckBoxPreference cb11 = findPreference("American");
                    CheckBoxPreference cb12 = findPreference("Greek");
                    CheckBoxPreference cb13 = findPreference("Italian");
                    CheckBoxPreference cb14 = findPreference("Chinese");
                    CheckBoxPreference cb15 = findPreference("OtherEthnicity");
                    cb11.setVisible(searchOnEthnicity);
                    cb12.setVisible(searchOnEthnicity);
                    cb13.setVisible(searchOnEthnicity);
                    cb14.setVisible(searchOnEthnicity);
                    cb15.setVisible(searchOnEthnicity);
                    if(!searchOnEthnicity){
                        ethnicityCriterias.clear();
                        cb11.setChecked(false);
                        cb12.setChecked(false);
                        cb13.setChecked(false);
                        cb14.setChecked(false);
                        cb15.setChecked(false);
                    }
                    break;
                case "American":
                case "Greek":
                case "Italian":
                case "Chinese":
                case "OtherEthnicity":
                    searchOnEthnicity = sharedPreferences.getBoolean("search_on_ethnicity",false);
                    CheckBoxPreference ethnicityCheckBox = findPreference(s);
                    if(searchOnEthnicity && ethnicityCheckBox.isChecked()) ethnicityCriterias.add(s);
                    else ethnicityCriterias.remove(s);
                    break;
                case "search_on_difficulty":
                    searchOnDifficulty = sharedPreferences.getBoolean(s,false);
                    CheckBoxPreference cb16 = findPreference("Easy");
                    CheckBoxPreference cb17 = findPreference("Medium");
                    CheckBoxPreference cb18 = findPreference("Hard");
                    CheckBoxPreference cb19 = findPreference("Expert");
                    cb16.setVisible(searchOnDifficulty);
                    cb17.setVisible(searchOnDifficulty);
                    cb18.setVisible(searchOnDifficulty);
                    cb19.setVisible(searchOnDifficulty);
                    if(!searchOnDifficulty){
                        difficutlyCriterias.clear();
                        cb16.setChecked(false);
                        cb17.setChecked(false);
                        cb18.setChecked(false);
                        cb19.setChecked(false);
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
                    CheckBoxPreference cb20 = findPreference("Spicy");
                    CheckBoxPreference cb21 = findPreference("Sweet");
                    CheckBoxPreference cb22 = findPreference("Savoury");
                    CheckBoxPreference cb23 = findPreference("Fresh");
                    CheckBoxPreference cb24 = findPreference("OtherFlavour");
                    cb20.setVisible(searchOnFlavour);
                    cb21.setVisible(searchOnFlavour);
                    cb22.setVisible(searchOnFlavour);
                    cb23.setVisible(searchOnFlavour);
                    cb24.setVisible(searchOnFlavour);
                    if(!searchOnFlavour){
                        flavourCriterias.clear();
                        cb20.setChecked(false);
                        cb21.setChecked(false);
                        cb22.setChecked(false);
                        cb23.setChecked(false);
                        cb24.setChecked(false);
                    }
                    break;
                case "Spicy":
                case "Sweet":
                case "Savoury":
                case "Fresh":
                case "OtherFlavour":
                    searchOnFlavour = sharedPreferences.getBoolean("search_on_flavour",false);
                    CheckBoxPreference flavourCheckBox = findPreference(s);
                    if(searchOnFlavour && flavourCheckBox.isChecked()) flavourCriterias.add(s);
                    else flavourCriterias.remove(s);
                    break;
                case "search_on_ingredient":
                    ingredientsCriterias.clear();
                    Set<String> entries = sharedPreferences.getStringSet("search_on_ingredient", null);
                    String[] selecteds = entries.toArray(new String[]{});
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.clear();
                    Collections.addAll(ingredients, selecteds);
                    ingredientsCriterias.addAll(ingredients);
                    break;
                case "search_on_ingredient_text":
                    ingredientsCriterias.removeAll(ingredientsTextCriterias);
                    ingredientsTextCriterias.clear();
                    String searchOnIngredientText = sharedPreferences.getString("search_on_ingredient_text",null);
                    if(searchOnIngredientText != null && !searchOnIngredientText.equals("")){
                        String[] texts = searchOnIngredientText.split(",");
                        for(String text:texts){
                            fuzzy_ingredient_search(text.trim(),ingredientsTextCriterias);
                        }
                    }
                    break;
                default:
                    break;

            }
            if(!ingredientsTextCriterias.isEmpty()) ingredientsCriterias.addAll(ingredientsTextCriterias);
            if(!ingredientsCriterias.isEmpty()) search_logic_ingredient(ingredientsCriterias);
            search_logic_foodCriteria(totalTimeCriterias,flavourCriterias,difficutlyCriterias,ethnicityCriterias);
            search_logic_answer();
        }

        private void search_logic_foodCriteria(ArrayList<String> totalTimeCriterias,ArrayList<String> flavourCriterias,ArrayList<String> difficutlyCriterias,ArrayList<String> ethnicityCriterias){
            foodsCriteriaResults.clear();
            AccessFoods accessFoods = new AccessFoods();
            ArrayList<Food> foodCriteria = new ArrayList<>();
            accessFoods.foodCriteriaSearch(totalTimeCriterias,flavourCriterias,difficutlyCriterias,ethnicityCriterias,foodCriteria);
            foodsCriteriaResults.addAll(foodCriteria);
        }

        private void search_logic_category(ArrayList<String> categorys){
            foodsCategoryResults.clear();
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
        private void fuzzy_ingredient_search(String str,ArrayList ingredientsTextCriterias){
            for (int index=0;index<allIngredient.size();index++){
                String ingredientName = allIngredient.get(index).getIngredientName();
                if (ingredientName.equals((str))){
                    ingredientsTextCriterias.add(String.valueOf(index + 1));
                } else if (ingredientName.toLowerCase().contains(str.toLowerCase())){
                    ingredientsTextCriterias.add(String.valueOf(index + 1));
                } else{
                    /* Time complexity for calculate long string levenshtein distance is large,
                      its not necessary, we break ingredient by space and search each word. */
                    String[] ingredientNames = ingredientName.split(" ");
                    for (String s : ingredientNames){
                        int distance = levenshtein(s, str);
                        if ((double)distance <= (double)str.length() / 3){
                            ingredientsTextCriterias.add(String.valueOf(index + 1));
                        }
                    }
                }
            }
        }

        public void search_logic_answer(){
            foodsFinalResult.clear();
            ArrayList<Food> twoListResult = new ArrayList<>();
            if(!foodsCategoryResults.isEmpty() && !foodsCriteriaResults.isEmpty()){
                twoListResult.addAll(intersection(foodsCategoryResults,foodsCriteriaResults));
            }else if(foodsCategoryResults.isEmpty()){
                twoListResult.addAll(foodsCriteriaResults);
            }else{
                twoListResult.addAll(foodsCategoryResults);
            }
            if(!foodsIngredientResults.isEmpty() && !twoListResult.isEmpty()){
                foodsFinalResult.addAll(intersection(twoListResult,foodsIngredientResults));
            }else if(!foodsIngredientResults.isEmpty() && twoListResult.isEmpty()){
                foodsFinalResult.addAll(foodsIngredientResults);
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

        /* Two helper methods for fuzzy search, calculate the levenshtein distance
           we assume if two word have levenshtein distance <= 1/3 word length, we consider it a result. */
        public int levenshtein (CharSequence lhs, CharSequence rhs){
            int len0 = lhs.length() + 1;
            int len1 = rhs.length() + 1;
            int[] cost = new int[len0];
            int[] newcost = new int[len0];
            for (int i = 0; i < len0; i++) cost[i] = i;
            for (int j = 1; j < len1; j++){
                newcost[0] = j;
                for(int i = 1; i < len0; i++){
                    int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;
                    int cost_replace = cost[i - 1] + match;
                    int cost_insert  = cost[i] + 1;
                    int cost_delete  = newcost[i - 1] + 1;
                    newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
                }
                int[] swap = cost; cost = newcost; newcost = swap;
            }

            return cost[len0 - 1];
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
