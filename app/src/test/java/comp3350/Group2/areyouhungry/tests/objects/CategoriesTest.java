package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.Categories;

public class CategoriesTest extends TestCase{

    public CategoriesTest(String arg0){
        super(arg0);
    }

    public void testCategoriesCreation(){
        Categories categories;
        int catID = 10;
        String catName = "Test Categories";
        int testNum = 0;
        try{
            categories = new Categories(catID, catName);
            testNum = 1;
        }catch(Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
    }

    public void testNullNameCreation(){
        Categories categories;
        int catID = 10;
        String catName = null;
        int testNum = 0;
        try{
            categories = new Categories(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testNegativeIDCreation(){
        Categories categories;
        int catID = -1;
        String catName = "Test Category";
        int testNum = 0;
        try{
            categories = new Categories(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testBadParameters(){
        Categories categories;
        int catID = -1;
        String catName = null;
        int testNum = 0;
        try{
            categories = new Categories(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testGetCategoryID(){
        Categories categories;
        int catID = 10;
        String catName = "Test Categories";

        categories = new Categories(catID, catName);
        assertEquals(catID,categories.getCategoryID());
    }
    public void testGetCategoryName(){
        Categories categories;
        int catID = 10;
        String catName = "Test Categories";
        categories = new Categories(catID, catName);
        assertTrue(catName.equals(categories.getCategoryName()));
    }

    public void testEquals(){
        int catID = 10;
        String catName = "Test Categories";
        Categories categories1 = new Categories(catID, catName);
        Categories categories2 = new Categories(catID, catName);
        Categories categories3= new Categories(11, catName);
        assertTrue(categories1.equals(categories2));
        assertFalse(categories1.equals(categories3));
    }

    public void testToString(){
        int catID = 10;
        String catName = "Test Categories";
        Categories categories = new Categories(catID, catName);
        String test = "categoryID: 10\n  categoryName: Test Categories\n";
        assertTrue(categories.toString().equals(test));
    }

}
