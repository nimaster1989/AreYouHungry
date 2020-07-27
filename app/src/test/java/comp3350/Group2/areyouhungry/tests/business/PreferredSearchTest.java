package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;


import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;


import static comp3350.Group2.areyouhungry.MainActivity.dbName;

public class PreferredSearchTest extends TestCase {
    public void testNullFoodSelection(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        System.out.println("Running test to test Preferred Search of foods in the database.");

        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //test for NULL
        try{
            accessFood.getPreferred(foodList, null);
        }
        catch(Exception e){
            assertNotNull(e);
        }
        Services.closeDataAccess();
    }

    public void testEmpty(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //test for empty search
        accessFood.getPreferred(foodList, "");
        assertTrue(foodList.isEmpty());//should return an empty list
        Services.closeDataAccess();
    }

    //test for a food you cant search by
    public void testNonExistingFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        accessFood.getPreferred(foodList, "Broccoli");
        assertTrue(foodList.isEmpty());//should return an empty list
        Services.closeDataAccess();
    }

    //test for correct food inside wrong food
    public void testValidFoodInsideNonExist(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        accessFood.getPreferred(foodList, "BrocMeatcoli");
        assertTrue(foodList.isEmpty());//should return an empty list
        Services.closeDataAccess();
    }

    //test preferred search for meat
    public void testCorrectMeatSearch(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        accessFood.getPreferred(foodList, "Meat");
        assertFalse(foodList.isEmpty());//should return a list of meat
        assertEquals(2, foodList.size()); //check if the size of the list is 1 cause theres only one "meat" obj right now
        assertEquals("2", foodList.get(0).getFoodID()); //check if the food is meat
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
    }

    public void testNumberSearch(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //test for numbers
        accessFood.getPreferred(foodList, "1");
        assertTrue(foodList.isEmpty());//should return an empty list
        Services.closeDataAccess();
    }

    public void testNullSearch(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //test for empty search
        accessFood.getPreferred(foodList, null);
        assertTrue(foodList.isEmpty());//should return an empty list
        Services.closeDataAccess();
    }
}

