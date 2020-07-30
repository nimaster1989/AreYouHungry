package comp3350.Group2.areyouhungry.objects;

public class FoodCategory{
    private int foodID;
    private int categoryID;

    public FoodCategory(int foodID,int categoryID){
        if(foodID >= 0 && categoryID >=0){
            this.foodID = foodID;
            this.categoryID = categoryID;
        }
        else{
            throw new NullPointerException();
        }

    }

    public int getFoodID(){
        return foodID;
    }

    public int getCategoryID(){
        return categoryID;
    }

    public void setFoodID(int foodID){
        this.foodID = foodID;
    }

    public void setCategoryID(int categoryID){
        this.categoryID = categoryID;
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

    public String toString(){
        return "FoodID: "+ foodID+"\n  CategoryID: "+ categoryID+"\n";
    }
}
