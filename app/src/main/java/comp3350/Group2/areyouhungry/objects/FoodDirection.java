package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.business.AccessDirections;

public class FoodDirection{
    private Food food;
    private ArrayList<Direction> directions;

    public FoodDirection(Food food){
        AccessDirections ad = new AccessDirections();
        ad.getDirection(food,directions);
    }
    // OR

    public FoodDirection(Food food,ArrayList directions){
        food = food;
        directions = directions;
    }

    public Food getFood(){
        return food;
    }

    public ArrayList<Direction> getDirections(){
        return directions;
    }
}
