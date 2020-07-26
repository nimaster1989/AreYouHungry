package comp3350.Group2.areyouhungry;

import comp3350.Group2.areyouhungry.persistence.DataAccess;
import comp3350.Group2.areyouhungry.persistence.DataAccessObject;
import comp3350.Group2.areyouhungry.persistence.DataAccessStub;

/* Services is to create and get data from the database. */
public class Services {
    private static DataAccess dataAccessService = null;

    public static DataAccess createDataAccess(String dbName){
        if (dataAccessService == null){
            dataAccessService = new DataAccessObject(dbName);
            dataAccessService.open(MainActivity.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess getDataAccess(DataAccess alternateDataAccessService){
        if (dataAccessService == null){
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(MainActivity.getDBPathName());
        }
        return dataAccessService;
    }

    public static DataAccess getDataAccess(String dbName){
        if (dataAccessService == null){
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess(){
        if (dataAccessService != null){
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
