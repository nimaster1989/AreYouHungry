package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;


import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.persistence.DataAccessStub;


public class AnswersTest extends TestCase{
    private DataAccess dataAccess;
    public static String dbName = MainActivity.dbName;
    private boolean stubdb = true;

    public AnswersTest(String arg0)
   {
        super(arg0);
    }

    public void setUp(){
        if(stubdb){
            System.out.println("\nStarting Persistence test DataAccess (using stub)");
            dataAccess = new DataAccessStub();
            dataAccess.open("Stub");
        }else{
            System.out.println("\nStarting Persistence test DataAccess (using HSQL)");
            dataAccess = new DataAccessObject(MainActivity.dbName);
            dataAccess.open(MainActivity.getDBPathName());
        }
    }

    public void testAnswerCreation(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        int testNum = 0;
        try{
            answer = new Answers(answers);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
    }

    public void testGetters(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answer = new Answers(answers);

        assertTrue("Sweet".equals(answer.getFlavor()));
        assertTrue("1".equals(answer.getPortionSize()));
        assertTrue("10".equals(answer.getPreptime()));
        assertTrue("Easy".equals(answer.getDifficulty()));
        assertTrue("Australian".equals(answer.getEthnicity()));
    }

    public void testSetters(){
        Answers answer;
        List<Integer> answers = new ArrayList<>();;
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answer = new Answers(answers);

        assertTrue(answer.getFlavor().equals("Sweet"));
        answer.setFlavor("Savory");
        assertTrue(answer.getFlavor().equals("Savory"));

        assertTrue(answer.getPortionSize().equals("1"));
        answer.setPortionSize("3");
        assertTrue(answer.getPortionSize().equals("3"));

        assertTrue(answer.getPreptime().equals("10"));
        answer.setPreptime("20");
        assertTrue(answer.getPreptime().equals("20"));

        assertTrue(answer.getDifficulty().equals("Easy"));
        answer.setDifficulty("Hard");
        assertTrue(answer.getDifficulty().equals("Hard"));

        assertTrue(answer.getEthnicity().equals("Australian"));
        answer.setEthnicity("American");
        assertTrue(answer.getEthnicity().equals("American"));
    }

    public void testUnknownAnswers(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(6);
        answers.add(5);
        answers.add(-93);
        answers.add(124);
        answers.add(11111);
        answer = new Answers(answers);

        assertTrue(answer.getEthnicity().equals("Unknown"));
        assertTrue(answer.getDifficulty().equals("Unknown"));
        assertTrue(answer.getPreptime().equals("Unknown"));
        assertTrue(answer.getPortionSize().equals("Unknown"));
        assertTrue(answer.getFlavor().equals("Unknown"));
    }

    public void testNotEnoughAnswers(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        int testNum = 0;
        try{
            answer = new Answers(answers);
        }catch(Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testTooManyAnswers(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        int testNum = 0;
        try{
            answer = new Answers(answers);
        }catch(Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testNoAnswers(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        int testNum = 0;
        try{
            answer = new Answers(answers);
        }catch(Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
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
        answer.setFlavor("Savory");
        answer.setPortionSize("1");
        answer.setPreptime("10");
        answer.setDifficulty("Easy");
        answer.setEthnicity("American");
        Food food1 = answer.getFoodBasedOnAnswers();
        Food food2 = new Food(1, "Fish and Chip",1,10, "Savory", "Easy", "American");
        assertTrue(food1.equals(food2));
        Services.closeDataAccess();
    }

    /* We understood that tests are suppsoe to avoid if/else and loops but this saves so much
       time for testing all possible options. */
    public void testAllOptions(){
        Answers answer;
        List<Integer> answers;
        System.out.println("\n Starting testAllOptions");
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
                        assertTrue(answer.getFlavor().equals("Savory"));
                    }else if(j == 2){
                        assertTrue(answer.getFlavor().equals("Spicy"));
                    }else if(j == 3){
                        assertTrue(answer.getFlavor().equals("Other"));
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
                        assertTrue(answer.getEthnicity().equals("Australian"));
                    }else if(j == 1){
                        assertTrue(answer.getEthnicity().equals("American"));
                    }else if(j == 2){
                        assertTrue(answer.getEthnicity().equals("Japanese"));
                    }else if(j == 3){
                        assertTrue(answer.getEthnicity().equals("Vietnamese"));
                    }
                }
            }
        }






    }

    public void testEquals(){

        List<Integer> answers = new ArrayList<>();;
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        Answers answer1 = new Answers(answers);
        Answers answer2 = new Answers(answers);
        answers = new ArrayList<>();;
        answers.add(1);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        Answers answer3 = new Answers(answers);
        assertTrue(answer1.equals(answer2));
        assertFalse(answer1.equals(answer3));
    }

    public void testToString(){
        List<Integer> answers = new ArrayList<>();;
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        Answers answer = new Answers(answers);
        String test = "Flavor: Sweet\n  PortionSize: 1\n  PrepTime: 10\n  Difficulty: Easy\n  Ethnicity: Australian\n";
        assertTrue(answer.toString().equals(test));
    }

}
