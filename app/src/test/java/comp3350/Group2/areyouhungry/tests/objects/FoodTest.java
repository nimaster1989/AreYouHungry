package comp3350.Group2.areyouhungry.tests.objects;


import junit.framework.TestCase;
import comp3350.Group2.areyouhungry.objects.Food;


public class FoodTest extends TestCase{


    public void testGetters(){
        System.out.println("Running test to test Food class getters");
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");

        assertEquals("6", food.getFoodID());
        assertEquals("Ceaser Salad", food.getFoodName());
        assertFalse(food.getFavourite());

    }

    public void testSetters(){
        System.out.println("Running test to test Food class setters.");
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");

        assertFalse(food.getFavourite());
        food.setFavourite(true);
        assertTrue(food.getFavourite());

    }

    //This tests food equality (if IDs are the same or if both are null)
    public void testEquals(){
        System.out.println("Running test to test Food class equality.");
        Food food1 = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        Food food2 = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        Food food3 = new Food(5, "Jimgatang",5,20, "Savory", "Medium", "American");

        assertTrue(food1.equals(food2));
        assertFalse(food1.equals(food3));

    }
}
