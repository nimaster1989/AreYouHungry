package comp3350.Group2.areyouhungry.ui.favorites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;
import comp3350.Group2.areyouhungry.ui.all_food.FoodListActivity;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;
import comp3350.Group2.areyouhungry.ui.more.MoreActivity;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFoodListActivity extends AppCompatActivity{

    private AccessFoods accessFoods;
    private ArrayList<Food> favouriteFoodList;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouritefood_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) findViewById(R.id.fav_fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent all_intent = new Intent(FavouriteFoodListActivity.this, FoodListActivity.class);
                FavouriteFoodListActivity.this.startActivity(all_intent);
            }
        });

        if (findViewById(R.id.favouritefood_detail_container) != null){
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.favouritefood_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){

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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView){
        accessFoods = new AccessFoods();
        favouriteFoodList = new ArrayList<Food>();
        User currentUser = MainActivity.currentUser;
        accessFoods.getFavouriteFoodsByUser(currentUser,favouriteFoodList);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, favouriteFoodList, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>{
        private final FavouriteFoodListActivity mParentActivity;
        private final List<Food> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Food food = (Food) view.getTag();
                if (mTwoPane){
                    Bundle arguments = new Bundle();
                    arguments.putString(FoodDetailFragment.ARG_ITEM_ID, food.getFoodID());
                    FoodDetailFragment fragment = new FoodDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.food_detail_container, fragment).commit();
                } else{
                    Context context = view.getContext();
                    Intent intent = new Intent(context, FoodDetailActivity.class);
                    intent.putExtra(FoodDetailFragment.ARG_ITEM_ID, food.getFoodID());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(FavouriteFoodListActivity parent, List<Food> items, boolean twoPane){
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favouritefood_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position){
            holder.mIdView.setText(mValues.get(position).getFoodID());
            holder.mContentView.setText(mValues.get(position).getFoodName());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount(){
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view){
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}