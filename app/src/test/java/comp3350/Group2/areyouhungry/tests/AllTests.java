package comp3350.Group2.areyouhungry.tests;

import org.junit.Test;
import junit.framework.TestSuite;

import comp3350.Group2.areyouhungry.tests.business.AccessFoodTest;
import comp3350.Group2.areyouhungry.tests.business.PreferredSearchTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodTest;

//This class when run tests all the other testing suites
public class AllTests {

    public static TestSuite suite;

    //This method runs ALL the test suites
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testDatabase();
        testPreferredSearch();
        testFood();
        return suite;
    }

    //This will add all the tests from the Database testing suite
    @Test
    private static void testDatabase(){
        suite.addTestSuite(AccessFoodTest.class);
    }

    @Test
    private static void testPreferredSearch(){
        suite.addTestSuite(PreferredSearchTest.class);
    }

    @Test
    private static void testFood(){
        suite.addTestSuite(FoodTest.class);
    }
}
