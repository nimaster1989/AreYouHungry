package comp3350.Group2.areyouhungry.presistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Food;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Food> foods;
    private Map<String,Food> Food_map;
    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub() { this(MainActivity.dbName); }

    public void open(String dbName){
        System.out.println("database open");
        Food food;

        foods = new ArrayList<Food>();
        Food_map = new HashMap<>();
        food = new Food("001","fish","google.1");
        food.setFavourite(true);
        foods.add(food);
        Food_map.put(food.foodID,food);
        food = new Food("002","burger","google.2");
        foods.add(food);
        Food_map.put(food.foodID,food);
        food = new Food("003","pie","google.3");
        food.setFavourite(true);
        foods.add(food);
        Food_map.put(food.foodID,food);
        food = new Food("004","cake","google.4");
        foods.add(food);
        Food_map.put(food.foodID,food);
        food = new Food("005","chips","google.5");
        foods.add(food);
        Food_map.put(food.foodID,food);
        food = new Food("006","veggies","google.6");
        food.setFavourite(true);
        foods.add(food);
        Food_map.put(food.foodID,food);

        System.out.println("Opened " +dbType +" database " +dbName);
    }
    public void close()
    {
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    //add on function to get the food map for FoodDetail Activity
    public Map getFoodMap(Map ret_food_map){
        ret_food_map.putAll(Food_map);
        return null;
    }
    public Map getFoodMapRamdom(Map foods) {
        Random random = new Random();
        Food aRandomFood =  (Food)foods.get(random.nextInt(foods.size()));
        foods.put(Food_map.get(aRandomFood.foodID),aRandomFood);
        return foods;
    }
    //xu yang: this function is from sample project, used to add food to a list that contain
    //all foods in the stub datasbase
    public String getFoodSequential(List<Food> foodResult){
        System.out.println("run getFoodSequential");
        foodResult.addAll((foods));
        return null;
    }
    //david le : generate the favourite list
    public String getFavouriteFoodSequential(List<Food> foodResult){
        System.out.println("run getFoodFavourite");
        Iterator<Food> foodIterator = foods.iterator(); //This iterates through the foods list
        Food recipe;
        while(foodIterator.hasNext()){
            recipe = foodIterator.next();
            if(recipe.favourite){
                foodResult.add(recipe);
            }
        }
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

    public String getFoodPreferred(List<Food> foodResult, String food){
        if(food.equals("Meat")){
            foodResult.add(foods.get(1));
        }
        else if(food.equals("Dessert")){
            foodResult.add(foods.get(3));
        }

        return null;
    }

}
