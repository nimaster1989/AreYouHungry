package comp3350.Group2.areyouhungry.presistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Food;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Food> foods;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub() { this(MainActivity.dbName); }

    public void open(String dbName){
        System.out.println("database open");
        Food food;
        foods = new ArrayList<Food>();

        food = new Food("001","fish");
        foods.add(food);
        food = new Food("002","burger");
        foods.add(food);
        food = new Food("003","pie");
        foods.add(food);

        System.out.println("Opened " +dbType +" database " +dbName);
    }
    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public String getFoodSequential(List<Food> foodResult){
        System.out.println("run getFoodSequential");
        foodResult.addAll((foods));
        return null;
    }

    public ArrayList<Food> getFoodRandom(Food currentFood)
    {
        System.out.println("File(DataAccessStub) run get FoodRandom");
        ArrayList<Food> newCourses;
        int index;

        newCourses = new ArrayList<Food>();
        index = foods.indexOf(currentFood);
        if (index >= 0)
        {
            newCourses.add(foods.get(index));
        }
        return newCourses;
    }
}
