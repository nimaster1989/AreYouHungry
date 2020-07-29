package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.Ingredient;

public class IngredientTest extends TestCase{

    public IngredientTest(String arg0)
   {
        super(arg0);
    }

    public void testIngredientCreation(){
        Ingredient ingredient;
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        int testNum = 0;
        try{
            ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
    }

    public void testBadIDIngredientCreation(){
        Ingredient ingredient;
        int ingreID = -1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        int testNum = 0;
        try{
            ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);

        ingreID = -1000;
        ingreName = "Flour";
        ingreMeasurement = "2cups";
        try{
            ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);
    }

    public void testBadNameIngredientCreation(){
        Ingredient ingredient;
        int ingreID = 1;
        String ingreName = null;
        String ingreMeasurement = "2cups";
        int testNum = 0;
        try{
            ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);

    }

    public void testBadMeasurementIngredientCreation(){
        Ingredient ingredient;
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = null;
        int testNum = 0;
        try{
            ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(0,testNum);

    }

    public void testGetters(){
        Ingredient ingredient;
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);

        assertEquals(ingreID,ingredient.getIngredientID());
        assertTrue(ingreName.equals(ingredient.getIngredientName()));
        assertTrue(ingreMeasurement.equals(ingredient.getMeasurement()));
    }

    public void testSetters(){
        Ingredient ingredient;
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);

        ingreName = "Better Flour";
        ingredient.setIngredientName(ingreName);
        assertTrue(ingreName.equals(ingredient.getIngredientName()));

        ingreMeasurement = "2cups";
        ingredient.setMeasurement(ingreMeasurement);
        assertTrue(ingreMeasurement.equals(ingredient.getMeasurement()));
    }

    public void testEquals(){
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        Ingredient ingredient1 = new Ingredient(ingreID,ingreName,ingreMeasurement);
        Ingredient ingredient2 = new Ingredient(ingreID,ingreName,ingreMeasurement);
        ingreID = 2;
        Ingredient ingredient3 = new Ingredient(ingreID,ingreName,ingreMeasurement);

        assertTrue(ingredient1.equals(ingredient2));
        assertFalse(ingredient1.equals(ingredient3));
    }

    public void testToString(){
        int ingreID = 1;
        String ingreName = "Flour";
        String ingreMeasurement = "2cups";
        Ingredient ingredient = new Ingredient(ingreID,ingreName,ingreMeasurement);
        String test = "ID: 1\n  IngredientName: Flour\n  Measurement: 2cups\n";
        assertTrue(test.equals(ingredient.toString()));
        test = "ID: 1\nIngredientName: Flour\nMeasurement: 2cups\n";
        assertFalse(test.equals(ingredient.toString()));
        test = "";
        assertFalse(test.equals(ingredient.toString()));
    }
}
