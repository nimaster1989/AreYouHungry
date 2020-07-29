package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class AccessDirectionsTest extends TestCase{

    public static String dbName = MainActivity.dbName;

    public AccessDirectionsTest(String arg0){
        super(arg0);
    }

    public void testGetEmptyDirections(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessDirections accessDirections = new AccessDirections();
        Food tempFood = new Food(777,"no food",7,10,"Sweet","Easy","American");
        ArrayList<Direction> directionList = new ArrayList<>();
        accessDirections.getDirection(tempFood,directionList);
        assertTrue(directionList.isEmpty());
        Services.closeDataAccess();
    }

    public void testGetDirections(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessDirections accessDirections = new AccessDirections();
        AccessFoods accessFoods = new AccessFoods();
        Food tempFood = accessFoods.getFoodByID("1");
        ArrayList<Direction> directionList = new ArrayList<>();
        accessDirections.getDirection(tempFood,directionList);
        assertFalse(directionList.isEmpty());
        for(Direction d:directionList){
            assertTrue(d.getDirectionID() > 0);
            assertFalse(d.getDirectionDescription().isEmpty());
        }
        Services.closeDataAccess();
    }
}
