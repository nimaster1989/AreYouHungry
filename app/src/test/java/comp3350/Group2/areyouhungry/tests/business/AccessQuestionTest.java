package comp3350.Group2.areyouhungry.tests.business;

import junit.framework.TestCase;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.Services;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessQuestions;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.tests.persistence.DataAccessStub;

public class AccessQuestionTest extends TestCase {

    public static String dbName = MainActivity.dbName;
    public AccessQuestionTest(String arg0){
        super(arg0);
    }

    public void testGetQuestions(){

    }

}
