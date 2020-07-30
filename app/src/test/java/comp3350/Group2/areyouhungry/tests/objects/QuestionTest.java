package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.Question;


public class QuestionTest extends TestCase{

    public QuestionTest(String arg0){
        super(arg0);
    }

    public void testQuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(1, testnum);
    }

    public void testBadQuestionQuestionCreation(){
        Question question;
        String theQuestion = null;
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testBadOption1QuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = null;
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testBadOption2QuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:04am";
        String option2 = null;
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testBadOption3QuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:04am";
        String option2 = "5:05am";
        String option3 = null;
        String option4 = "Hammer Time";
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testBadOption4QuestionCreation(){
        Question question;
        String theQuestion = "What time is it";
        String option1 = "4:04am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = null;
        int testnum = 0;
        try{
            question = new Question(theQuestion, option1, option2, option3, option4);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testGetters(){
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

    public void testSetters(){
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

    public void testEquals(){
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        Question question1 = new Question(theQuestion, option1, option2, option3, option4);
        Question question2 = new Question(theQuestion, option1, option2, option3, option4);
        theQuestion = "How do you do";
        Question question3 = new Question(theQuestion, option1, option2, option3, option4);

        assertTrue(question1.equals(question2));
        assertFalse(question1.equals(question3));
    }

    public void testToString(){
        String theQuestion = "What time is it";
        String option1 = "4:14am";
        String option2 = "5:05am";
        String option3 = "10:00am";
        String option4 = "Hammer Time";
        Question question = new Question(theQuestion, option1, option2, option3, option4);
        String test = "Question: What time is it\n  Option1: 4:14am\n  Option2: 5:05am\n  Option3: 10:00am\n  Option4: Hammer Time\n";
        assertTrue(test.equals(question.toString()));
    }
}
