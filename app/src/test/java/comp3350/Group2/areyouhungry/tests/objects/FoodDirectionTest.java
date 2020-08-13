package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodDirection;



public class FoodDirectionTest extends TestCase{


    public FoodDirectionTest(String arg0){
        super(arg0);
    }


    public void testFoodDirectionCreationWithFoodAndDirections(){
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        try{
            foodDirection = new FoodDirection(food, directions);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }

        assertEquals(1,testNum);
    }

    public void testBadDirectionsFoodDirectionCreation(){
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = -1;

        try{
            Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
            directions.add(testDirection);
        }catch (Exception e){
            testNum = 0;
        }

        try{
            foodDirection = new FoodDirection(food, directions);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }

        assertEquals(0,testNum);

    }

    public void testGetFood(){
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        foodDirection = new FoodDirection(food, directions);
        Food newFood = foodDirection.getFood();
        assertTrue(food.equals(newFood));

    }

    public void testGetDirection(){
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        foodDirection = new FoodDirection(food, directions);
        ArrayList<Direction> newDirections = foodDirection.getDirections();
        assertTrue(directions.equals(newDirections));
    }

    public void testEquals(){
        Food food;

        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        FoodDirection foodDirection1 = new FoodDirection(food, directions);
        FoodDirection foodDirection2 = new FoodDirection(food, directions);


        foodID = 11;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);
        FoodDirection foodDirection3 = new FoodDirection(food, directions);

        assertTrue(foodDirection1.equals(foodDirection2));
        assertFalse(foodDirection1.equals(foodDirection3));
    }

    public void testToString(){
        Food food;

        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int totalTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, totalTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        FoodDirection foodDirection = new FoodDirection(food, directions);
        String test = "FoodID: 10\n  Length of list: 1\n";
        assertTrue(test.equals(foodDirection.toString()));

    }
}
