package comp3350.Group2.areyouhungry.objects;

//class for food
//DATABASE commit test
public class Food {
    private String foodID;
    private String foodName;
    private String recipeLink;
    private boolean favourite;

    public Food(String foodID) {
        this.foodID = foodID;
        this.foodName = null;
    }

    public Food(String newFoodID, String newFoodName)
    {
        foodID = newFoodID;
        foodName = newFoodName;
        recipeLink = null;
        favourite = false;
    }
    public Food(String newFoodID, String newFoodName,String recipeLink)
    {
        this.foodID = newFoodID;
        this.foodName = newFoodName;
        this.recipeLink = recipeLink;
        favourite = false;
    }
    public Food(String newFoodID, String newFoodName,String recipeLink,Boolean favourite)
    {
        this.foodID = newFoodID;
        this.foodName = newFoodName;
        this.recipeLink = recipeLink;
        this.favourite = favourite;
    }

    public String getFoodID(){ return foodID; }

    public String getFoodName() { return foodName; }

    public String getRecipeLink() { return recipeLink; }

    public Boolean getFavourite() {return favourite; }

    public void setRecipeLink(String recipeLink) {
        this.recipeLink = recipeLink;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;

    }

    @Override
    public String toString() {
        return "ID: " + foodID + "\n  foodName: " + foodName + "\n  recipe link: " + recipeLink + "\n  favourited: " + favourite + "\n";
    }

    public boolean equals(Object otherObject){
        boolean result = false;

        if (otherObject instanceof Food){
            Food otherFood = (Food) otherObject;

            if(foodID == null && otherFood.foodID == null){
                result = true;
            }
            else if (foodID.equals(otherFood.foodID)){
                result = true;
            }
        }
        return result;
    }

}
