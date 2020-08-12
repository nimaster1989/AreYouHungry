package comp3350.Group2.areyouhungry.tests;

import junit.framework.TestSuite;

import comp3350.Group2.areyouhungry.tests.integration.BusinessPersistenceSeamTest;
import comp3350.Group2.areyouhungry.tests.integration.BusinessUISeamTest;

public class RunIntegrationTests{
    public static TestSuite suite;

    /* This method runs ALL the test suites. */
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testBusinessUISeam();
        testBusinessPersistenceSeam();
        return suite;
    }

    public static void testBusinessUISeam(){
        suite.addTestSuite(BusinessUISeamTest.class);
    }

    public static void testBusinessPersistenceSeam(){
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
    }
}
