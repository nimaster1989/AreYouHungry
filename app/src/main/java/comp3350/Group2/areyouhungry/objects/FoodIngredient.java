package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessIngredients;

public class FoodIngredient{
    private Food food;
    private List<Ingredient> ingredients;


    public FoodIngredient(Food food, ArrayList ingredients){
        this.food = food;
        this.ingredients = new ArrayList<>();
        this.ingredients.clear();
        this.ingredients.addAll(ingredients);
    }

    public Food getFood(){
        return food;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }
}
