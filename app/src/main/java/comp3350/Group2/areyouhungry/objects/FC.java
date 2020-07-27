package comp3350.Group2.areyouhungry.objects;

//FC stands for food-Categorys
public class FC {
    private int foodID;
    private int categoryID;
    public FC(int foodID,int categoryID){
        this.foodID = foodID;
        this.categoryID = categoryID;
    }

    public int getFoodID(){
        return foodID;
    }

    public int getCategoryID(){
        return categoryID;
    }
}
