package comp3350.Group2.areyouhungry.objects;

public class Question{
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Question(){}

    public Question(String question, String option1, String option2, String option3, String option4){
        if(!question.isEmpty() && !option1.isEmpty() && !option2.isEmpty() && !option3.isEmpty() && !option4.isEmpty()){
            this.question = question;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.option4 = option4;
        }else{
            throw new NullPointerException();
        }

    }

    public String getQuestion(){
        return question;
    }

    public String getOption1(){
        return option1;
    }

    public String getOption2(){
        return option2;
    }

    public String getOption3(){
        return option3;
    }

    public String getOption4(){
        return option4;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setOption1(String option1){
        this.option1 = option1;
    }

    public void setOption2(String option2){
        this.option2 = option2;
    }

    public void setOption3(String option3){
        this.option3 = option3;
    }

    public void setOption4(String option4){
        this.option4 = option4;
    }

    public boolean equals(Object otherObject){
        boolean equal = false;
        if(otherObject instanceof Question){
            Question otherUser = (Question)otherObject;
            if(this.getQuestion().equals(otherUser.getQuestion())){
                equal = true;
            }
        }
        return equal;
    }

    public String toString(){
        return "Question: " + question +"\n  Option1: " + option1 +"\n  Option2: " + option2 +"\n  Option3: " + option3 +"\n  Option4: " + option4 +"\n";
    }
}
