package comp3350.Group2.areyouhungry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.Group2.areyouhungry.acceptance.AddFoodTest;
import comp3350.Group2.areyouhungry.acceptance.FavouriteRecipeTest;
import comp3350.Group2.areyouhungry.acceptance.ReceiveRecipeSuggestionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({AddFoodTest.class, ReceiveRecipeSuggestionTest.class, FavouriteRecipeTest.class})

public class RunAcceptanceTests{
    public RunAcceptanceTests(){
        System.out.println("Running Acceptance Tests");
    }
}
