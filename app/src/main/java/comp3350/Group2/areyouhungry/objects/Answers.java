package comp3350.Group2.areyouhungry.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Answers implements Serializable {

    private List<Integer> answers;
    private String flavor;
    private String portionSize;
    private String preptime;
    private String difficulty;
    private String ethnicity;


    public Answers(List<Integer> newAnswers){
        answers = newAnswers;
        for(int i = 0; i < 5; i++){
            int answer = newAnswers.get(i);
            if(i == 0){
                if(answer== 0){
                    flavor = "Flavor: Sweet";
                }else if(answer ==1){
                    flavor = "Flavor: Savory";
                }else if(answer ==2){
                    flavor = "Flavor: Spicy";
                }else if(answer ==3){
                    flavor = "Flavor: Other";
                }else{
                    flavor = "Flavor: Unknown";
                }
            }else if(i == 1){
                if(answer== 0){
                    portionSize = "Serves: 1 - 2 People";
                }else if(answer ==1){
                    portionSize = "Serves: 3 - 4 People";
                }else if(answer ==2){
                    portionSize = "Serves: 4 - 6 People";
                }else if(answer ==3){
                    portionSize = "Serves: 6 - 8 People";
                }else{
                    portionSize = "Serves: Unknown";
                }
            }else if(i == 2){
                if(answer== 0){
                    preptime = "Preparation Time: 10 minutes";
                }else if(answer== 1){
                    preptime = "Preparation Time: 20 minutes";
                }else if(answer== 2){
                    preptime = "Preparation Time: 30 minutes";
                }else if(answer ==3){
                    preptime = "Preparation Time: 40 minutes";
                }else{
                    preptime = "Preparation Time: Unknown";
                }
            }else if(i == 3){
                if(answer== 0){
                    difficulty = "Difficulty: Easy";
                }else if(answer== 1){
                    difficulty = "Difficulty: Medium";
                }else if(answer== 2){
                    difficulty = "Difficulty: Hard";
                }else if(answer ==3){
                    difficulty = "Difficulty: Chef Ramsey";
                }else{
                    difficulty = "Difficulty: Unknown";
                }
            }else if(i == 4){
                if(answer== 0){
                    ethnicity = "Ethnicity: Australian";
                }else if(answer== 1){
                    ethnicity = "Ethnicity: American";
                }else if(answer== 2){
                    ethnicity = "Ethnicity: Japanese";
                }else if(answer ==3){
                    ethnicity = "Ethnicity: Vietnamese";
                }else{
                    ethnicity = "Ethnicity: Unknown";
                }
            }

        }


    }
    public List<Integer> getAnswers(){
        return answers;
    }

    public void setAnswers(List<Integer> answers){
        this.answers = answers;
    }

    public String getFlavor(){
        return flavor;
    }

    public void setFlavor(String flavor){
        this.flavor = flavor;
    }

    public String getPortionSize(){
        return portionSize;
    }

    public void setPortionSize(String portionSize){
        this.portionSize = portionSize;
    }

    public String getPreptime(){
        return preptime;
    }

    public void setPreptime(String preptime){
        this.preptime = preptime;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String getEthnicity(){
        return ethnicity;
    }

    public void setEthnicity(String ethnicity){
        this.ethnicity = ethnicity;
    }




}
