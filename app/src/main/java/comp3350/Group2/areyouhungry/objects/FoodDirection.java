package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;
import comp3350.Group2.areyouhungry.business.AccessDirections;

public class FoodDirection{
    private Food food;
    private ArrayList<Direction> directions;

    public FoodDirection(Food food,ArrayList directions){
        if(food != null && directions.size() > 0){
          this.food = food;
          this.directions = new ArrayList<>();
          this.directions.clear();
          this.directions.addAll(directions);
        }else{
            throw new NullPointerException();
        }
    }

    public Food getFood(){
        return this.food;
    }

    public ArrayList<Direction> getDirections(){
        return this.directions;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof FoodDirection){
            FoodDirection otherFoodDirection = (FoodDirection)otherObject;
            if(this.getFood().getFoodID().equals(otherFoodDirection.getFood().getFoodID())){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "FoodID: "+ food.getFoodID()+"\n  Length of list: "+ directions.size()+"\n";
    }
}
