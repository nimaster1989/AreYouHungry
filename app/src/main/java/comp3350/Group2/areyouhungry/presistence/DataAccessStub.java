package comp3350.Group2.areyouhungry.presistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    //xu yang: this function is from sample project, used to add food to a list that contain
    //all foods in the stub datasbase
    public String getFoodSequential(List<Food> foodResult){
        System.out.println("run getFoodSequential");
        foodResult.addAll((foods));
        return null;
    }

    //xu yang: this function is used to generate a random food from the stub database
    //and add it into the foodresult List
    public String getFoodRandom(List<Food> foodResult){
        System.out.println("run getFoodRandom");
        Random random = new Random();
        foodResult.add(foods.get(random.nextInt(foods.size())));
        return null;
    }
}
