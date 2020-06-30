package comp3350.Group2.areyouhungry.business;

//import comp3350.Group2.areyouhungry.*;
import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.dummy.DummyContent;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.presistence.DataAccessStub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccessFoods {
    private DataAccessStub dataAccess;
    private List<Food> foods;
    private Food food;
    private int currentFood;

    public AccessFoods(){
        System.out.println("File(AccessFood) access Course");
        dataAccess = (DataAccessStub) Services.getDataAccess(MainActivity.dbName);
        foods = null;
        food = null;
        currentFood = 0;
    }
    public static final List<Food> ITEMS = new ArrayList<Food>();
    public static final Map<String, Food> ITEM_MAP = new HashMap<String,Food>();

    public Map getMap(Map foods){
        foods.clear();
        return dataAccess.getFoodMap(foods);
    }
    public Map getMapRandom(Map foods){
        foods.clear();
        return dataAccess.getFoodMapRamdom(foods);
    }
    public String getFoods(List<Food> foods)
    {
        System.out.println("File(AccessFood) getCoures create");
        foods.clear();
        return dataAccess.getFoodSequential(foods);
    }
    public String getRandom(List<Food> foods)
    {
        System.out.println("File(AccessFood) getCoures create");
        foods.clear();
        return dataAccess.getFoodRandom(foods);
    }
    public Food getSequential()
    {
        System.out.println("File(AccessFood) getSequantial create");
        String result = null;
        if (foods == null)
        {
            result = dataAccess.getFoodSequential(foods);
            currentFood = 0;
        }
        if (currentFood < foods.size())
        {
            food = (Food) foods.get(currentFood);
            currentFood++;
        }
        else
        {
            foods = null;
            food = null;
            currentFood = 0;
        }
        return food;
    }
//    public Food getRandom(String foodID)
//    {
//        System.out.println("File(AccessCourse) getRandom create");
//        foods = dataAccess.getFoodRandom(foods);
//        currentFood = 0;
//        if (currentFood < foods.size())
//        {
//            food = (Food) foods.get(currentFood);
//            currentFood++;
//        }
//        else
//        {
//            foods = null;
//            food = null;
//            currentFood = 0;
//        }
//        return food;
//    }
}
