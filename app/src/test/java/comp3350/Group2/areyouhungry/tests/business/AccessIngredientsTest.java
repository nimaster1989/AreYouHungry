package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class AccessIngredientsTest extends TestCase{
    public static String dbName = MainActivity.dbName;

    public AccessIngredientsTest(String arg0){
        super(arg0);
    }

    public void testGetEmptyIngredients(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessIngredients accessIngredients = new AccessIngredients();
        Food tempFood = new Food(777,"no food",7,10,"Sweet","Easy","American");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        accessIngredients.getIngredient(tempFood,ingredients);
        assertTrue(ingredients.isEmpty());
        Services.closeDataAccess();
    }

    public void testGetIngredients(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessIngredients accessIngredients = new AccessIngredients();
        AccessFoods accessFoods = new AccessFoods();
        Food tempFood = accessFoods.getFoodByID("1");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        accessIngredients.getIngredient(tempFood,ingredients);
        assertFalse(ingredients.isEmpty());
        for(Ingredient i:ingredients){
            assertTrue(i.getIngredientID() > 0);
            assertFalse(i.getIngredientName().isEmpty());
            assertFalse(i.getMeasurement().isEmpty());
        }
        Services.closeDataAccess();
    }

    public void testGetFoodByIngredients(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessIngredients accessIngredients = new AccessIngredients();
        ArrayList<Food> foodResult = new ArrayList<>();
        accessIngredients.getFoodsByIngredient("Salmon Fillets",foodResult);
        assertEquals(foodResult.size(),1);
        assertEquals(foodResult.get(0).getFoodName(),"Baked Salmon");
        Services.closeDataAccess();
    }
}
