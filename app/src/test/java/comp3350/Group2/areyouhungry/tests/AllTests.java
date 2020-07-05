package comp3350.Group2.areyouhungry.tests;

import org.junit.Test;
import junit.framework.TestSuite;

import comp3350.Group2.areyouhungry.tests.business.DatabaseTest;

//This class when run tests all the other testing suites
public class AllTests {

    public static TestSuite suite;

    //This method runs ALL the test suites
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testDatabase();
        return suite;
    }

    //This will add all the tests from the Database testing suite
    @Test
    private static void testDatabase(){
        suite.addTestSuite(DatabaseTest.class);

    }

}
