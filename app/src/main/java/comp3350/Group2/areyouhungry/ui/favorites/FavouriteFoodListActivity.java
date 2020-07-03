package comp3350.Group2.areyouhungry.ui.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;

import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of FavouriteFoods. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link FavouriteFoodDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class FavouriteFoodListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private AccessFoods accessFoods;
    private ArrayList<Food> favouriteFoodList;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("FavouriteFoodListActivity oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritefood_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.favouritefood_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.favouritefood_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        Intent home_intent = new Intent(FavouriteFoodListActivity.this, HomeActivity.class);
                        startActivity(home_intent);
                        break;

                    case R.id.navigation_favorites:
                        break;

                    case R.id.navigation_more:
                        Intent more_intent = new Intent(FavouriteFoodListActivity.this, MoreActivity.class);
                        startActivity(more_intent);
                        break;
                }
                return false;
            }
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        accessFoods = new AccessFoods();
        favouriteFoodList = new ArrayList<Food>();
        accessFoods.getFavouriteFoods(favouriteFoodList);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, favouriteFoodList, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final FavouriteFoodListActivity mParentActivity;
        private final List<Food> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food food = (Food) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(FavouriteFoodDetailFragment.ARG_ITEM_ID, food.foodID);
                    FavouriteFoodDetailFragment fragment = new FavouriteFoodDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.favouritefood_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, FavouriteFoodDetailActivity.class);
                    intent.putExtra(FavouriteFoodDetailFragment.ARG_ITEM_ID, food.foodID);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(FavouriteFoodListActivity parent,
                                      List<Food> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favouritefood_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).foodID);
            holder.mContentView.setText(mValues.get(position).foodName);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}