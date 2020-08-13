package comp3350.Group2.areyouhungry.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.ListIterator;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.business.AccessUsers;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class BusinessPersistenceSeamTest extends TestCase{
    private DataAccess dataAccess;
    public static String dbName = MainActivity.dbName;

    public void setUp(){
        System.out.println("Starting Persistence");
        //dataAccess = new DataAccessObject(dbName);
        //dataAccess.open(MainActivity.getDBPathName());
        dataAccess = new DataAccessStub(dbName);
        dataAccess.open(MainActivity.getDBPathName());
    }
    public BusinessPersistenceSeamTest(String arg0){
        super(arg0);
    }

    public void testAccessFood(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Food testFood = new Food(7,"testFood",1,10,"Spicy","Hard","Canadian");
        AccessFoods accessFoods = new AccessFoods();
        accessFoods.addFood(testFood);
        Food returnedFood = accessFoods.getFoodByID("7");
        assertTrue(testFood.equals(returnedFood));

        testFood.setFavourite(true);
        returnedFood = accessFoods.getFoodByID("7");
        assertTrue(returnedFood.getFavourite());

        testFood.setFavourite(false);
        returnedFood = accessFoods.getFoodByID("7");
        assertFalse(returnedFood.getFavourite());

        dataAccess.deleteFood(7);
        Services.closeDataAccess();
    }

    public void testGetFoodIngredients(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);

        AccessIngredients accessIngredients = new AccessIngredients();
        AccessFoods accessFoods = new AccessFoods();

        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        Food testFood = new Food(7,"testFood",1,10,"Spicy","Hard","Canadian");
        accessFoods.addFood(testFood);

        Food dbFood = accessFoods.getFoodByID(testFood.getFoodID());

        assertNull(accessIngredients.getIngredient(dbFood,ingredientList));

        Ingredient ingredient = new Ingredient(42, "milk", "1/2 Cup");
        accessIngredients.addIngredient(ingredient);
        
        accessIngredients.setFoodIngredient(Integer.parseInt(dbFood.getFoodID()), ingredient.getIngredientID());

        dbFood = accessFoods.getFoodByID(testFood.getFoodID());
        ingredientList = new ArrayList<>();

        accessIngredients.getIngredient(dbFood,ingredientList);

        int matchedId = 0;
        ListIterator<Ingredient> ingredientListIterator = ingredientList.listIterator();
        while (ingredientListIterator.hasNext()){
            Ingredient tempIngredient = ingredientListIterator.next();
            if(tempIngredient.getIngredientID() == 42){
                matchedId = 42;
            }
        }

        assertEquals(42, matchedId);
        dataAccess.deleteFoodIngredient(7, 42);
        dataAccess.deleteIngredient(42);
        dataAccess.deleteFood(7);


        Services.closeDataAccess();
    }

    public void testGetFoodDirections(){

        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);

        AccessDirections accessDirections = new AccessDirections();
        AccessFoods accessFoods = new AccessFoods();

        ArrayList<Direction> directionList = new ArrayList<>();

        Food testFood = new Food(7,"testFood",1,10,"Spicy","Hard","Canadian");
        accessFoods.addFood(testFood);

        Food dbFood = accessFoods.getFoodByID(testFood.getFoodID());

        assertNull(accessDirections.getDirection(dbFood,directionList));

        Direction direction = new Direction(150, "Add milk to bowl", 1);
        accessDirections.addDirection(direction);

        accessDirections.addFoodDirection(Integer.parseInt(dbFood.getFoodID()), direction.getDirectionID());

        dbFood = accessFoods.getFoodByID(testFood.getFoodID());
        directionList = new ArrayList<>();

        accessDirections.getDirection(dbFood,directionList);

        int matchedId = 0;
        ListIterator<Direction> directionListIterator = directionList.listIterator();
        while (directionListIterator.hasNext()){
            Direction tempDirection = directionListIterator.next();
            if(tempDirection.getDirectionID() == 150){
                matchedId = 150;
            }
        }

        assertEquals(150, matchedId);
        dataAccess.deleteFoodDirection(7,150);
        dataAccess.deleteDirection(150);
        dataAccess.deleteFood(7);
        Services.closeDataAccess();

    }

    public void testGetUser(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        User newUser = new User(3,"testuser");
        AccessUsers accessUsers = new AccessUsers();
        User returnedUser = accessUsers.getUserByID(3);
        assertNull(returnedUser);

        accessUsers.newUsers(newUser.getUserName());
        returnedUser = accessUsers.getUserByID(3);
        assertTrue(newUser.equals(returnedUser));

        ArrayList<User> userList = new ArrayList<>();
        accessUsers.getUsers(userList);
        assertEquals(userList.size(),3);
        dataAccess.deleteUser(3);
        Services.closeDataAccess();
    }

}
