package comp3350.Group2.areyouhungry.business;

import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.persistence.DataAccess;

public class AccessIngredients{
    private DataAccess dataAccess;
    private List<Ingredient> ingredients;

    public AccessIngredients(){
        dataAccess = Services.getDataAccess(MainActivity.dbName);
        ingredients = null;
    }

    public String addIngredient(Ingredient newIngredient){
        return  dataAccess.addNewIngredient(newIngredient);
    }
    public String getIngredient(Food food, List<Ingredient> ingredients){
        return dataAccess.getIngredientByFood(food,ingredients);
    }
    public int getNewIngredientId(){
        return dataAccess.getIngredientRow() + 1;
    }
    public String setFoodIngredient(int foodid,int ingredientid){
        return dataAccess.addFoodIngredient(foodid,ingredientid);
    }
}
