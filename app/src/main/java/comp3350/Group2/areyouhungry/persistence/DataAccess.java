package comp3350.Group2.areyouhungry.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;

public interface DataAccess{
    void open(String string);

    void close();

    Map getFoodMap(Map ret_food_map);

    String getFoodSequential(List<Food> foodResult);

    String getFoodRandom(List<Food> foodResult);

    String getFoodPreferred(List<Food> foodResult, String food);

    Food getFoodFromID(String foodID);

    String addFood(Food addFood);

    int getFoodTableRow();

    String addFoodCategory(int foodID, int categoryID);

    int getIDByFood(Food food);

    int getCategoryIDbyName(String categoryName);

    List<Question> getAllQuestions();

    int getTotalQuestions();

    User getDefault();

    String getFavouriteFoodByUserSequential(User user, List<Food> favouriteFoodList);

    String setFoodToFavouriteByUser(User user,String curr_id, boolean b);

    boolean getFoodFavByUser(User user, Food food);

    String getUserSequential(List<User> userResult);

    User getUser(int userID);

    User setNewUser(int userID, String username);

    String getIngredientByFood(Food food,List<Ingredient> ingredients);

    String getDirectionByFood(Food food, List<Direction> directions);
}
