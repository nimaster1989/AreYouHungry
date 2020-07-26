package comp3350.Group2.areyouhungry.business;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.persistance.DataAccess;
import comp3350.Group2.areyouhungry.persistance.DataAccessStub;

import java.util.ArrayList;
import java.util.Iterator;
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


    //definition of duplicate: we assume foods might have same name, but not same recipe,
    //we call two foods to be equal if they have same name, and same recipe
    public boolean checkDuplicate(Food food){
        boolean duplicate = false;
        List<Food> foodsInDatabase = new ArrayList<Food>();
        String result = this.getFoods(foodsInDatabase);
        if(result == null){
            Iterator<Food> foodIterator = foodsInDatabase.iterator(); //This iterates through the foods list
            Food curr_food;
            while(foodIterator.hasNext()){
                curr_food = foodIterator.next();
                if(curr_food.equals(food)){
                    duplicate = true;
                    break;
                }
            }
        }
        return duplicate;
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

    public String addFoodCategory(Food newFood, String categoryName) {
        int foodID = getFoodID(newFood);
        int categoryID = getCategoryID(categoryName);
        return dataAccess.addFoodCategory(foodID, categoryID);
    }

    private int getCategoryID(String categoryName) {
        return dataAccess.getCategoryIDbyName(categoryName);
    }

    //xu ang: if not found, should return -1
    public int getFoodID(Food food){
        return dataAccess.getIDByFood(food);
    }
}
