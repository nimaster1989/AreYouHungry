package comp3350.Group2.areyouhungry.persistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import comp3350.Group2.areyouhungry.objects.Food;

public class DataAccessStub {

    private ArrayList<Food> foods;
    private Map<String,Food> Food_map;



    public void open(){
        Food food;

        foods = new ArrayList<Food>();
        Food_map = new HashMap<>();
        food = new Food("001","Fish","https://www.food.com/recipe/beer-battered-fish-130102");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
        food = new Food("002","Burger","https://www.food.com/recipe/the-perfect-burger-92021");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
        food = new Food("003","Pie","https://www.tasteofhome.com/recipes/apple-pie/");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
        food = new Food("004","Cake","https://addapinch.com/the-best-chocolate-cake-recipe-ever/");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
        food = new Food("005","Fries","https://www.spendwithpennies.com/crispy-oven-fries/");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
        food = new Food("006","veggies","https://www.cookingclassy.com/greek-salad/");
        foods.add(food);
        Food_map.put(food.getFoodID(),food);
    }


    //add on function to get the food map for FoodDetail Activity
    public Map getFoodMap(Map ret_food_map){
        ret_food_map.putAll(Food_map);
        return null;
    }

    //xu yang: this function is from sample project, used to add food to a list that contain
    //all foods in the stub datasbase
    public String getFoodSequential(List<Food> foodResult){
        foodResult.addAll((foods));
        return null;
    }
    //david le : generate the favourite list
    public String getFavouriteFoodSequential(List<Food> foodResult){
        Iterator<Food> foodIterator = foods.iterator(); //This iterates through the foods list
        Food food;
        while(foodIterator.hasNext()){
            food = foodIterator.next();
            if(food.getFavourite()){
                foodResult.add(food);
            }
        }
        return null;
    }

    //xu yang: this function is used to generate a random food from the stub database
    //and add it into the foodresult List
    public String getFoodRandom(List<Food> foodResult){
        Random random = new Random();
        foodResult.add(foods.get(random.nextInt(foods.size())));
        return null;
    }

    public String getFoodPreferred(List<Food> foodResult, String food){
        if(food != null) {
            if (food.equals("Meat")) {
                foodResult.add(foods.get(1));
                foodResult.add(foods.get(0));
            } else if (food.equals("Dessert")) {
                foodResult.add(foods.get(2));
                foodResult.add(foods.get(3));
            }
        }
        return null;

    }

    public String addFood(Food newFood) {
        if(newFood != null) {
            if(newFood.getFoodID() != "" && newFood.getFoodName() != "") {
                foods.add(newFood);
                Food_map.put(newFood.getFoodID(), newFood);
            }
        }
        return null;
    }
}
