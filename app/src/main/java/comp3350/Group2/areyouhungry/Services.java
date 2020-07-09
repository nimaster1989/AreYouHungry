package comp3350.Group2.areyouhungry;

import comp3350.Group2.areyouhungry.presistence.DataAccessStub;

//services is to create and get data from the database
public class Services {
    private static DataAccessStub dataAccessService = null;

    public static DataAccessStub createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessStub();
            dataAccessService.open();
        }
        return dataAccessService;
    }

    public static DataAccessStub getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

}
