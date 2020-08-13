package comp3350.Group2.areyouhungry;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.Group2.areyouhungry.acceptance.*;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddUserTest.class,
        SwithUserTest.class,
        ReceiveRecipeSuggestionTest.class,
        FavouriteFoodTest.class,
        LikeDislikeRecipeTest.class,
        OnHandTest.class,
        OnHandFuzzyTest.class,
        OnHandComplexTest.class,
        AddFoodTest.class,})

public class RunAcceptanceTests{
    public RunAcceptanceTests(){
        System.out.println("Running Acceptance Tests");
    }
}
