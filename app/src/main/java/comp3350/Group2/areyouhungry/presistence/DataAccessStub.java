package comp3350.Group2.areyouhungry.presistence;

import java.util.ArrayList;

import comp3350.*;
import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Foods;

public class DataAccessStub {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Foods> foods;

    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    public DataAccessStub()
    {
        //this(MainActivity.dbName);
    }
}
