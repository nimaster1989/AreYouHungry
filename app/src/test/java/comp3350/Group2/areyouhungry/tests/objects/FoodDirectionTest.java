package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodDirection;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.persistence.DataAccessStub;

public class FoodDirectionTest extends TestCase {
    private DataAccess dataAccess;
    public static String dbName = MainActivity.dbName;
    private boolean stubdb = true;

    public FoodDirectionTest(String arg0)
    {
        super(arg0);
    }

    public void setUp(){
        if(stubdb){
            System.out.println("\nStarting Persistence test DataAccess (using stub)");
            dataAccess = new DataAccessStub();
            dataAccess.open("Stub");
        }else{
            System.out.println("\nStarting Persistence test DataAccess (using HSQL)");
            dataAccess = new DataAccessObject(MainActivity.dbName);
            dataAccess.open(MainActivity.getDBPathName());
        }
    }

    public void testFoodDirectionCreationWithOnlyFood(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
        try{
            foodDirection = new FoodDirection(food);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
        Services.closeDataAccess();
    }

    public void testBadFoodDirectionCreation(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Sour";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;

        try {
            food = new Food(foodID, foodName, portionSize, prepTime, flavour, difficulty, ethnicity);
        }catch(Exception e){
            food = null;
        }
        try{
            foodDirection = new FoodDirection(food);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
        Services.closeDataAccess();
    }

    public void testFoodDirectionCreationWithFoodAndDirections(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

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

        Services.closeDataAccess();
    }

    public void testBadDirectionsFoodDirectionCreation(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

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

        Services.closeDataAccess();
    }

    public void testGetFood(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        foodDirection = new FoodDirection(food, directions);
        Food newFood = foodDirection.getFood();
        assertTrue(food.equals(newFood));

        Services.closeDataAccess();
    }

    public void testGetDirection(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food food;
        FoodDirection foodDirection;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        foodDirection = new FoodDirection(food, directions);
        ArrayList<Direction> newDirections = foodDirection.getDirections();
        assertTrue(directions.equals(newDirections));
        Services.closeDataAccess();
    }

    public void testEquals(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
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

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        FoodDirection foodDirection1 = new FoodDirection(food, directions);
        FoodDirection foodDirection2 = new FoodDirection(food, directions);


        foodID = 11;
        food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
        FoodDirection foodDirection3 = new FoodDirection(food, directions);

        assertTrue(foodDirection1.equals(foodDirection2));
        assertFalse(foodDirection1.equals(foodDirection3));
        Services.closeDataAccess();
    }

    public void testToString(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
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

        ArrayList<Direction> directions = new ArrayList<>();
        int directionID = 1;
        String directionDescription = "Test Description";
        int stepNumber = 1;
        Direction testDirection = new Direction(directionID, directionDescription, stepNumber);
        directions.add(testDirection);

        FoodDirection foodDirection = new FoodDirection(food, directions);
        String test = "FoodID: 10\n  Length of list: 1\n";
        assertTrue(test.equals(foodDirection.toString()));

        Services.closeDataAccess();
    }
}
