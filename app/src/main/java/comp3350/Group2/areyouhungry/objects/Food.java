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
        if(foodID>=0 &&
                !foodName.isEmpty() /*&&
            (portionSize == 1 || portionSize == 3 || portionSize == 5 || portionSize == 7) &&
            (prepTime == 10 || prepTime == 20 || prepTime == 30 || prepTime == 40) &&
            (flavour.equals("Spicy") || flavour.equals("Sweet") || flavour.equals("Savory") || flavour.equals("Other")) &&
            (difficulty.equals("Easy") || difficulty.equals("Medium") || difficulty.equals("Hard") || difficulty.equals("Expert"))&&
            (ethnicity.equals("Australian") || ethnicity.equals("American") || ethnicity.equals("Japanese") || ethnicity.equals("Vietnamese"))*/
        ){
            this.foodID = foodID;
            this.foodName = foodName;
            this.portionSize = portionSize;
            this.prepTime = prepTime;
            this.flavour = flavour;
            this.difficulty = difficulty;
            this.ethnicity = ethnicity;
            this.favourite = false;
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

    public boolean isFavourite(){
        return favourite;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof Food){
            Food otherFood = (Food)otherObject;
            if(foodID == Integer.parseInt(otherFood.getFoodID())){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "ID: " + foodID + "\n  FoodName: " + foodName + "\n  Favourited: " + favourite + "\n  PortionSize: " + portionSize + "\n  PrepTime: "+ prepTime + "\n  Flavour: " + flavour + "\n  Difficulty: " + difficulty + "\n  Ethnicity: " + ethnicity+"\n";
    }



}
