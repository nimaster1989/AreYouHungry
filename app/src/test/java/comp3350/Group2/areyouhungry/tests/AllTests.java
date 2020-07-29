package comp3350.Group2.areyouhungry.tests;

import org.junit.Test;
import junit.framework.TestSuite;


import comp3350.Group2.areyouhungry.tests.business.AccessDirectionsTest;
import comp3350.Group2.areyouhungry.tests.business.AccessFoodsTest;
import comp3350.Group2.areyouhungry.tests.business.AccessIngredientsTest;
import comp3350.Group2.areyouhungry.tests.business.AccessQuestionTest;
import comp3350.Group2.areyouhungry.tests.business.AccessUsersTest;
import comp3350.Group2.areyouhungry.tests.objects.AnswersTest;
import comp3350.Group2.areyouhungry.tests.objects.CategorysTest;
import comp3350.Group2.areyouhungry.tests.objects.DirectionTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodCategoryTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodDirectionTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodIngredientTest;
import comp3350.Group2.areyouhungry.tests.objects.FoodTest;
import comp3350.Group2.areyouhungry.tests.objects.IngredientTest;
import comp3350.Group2.areyouhungry.tests.objects.QuestionTest;
import comp3350.Group2.areyouhungry.tests.objects.UserTest;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessTest;

/* This class when run tests all the other testing suites. */
public class AllTests{

    public static TestSuite suite;

    /* This method runs ALL the test suites. */
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testBusiness();
        testObject();
        testPersistence();
        return suite;
    }

    /* This will add all the tests from the Database testing suite. */

    @Test
    private static void testBusiness(){
        suite.addTestSuite(AccessFoodsTest.class);
        suite.addTestSuite(AccessQuestionTest.class);
        suite.addTestSuite(AccessUsersTest.class);
        suite.addTestSuite(AccessIngredientsTest.class);
        suite.addTestSuite(AccessDirectionsTest.class);
    }

    @Test
    private static void testObject(){
        suite.addTestSuite(AnswersTest.class);
        suite.addTestSuite(CategorysTest.class);
        suite.addTestSuite(DirectionTest.class);
        suite.addTestSuite(FoodCategoryTest.class);
        suite.addTestSuite(FoodDirectionTest.class);
        suite.addTestSuite(FoodIngredientTest.class);
        suite.addTestSuite(FoodTest.class);
        suite.addTestSuite(IngredientTest.class);
        suite.addTestSuite(QuestionTest.class);
        suite.addTestSuite(UserTest.class);
    }

    private static void testPersistence(){
        suite.addTestSuite(DataAccessTest.class);
    }
}
