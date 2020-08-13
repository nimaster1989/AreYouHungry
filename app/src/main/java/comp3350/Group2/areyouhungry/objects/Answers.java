package comp3350.Group2.areyouhungry.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comp3350.Group2.areyouhungry.business.AccessFoods;

public class Answers implements Serializable{

    private List<Integer> answers;
    private String flavor;
    private String portionSize;
    private String totalTime;
    private String difficulty;
    private String ethnicity;
    private AccessFoods accessFoods;

    public Answers(List<Integer> newAnswers){
        answers = newAnswers;
        if(newAnswers.size() == 5){
            for (int i = 0; i < newAnswers.size(); i++){
                int answer = newAnswers.get(i);
                if (i == 0){
                    if (answer == 0){
                        flavor = "Sweet";
                    } else if (answer == 1){
                        flavor = "Savoury";
                    } else if (answer == 2){
                        flavor = "Spicy";
                    } else if (answer == 3){
                        flavor = "Fresh";
                    } else{
                        flavor = "Unknown";
                    }
                } else if (i == 1){
                    if (answer == 0){
                        portionSize = "1";
                    } else if (answer == 1){
                        portionSize = "3";
                    } else if (answer == 2){
                        portionSize = "5";
                    } else if (answer == 3){
                        portionSize = "7";
                    } else{
                        portionSize = "Unknown";
                    }
                } else if (i == 2){
                    if (answer == 0){
                        totalTime = "10";
                    } else if (answer == 1){
                        totalTime = "20";
                    } else if (answer == 2){
                        totalTime = "30";
                    } else if (answer == 3){
                        totalTime = "40";
                    } else{
                        totalTime = "Unknown";
                    }
                } else if (i == 3){
                    if (answer == 0){
                        difficulty = "Easy";
                    } else if (answer == 1){
                        difficulty = "Medium";
                    } else if (answer == 2){
                        difficulty = "Hard";
                    } else if (answer == 3){
                        difficulty = "Expert";
                    } else{
                        difficulty = "Unknown";
                    }
                } else if (i == 4){
                    if (answer == 0){
                        ethnicity = "Greek";
                    } else if (answer == 1){
                        ethnicity = "American";
                    } else if (answer == 2){
                        ethnicity = "Italian";
                    } else if (answer == 3){
                        ethnicity = "Chinese";
                    } else{
                        ethnicity = "Unknown";
                    }
                }

            }
        }else{
            throw new NullPointerException();
        }


    }

    public String getFlavor(){
        return flavor;
    }

    public String getPortionSize(){
        return portionSize;
    }

    public String getTotalTime(){
        return totalTime;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getEthnicity(){
        return ethnicity;
    }

    public Food getFoodBasedOnAnswers(){
        accessFoods = new AccessFoods();
        List<Food> foods = new ArrayList<Food>();
        String result = accessFoods.getFoods(foods);
        Iterator<Food> foodIterator = foods.iterator(); /* This iterates through the foods list. */
        Food curr_food = null;
        while(foodIterator.hasNext()){
            curr_food = foodIterator.next();
            if(checkValid(curr_food)){
                if ((curr_food.getTotalTime() == Integer.parseInt(totalTime.trim())) &&
                        curr_food.getFlavour().equals(flavor) &&
                        curr_food.getEthnicity().equals(ethnicity) &&
                        curr_food.getDifficulty().equals(difficulty) &&
                        curr_food.getPortionSize() == Integer.parseInt(portionSize.trim())){
                    return curr_food;
                }
            }
        }
        accessFoods.getRandom(foods);
        return foods.get(0);
    }

    public void setFlavor(String flavor){
        this.flavor = flavor;
    }

    public void setPortionSize(String portionSize){
        this.portionSize = portionSize;
    }

    public void setTotalTime(String totalTime){
        this.totalTime = totalTime;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public void setEthnicity(String ethnicity){
        this.ethnicity = ethnicity;
    }

    private boolean checkValid(Food food){
        boolean valid = false;
        if(food.getTotalTime() != 0 &&
            food.getPortionSize() != 0 &&
            food.getDifficulty() != null &&
            food.getFlavour() != null &&
            food.getEthnicity() != null){
            valid = true;
        }
        return valid;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof Answers){
            Answers otherAnswer = (Answers)otherObject;
            if(flavor.equals(otherAnswer.getFlavor()) &&
                portionSize.equals(otherAnswer.getPortionSize()) &&
                totalTime.equals(otherAnswer.getTotalTime()) &&
                difficulty.equals(otherAnswer.getDifficulty()) &&
                ethnicity.equals(otherAnswer.getEthnicity())){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "Flavor: " + flavor +"\n  PortionSize: "+portionSize+"\n  TotalTime: "+totalTime+"\n  Difficulty: "+difficulty+"\n  Ethnicity: "+ethnicity+"\n";
    }
}
