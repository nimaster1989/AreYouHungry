package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;

public class Food{
    private int foodID;
    private String foodName;
    private boolean favourite;
    private int portionSize;
    private int prepTime;

    private String flavour;
    private String difficulty;
    private String ethnicity;

    public Food(int foodID, String foodName, int portionSize, int prepTime,String flavour,String difficulty,String ethnicity){
        this.foodID = foodID;
        this.foodName = foodName;
        this.portionSize = portionSize;
        this.prepTime = prepTime;
        this.flavour = flavour;
        this.difficulty = difficulty;
        this.ethnicity = ethnicity;
        this.favourite = false;
    }

    public String getFoodID(){
        return String.valueOf(foodID);
    }

    public String getFoodName(){
        return foodName;
    }

    public boolean isFavourite(){
        return favourite;
    }

    public int getPortionSize(){
        return portionSize;
    }

    public int getPrepTime(){
        return prepTime;
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

    public void setPrepTime(int prepTime){
        this.prepTime = prepTime;
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

    @Override
    public String toString(){
        return "ID: " + foodID + "\n  FoodName: " + foodName + "\n  Favourited: " + favourite + "\n  PortionSize: " + portionSize + "\n  PrepTime: "
                + prepTime + "\n  Flavour: " + flavour + "\n  Difficulty: " + difficulty + "\n Ethnicity: " + ethnicity;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return getFoodID().equals(food.getFoodID());
    }

}
