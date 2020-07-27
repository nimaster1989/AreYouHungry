package comp3350.Group2.areyouhungry.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;

public class DataAccessTest extends TestCase{
    private DataAccess dataAccess;

    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void setUp(){
        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statements to run with the stub database:
        dataAccess = new DataAccessStub();
        dataAccess.open("Stub");
        // or switch to the real database:
//         dataAccess = new DataAccessObject(MainActivity.dbName);
//         dataAccess.open(MainActivity.getDBPathName());
        // Note the increase in test execution time.
    }
    public void tearDown(){
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }
    public void testDataAccess(){
        ArrayList<Food> foods;
        Food food;
        String result;

        foods = new ArrayList<Food>();
        result = dataAccess.getFoodSequential(foods);
        assertNull(result);
        assertEquals(8, foods.size());
        food = foods.get(0);
        assertEquals("1", food.getFoodID());
        // ...
        // TODO: 26/07/20
        //add more data access test here
        // ...
    }
}
