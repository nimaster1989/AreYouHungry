package comp3350.Group2.areyouhungry.presistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import comp3350.Group2.areyouhungry.objects.Food;

public class DataAccessObject implements DataAccess{
    private Statement st1,st2,st3;
    private Connection c1;
    private ResultSet rs2,rs3,rs4,rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Food> foods;

    //cmdString is for sql string
    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName){ this.dbName = dbName; }

    public void open(String dbPath){
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();

            /*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/

            /*
             * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
             * // See also: https://github.com/SQLDroid/SQLDroid
             * dbType = "SQLite";
             * Class.forName("SQLite.JDBCDriver").newInstance();
             * url = "jdbc:sqlite:" + dbPath;
             * c1 = DriverManager.getConnection(url);
             *
             * ... create statements
             */

            /*** The following two work on desktop builds: ***/

            /*
             * // Setup for Access
             * dbType = "Access";
             * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
             * url = "jdbc:odbc:SC";
             * c1 = DriverManager.getConnection(url,"userid","userpassword");
             *
             * ... create statements
             */

            /*
             * //Setup for MySQL
             * dbType = "MySQL";
             * Class.forName("com.mysql.jdbc.Driver");
             * url = "jdbc:mysql://localhost/database01";
             * c1 = DriverManager.getConnection(url, "root", "");
             *
             * ... create statements
             */
        }
        catch (Exception e)
        {
            processSQLError(e);
            System.out.println("open fail,"+ e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public Map getFoodMap(Map ret_food_map) {
        ArrayList<Food> mfoods = new ArrayList<Food>();
        mfoods.clear();
        this.getFoodSequential(mfoods);
        Iterator<Food> foodIterator = mfoods.iterator();
        Food food;
        while(foodIterator.hasNext()){
            food = foodIterator.next();
            ret_food_map.put(food.getFoodID(),food);
        }
        return ret_food_map;
    }

    public String getFoodSequential(List<Food> foodResult) {
        System.out.println("-----------");
        System.out.println("getFoodSequential()");
        System.out.println("-----------");
        Food food;
        String myID,myFoodName,myRecipe;
        Boolean myFavourite;
        myID = EOF;
        myFoodName = EOF;

        result = null;
        try {
            cmdString = "Select * from Foods";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: "+myID);
                myFoodName = rs5.getString("FoodName");
                System.out.println("get name: "+myFoodName);
                myRecipe = rs5.getString("Recipe");
                System.out.println("get recipe: "+myRecipe);
                myFavourite = rs5.getBoolean("Favourite");
                System.out.println("get favourite: "+myFavourite);
                food = new Food(myID,myFoodName,myRecipe,myFavourite);
                foodResult.add(food);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    public String getFavouriteFoodSequential(List<Food> foodResult) {
        System.out.println("-----------");
        System.out.println("getFavouriteFoodSequential()");
        System.out.println("-----------");
        Food food;
        String myID,myFoodName,myRecipe;
        Boolean myFavourite;
        myID = EOF;
        myFoodName = EOF;

        result = null;
        try {
            cmdString = "SELECT * from Foods WHERE Favourite = TRUE";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: "+myID);
                myFoodName = rs5.getString("FoodName");
                System.out.println("get name: "+myFoodName);
                myRecipe = rs5.getString("Recipe");
                System.out.println("get recipe: "+myRecipe);
                myFavourite = rs5.getBoolean("Favourite");
                System.out.println("get favourite: "+myFavourite);
                food = new Food(myID,myFoodName,myRecipe,myFavourite);
                foodResult.add(food);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;

    }

    public String getFoodRandom(List<Food> foodResult) {
        System.out.println("-----------");
        System.out.println("getFoodRandom()");
        System.out.println("-----------");
        Food food;
        String myID,myFoodName,myRecipe;
        Boolean myFavourite;
        myID = EOF;
        myFoodName = EOF;

        result = null;
        try {
            cmdString = "SELECT * from Foods ORDER BY RAND() LIMIT 1";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: "+myID);
                myFoodName = rs5.getString("FoodName");
                System.out.println("get name: "+myFoodName);
                myRecipe = rs5.getString("Recipe");
                System.out.println("get recipe: "+myRecipe);
                myFavourite = rs5.getBoolean("Favourite");
                System.out.println("get favourite: "+myFavourite);
                food = new Food(myID,myFoodName,myRecipe,myFavourite);
                foodResult.add(food);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    public String getFoodPreferred(List<Food> foodResult, String preferredCategory) {
        System.out.println("preferred food want: "+ preferredCategory);
        System.out.println("-----------");
        System.out.println("getFoodRandom()");
        System.out.println("-----------");
        Food preferredFood;
        String myID,myFoodName,myRecipe;
        Boolean myFavourite;
        myID = EOF;
        myFoodName = EOF;

        result = null;
        try {
            cmdString = "SELECT * from FOODS as f INNER JOIN FOODSCATEGORY as fc ON ( f.FOODID = fc.FOODID ) INNER JOIN CATEGORYS as c ON (fc.CATEGORYID = c.CATEGORYID) WHERE c.CATEGORYNAME = '"+preferredCategory+"'";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: "+myID);
                myFoodName = rs5.getString("FoodName");
                System.out.println("get name: "+myFoodName);
                myRecipe = rs5.getString("Recipe");
                System.out.println("get recipe: "+myRecipe);
                myFavourite = rs5.getBoolean("Favourite");
                System.out.println("get favourite: "+myFavourite);
                preferredFood = new Food(myID,myFoodName,myRecipe,myFavourite);
                foodResult.add(preferredFood);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    public Food getFoodFromID(String foodID){

        System.out.println("-----------");
        System.out.println("getFoodFromId: "+foodID );
        System.out.println("-----------");
        Food foodByID = null;
        String myID,myFoodName,myRecipe;
        Boolean myFavourite;
        myID = EOF;
        myFoodName = EOF;

        result = null;
        try {
            cmdString = "SELECT * from FOODS  WHERE FOODID = '"+foodID+"'";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: "+myID);
                myFoodName = rs5.getString("FoodName");
                System.out.println("get name: "+myFoodName);
                myRecipe = rs5.getString("Recipe");
                System.out.println("get recipe: "+myRecipe);
                myFavourite = rs5.getBoolean("Favourite");
                System.out.println("get favourite: "+myFavourite);
                foodByID = new Food(myID,myFoodName,myRecipe,myFavourite);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return foodByID;
    }
    @Override
    public String setFoodToFavourite(String FoodID, boolean favourite){
        String values;
        String where;

        result = null;
        try
        {
            cmdString = "Update Foods Set Favourite = '" +favourite +"' where Foods.foodID = '"+FoodID+"'";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }
    public int getFoodTableRow(){
        System.out.println("-----------");
        System.out.println("get food table row" );
        System.out.println("-----------");
        result = null;
        int myRow = 0;
        try
        {
            cmdString = "Select * from Foods";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myRow ++;
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return myRow;
    }



    public int getIDByFood(Food food) {
        System.out.println("-----------");
        System.out.println("get id by food" );
        System.out.println("-----------");
        result = null;
        int myID = -1;
        try
        {
            cmdString = "Select * from Foods where Foods.foodname = '"+food.getFoodName()+"' and Foods.RECIPE = '"+food.getRecipeLink()+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("FoodID");
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return myID;
    }

    @Override
    public int getCategoryIDbyName(String categoryName) {
        System.out.println("-----------");
        System.out.println("get categoryID by category name: "+categoryName );
        System.out.println("-----------");
        result = null;
        int myID = -1;
        try
        {
            cmdString = "Select * from Categorys where Categorys.categoryname = '"+categoryName+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("CATEGORYID");
            }
            rs5.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        System.out.println("get: "+myID);
        return myID;
    }


    public String addFoodCategory(int foodID, int categoryID) {
        String values;

        result = null;
        try
        {
            cmdString ="INSERT INTO FOODSCATEGORY VALUES("+ foodID+","+ categoryID+")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String addFood(Food addFood){
        String values;

        result = null;
        try
        {
            cmdString = "Insert into Foods Values("+Integer.parseInt(addFood.getFoodID()) +", '"+addFood.getFoodName()+"','"+addFood.getRecipeLink()+"',"+addFood.getFavourite()+")";

            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();
        System.out.println(result);

        return result;
    }
    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }
}
