package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Iterator;


import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;


import static comp3350.Group2.areyouhungry.MainActivity.dbName;

public class DatabaseTest extends TestCase {

    public void testFavourites(){
        System.out.println("Running test to test favouriting foods in the database");
        Services.createDataAccess(dbName);
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();

        /* DELETE THIS COMMENT BLOCK LATER
           This test assumes that our database stub does not have anything favourited yet.
        */

        //This portion checks if we can get favourited foods, and list should be empty since we have nothing favourited yet.
        accessFood.getFavouriteFoods(foodList);
        assertTrue(foodList.isEmpty()); //True as we have no foods favourited yet.

        //This portion grabs a random food, favourites it, clears our foodList and fills foodList with all our favourited foods.
        accessFood.getRandom(foodList);
        foodList.get(0).setFavourite(true);
        foodList.clear();
        accessFood.getFavouriteFoods(foodList);
        assertFalse(foodList.isEmpty()); //This should not be empty now as we have a favourited food!!

        //This portion unfavourites the favourited food, clears foodList and "tries" to fill foodList with all our favourited foods.
        foodList.get(0).setFavourite(false);
        foodList.clear();
        accessFood.getFavouriteFoods(foodList);
        assertTrue(foodList.isEmpty());
    }

    public void testSequential(){
        System.out.println("Running test to test if we can get a list of food.");
        Services.createDataAccess(dbName);
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();
        String foodId;
        int num = 1;
        Iterator<Food> foodIterator;

        //Since we have a stub database of 6 food objects, check if we can retrieve all 6.
        accessFood.getFoods(foodList);
        assertEquals(6, foodList.size());
        foodIterator = foodList.iterator();

        //This part verifies that they're all different IDs/foods.
        while(foodIterator.hasNext()){
            foodId = "00" + num;
            assertEquals(foodId, foodIterator.next().getFoodID());
            num++;
        }
        
    }

}
