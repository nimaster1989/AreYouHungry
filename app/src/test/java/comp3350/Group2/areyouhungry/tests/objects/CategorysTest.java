package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.Categorys;

public class CategorysTest extends TestCase{

    public CategorysTest(String arg0){
        super(arg0);
    }

    public void testCategoryCreation(){
        Categorys categorys;
        int catID = 10;
        String catName = "Test Categories";
        int testNum = 0;
        try{
            categorys = new Categorys(catID, catName);
            testNum = 1;
        }catch(Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);
    }

    public void testNullNameCreation(){
        Categorys categorys;
        int catID = 10;
        String catName = null;
        int testNum = 0;
        try{
            categorys = new Categorys(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testNegativeIDCreation(){
        Categorys categorys;
        int catID = -1;
        String catName = "Test Category";
        int testNum = 0;
        try{
            categorys = new Categorys(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testBadParameters(){
        Categorys categorys;
        int catID = -1;
        String catName = null;
        int testNum = 0;
        try{
            categorys = new Categorys(catID, catName);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testGetCategoryID(){
        Categorys categorys;
        int catID = 10;
        String catName = "Test Categories";

        categorys = new Categorys(catID, catName);
        assertEquals(catID,categorys.getCategoryID());
    }
    public void testGetCategoryName(){
        Categorys categorys;
        int catID = 10;
        String catName = "Test Categories";
        categorys = new Categorys(catID, catName);
        assertTrue(catName.equals(categorys.getCategoryName()));
    }

    public void testEquals(){
        int catID = 10;
        String catName = "Test Categories";
        Categorys categorys1 = new Categorys(catID, catName);
        Categorys categorys2 = new Categorys(catID, catName);
        Categorys categorys3= new Categorys(11, catName);
        assertTrue(categorys1.equals(categorys2));
        assertFalse(categorys1.equals(categorys3));
    }

    public void testToString(){
        int catID = 10;
        String catName = "Test Categories";
        Categorys categorys = new Categorys(catID, catName);
        String test = "categoryID: 10\n  categoryName: Test Categories\n";
        assertTrue(categorys.toString().equals(test));
    }

}
