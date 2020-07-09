package comp3350.Group2.areyouhungry.tests.business;


import junit.framework.TestCase;
import comp3350.Group2.areyouhungry.objects.Food;


public class FoodTest extends TestCase{


    public void testGetters(){
        System.out.println("Running test to test Food class getters");
        Food food = new Food("123", "foodName", "linkPage");

        assertEquals("123", food.getFoodID());
        assertEquals("foodName", food.getFoodName());
        assertEquals("linkPage", food.getRecipeLink());
        assertFalse(food.getFavourite());

    }

    public void testSetters(){
        System.out.println("Running test to test Food class setters.");
        Food food = new Food("123", "Chicken");

        food.setRecipeLink("bing");
        assertEquals("bing", food.getRecipeLink());

        assertFalse(food.getFavourite());
        food.setFavourite(true);
        assertTrue(food.getFavourite());

    }

    //This tests food equality (if IDs are the same or if both are null)
    public void testEquals(){
        System.out.println("Running test to test Food class equality.");
        Food food1 = new Food("123");
        Food food2 = new Food("123");
        Food food3 = new Food("1234");
        Food food4 = new Food(null);
        Food food5 = new Food(null);

        assertTrue(food1.equals(food2));
        assertFalse(food1.equals(food3));
        assertTrue(food4.equals(food5));

    }


}
