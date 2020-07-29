package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Food;

public class AnswersTest extends TestCase {

    public AnswersTest(String arg0)
    {
        super(arg0);
    }

    public void testAnswerCreation(){
        Answers answer;
        List<Integer> answers;

        System.out.println("\n Starting testAnswerCreation");
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answer = new Answers(answers);
        assertNotNull(answer);
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

        assertNotNull(answer.getFlavor());
        assertNotNull(answer.getPortionSize());
        assertNotNull(answer.getPreptime());
        assertNotNull(answer.getDifficulty());
        assertNotNull(answer.getEthnicity());
    }

    public void testSetters(){
        Answers answer;
        List<Integer> answers = new ArrayList<>();
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

        System.out.println("\n Starting testNotEnoughAnswers");
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        try{
            answer = new Answers(answers);
        }catch(Exception e){
            assertNotNull(e);
        }
    }

    public void testTooManyAnswers(){
        Answers answer;
        List<Integer> answers;

        System.out.println("\n Starting testNotEnoughAnswers");
        answers = new ArrayList<>();
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        answers.add(0);
        try{
            answer = new Answers(answers);
        }catch(Exception e){
            assertNotNull(e);
        }
    }

    public void testNoAnswers(){
        Answers answer;
        List<Integer> answers;
        answers = new ArrayList<>();
        answer = new Answers(answers);
        assertTrue(answer.getFlavor().equals("Unknown"));
        assertTrue(answer.getPortionSize().equals("Unknown"));
        assertTrue(answer.getPreptime().equals("Unknown"));
        assertTrue(answer.getDifficulty().equals("Unknown"));
        assertTrue(answer.getEthnicity().equals("Unknown"));
    }

    public void testGetFoodBasedOnAnswers(){
    //TODO Set up this test function
    }

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

}
