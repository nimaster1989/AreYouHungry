package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;



import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;




public class AnswersTest extends TestCase{

    public AnswersTest(String arg0){
        super(arg0);
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
        assertTrue("Greek".equals(answer.getEthnicity()));
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

        assertTrue(answer.getEthnicity().equals("Greek"));
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
        String test = "Flavor: Sweet\n  PortionSize: 1\n  PrepTime: 10\n  Difficulty: Easy\n  Ethnicity: Greek\n";
        assertTrue(answer.toString().equals(test));
    }

}
