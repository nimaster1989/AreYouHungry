package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;
public class DatabaseTest extends TestCase {
    //TODO add real tests, these are fake just to test the testing suite is working
    @Test
    public void testAddition() {
        System.out.println("Running test to test addition");
        assertEquals(4, 2 + 2);
    }

    public void testSubtraction() {
        System.out.println("Running test to test subtraction");
        assertEquals(4, 6 - 2);
    }
}
