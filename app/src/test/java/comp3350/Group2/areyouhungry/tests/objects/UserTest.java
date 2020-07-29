package comp3350.Group2.areyouhungry.tests.objects;

import junit.framework.TestCase;

import comp3350.Group2.areyouhungry.objects.User;

public class UserTest extends TestCase{
    public UserTest(String arg0){
        super(arg0);
    }

    public void testUserCreation(){
        User user;
        int userID = 1;
        String userName = "Test";
        int testnum = 0;
        try{
            user = new User(userID, userName);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(1, testnum);
    }

    public void testBadIDUserCreation(){
        User user;
        int userID = -1;
        String userName = "Test";
        int testnum = 0;
        try{
            user = new User(userID, userName);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testBadNameUserCreation(){
        User user;
        int userID = 1;
        String userName = null;
        int testnum = 0;
        try{
            user = new User(userID, userName);
            testnum = 1;
        }catch(Exception e){
            testnum = 0;
        }
        assertEquals(0, testnum);
    }

    public void testGetters(){
        User user;
        int userID = 1;
        String userName = "Test";
        user = new User(userID, userName);
        assertEquals(userID, user.getUserID());
        assertTrue(userName.equals(user.getUserName()));
    }

    public void testSetters(){
        User user;
        int userID = 1;
        String userName = "Test";
        user = new User(userID, userName);

        user.setUserID(2);
        assertEquals(2,user.getUserID());

        user.setUserName("Joe");
        assertTrue("Joe".equals(user.getUserName()));
    }

    public void testEquals(){
        int userID = 1;
        String userName = "Test";
        User user1 = new User(userID, userName);
        User user2 = new User(userID, userName);
        userID = 2;
        User user3 = new User(userID, userName);

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
    }

    public void testToString(){
        int userID = 1;
        String userName = "Test";
        User user = new User(userID, userName);
        String test = "UserID: 1\n  UserName: Test\n";
        assertTrue(test.equals(user.toString()));
    }
}
