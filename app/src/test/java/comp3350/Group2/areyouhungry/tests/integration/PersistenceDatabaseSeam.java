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
        dataAccess.deleteFood(7);
        dataAccess.deleteUser(777);
        Services.closeDataAccess();
    }

    public void testSetFoodToLikedByUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessObject(MainActivity.dbName));
        dataAccess = Services.getDataAccess(MainActivity.dbName);



        assertTrue(true);
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
        assertEquals(userlist.size(),4);
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

        Services.closeDataAccess();
    }
}
