package comp3350.Group2.areyouhungry.objects;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Integer> answers;
    private String flavor;
    private String portionSize;
    private String preptime;
    private String difficulty;
    private String faveColor;


    public Answers(List<Integer> newAnswers){
        answers = newAnswers;
        for(int i = 0; i < 4; i++){
            int answer = newAnswers.get(i);
            if(i == 0){
                if(answer!= 0){
                    flavor = "spicy";
                }
            }else if(i == 1){
                if(answer!= 0){
                    portionSize = "small";
                }
            }else if(i == 2){
                if(answer!= 0){
                    preptime = "short";
                }
            }else if(i == 3){
                if(answer!= 0){
                    difficulty = "easy";
                }
            }else if(i == 4){
                if(answer!= 0){
                    faveColor = "blue";
                }
            }

        }


    }
    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(String portionSize) {
        this.portionSize = portionSize;
    }

    public String getPreptime() {
        return preptime;
    }

    public void setPreptime(String preptime) {
        this.preptime = preptime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getFaveColor() {
        return faveColor;
    }

    public void setFaveColor(String faveColor) {
        this.faveColor = faveColor;
    }




}
