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

    public Map getFoodMap(Map ret_food_map);

    public String getFoodSequential(List<Food> foodResult);

    //public String getFavouriteFoodSequential(List<Food> foodResult);

    public String getFoodRandom(List<Food> foodResult);

    public String getFoodFromQuestions(List<Food> foodResult);

    public String getFoodPreferred(List<Food> foodResult, String food);

    public Food getFoodFromID(String foodID);

    //public String setFoodToFavourite(String curr_id, boolean favourite);

    public String addFood(Food addFood);

    public int getFoodTableRow();

    public String addFoodCategory(int foodID,int categoryID);

    public int getIDByFood(Food food);

    public int getCategoryIDbyName(String categoryName);

    public List<Question> getAllQuestions();

    public int getTotalQuestions();

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
