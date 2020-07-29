package comp3350.Group2.areyouhungry.objects;

public class Categorys{
    private int categoryID;
    private String categoryName;
    public Categorys(int categoryID,String categoryName){
        if(categoryID >= 0 && !categoryName.isEmpty()) {
            this.categoryID = categoryID;
            this.categoryName = categoryName;
        }
        else{
            throw new NullPointerException();
        }

    }

    public int getCategoryID(){
        return categoryID;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof Categorys){
            Categorys otherCategory = (Categorys) otherObject;
            if(categoryID == otherCategory.getCategoryID() ){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "categoryID: " + categoryID + "\n  categoryName: " + categoryName + "\n";
    }
}
