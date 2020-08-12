package comp3350.Group2.areyouhungry.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.business.AccessUsers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodIngredient;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

import static java.lang.Integer.parseInt;

public class BusinessPersistenceSeamTest extends TestCase{
    public static String dbName = MainActivity.dbName;

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

//    public void testGetFoodIngredients(){
//        Services.closeDataAccess();
//        Services.createDataAccess(new DataAccessStub(dbName));
//        Food testFood = new Food(7,"testFood",1,10,"Spicy","Hard","Canadian");
//        AccessFoods accessFoods = new AccessFoods();
//        accessFoods.addFood(testFood);
//        ArrayList<Ingredient> ingredients = new ArrayList<>();
//        AccessIngredients accessIngredients = new AccessIngredients();
//        accessIngredients.getIngredient(testFood,ingredients);
//        assertEquals(ingredients.size(),0);
//
//        Ingredient newIngredient = new Ingredient(42,"test","GALLONS");
//        ingredients.add(newIngredient);
//        FoodIngredient fi = new FoodIngredient(testFood,ingredients);
//
//        //accessIngredients.addIngredient(newIngredient);
//        //accessIngredients.setFoodIngredient(parseInt(testFood.getFoodID()),42);
//
//        ArrayList<Ingredient> foundIngredients = new ArrayList<>();
//        accessIngredients.getIngredient(testFood,foundIngredients);
//        assertEquals(foundIngredients.size(), 1);
//
//
//        Services.closeDataAccess();
//    }

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
