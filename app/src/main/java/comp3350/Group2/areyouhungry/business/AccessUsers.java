package comp3350.Group2.areyouhungry.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;

public class AccessUsers {
    private DataAccess dataAccess;
    private List<User> Users;

    public AccessUsers(){
        dataAccess = Services.getDataAccess(MainActivity.dbName);
        Users = null;
    }
    public User getDefaultUser(){
        return dataAccess.getDefault();
    }

    public String getUsers(ArrayList<User> userList) {
        return dataAccess.getUserSequential(userList);
    }

    public User getUserByID(int userID) {
        return dataAccess.getUser(userID);
    }

    public User newUsers(String m_text) {
        List users = new ArrayList<User>();
        users.clear();
        dataAccess.getUserSequential(users);
        int newId = users.size()+1;
        return dataAccess.setNewUser(newId,m_text);
    }
}
