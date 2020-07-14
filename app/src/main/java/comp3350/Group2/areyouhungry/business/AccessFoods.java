package comp3350.Group2.areyouhungry.business;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.presistence.DataAccess;
import comp3350.Group2.areyouhungry.presistence.DataAccessStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessFoods {
    private DataAccess dataAccess;
    private List<Food> foods;
    private Food food;
    private int currentFood;

    public AccessFoods(){
        dataAccess = Services.getDataAccess(MainActivity.dbName);
        foods = null;
        food = null;
        currentFood = 0;
    }

    public Map getMap(Map foods){
        foods.clear();
        return dataAccess.getFoodMap(foods);
    }

    public String getFoods(List<Food> foods)
    {
        foods.clear();
        return dataAccess.getFoodSequential(foods);
    }
    public Food getFoodByID(String foodID){
        return dataAccess.getFoodFromID(foodID);
    }

    public String getFavouriteFoods(ArrayList<Food> favouriteFoodList) {
        favouriteFoodList.clear();
        return dataAccess.getFavouriteFoodSequential(favouriteFoodList);
    }
    public String getRandom(List<Food> foods)
    {
        foods.clear();
        return dataAccess.getFoodRandom(foods);
    }
    public String getPreferred(List<Food> foods, String food){
        foods.clear();
        return dataAccess.getFoodPreferred(foods, food);
    }
    public int getFoodRow(){
        return dataAccess.getFoodTableRow();
    }

    public String addFood(Food addedFood) {
        return dataAccess.addFood(addedFood);
    }

    public String setFoodFavourite(String curr_id,boolean favourite) {
        return dataAccess.setFoodToFavourite(curr_id,favourite);
    }
}
