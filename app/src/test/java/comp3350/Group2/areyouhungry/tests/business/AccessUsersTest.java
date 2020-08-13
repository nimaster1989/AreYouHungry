package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessUsers;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class AccessUsersTest extends TestCase{

    public static String dbName = MainActivity.dbName;

    public AccessUsersTest(String arg0){super(arg0);}

    public void testGetEmptyUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessUsers accessUsers= new AccessUsers();
        assertNull(accessUsers.getUserByID(-1));
        Services.closeDataAccess();
    }
    public void testGetUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessUsers accessUsers= new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        accessUsers.getUsers(users);
        assertFalse(users.isEmpty());
        assertEquals(users.size(),2);
        Services.closeDataAccess();
    }
    public void testGetDefaultUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        AccessUsers accessUsers= new AccessUsers();
        User tempUser = accessUsers.getDefaultUser();
        assertNotNull(tempUser);
        assertEquals(tempUser.getUserID(),1);
        assertEquals(tempUser.getUserName(),"default user");
        Services.closeDataAccess();
    }

    public void testNewUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        String newUserName = "a man has no name";
        AccessUsers accessUsers= new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        accessUsers.getUsers(users);
        assertEquals(users.size(),2);
        users.clear();
        accessUsers.newUsers(newUserName);
        accessUsers.getUsers(users);
        assertEquals(users.get(2).getUserName(),"a man has no name");
        assertEquals(users.get(2).getUserID(),3);
        assertEquals(users.size(),3);
        Services.closeDataAccess();
    }

    public void testInvalidNewUser(){
        Services.closeDataAccess();
        Services.createDataAccess(new DataAccessStub(dbName));
        String newUserName = "";
        AccessUsers accessUsers= new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        accessUsers.getUsers(users);
        assertEquals(users.size(),2);
        users.clear();
        accessUsers.newUsers(newUserName);
        accessUsers.getUsers(users);
        assertEquals(users.size(),2);
        Services.closeDataAccess();
    }
}
