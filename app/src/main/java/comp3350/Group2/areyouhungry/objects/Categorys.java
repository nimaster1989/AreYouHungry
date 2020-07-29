package comp3350.Group2.areyouhungry.objects;

public class Categorys{
    private int categoryID;
    private String categoryName;
    public Categorys(int categoryID,String categoryName){
        this.categoryID = categoryID;
        this.categoryName  = categoryName;
    }

    public int getCategoryID(){
        return categoryID;
    }

    public String getCategoryName(){
        return categoryName;
    }
}
