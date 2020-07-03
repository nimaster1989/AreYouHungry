package comp3350.Group2.areyouhungry.ui.favorites;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;

/**
 * A fragment representing a single FavouriteFood detail screen.
 * This fragment is either contained in a {@link FavouriteFoodListActivity}
 * in two-pane mode (on tablets) or a {@link FavouriteFoodDetailActivity}
 * on handsets.
 */
public class FavouriteFoodDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    //private DummyContent.DummyItem mItem;
    private Food mFood;
    private AccessFoods accessFoods;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FavouriteFoodDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            accessFoods = new AccessFoods();
            Map<String, Food> Food_map = new HashMap<String,Food>();
            accessFoods.getMap(Food_map);
            mFood = Food_map.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mFood.foodName);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favouritefood_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mFood != null) {
            ((TextView) rootView.findViewById(R.id.favouritefood_detail)).setText(mFood.recipeLink);
        }

        return rootView;
    }
}