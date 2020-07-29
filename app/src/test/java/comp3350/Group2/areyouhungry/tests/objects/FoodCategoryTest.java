package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.FoodCategory;

public class FoodCategoryTest extends TestCase{
    public FoodCategoryTest(String arg0)
   {
        super(arg0);
    }

    public void testFoodCategoryCreation(){
        FoodCategory foodCategory;
        int foodID = 1;
        int categoryID = 5;
        int testNum = 0;
        try{
            foodCategory = new FoodCategory(foodID, categoryID);
            testNum = 1;
        }catch(Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);

    }
    public void testBadFoodID(){
        FoodCategory foodCategory;
        int foodID = -1;
        int categoryID = 1;
        int testNum = 0;
        try{
            foodCategory = new FoodCategory(foodID, categoryID);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testBadCategoryID(){
        FoodCategory foodCategory;
        int foodID = 1;
        int categoryID = -1;
        int testNum = 0;
        try{
            foodCategory = new FoodCategory(foodID, categoryID);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testGetters(){
        FoodCategory foodCategory;
        int foodID = 1;
        int categoryID = 1;
        foodCategory = new FoodCategory(foodID, categoryID);
        assertEquals(foodID,foodCategory.getFoodID());
        assertEquals(categoryID,foodCategory.getCategoryID());
    }
    public void testSetters(){
        FoodCategory foodCategory;
        int foodID = 1;
        int categoryID = 1;
        foodCategory = new FoodCategory(5, 5);
        foodCategory.setFoodID(foodID);
        foodCategory.setCategoryID(categoryID);
        assertEquals(foodID,foodCategory.getFoodID());
        assertEquals(categoryID,foodCategory.getCategoryID());
    }
    public void testEquals(){
        int foodID = 1;
        int categoryID = 1;
        FoodCategory foodCategory1 = new FoodCategory(foodID, categoryID);
        FoodCategory foodCategory2 = new FoodCategory(foodID, categoryID);

        foodID = 2;
        FoodCategory foodCategory3 = new FoodCategory(foodID, categoryID);

        assertTrue(foodCategory1.equals(foodCategory2));
        assertFalse(foodCategory1.equals(foodCategory3));
    }

    public void testToString(){
        int foodID = 1;
        int categoryID = 1;
        FoodCategory foodCategory = new FoodCategory(foodID, categoryID);
        String test ="FoodID: 1\n  CategoryID: 1\n";

        assertTrue(test.equals(foodCategory.toString()));
    }
}
