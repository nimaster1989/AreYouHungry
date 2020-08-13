package comp3350.Group2.areyouhungry.ui.on_hand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailActivity;
import comp3350.Group2.areyouhungry.ui.all_food.FoodDetailFragment;

public class SearchResultActivity extends AppCompatActivity{

    private AccessFoods accessFoods;
    private ArrayList<Food> searchResultList;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setTitle("Search Result");

        View recyclerView = findViewById(R.id.searchresult_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView){
        searchResultList = new ArrayList<Food>();
        searchResultList.addAll(OnhandActivity.resultList);
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, searchResultList, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>{
        private final SearchResultActivity mParentActivity;
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

        SimpleItemRecyclerViewAdapter(SearchResultActivity parent, List<Food> items, boolean twoPane){
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favouritefood_list_content, parent, false);
            return new SimpleItemRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleItemRecyclerViewAdapter.ViewHolder holder, int position){
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