package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.objects.Question;


public class QuestionTest extends TestCase{
    public void testQuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        question = new Question(theQuestion, option1, option2, option3, option4);
        assertNotNull(question);
    }

    public void testQuestionGetters(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        question = new Question(theQuestion, option1, option2, option3, option4);
        assertTrue(question.getQuestion().equals("What time is it"));
        assertTrue(question.getOption1().equals("4:14am"));
        assertTrue(question.getOption2().equals("5:05am"));
        assertTrue(question.getOption3().equals("10:00am"));
        assertTrue(question.getOption4().equals("Hammer Time"));
    }

    public void testQuestionSetters(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        question = new Question(theQuestion, option1, option2, option3, option4);

        assertTrue(question.getQuestion().equals("What time is it"));
        question.setQuestion("new time");
        assertTrue(question.getQuestion().equals("new time"));

        assertTrue(question.getOption1().equals("4:14am"));
        question.setOption1("1pm");
        assertTrue(question.getOption1().equals("1pm"));

        assertTrue(question.getOption2().equals("5:05am"));
        question.setOption2("3pm");
        assertTrue(question.getOption2().equals("3pm"));

        assertTrue(question.getOption3().equals("10:00am"));
        question.setOption3("11pm");
        assertTrue(question.getOption3().equals("11pm"));

        assertTrue(question.getOption4().equals("Hammer Time"));
        question.setOption4("Fun time");
        assertTrue(question.getOption4().equals("Fun time"));
    }

    public void testCreatingNull(){
        Question question;
        question = new Question(null, null, null, null, null);

        assertNull(question.getQuestion());

        assertNull(question.getOption1());

        assertNull(question.getOption2());

        assertNull(question.getOption3());

        assertNull(question.getOption4());

    }

    public void testSettingNull(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        question = new Question(theQuestion, option1, option2, option3, option4);

        question.setQuestion(null);
        assertNull(question.getQuestion());

        question.setOption1(null);
        assertNull(question.getOption1());

        question.setOption2(null);
        assertNull(question.getOption2());

        question.setOption3(null);
        assertNull(question.getOption3());

        question.setOption4(null);
        assertNull(question.getOption4());

    }
}
