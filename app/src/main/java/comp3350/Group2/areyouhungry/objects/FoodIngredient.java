package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

public class FoodIngredient{
    private Food food;
    private List<Ingredient> ingredients;

    public FoodIngredient(Food food, ArrayList ingredients){
        if(food != null && ingredients.size() > 0){
          this.food = food;
          this.ingredients = new ArrayList<>();
          this.ingredients.clear();
          this.ingredients.addAll(ingredients);
        }else{
            throw new NullPointerException();
        }
    }

    public Food getFood(){
        return food;
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof FoodIngredient){
            FoodIngredient otherUser = (FoodIngredient)otherObject;
            if(this.getFood().getFoodID().equals(otherUser.getFood().getFoodID())){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "FoodID: "+ food.getFoodID()+"\n  Length of list: "+ ingredients.size()+"\n";
    }
}
