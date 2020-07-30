package comp3350.Group2.areyouhungry.ui.on_hand;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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
        foods = new ArrayList<>();
        resultList = new ArrayList();
        AccessFoods accessFoods = new AccessFoods();
        accessFoods.getFoods(foods);
        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.search_fba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("search onclick");
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

        ArrayList<String> categoryCriterias;
        //food ojbect criterias
        ArrayList<String> portionSizeCriterias;
        ArrayList<String> prepTimeCriterias;
        ArrayList<String> flavourCriterias;
        ArrayList<String> difficutlyCriterias;
        ArrayList<String> ethnicityCriterias;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            categoryCriterias = new ArrayList<>();
            portionSizeCriterias = new ArrayList<>();
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
            for (Ingredient ingredient : ingredients) {
                str_input = ingredient.getMeasurement() +" Of "+ingredient.getIngredientName();
                entries[i] = (CharSequence) str_input;
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
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            Boolean searchOnCategory = false;
            Boolean searchOnTime = false;
            Boolean searchOnEthnicity = false;
            Boolean searchOnDifficulty = false;
            Boolean searchOnFlavour = false;
            switch (s) {
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
                    break;
                case"Easy":
                case "Medium":
                case "Hard":
                case "Expert":
                    searchOnDifficulty = sharedPreferences.getBoolean("search_on_difficulty",false);
                    CheckBoxPreference difficultyCheckBox = findPreference(s);
                    if(searchOnDifficulty && difficultyCheckBox.isChecked()) difficutlyCriterias.add(s);
                    else difficutlyCriterias.remove(s);
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
                    break;
                case "search_on_ingredient":
                    Set<String> entries = sharedPreferences.getStringSet("search_on_ingredient", null);
                    String[] selecteds = entries.toArray(new String[]{});
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.clear();
                    Collections.addAll(ingredients, selecteds);
                    System.out.println("select:"+ingredients.size());
                    System.out.println(ingredients.toString());
                    search_logic_ingredient(ingredients);
                    break;
                default:
                    break;

            }
        }

        private void search_logic_foodCriteria(ArrayList<String> portionSizeCriterias){

        }

        private void search_logic_category(ArrayList<String> categorys) {
            System.out.println("category search :"+categorys.toString());
        }

        private void search_logic_ingredient(ArrayList<String> ingredients) {
            System.out.println("ingredient search"+ingredients.toString());

        }
        public <T> List<T> union(List<T> list1, List<T> list2) {
            Set<T> set = new HashSet<T>();

            set.addAll(list1);
            set.addAll(list2);

            return new ArrayList<T>(set);
        }

        public <T> List<T> intersection(List<T> list1, List<T> list2) {
            List<T> list = new ArrayList<T>();

            for (T t : list1) {
                if(list2.contains(t)) {
                    list.add(t);
                }
            }

            return list;
        }
    }

    public void onBackPressed() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        finish();
    }

}