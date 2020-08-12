package comp3350.Group2.areyouhungry.business;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccessFoods{
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

    public String getFoods(List<Food> foods){
        foods.clear();
        return dataAccess.getFoodSequential(foods);
    }

    public Food getFoodByID(String foodID){
        return dataAccess.getFoodFromID(foodID);
    }

    public String getFavouriteFoodsByUser(User user, ArrayList<Food> favouriteFoodList){
        favouriteFoodList.clear();
        return dataAccess.getFavouriteFoodByUserSequential(user, favouriteFoodList);
    }

    public String getRandom(List<Food> foods){
        foods.clear();
        return dataAccess.getFoodRandom(foods);
    }

    public int getFoodRow(){
        return dataAccess.getFoodTableRow();
    }

    public int getCategoryID(String categoryName){
        return dataAccess.getCategoryIDbyName(categoryName);
    }

    public boolean getFoodFavouriteByUser(User user,Food food){
        return dataAccess.getFoodFavByUser(user,food);
    }

    public String setFoodFavouriteByUser(User user,String curr_id, boolean b){
        return dataAccess.setFoodToFavouriteByUser(user,curr_id,b);
    }

    public boolean getFoodLikedByUser(User user,Food food){
        return dataAccess.getFoodLikedByUser(user,food);
    }


    public String setFoodLikedByUser(User user,String curr_id, boolean b){
        return dataAccess.setFoodToLikedByUser(user,curr_id,b);
    }

    public boolean getFoodDislikedByUser(User user,Food food){
        return dataAccess.getFoodDislikedByUser(user,food);
    }

    public String setFoodDislikedByUser(User user,String curr_id, boolean b){
        return dataAccess.setFoodToDislikedByUser(user,curr_id,b);
    }

    public String addFood(Food addedFood){
        return dataAccess.addFood(addedFood);
    }

    public FoodCategory addFoodCategory(Food newFood, String categoryName){
        int foodID = Integer.parseInt(newFood.getFoodID());
        int categoryID = getCategoryID(categoryName);
        return dataAccess.addFoodCategory(foodID, categoryID);
    }

    public String getImagebyFood(String foodid){
        return dataAccess.getImageByFood(Integer.parseInt(foodid));

    }

    public String addFoodImage(String foodid, String foodURL){
        return dataAccess.addFoodImage(Integer.parseInt(foodid), foodURL);
    }

    /* Definition of duplicate: we assume foods might have same name,
   but not same recipe, we call two foods to be equal if they have same
   name, and same recipe. */
    public boolean checkDuplicate(Food food){
        boolean duplicate = false;
        List<Food> foodsInDatabase = new ArrayList<Food>();
        String result = this.getFoods(foodsInDatabase);
        if(result == null){
            Iterator<Food> foodIterator = foodsInDatabase.iterator(); /* This iterates through the foods list. */
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

    public String foodCriteriaSearch(ArrayList<String> prepTimeCriterias, ArrayList<String> flavourCriterias, ArrayList<String> difficutlyCriterias, ArrayList<String> ethnicityCriterias, ArrayList<Food> foodResult){
        return dataAccess.searchFoodByCriteriaLists(prepTimeCriterias,flavourCriterias,difficutlyCriterias,ethnicityCriterias,foodResult);
    }

    public String getFoodsByCategory(String category, ArrayList<Food> foodCategoryResult){
        return dataAccess.getFoodSequentialByCategory(category,foodCategoryResult);
    }
}
