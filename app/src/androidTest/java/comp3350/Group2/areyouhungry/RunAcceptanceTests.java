package comp3350.Group2.areyouhungry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.Group2.areyouhungry.acceptance.AddFoodTest;
import comp3350.Group2.areyouhungry.acceptance.AddUserTest;
import comp3350.Group2.areyouhungry.acceptance.LikeDislikeRecipeTest;
import comp3350.Group2.areyouhungry.acceptance.OnHandTest;
import comp3350.Group2.areyouhungry.acceptance.ReceiveRecipeSuggestionTest;
import comp3350.Group2.areyouhungry.acceptance.FavouriteRecipeTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({AddFoodTest.class, ReceiveRecipeSuggestionTest.class, FavouriteRecipeTest.class, LikeDislikeRecipeTest.class, OnHandTest.class, AddUserTest.class})

public class RunAcceptanceTests{
    public RunAcceptanceTests(){
        System.out.println("Running Acceptance Tests");
    }
}
