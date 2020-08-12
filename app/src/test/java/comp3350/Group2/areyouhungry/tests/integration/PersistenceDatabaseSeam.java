package comp3350.Group2.areyouhungry.tests.integration;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Food;
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

        Services.closeDataAccess();
    }
}
