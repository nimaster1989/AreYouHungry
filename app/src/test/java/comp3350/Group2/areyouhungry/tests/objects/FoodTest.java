package comp3350.Group2.areyouhungry.tests.objects;


import junit.framework.TestCase;


import comp3350.Group2.areyouhungry.objects.Food;


public class FoodTest extends TestCase{

    public FoodTest(String arg0)
    {
        super(arg0);
    }

    public void testFoodCreation(){
        Food food;
        int foodID = 10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
    }

    public void testBadIDCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadNameCreation(){
        Food food;
        int foodID = 10;
        String foodName = "";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadSizeCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 2;
        int prepTime = 10;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadTimeCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 11;
        String flavour = "Spicy";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadFlavourCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Sour";
        String difficulty = "Easy";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadDifficultyCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Sweet";
        String difficulty = "Difficult";
        String ethnicity = "American";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadEthnicityCreation(){
        Food food;
        int foodID = -10;
        String foodName = "TestFood";
        int portionSize = 1;
        int prepTime = 10;
        String flavour = "Sour";
        String difficulty = "Easy";
        String ethnicity = "Canadian";
        int testNum = 0;
        try{
            food = new Food(foodID, foodName, portionSize, prepTime,flavour, difficulty,ethnicity);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testGetters(){
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        assertEquals("6", food.getFoodID());
        assertEquals("Ceaser Salad", food.getFoodName());
        assertFalse(food.getFavourite());
        assertEquals(7, food.getPortionSize());
        assertEquals(30, food.getPrepTime());
        assertEquals("Other", food.getFlavour());
        assertEquals("Hard", food.getDifficulty());
        assertEquals("Vietnamese", food.getEthnicity());


    }

    public void testSetters(){
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");

        food.setFoodName("New Food");
        assertTrue(food.getFoodName().equals("New Food"));

        food.setDifficulty("Medium");
        assertTrue(food.getDifficulty().equals("Medium"));

        food.setEthnicity("American");
        assertTrue(food.getEthnicity().equals("American"));

        food.setFlavour("Spicy");
        assertTrue(food.getFlavour().equals("Spicy"));

        food.setPortionSize(1);
        assertEquals(1,food.getPortionSize());

        food.setPrepTime(10);
        assertEquals(10,food.getPrepTime());

        food.setFavourite(true);
        assertTrue(food.getFavourite());

    }

    public void testIsFavourite(){
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        assertFalse(food.isFavourite());
        food.setFavourite(true);
        assertTrue(food.isFavourite());
    }

    public void testEquals(){
        System.out.println("Running test to test Food class equality.");
        Food food1 = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        Food food2 = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        Food food3 = new Food(5, "Jimgatang",5,20, "Savory", "Medium", "American");

        assertTrue(food1.equals(food2));
        assertFalse(food1.equals(food3));

    }

    public void testToString(){
        Food food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        String test = "ID: 6\n  FoodName: Ceaser Salad\n  Favourited: false\n  PortionSize: 7\n  PrepTime: 30\n  Flavour: Other\n  Difficulty: Hard\n  Ethnicity: Vietnamese\n";
        System.out.println(test);
        System.out.println(food.toString());
        assertTrue(test.equals(food.toString()));
    }
}
