package comp3350.Group2.areyouhungry.ui.add_food;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import comp3350.Group2.areyouhungry.R;

public class AddFoodActivity extends AppCompatActivity {

    @Override
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
            if(!s.equals("favourite")){
                String getting = sharedPreferences.getString(s, "");
                System.out.println(s+"set as:"+getting);
                switch (s) {
                    case "ingredient":
                        EditTextPreference ep2 = findPreference("ingredient2");
                        if (ep2 != null && !getting.equals("")) {
                            ep2.setVisible(true);
                            break;
                        }
                    case "ingredient2":
                        ep2 = findPreference("ingredient2");
                        ep2.setSummary(getting);
                        EditTextPreference ep3 = findPreference("ingredient3");
                        if (ep3 != null && !getting.equals("")) {
                            ep3.setVisible(true);
                            break;
                        }
                    case "ingredient3":
                        ep3 = findPreference("ingredient3");
                        ep3.setSummary(getting);
                        EditTextPreference ep4 = findPreference("ingredient4");
                        if (ep4 != null && !getting.equals("")) {
                            ep4.setVisible(true);
                            break;
                        }
                    case "ingredient4":
                        ep4 = findPreference("ingredient4");
                        ep4.setSummary(getting);
                        EditTextPreference ep5 = findPreference("ingredient5");
                        if (ep5 != null && !getting.equals("")) {
                            ep5.setVisible(true);
                            break;
                        }
                    case "instruction":
                        EditTextPreference inp2 = findPreference("instruction2");
                        if (inp2 != null && !getting.equals("")) {
                            inp2.setVisible(true);
                            break;
                        }
                    case "instruction2":
                        inp2 = findPreference("instruction2");
                        inp2.setSummary(getting);
                        EditTextPreference inp3 = findPreference("instruction3");
                        if (inp3 != null && !getting.equals("")) {
                            inp3.setVisible(true);
                            break;
                        }
                    case "instruction3":
                        inp3 = findPreference("instruction3");
                        inp3.setSummary(getting);
                        EditTextPreference inp4 = findPreference("instruction4");
                        if (inp4 != null && !getting.equals("")) {
                            inp4.setVisible(true);
                            break;
                        }
                    case "instruction4":
                        inp4 = findPreference("instruction4");
                        inp4.setSummary(getting);
                        EditTextPreference inp5 = findPreference("instruction5");
                        if (inp5 != null && !getting.equals("")) {
                            inp5.setVisible(true);
                            break;
                        }
                    default:
                        break;
                }
            }
        }
    }
}