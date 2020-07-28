package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessIngredients;

public class FoodIngredient{
    private Food food;
    private List<Ingredient> ingredients;

    public FoodIngredient(Food food){
        AccessIngredients ai = new AccessIngredients();
        ai.getIngredient(food,ingredients);
    }
    // OR

    public FoodIngredient(Food food, ArrayList ingredients){
        food = food;
        ingredients = ingredients;
    }

    public Food getFood(){
        return food;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}
