package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;


import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Food;


import static comp3350.Group2.areyouhungry.MainActivity.dbName;

public class PreferredSearchTest extends TestCase {
    public void testFoodSelection(){
        System.out.println("Running test to test Preferred Search of foods in the database");
        Services.createDataAccess(dbName);
        AccessFoods accessFood = new AccessFoods();
        ArrayList<Food> foodList = new ArrayList<>();

        /* DELETE THIS COMMENT BLOCK LATER
           This test assumes that our database stub does not have anything favourited yet.
        */

        //test for empty search
        accessFood.getPreferred(foodList, "");
        assertTrue(foodList.isEmpty());//should return an empty list

        //test for a food you cant search by
        accessFood.getPreferred(foodList, "Broccoli");
        assertTrue(foodList.isEmpty());//should return an empty list

        //test preferred search for meat
        accessFood.getPreferred(foodList, "Meat");
        assertFalse(foodList.isEmpty());//should return a list of meat
        assertEquals(1, foodList.size()); //check if the size of the list is 1 cause theres only one "meat" obj right now
        assertEquals("002", foodList.get(0).getFoodID()); //check if the food is meat

        //test for dessert
        accessFood.getPreferred(foodList, "Dessert");
        assertFalse(foodList.isEmpty());//should return a list of meat
        assertEquals(1, foodList.size()); //check if the size of the list is 1 cause theres only one "meat" obj right now
        assertEquals("004", foodList.get(0).getFoodID()); //check if the food is meat


    }
}
