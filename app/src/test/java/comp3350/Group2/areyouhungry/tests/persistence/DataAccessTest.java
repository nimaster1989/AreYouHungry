package comp3350.Group2.areyouhungry.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;


public class DataAccessTest extends TestCase{
    private DataAccess dataAccess;

    public DataAccessTest(String arg0){
        super(arg0);
    }

    public void setUp(){
        System.out.println("Starting Persistence");
        /* Use the following statements to run with the stub database. */
//         dataAccess = new DataAccessStub();
//         dataAccess.open("Stub");
        /* or switch to the real database: */
        dataAccess = new DataAccessObject(MainActivity.dbName);
        dataAccess.open(MainActivity.getDBPathName());
}

    public void tearDown(){
        System.out.println("Finished Persistence");
    }

    public void testDataAccess(){
        ArrayList<Food> foods;
        Food food;
        String result;
        foods = new ArrayList<Food>();
        result = dataAccess.getFoodSequential(foods);

        assertNull(result);
        assertEquals(6, foods.size());
    }

    public void testGetFoodByID(){
        String foodID = "1";
        Food food = dataAccess.getFoodFromID(foodID);
        Food compareFood = new Food(1, "Fish and Chip",1,10, "Savory", "Easy", "American");
        assertTrue(food.equals(compareFood));
    }

    public void testGetNegativeFoodByID(){
        String foodID = "-1";
        Food food = dataAccess.getFoodFromID(foodID);
        assertNull(food);
    }

    public void testGetNullFoodByID(){
        String foodID = null;
        Food food = dataAccess.getFoodFromID(foodID);
        assertNull(food);
    }

    public void testGetNonExistantFoodByID(){
        String foodID = "738649298";
        Food food = dataAccess.getFoodFromID(foodID);
        assertNull(food);
    }

    public void testEmptyStringGetFoodByID(){
        String foodID = "";
        Food food = dataAccess.getFoodFromID(foodID);
        assertNull(food);
    }

    public void testGetTotalQuestions(){
        int totalTests = dataAccess.getTotalQuestions();
        assertEquals(totalTests,5);
    }

    public void testSetAndDeleteNewUser(){
        int id = 4;
        String username = "Test User";
        User user1 = new User(id,username);
        User userSet = dataAccess.setNewUser(id,username);
        assertEquals(user1, userSet);

    }
    public void testSetAndDeleteNewFood(){
        Food newFood = new Food(150, "Fish and Chip",1,10, "Savory", "Easy", "American");
        dataAccess.addFood(newFood);
        assertEquals(newFood, dataAccess.getFoodFromID("150"));
        dataAccess.deleteFood(150);
        assertNull(dataAccess.getFoodFromID("150"));

    }
    public void testDuplicateSetNewUser(){
        int id = 4;
        String username = "Test User";
        dataAccess.setNewUser(id,username);
        User user2 =  dataAccess.setNewUser(id,username);
        assertNull(user2);
        dataAccess.deleteUser(id);
    }

    public void testNegativeIDSetNewUser(){
        int id = -1;
        String username = "Test User";
        User user1 = dataAccess.setNewUser(id,username);
        assertNull(user1);
    }

    public void testNullUsernameSetNewUser(){
        int id = 5;
        String username = null;
        User user1 = dataAccess.setNewUser(id,username);
        assertNull(user1);
    }

    public void testAddNewAndDeleteFoodCategory(){
        int id=1;
        int category=3;
        FoodCategory test = new FoodCategory(id,category);
        FoodCategory result = dataAccess.addFoodCategory(id,category);
        assertEquals(test, result);
        dataAccess.deleteFoodCategory(1,3);
    }

    public void testAddDuplicateFoodCategory(){
        int id = 1;
        int category = 1;
        FoodCategory result = dataAccess.addFoodCategory(id, category);
        assertNull(result);
    }

    public void testNegativeIDAddFoodCategory(){
        int id = -1;
        int category=4;
        FoodCategory result = dataAccess.addFoodCategory(id,category);
        assertNull(result);
    }

    public void testNegativeCategoryAddFoodCategory(){
        int id = 1;
        int category=-4;
        FoodCategory result = dataAccess.addFoodCategory(id,category);
        assertNull(result);
    }

    /* We understood that tests are suppsoe to avoid if/else and loops but this saves so much
   time for testing all possible options. */
    public void testAllAnswerOptions(){
        Answers answer;
        List<Integer> answers;
        for(int i =0; i<5; i++){
            for(int j =0; j<4; j++){
                if(i == 0){
                    answers = new ArrayList<>();
                    answers.add(j);
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answer = new Answers(answers);
                    if(j == 0){
                        assertTrue(answer.getFlavor().equals("Sweet"));
                    }else if(j == 1){
                        assertTrue(answer.getFlavor().equals("Savoury"));
                    }else if(j == 2){
                        assertTrue(answer.getFlavor().equals("Spicy"));
                    }else if(j == 3){
                        assertTrue(answer.getFlavor().equals("Fresh"));
                    }
                }else if(i == 1){
                    answers = new ArrayList<>();
                    answers.add(0);
                    answers.add(j);
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answer = new Answers(answers);
                    if(j == 0){
                        assertTrue(answer.getPortionSize().equals("1"));
                    }else if(j == 1){
                        assertTrue(answer.getPortionSize().equals("3"));
                    }else if(j == 2){
                        assertTrue(answer.getPortionSize().equals("5"));
                    }else if(j == 3){
                        assertTrue(answer.getPortionSize().equals("7"));
                    }
                }else if(i == 2){
                    answers = new ArrayList<>();
                    answers.add(0);
                    answers.add(0);
                    answers.add(j);
                    answers.add(0);
                    answers.add(0);
                    answer = new Answers(answers);
                    if(j == 0){
                        assertTrue(answer.getPreptime().equals("10"));
                    }else if(j == 1){
                        assertTrue(answer.getPreptime().equals("20"));
                    }else if(j == 2){
                        assertTrue(answer.getPreptime().equals("30"));
                    }else if(j == 3){
                        assertTrue(answer.getPreptime().equals("40"));
                    }
                }else if(i == 3){
                    answers = new ArrayList<>();
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answers.add(j);
                    answers.add(0);
                    answer = new Answers(answers);
                    if(j == 0){
                        assertTrue(answer.getDifficulty().equals("Easy"));
                    }else if(j == 1){
                        assertTrue(answer.getDifficulty().equals("Medium"));
                    }else if(j == 2){
                        assertTrue(answer.getDifficulty().equals("Hard"));
                    }else if(j == 3){
                        assertTrue(answer.getDifficulty().equals("Expert"));
                    }
                }else if(i == 4){
                    answers = new ArrayList<>();
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answers.add(0);
                    answers.add(j);
                    answer = new Answers(answers);
                    if(j == 0){
                        assertTrue(answer.getEthnicity().equals("Greek"));
                    }else if(j == 1){
                        assertTrue(answer.getEthnicity().equals("American"));
                    }else if(j == 2){
                        assertTrue(answer.getEthnicity().equals("Italian"));
                    }else if(j == 3){
                        assertTrue(answer.getEthnicity().equals("Chinese"));
                    }
                }
            }
        }
    }

    public void testGetFoodBasedOnAnswers(){
        Services.closeDataAccess();
        Services.createDataAccess(dataAccess);
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answer = new Answers(answers);
        answer.setFlavor("Savoury");
        answer.setPortionSize("3");
        answer.setPreptime("20");
        answer.setDifficulty("Easy");
        answer.setEthnicity("American");
        Food food1 = answer.getFoodBasedOnAnswers();
        Food food2 = new Food(1, "Baked Salmon",3,20, "Savoury", "Easy", "American");
        assertTrue(food1.equals(food2));
        Services.closeDataAccess();
    }

}
