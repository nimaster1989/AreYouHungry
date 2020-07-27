package comp3350.Group2.areyouhungry.objects;

public class User{
    private int UserID;
    private String UserName;

    public User(int userID, String userName){
        UserID = userID;
        UserName = userName;
    }
    public int getUserID(){
        return UserID;
    }
    public String getUserName(){
        return UserName;
    }
}
