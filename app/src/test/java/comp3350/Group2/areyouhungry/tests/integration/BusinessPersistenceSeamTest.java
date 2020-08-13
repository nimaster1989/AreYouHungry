package comp3350.Group2.areyouhungry.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.business.AccessUsers;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodIngredient;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

import static java.lang.Integer.parseInt;

public class BusinessPersistenceSeamTest extends TestCase{
    private DataAccess dataAccess;
    public static String dbName = MainActivity.dbName;

    public void setUp(){
        System.out.println("Starting Persistence");
        dataAccess = new DataAccessObject(dbName);
        dataAccess.open(MainActivity.getDBPathName());
    }
    public BusinessPersistenceSeamTest(String arg0){
        super(arg0);
    }

    public void testAccessFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
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

        Services.closeDataAccess();
    }

    public void testGetFoodIngredients(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);

        AccessIngredients accessIngredients = new AccessIngredients();
        AccessFoods accessFoods = new AccessFoods();

        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        //add food
        Food testFood = new Food(8,"testFood",1,10,"Spicy","Hard","Canadian");
        accessFoods.addFood(testFood);

        //get food
        Food dbFood = accessFoods.getFoodByID(testFood.getFoodID());

        //get Ingredients
        assertNull(accessIngredients.getIngredient(dbFood,ingredientList));

        //add Ingredients
        Ingredient ingredient = new Ingredient(150, "milk", "1/2 Cup");
        accessIngredients.addIngredient(ingredient);

        String result = accessIngredients.setFoodIngredient(Integer.parseInt(dbFood.getFoodID()), ingredient.getIngredientID());

        //get food/ingredient and check
        dbFood = accessFoods.getFoodByID(testFood.getFoodID());
        ingredientList = new ArrayList<>();

        accessIngredients.getIngredient(dbFood,ingredientList);

        int matchedId = 0;
        ListIterator<Ingredient> ingredientListIterator = ingredientList.listIterator();
        while (ingredientListIterator.hasNext()){
            Ingredient tempIngredient = ingredientListIterator.next();
            if(tempIngredient.getIngredientID() == 150){
                matchedId = 150;
            }
        }

        assertEquals(150, matchedId);

        Services.closeDataAccess();
    }

    public void testGetFoodDirections(){

        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);

        AccessDirections accessDirections = new AccessDirections();
        AccessFoods accessFoods = new AccessFoods();

        ArrayList<Direction> directionList = new ArrayList<>();

        //add food
        Food testFood = new Food(9,"testFood",1,10,"Spicy","Hard","Canadian");
        accessFoods.addFood(testFood);

        //get food
        Food dbFood = accessFoods.getFoodByID(testFood.getFoodID());

        //get Directions
        assertNull(accessDirections.getDirection(dbFood,directionList));

        //add Direction
        Direction direction = new Direction(150, "Add milk to bowl", 1);
        accessDirections.addDirection(direction);

        String result = accessDirections.addFoodDirection(Integer.parseInt(dbFood.getFoodID()), direction.getDirectionID());

        //get food/direction and check
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

        Services.closeDataAccess();

    }

    public void testGetUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        User newUser = new User(4,"testuser");
        AccessUsers accessUsers = new AccessUsers();
        User returnedUser = accessUsers.getUserByID(4);
        assertNull(returnedUser);

        accessUsers.newUsers(newUser.getUserName());
        returnedUser = accessUsers.getUserByID(4);
        assertTrue(newUser.equals(returnedUser));

        ArrayList<User> userList = new ArrayList<>();
        accessUsers.getUsers(userList);
        assertEquals(userList.size(),4);
        Services.closeDataAccess();
    }

}
