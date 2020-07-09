package comp3350.Group2.areyouhungry.ui.all_food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.add_food.AddActivity;
import comp3350.Group2.areyouhungry.ui.favorites.FavouriteFoodListActivity;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Foods. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link FoodDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class FoodListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private AccessFoods accessFoods;
    private ArrayList<Food> foodList;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("FoodListActivity oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //for button navigation bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        Intent home_intent = new Intent(FoodListActivity.this, HomeActivity.class);
                        startActivity(home_intent);
                        break;

                    case R.id.navigation_favorites:
                        Intent fav_intent = new Intent(FoodListActivity.this, FavouriteFoodListActivity.class);
                        startActivity(fav_intent);
                        break;

                    case R.id.navigation_more:
                        Intent more_intent = new Intent(FoodListActivity.this, MoreActivity.class);
                        startActivity(more_intent);
                        break;
                }
                return false;
            }
        });

        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.all_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_intent = new Intent(FoodListActivity.this, AddActivity.class);
                FoodListActivity.this.startActivity(add_intent);
                System.out.println("after call add activity");
            }
        });

        if (findViewById(R.id.food_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.food_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        accessFoods = new AccessFoods();
        foodList = new ArrayList<Food>();
        accessFoods.getFoods(foodList);
        SimpleItemRecyclerViewAdapter theAdapter  = new SimpleItemRecyclerViewAdapter(this, foodList, mTwoPane);
        recyclerView.setAdapter(theAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final FoodListActivity mParentActivity;
        private final List<Food> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Food food = (Food) view.getTag();
                if (mTwoPane) {
                    System.out.println("Two Page tablet mode");
                    Bundle arguments = new Bundle();
                    arguments.putString(FoodDetailFragment.ARG_ITEM_ID, food.getFoodID());
                    FoodDetailFragment fragment = new FoodDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.food_detail_container, fragment)
                            .commit();
                } else {
                    System.out.println("Two Page tablet mode");
                    Context context = view.getContext();
                    Intent intent = new Intent(context, FoodDetailActivity.class);
                    intent.putExtra(FoodDetailFragment.ARG_ITEM_ID, food.getFoodID());

                    context.startActivity(intent);
                }
            }
        };


            SimpleItemRecyclerViewAdapter(FoodListActivity parent,
                                    List<Food> items,
                                        boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.food_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getFoodID());
            holder.mContentView.setText(mValues.get(position).getFoodName());
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