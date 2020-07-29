package comp3350.Group2.areyouhungry.tests;

import org.junit.Test;
import junit.framework.TestSuite;

import comp3350.Group2.areyouhungry.tests.business.AccessFoodTest;
import comp3350.Group2.areyouhungry.tests.business.AccessQuestionTest;
import comp3350.Group2.areyouhungry.tests.objects.AnswersTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodTest;
import comp3350.Group2.areyouhungry.tests.objects.QuestionTest;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessTest;

//This class when run tests all the other testing suites
public class AllTests{

    public static TestSuite suite;

    //This method runs ALL the test suites
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testBusiness();
        testObject();
        testPersistence();
        return suite;
    }

    //This will add all the tests from the Database testing suite

    @Test
    private static void testBusiness(){
        suite.addTestSuite(AccessFoodTest.class);
        suite.addTestSuite(AccessQuestionTest.class);
    }

    @Test
    private static void testObject(){
        suite.addTestSuite(FoodTest.class);
        suite.addTestSuite(AnswersTest.class);
        suite.addTestSuite(QuestionTest.class);
        // TODO: 27/07/20
        //write test for user
    }

    private static void testPersistence(){
        suite.addTestSuite(DataAccessTest.class);
    }
}
