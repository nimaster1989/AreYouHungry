package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.Direction;

public class DirectionTest extends TestCase {

    public DirectionTest(String arg0)
    {
        super(arg0);
    }

    public void testDirectionCreation(){
        Direction direction;
        int dirID = 10;
        String dirDesc = "Test Categories";
        int stepNum = 3;
        int testNum = 0;
        try{
            direction = new Direction(dirID, dirDesc,stepNum);
            testNum = 1;
        }catch (Exception e){
            testNum = 0;
        }
        assertEquals(1,testNum);

    }

    public void testNullDescCreation(){
        Direction direction;
        int dirID = 10;
        String dirDesc = null;
        int stepNum = 3;
        int testNum = 0;
        try{
            direction = new Direction(dirID, dirDesc,stepNum);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testNegativeIDCreation(){
        Direction direction;
        int dirID = -1;
        String dirDesc = "Test Categories";
        int stepNum = 3;
        int testNum = 0;
        try{
            direction = new Direction(dirID, dirDesc,stepNum);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testNegativeStepCreation(){
        Direction direction;
        int dirID = 1;
        String dirDesc = "Test Categories";
        int stepNum = -3;
        int testNum = 0;
        try{
            direction = new Direction(dirID, dirDesc,stepNum);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }
    public void testBadParameters(){
        Direction direction;
        int dirID = -1;
        String dirDesc = null;
        int stepNum = -1;
        int testNum = 0;
        try{
            direction = new Direction(dirID, dirDesc,stepNum);
        }catch (Exception e){
            testNum = 1;
        }
        assertEquals(1,testNum);
    }

    public void testGetters(){
        Direction direction;
        int dirID = 1;
        String dirDesc = "Test Categories";
        int stepNum = 3;

        direction = new Direction(dirID, dirDesc,stepNum);
        assertEquals(dirID,direction.getDirectionID());
        assertEquals(dirDesc,direction.getDirectionDescription());
        assertEquals(stepNum,direction.getStepNumber());
    }

    public void testSetters(){
        Direction direction = new Direction(4, "Test",1);
        int dirID = 1;
        String dirDesc = "Test Categories";
        int stepNum = 3;
        direction.setStepNumber(stepNum);
        direction.setDirectionID(dirID);
        direction.setDirectionDescription(dirDesc);

        assertEquals(dirID,direction.getDirectionID());
        assertEquals(dirDesc,direction.getDirectionDescription());
        assertEquals(stepNum,direction.getStepNumber());
    }

    public void testEquals(){
        Direction direction1 = new Direction(4, "Test",1);
        Direction direction2 = new Direction(4, "Test",1);
        Direction direction3 = new Direction(5, "Test",1);
        assertTrue(direction1.equals(direction2));
        assertFalse(direction1.equals(direction3));
    }

    public void testToString(){
        Direction direction = new Direction(4, "Test",1);
        String test = "ID: 4\n  Direction Description: Test\n  Step Number: 1\n";
        assertTrue(direction.toString().equals(test));
    }

}
