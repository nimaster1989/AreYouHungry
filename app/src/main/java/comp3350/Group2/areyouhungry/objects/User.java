package comp3350.Group2.areyouhungry.objects;

public class User{
    private int UserID;
    private String UserName;

    public User(int userID, String userName){
        if(userID >= 0 && !userName.isEmpty()){
            UserID = userID;
            UserName = userName;
        }else{
            throw new NullPointerException();
        }
    }
    public int getUserID(){
        return UserID;
    }
    public String getUserName(){
        return UserName;
    }
    public void setUserID(int UserID){
        this.UserID = UserID;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof User){
            User otherUser = (User)otherObject;
            if(this.getUserID() == otherUser.getUserID()){
                equal = true;
            }
        }
        return equal;
    }


    public String toString(){
        return "UserID: " + UserID + "\n  UserName: " + UserName + "\n";
    }
}
