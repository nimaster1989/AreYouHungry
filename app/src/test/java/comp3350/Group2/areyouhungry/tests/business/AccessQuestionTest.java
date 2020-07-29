package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessQuestions;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.persistence.DataAccessStub;

public class AccessQuestionTest extends TestCase{
    private DataAccess dataAccess;
    public static String dbName = MainActivity.dbName;
    private boolean stubdb = true;
    public AccessQuestionTest(String arg0){
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
    public void testGetQuestions(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessQuestions accessQuestion = new AccessQuestions();
        List<Question> questionList =  accessQuestion.getQuestions();
        assertFalse(questionList.isEmpty());
        Services.closeDataAccess();


    }

    public void testGetTotalQuestions(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessQuestions accessQuestion = new AccessQuestions();
        List<Question> questionList =  accessQuestion.getQuestions();
        int questionTotal =  accessQuestion.getTotalQuestions();
        assertTrue(questionTotal == questionList.size());
        Services.closeDataAccess();
    }

}
