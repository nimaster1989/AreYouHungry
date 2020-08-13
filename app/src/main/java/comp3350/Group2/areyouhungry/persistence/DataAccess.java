package comp3350.Group2.areyouhungry.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.objects.FoodCategory;

public interface DataAccess{
    void open(String string);

    void close();

    User getDefault();

    Map getFoodMap(Map ret_food_map);

    String getFoodSequential(List<Food> foodResult);

    String getFoodRandom(List<Food> foodResult);

    Food getFoodFromID(String foodID);

    String getIngredientByFood(Food food,List<Ingredient> ingredients);

    String getDirectionByFood(Food food, List<Direction> directions);

    int getFoodTableRow();

    List<Question> getAllQuestions();

    int getTotalQuestions();

    boolean getFoodFavByUser(User user, Food food);

    boolean getFoodLikedByUser(User user, Food food);

    boolean getFoodDislikedByUser(User user, Food food);

    String getUserSequential(List<User> userResult);

    int getCategoryIDbyName(String categoryName);

    int getTotalUser(); /* Not used but will be used for later functionality. */

    String getFavouriteFoodByUserSequential(User user, List<Food> favouriteFoodList);

    User getUser(int userID);

    String setFoodToFavouriteByUser(User user,String curr_id, boolean b);

    String setFoodToLikedByUser(User user,String curr_id, boolean b);

    String setFoodToDislikedByUser(User user,String curr_id, boolean b);



    User setNewUser(int userID, String username);

    FoodCategory addFoodCategory(int foodID, int categoryID);

    String addFood(Food addFood);

    void deleteFoodCategory(int foodID, int categoryID);

    void deleteUser(int userID);

    void deleteFood(int foodID);

    int getIngredientRow();

    String addFoodIngredient(int foodid, int ingredientid);

    String addNewIngredient(Ingredient newIngredient);

    int getDirectionRow();

    String addNewDirection(Direction newDirection);

    String addFoodDirection(int foodid, int directionid);

    String getIngredientSequential(List<Ingredient> ingredients);

    String searchFoodByCriteriaLists(ArrayList<String> totalTimeCriterias, ArrayList<String> flavourCriterias, ArrayList<String> difficutlyCriterias, ArrayList<String> ethnicityCriterias, ArrayList<Food> foodResult);

    String getFoodSequentialByCategory(String category, ArrayList<Food> foodCategoryResult);

    String getFoodsSequentialByIngredient(String ingredient, ArrayList<Food> foodIngredientResult);

    String addFoodImage(int foodid, String foodURL);

    String getImageByFood(int foodid);


}
