package comp3350.Group2.areyouhungry.objects;

//FoodCategory stands for food-Categorys
public class FoodCategory {
    private int foodID;
    private int categoryID;
    public FoodCategory(int foodID, int categoryID){
        this.foodID = foodID;
        this.categoryID = categoryID;
    }

    public int getFoodID(){
        return foodID;
    }

    public int getCategoryID(){
        return categoryID;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof FoodCategory){
            FoodCategory otherFoodCategory = (FoodCategory)otherObject;
            if(this.getFoodID() == otherFoodCategory.getFoodID() && this.getCategoryID() == otherFoodCategory.getCategoryID()){
                equal = true;
            }
        }
        return equal;
    }
}
