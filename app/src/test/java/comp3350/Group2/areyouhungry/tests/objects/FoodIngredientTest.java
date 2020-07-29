package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodIngredient;
import comp3350.Group2.areyouhungry.objects.Ingredient;


public class FoodIngredientTest extends TestCase{

    public FoodIngredientTest(String arg0){
        super(arg0);
    }


    public void testFoodIngredientCreationWithFoodAndIngredients(){

        Food food;
        FoodIngredient foodIngredient;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = 1;
        String ingredientName = "Flour";
        String measurement = "2cups";
        Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
        ingredients.add(testIngredient);

        try{
            foodIngredient = new FoodIngredient(food, ingredients);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }

        assertEquals(1,testNum);

        Services.closeDataAccess();
    }

    public void testBadIngredientsFoodIngredientCreation(){

        Food food;
        FoodIngredient foodIngredient;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = -1;
        String ingredientName = "Flour";
        String measurement = "2cups";

        try{
            Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
            ingredients.add(testIngredient);
        }catch (Exception e){
            testNum = 0;
        }

        try{
            foodIngredient = new FoodIngredient(food, ingredients);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }

        assertEquals(0,testNum);


    }

    public void testGetFood(){

        Food food;
        FoodIngredient foodIngredient;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = 1;
        String ingredientName = "Flour";
        String measurement = "2cups";
        Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
        ingredients.add(testIngredient);

        foodIngredient = new FoodIngredient(food, ingredients);
        Food newFood = foodIngredient.getFood();
        assertTrue(food.equals(newFood));


    }

    public void testGetIngredients(){

        Food food;
        FoodIngredient foodIngredient;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = 1;
        String ingredientName = "Flour";
        String measurement = "2cups";
        Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
        ingredients.add(testIngredient);

        foodIngredient = new FoodIngredient(food, ingredients);
        List<Ingredient> newIngredients = foodIngredient.getIngredients();
        assertTrue(ingredients.equals(newIngredients));


    }

    public void testEquals(){
        Food food;

        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = 1;
        String ingredientName = "Flour";
        String measurement = "2cups";
        Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
        ingredients.add(testIngredient);

        FoodIngredient foodIngredient1 = new FoodIngredient(food, ingredients);
        FoodIngredient foodIngredient2 = new FoodIngredient(food, ingredients);

        foodID = 11;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
        FoodIngredient foodIngredient3 = new FoodIngredient(food, ingredients);

        assertTrue(foodIngredient1.equals(foodIngredient2));
        assertFalse(foodIngredient1.equals(foodIngredient3));

    }

    public void testToString(){
        Food food;

        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        int ingredientID = 1;
        String ingredientName = "Flour";
        String measurement = "2cups";
        Ingredient testIngredient = new Ingredient(ingredientID, ingredientName, measurement);
        ingredients.add(testIngredient);

        FoodIngredient foodIngredient = new FoodIngredient(food, ingredients);
        String test = "FoodID: 10\n  Length of list: 1\n";
        assertTrue(test.equals(foodIngredient.toString()));

    }
}

