package comp3350.Group2.areyouhungry.objects;
import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable{
    private int foodID;
    private String foodName;
    private boolean favourite;
    private boolean liked;
    private int portionSize;
    private int totalTime;


    private String flavour;
    private String difficulty;
    private String ethnicity;

    public Food(int foodID, String foodName, int portionSize, int totalTime,String flavour,String difficulty,String ethnicity){
        if(foodID>=0 && !foodName.isEmpty()){
            this.foodID = foodID;
            this.foodName = foodName;
            this.portionSize = portionSize;
            this.totalTime = totalTime;
            this.flavour = flavour;
            this.difficulty = difficulty;
            this.ethnicity = ethnicity;
            this.favourite = false;
            this.liked = false;
        }else{
            throw new NullPointerException();
        }
    }

    public String getFoodID(){
        return String.valueOf(foodID);
    }

    public String getFoodName(){
        return foodName;
    }

    public int getPortionSize(){
        return portionSize;
    }

    public int getTotalTime(){
        return totalTime;
    }

    public String getFlavour(){
        return flavour;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getEthnicity(){
        return ethnicity;
    }

    public Boolean getFavourite(){
        return favourite;
    }

    public void setFavourite(boolean favourite){
        this.favourite = favourite;
    }

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public void setPortionSize(int portionSize){
        this.portionSize = portionSize;
    }

    public void setTotalTime(int totalTime){
        this.totalTime = totalTime;
    }

    public void setFlavour(String flavour){
        this.flavour = flavour;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public void setEthnicity(String ethnicity){
        this.ethnicity = ethnicity;
    }

    public boolean isFavourite(){
        return favourite;
    }

    public String toString(){
        return "ID: " + foodID + "\n  FoodName: " + foodName + "\n  Favourited: " + favourite + "\n  PortionSize: " + portionSize + "\n  TotalTime: "+ totalTime + "\n  Flavour: " + flavour + "\n  Difficulty: " + difficulty + "\n  Ethnicity: " + ethnicity+"\n";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return this.getFoodID().equals(food.getFoodID());
    }

    @Override
    public int hashCode(){
        return Objects.hash(foodID, foodName, favourite, portionSize, totalTime, flavour, difficulty, ethnicity);
    }
}
