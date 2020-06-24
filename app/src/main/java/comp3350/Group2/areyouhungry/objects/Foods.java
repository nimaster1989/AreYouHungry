package comp3350.Group2.areyouhungry.objects;

//class for food
public class Foods {
    private int foodID;
    private String foodName;
    private String recipeLink;
    private boolean favourite;

    public Foods(int newFoodID,String newFoodName)
    {
        foodID = newFoodID;
        foodName = newFoodName;
        recipeLink = null;
        favourite= false;
    }
    public int getFoodID(){ return foodID; }

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
        return "Foods{" +
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

        Foods foods = (Foods) o;

        if (getFoodID() != foods.getFoodID()) return false;
        if (favourite != foods.favourite) return false;
        if (getFoodName() != null ? !getFoodName().equals(foods.getFoodName()) : foods.getFoodName() != null)
            return false;
        return getRecipeLink() != null ? getRecipeLink().equals(foods.getRecipeLink()) : foods.getRecipeLink() == null;
    }

}
