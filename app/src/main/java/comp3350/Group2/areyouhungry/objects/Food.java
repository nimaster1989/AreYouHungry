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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (getFoodID() != food.getFoodID()) return false;
        if (favourite != food.favourite) return false;
        if (getFoodName() != null ? !getFoodName().equals(food.getFoodName()) : food.getFoodName() != null)
            return false;
        return getRecipeLink() != null ? getRecipeLink().equals(food.getRecipeLink()) : food.getRecipeLink() == null;
    }

}
