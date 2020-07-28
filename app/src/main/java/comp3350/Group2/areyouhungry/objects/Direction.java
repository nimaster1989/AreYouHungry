package comp3350.Group2.areyouhungry.objects;

public class Direction{
    private int directionID;
    private String directionDescription;
    private int stepNumber;

    public Direction(int directionID, String directionDescription, int stepNumber){
        this.directionID = directionID;
        this.directionDescription = directionDescription;
        this.stepNumber = stepNumber;
    }

    public int getDirectionID(){
        return directionID;
    }

    public String getDirectionDescription(){
        return directionDescription;
    }

    public int getStepNumber(){
        return stepNumber;
    }

    public void setDirectionDescription(String directionDescription){
        this.directionDescription = directionDescription;
    }

    public void setStepNumber(int measurement){
        this.stepNumber = stepNumber;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof Direction){
            Direction otherDirection = (Direction) otherObject;
            if(this.directionID == otherDirection.directionID){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "ID: " + directionID + "\n  Direction Description: " + directionDescription + "\n  Step Number: " + stepNumber + "\n";
    }
}
