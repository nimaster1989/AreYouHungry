package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.business.AccessDirections;

public class FoodDirection{
    private Food food;
    private ArrayList<Direction> directions;


    public FoodDirection(Food food,ArrayList directions){
        this.food = food;
        this.directions = new ArrayList<>();
        this.directions.clear();
        this.directions.addAll(directions);
    }

    public Food getFood(){
        return this.food;
    }

    public ArrayList<Direction> getDirections(){
        return this.directions;
    }

}
