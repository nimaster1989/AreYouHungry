package comp3350.Group2.areyouhungry.tests;

import junit.framework.TestSuite;

import comp3350.Group2.areyouhungry.tests.acceptance.FavouriteRecipesTest;
import comp3350.Group2.areyouhungry.tests.acceptance.LikeDislikeRecipeTest;
import comp3350.Group2.areyouhungry.tests.acceptance.ReceiveFoodSuggestionTest;
import comp3350.Group2.areyouhungry.tests.acceptance.SuggestionCriteriaTest;

public class RunAcceptanceTests {
    public static TestSuite suite;

    /* This method runs ALL the test suites. */
    public static TestSuite suite(){
        suite = new TestSuite("All Tests");
        testAddRecipe();
        testFavouriteRecipes();
        testLikeDislikeRecipe();
        testReceiveFoodSuggestion();
        testSuggestionCriteria();
        return suite;
    }

    public static void testAddRecipe(){
        suite.addTestSuite(AddRecipeTest.class);
    }

    public static void testFavouriteRecipes(){
        suite.addTestSuite(FavouriteRecipesTest.class);
    }

    public static void testLikeDislikeRecipe(){
        suite.addTestSuite(LikeDislikeRecipeTest.class);
    }

    public static void testReceiveFoodSuggestion(){
        suite.addTestSuite(ReceiveFoodSuggestionTest.class);
    }

    public static void testSuggestionCriteria(){
        suite.addTestSuite(SuggestionCriteriaTest.class);
    }
}
