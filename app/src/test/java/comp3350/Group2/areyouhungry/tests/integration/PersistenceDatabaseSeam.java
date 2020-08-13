package comp3350.Group2.areyouhungry.tests.integration;

import junit.framework.TestCase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Categories;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class PersistenceDatabaseSeam extends TestCase{
    private DataAccess dataAccess;

    public void testSetFoodToFavouriteByUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));

        dataAccess = Services.getDataAccess(MainActivity.dbName);
        Food newFood = new Food(7,"testFood",1,1,"Spicy","Easy","American");
        dataAccess.addFood(newFood);
        Food foodReturned = dataAccess.getFoodFromID("7");
        assertTrue(newFood.equals(foodReturned));

        User newUser = new User(777,"i like one food");
        dataAccess.setNewUser(newUser.getUserID(),newUser.getUserName());
        dataAccess.setFoodToFavouriteByUser(newUser,String.valueOf(newFood.getFoodID()),true);
        assertTrue(dataAccess.getFoodFavByUser(newUser,newFood));

        dataAccess.setFoodToFavouriteByUser(newUser,String.valueOf(newFood.getFoodID()),false);
        assertFalse(dataAccess.getFoodFavByUser(newUser,newFood));

        dataAccess.deleteUser(777);
        User deletedUser = dataAccess.getUser(777);
        assertNull(deletedUser);

        Services.closeDataAccess();
    }

    public void testLikedFoodByUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));
        dataAccess = Services.getDataAccess(MainActivity.dbName);

        User testUser = dataAccess.getUser(1);
        Food gotFood = dataAccess.getFoodFromID("1");
        dataAccess.setFoodToLikedByUser(testUser,String.valueOf(gotFood.getFoodID()),true);
        assertTrue(dataAccess.getFoodLikedByUser(testUser,gotFood));

        dataAccess.setFoodToLikedByUser(testUser,String.valueOf(gotFood.getFoodID()),false);
        assertFalse(dataAccess.getFoodLikedByUser(testUser,gotFood));

        Services.closeDataAccess();
    }

    public void testDislikedFoodByUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));
        dataAccess = Services.getDataAccess(MainActivity.dbName);

        User testUser = dataAccess.getUser(1);
        Food gotFood = dataAccess.getFoodFromID("1");
        dataAccess.setFoodToDislikedByUser(testUser,String.valueOf(gotFood.getFoodID()),true);
        assertTrue(dataAccess.getFoodDislikedByUser(testUser,gotFood));

        dataAccess.setFoodToDislikedByUser(testUser,String.valueOf(gotFood.getFoodID()),false);
        assertFalse(dataAccess.getFoodDislikedByUser(testUser,gotFood));

        Services.closeDataAccess();
    }

    public void testUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));
        dataAccess = Services.getDataAccess(MainActivity.dbName);

        User defaultUser = dataAccess.getDefault();
        assertTrue(defaultUser.getUserName().equals("Default User"));

        User newUser = new User(777,"testUser");
        dataAccess.setNewUser(newUser.getUserID(),newUser.getUserName());
        User compareAdded = dataAccess.getUser(777);
        assertTrue(compareAdded.equals(newUser));

        ArrayList<User> userlist = new ArrayList<>();
        dataAccess.getUserSequential(userlist);
        assertEquals(userlist.size(),3);

        dataAccess.deleteUser(777);
        User deletedUser = dataAccess.getUser(777);
        assertNull(deletedUser);
        Services.closeDataAccess();

    }

    public void testFoodCategory(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));
        dataAccess = Services.getDataAccess(MainActivity.dbName);

        Food newFood = new Food(7,"testFood",1,1,"Spicy","Easy","American");
        dataAccess.addFood(newFood);
        Food foodReturned = dataAccess.getFoodFromID("7");
        assertTrue(newFood.equals(foodReturned));

        int dairyID = dataAccess.getCategoryIDbyName("Dairy");

        FoodCategory newFC = new FoodCategory(Integer.valueOf(newFood.getFoodID()),dairyID);
        dataAccess.addFoodCategory(Integer.valueOf(newFood.getFoodID()),dairyID);
        ArrayList<Food> foodlist = new ArrayList<>();
        dataAccess.getFoodSequentialByCategory("Dairy",foodlist);
        assertEquals(foodlist.size(),3);

        dataAccess.deleteFoodCategory(Integer.valueOf(newFood.getFoodID()),dairyID);
        dataAccess.getFoodSequentialByCategory("Dairy",foodlist);
        assertEquals(foodlist.size(),2);

        Services.closeDataAccess();
    }
}
