package comp3350.Group2.areyouhungry.business;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.persistence.DataAccess;


import java.util.List;

public class AccessQuestions{
    private DataAccess dataAccess;

    public AccessQuestions(){
        dataAccess = Services.getDataAccess(MainActivity.dbName);
    }

    public List<Question> getQuestions(){
        return dataAccess.getAllQuestions();
    }

    public int getTotalQuestions(){
        return dataAccess.getTotalQuestions();
    }

}
