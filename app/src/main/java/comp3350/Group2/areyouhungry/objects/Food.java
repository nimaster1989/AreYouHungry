package comp3350.Group2.areyouhungry.objects;

//class for food
//DATABASE commit test
public class Food {
    public String foodID;
    public String foodName;
    public String recipeLink;
    public boolean favourite;

    public Food(String foodID) {
        this.foodID = foodID;
        this.foodName = null;
    }

    public Food(String newFoodID, String newFoodName)
    {
        foodID = newFoodID;
        foodName = newFoodName;
        recipeLink = null;
        favourite= false;
    }
    public Food(String newFoodID, String newFoodName,String recipeLink)
    {
        this.foodID = newFoodID;
        this.foodName = newFoodName;
        this.recipeLink = recipeLink;
        favourite= false;
    }

    public String getFoodID(){ return foodID; }

    public String getFoodName() { return foodName; }

    public String getRecipeLink() { return recipeLink; }

    public void setRecipeLink(String recipeLink) {
        this.recipeLink = recipeLink;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodID=" + foodID +
                ", foodName='" + foodName + '\'' +
                ", recipeLink='" + recipeLink + '\'' +
                ", favourite=" + favourite +
                '}';
    }

    public boolean equals(Food o) {
        if (this.foodID == o.foodID) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (getFoodID() != food.getFoodID()) return false;
        if (favourite != food.favourite) return false;
        if (getFoodName() != null ? !getFoodName().equals(food.getFoodName()) : food.getFoodName() != null)
            return false;
        return getRecipeLink() != null ? getRecipeLink().equals(food.getRecipeLink()) : food.getRecipeLink() == null;
    }

}
