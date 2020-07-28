package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;


import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;


import static comp3350.Group2.areyouhungry.MainActivity.dbName;

public class AccessFoodTest extends TestCase {

    public static String dbName = MainActivity.dbName;

    public AccessFoodTest(String arg0){
        super(arg0);
    }

    public void testGetEmptyFavourites(){
        //System.out.println("Running test to test favouriting foods in the database");
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //This portion checks if we can get favourited foods, and list should be empty since we have nothing favourited yet.
        accessFood.getFavouriteFoods(foodList);
        assertTrue(foodList.isEmpty()); //True as we have no foods favourited yet.

        Services.closeDataAccess();
    }

    public void testAddingFavourite(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //This portion grabs a random food, favourites it, clears our foodList and fills foodList with all our favourited foods.
        accessFood.getRandom(foodList);
        foodList.get(0).setFavourite(true);
        foodList.clear();
        accessFood.getFavouriteFoods(foodList);
        assertFalse(foodList.isEmpty()); //This should not be empty now as we have a favourited food!!
        //Removing the favourite food for future tests
        foodList.get(0).setFavourite(false);
        foodList.clear();
        Services.closeDataAccess();
    }

    public void testUnfavouriteFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //Accesses a random food and favourites it
        accessFood.getRandom(foodList);
        foodList.get(0).setFavourite(true);
        foodList.clear();
        //Gets favourite food list and removed item added
        accessFood.getFavouriteFoods(foodList);
        foodList.get(0).setFavourite(false);
        foodList.clear();
        accessFood.getFavouriteFoods(foodList);
        assertTrue(foodList.isEmpty());

        Services.closeDataAccess();
    }

    public void testFavouriteTwice(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        //This portion grabs a random food, favourites it, clears our foodList and fills foodList with all our favourited foods.
        accessFood.getRandom(foodList);
        //Setting its favourite to true twice in a row
        foodList.get(0).setFavourite(true);
        foodList.get(0).setFavourite(true);
        foodList.clear();
        accessFood.getFavouriteFoods(foodList);
        assertFalse(foodList.isEmpty()); //This should not be empty now as we have a favourited food!!
        //Removing the favourite food for future tests
        foodList.get(0).setFavourite(false);
        foodList.clear();
        Services.closeDataAccess();
    }

    //Tests trying to add a null item to the food list, this checks to make sure the null item isnt added
    public void testAddNullFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        Food newItem = null;
        accessFood.addFood(newItem);
        accessFood.getFoods(foodList);
        assertFalse(foodList.contains(null));
        Services.closeDataAccess();
    }

    public void testAddRealFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        Food newItem = new Food(8, "Pizza");
        accessFood.addFood(newItem);
        accessFood.getFoods(foodList);
        assertTrue(foodList.contains(newItem));
        Services.closeDataAccess();
    }

    public void testAddEmptyFood(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        Food newItem = new Food(-1, "");
        accessFood.addFood(newItem);
        accessFood.getFoods(foodList);
        assertFalse(foodList.contains(newItem));
        Services.closeDataAccess();
    }

    public void testFoodMissingName(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        Food newItem = new Food(9, "");
        accessFood.addFood(newItem);
        accessFood.getFoods(foodList);
        assertFalse(foodList.contains(newItem));
        Services.closeDataAccess();
    }


    //Tests the stub database, ensuring our hardcoded data is working and we can access all entries
    public void testSequential(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        int foodId;
        int num = 1;
        Iterator<Food> foodIterator;

        //Since we have a stub database of 6 food objects, check if we can retrieve all 6.
        accessFood.getFoods(foodList);
        assertEquals(6, foodList.size());
        foodIterator = foodList.iterator();

        //This part verifies that they're all different IDs/foods.
        while(foodIterator.hasNext()){
            foodId = num;
            assertEquals(String.valueOf(foodId), foodIterator.next().getFoodID());
            num++;
        }
        Services.closeDataAccess();
    }

}
