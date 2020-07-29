package comp3350.Group2.areyouhungry.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.objects.FC;

public class DataAccessObject implements DataAccess{
    private Statement st1,st2,st3;
    private Connection c1;
    private ResultSet rs2,rs3,rs4,rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Food> foods;

    /* CmdString is for sql string. */
    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName){
        this.dbName = dbName;
    }

    public void open(String dbPath){
        String url;
        try{
            /* Setup for HSQL */
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; /* Stored on disk mode. */
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e){
            processSQLError(e);
            System.out.println("open fail,"+ e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close(){
        try{	/* Commit all changes to the database. */
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    public Map getFoodMap(Map ret_food_map){
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
    @Override
    public String getUserSequential(List<User> userResult){
        System.out.println("-----------");
        System.out.println("getUserSequential()");
        System.out.println("-----------");
        User user;
        int myUserID;
        String myUserName;
        myUserID = -1;
        myUserName= EOF;

        result = null;
        try{
            cmdString = "Select * from USERS";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myUserID = rs3.getInt("USERID");
                System.out.println("get ID: "+myUserID);
                myUserName = rs3.getString("USERNAME");
                System.out.println("get name: "+myUserName);
                user = new User(myUserID,myUserName);
                userResult.add(user);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    @Override
    public User getUser(int userID){
        System.out.println("-----------");
        System.out.println("getUserFromId: "+userID );
        System.out.println("-----------");
        User userByID = null;
        int myID;
        String myUserName;
        myID = -1;
        myUserName= EOF;
        result = null;
        try{
            cmdString = "SELECT * from USERS  WHERE USERID = '"+userID+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("USERID");
                System.out.println("get ID: "+myID);
                myUserName = rs5.getString("USERNAME");
                System.out.println("get name: "+myUserName);
                userByID = new User(userID,myUserName);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return userByID;
    }

    public String getFoodSequential(List<Food> foodResult){
        System.out.println("-----------");
        System.out.println("getFoodSequential()");
        System.out.println("-----------");
        Food food;
        int myFoodID,myPrepTime,myPortionSize;
        String myFoodName=EOF,myFlavour=EOF,myDifficulty=EOF,myEthnicity=EOF;

        result = null;
        try{
            cmdString = "SELECT DISTINCT FOODS.* from FOODS,FOODINGREDIENT,FOODDIRECTION where FOODS.FOODID = FOODDIRECTION.FOODID and FOODS.FOODID = FOODINGREDIENT.FOODID";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myFoodID = rs3.getInt("FOODID");
                myFoodName = rs3.getString("FOODNAME");
                myPortionSize = rs3.getInt("PORTIONSIZE");
                myPrepTime = rs3.getInt("PREPTIME");
                myFlavour = rs3.getString("FLAVOUR");
                myDifficulty = rs3.getString("DIFFICULTY");
                myEthnicity = rs3.getString("ETHNICITY");
                food = new Food(myFoodID,myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
                foodResult.add(food);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

//    public String getFoodSequential(List<Food> foodResult){
//        System.out.println("-----------");
//        System.out.println("getFoodSequential()");
//        System.out.println("-----------");
//        result = null;
//        cmdString = "SELECT * from FOODS as f INNER JOIN FOODINGREDIENT as fi ON (f.FOODID = fi.FOODID) INNER JOIN INGREDIENT as i ON(fi.INGREDIENTID = i.INGREDIENTID) INNER JOIN FOODDIRECTION as fd ON (f.FOODID = fd.FOODID) INNER JOIN DIRECTION as d ON(fd.DIRECTIONID = d.DIRECTIONID) ORDER BY FoodID";
//        fillFoodObject(cmdString,foodResult);
//        return result;
//    }

    private void fillFoodObject(String cmdString,List<Food> foodResult){
        String myID="",myFoodName="",myFlavour="",myDifficulty="",myEthnicity="",myIngredientName="",myIngredientMeasure="",myIngredientId="",myDirectionID="",myDirectionDesc="",myDirectionStep="";
        String currentID = "-1";
        String currentIngredientID = "-1";
        String currentDirectionID = "-1";
        int myPortionSize=0,myPrepTime=0;
        Boolean myFavourite=false;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Direction> directions = new ArrayList<>();
        try{
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null) System.out.println("return state 5 is null");
            while (rs5.next()){
                myID = rs5.getString("FoodID");
                System.out.println("get ID: " + myID);
                if(!currentID.equals(myID)){
                    //Checking if there is variables set to add to a food
                    if(!currentID.equals("-1")){
                        Food foundFood = new Food(Integer.valueOf(currentID), myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
                        foodResult.add(foundFood);
                        ingredients = new ArrayList<>();
                        directions = new ArrayList<>();
                    }
                    myID = rs5.getString("FoodID");
                    myFoodName = rs5.getString("FoodName");
                    myFavourite = rs5.getBoolean("Favourite");
                    myPortionSize = rs5.getInt("PORTIONSIZE");
                    myPrepTime = rs5.getInt("PREPTIME");
                    myFlavour = rs5.getString("FLAVOUR");
                    myDifficulty = rs5.getString("DIFFICULTY");
                    myEthnicity = rs5.getString("ETHNICITY");
                    myIngredientName = rs5.getString("INGREDIENTNAME");
                    myIngredientId= rs5.getString("INGREDIENTID");
                    myIngredientMeasure= rs5.getString("INGREDIENTMEASUREMENT");
                    Ingredient newIngredient = new Ingredient(Integer.valueOf(myIngredientId),myIngredientName,myIngredientMeasure);
                    ingredients.add(newIngredient);

                    //Set variables for next food item
                }
                else{
                    //Checking if this result sets ingredient has been added or not
                    myIngredientName = rs5.getString("INGREDIENTNAME");
                    myIngredientId = rs5.getString("INGREDIENTID");
                    myIngredientMeasure = rs5.getString("INGREDIENTMEASUREMENT");
                    Ingredient newIngredient = new Ingredient(Integer.valueOf(myIngredientId), myIngredientName, myIngredientMeasure);
                    if (!ingredients.contains(newIngredient)){
                        ingredients.add(newIngredient);
                    }
                    //Checking if this result sets direction has been added or not
                    myDirectionID = rs5.getString("DIRECTIONID");
                    myDirectionDesc = rs5.getString("DIRECTIONDESCRIPTION");
                    myDirectionStep = rs5.getString("DIRECTIONSTEPNUMBER");
                    Direction newDirection = new Direction(Integer.valueOf(myDirectionID), myDirectionDesc,Integer.valueOf( myDirectionStep));
                    if(!directions.contains(newDirection)){
                        directions.add(newDirection);
                    }
                }
                currentID = myID;
            }
            //Adding the last food returned from the query
            Food foundFood = new Food(Integer.valueOf(myID), myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
            foodResult.add(foundFood);
            rs5.close();
        }catch(Exception e){
            processSQLError(e);
        }

    }


//    public String getFavouriteFoodSequential(List<Food> foodResult){
//
//        System.out.println("-----------");
//        System.out.println("getFavouriteFoodSequential()");
//        System.out.println("-----------");
//        Food food;
//        int myFoodID,myPrepTime,myPortionSize;
//        String myFoodName=EOF,myFlavour=EOF,myDifficulty=EOF,myEthnicity=EOF;
//
//        result = null;
//        try{
//            cmdString = "SELECT DISTINCT FOODS.* from FOODS,FOODINGREDIENT,FOODDIRECTION where FOODS.FOODID = FOODDIRECTION.FOODID and FOODS.FOODID = FOODINGREDIENT.FOODID";
//            rs3 = st2.executeQuery(cmdString);
//            while (rs3.next()){
//                myFoodID = rs3.getInt("FOODID");
//                System.out.println("get ID: "+myFoodID);
//                myFoodName = rs3.getString("FOODNAME");
//                System.out.println("get food name: "+myFoodName);
//                myPortionSize = rs3.getInt("PORTIONSIZE");
//                System.out.println("get PORTIONSIZE: "+myPortionSize);
//                myPrepTime = rs3.getInt("PREPTIME");
//                System.out.println("get PREPTIME: "+myPrepTime);
//                myFlavour = rs3.getString("FLAVOUR");
//                System.out.println("get FLAVOUR: "+myFlavour);
//                myDifficulty = rs3.getString("DIFFICULTY");
//                System.out.println("get DIFFICULTY: "+myDifficulty);
//                myEthnicity = rs3.getString("ETHNICITY");
//                System.out.println("get ETHNICITY: "+myEthnicity);
//                food = new Food(myFoodID,myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
//                foodResult.add(food);
//            }
//            rs3.close();
//        }catch (Exception e){
//            processSQLError(e);
//        }
//        return result;
//    }

    public String getFoodRandom(List<Food> foodResult){
        System.out.println("-----------");
        System.out.println("getFoodRandom()");
        System.out.println("-----------");
        result = null;
        cmdString = "SELECT * from FOODS as f INNER JOIN FOODINGREDIENT as fi ON (f.FOODID = fi.FOODID) INNER JOIN INGREDIENT as i ON(fi.INGREDIENTID = i.INGREDIENTID) INNER JOIN FOODDIRECTION as fd ON (f.FOODID = fd.FOODID) INNER JOIN DIRECTION as d ON(fd.DIRECTIONID = d.DIRECTIONID) ORDER BY RAND() LIMIT 1";
        fillFoodObject(cmdString,foodResult);
        return result;
    }

    public String getFoodPreferred(List<Food> foodResult, String preferredCategory){
        System.out.println("preferred food want: "+ preferredCategory);
        System.out.println("-----------");
        System.out.println("getFoodRandom()");
        System.out.println("-----------");
        result = null;
        cmdString = "SELECT * from FOODS as f INNER JOIN FOODSCATEGORY as fc ON ( f.FOODID = fc.FOODID ) INNER JOIN CATEGORYS as c ON (fc.CATEGORYID = c.CATEGORYID) WHERE c.CATEGORYNAME = '"+preferredCategory+"'";
        fillFoodObject(cmdString,foodResult);
        return result;
    }

    public Food getFoodFromID(String foodID){
        System.out.println("-----------");
        System.out.println("getFoodFromId: "+foodID );
        System.out.println("-----------");
        Food foodByID = null;
        result = null;
        try{
            cmdString = "SELECT * from FOODS WHERE FOODS.FOODID = '"+foodID+"'";
            rs3 = st3.executeQuery(cmdString);
            while (rs3.next()){
                int myFoodID = rs3.getInt("FOODID");
                System.out.println("get ID: "+myFoodID);
                String myFoodName = rs3.getString("FOODNAME");
                System.out.println("get food name: "+myFoodName);
                int myPortionSize = rs3.getInt("PORTIONSIZE");
                System.out.println("get PORTIONSIZE: "+myPortionSize);
                int myPrepTime = rs3.getInt("PREPTIME");
                System.out.println("get PREPTIME: "+myPrepTime);
                String myFlavour = rs3.getString("FLAVOUR");
                System.out.println("get FLAVOUR: "+myFlavour);
                String myDifficulty = rs3.getString("DIFFICULTY");
                System.out.println("get DIFFICULTY: "+myDifficulty);
                String myEthnicity = rs3.getString("ETHNICITY");
                System.out.println("get ETHNICITY: "+myEthnicity);
                foodByID = new Food(myFoodID,myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return foodByID;
    }

//    @Override
//    public String setFoodToFavourite(String FoodID, boolean favourite){
//        String values;
//        String where;
//
//        result = null;
//        try{
//            cmdString = "Update Foods Set Favourite = '" +favourite +"' where Foods.foodID = '"+FoodID+"'";
//            updateCount = st1.executeUpdate(cmdString);
//            result = checkWarning(st1, updateCount);
//        } catch (Exception e){
//            result = processSQLError(e);
//        }
//        return result;
//    }

    public int getFoodTableRow(){
        System.out.println("-----------");
        System.out.println("get food table row" );
        System.out.println("-----------");
        result = null;
        int myRow = 0;
        try{
            cmdString = "Select * from Foods";
            rs5 = st3.executeQuery(cmdString);
            if (rs5 == null){
                System.out.println("return state 5 is null");
            }
            while (rs5.next()){
                myRow ++;
            }
            rs5.close();
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return myRow;
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        Question question;
        result = null;
        try{
            cmdString = "Select * from Questions";
            rs5 = st3.executeQuery(cmdString);
            while(rs5.next()){
                question = new Question();
                question.setQuestion(rs5.getString("QUESTION"));
                question.setOption1(rs5.getString("OPT1"));
                question.setOption2(rs5.getString("OPT2"));
                question.setOption3(rs5.getString("OPT3"));
                question.setOption4(rs5.getString("OPT4"));
                questionList.add(question);
            }
            rs5.close();
        }catch (Exception e){
            result = processSQLError(e);
        }
        return questionList;
    }

    public int getTotalQuestions(){
        result = null;
        int count = 0;
        try{
            cmdString = "Select * from Questions";
            rs5 = st3.executeQuery(cmdString);
            while(rs5.next()){
                count++;
            }
            rs5.close();
        }catch (Exception e){
            result = processSQLError(e);
        }
        return count;
    }

    @Override
    public User getDefault(){
        System.out.println("-----------");
        System.out.println("getDefaultUser: ");
        System.out.println("-----------");
        User myUser = null;
        int myUserID;
        String myUsername;
        myUserID = 0;
        myUsername = null;

        result = null;
        try{
            cmdString = "SELECT * from USERS WHERE USERID = 1";
            rs4 = st2.executeQuery(cmdString);
            while (rs4.next()){
                myUserID = rs4.getInt("USERID");
                System.out.println("get ID: "+myUserID);
                myUsername = rs4.getString("USERNAME");
                System.out.println("get name: "+myUsername);
                myUser = new User(myUserID,myUsername);
            }
            rs4.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return myUser;
    }


    public String getFavouriteFoodByUserSequential(User user, List<Food> foodResult){
        System.out.println("-----------");
        System.out.println("getFoodSequential()");
        System.out.println("-----------");
        Food food;
        int myFoodID,myPrepTime,myPortionSize;
        String myFoodName=EOF,myFlavour=EOF,myDifficulty=EOF,myEthnicity=EOF;

        result = null;
        try{
            cmdString = "SELECT DISTINCT FOODS.* from FOODS,FOODINGREDIENT,FOODDIRECTION,USERSFAVOURITE where FOODS.FOODID = FOODDIRECTION.FOODID and FOODS.FOODID = FOODINGREDIENT.FOODID and FOODS.FOODID = USERSFAVOURITE.FOODID and USERSFAVOURITE.USERID = '"+user.getUserID()+"'";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myFoodID = rs3.getInt("FOODID");
                System.out.println("get ID: "+myFoodID);
                myFoodName = rs3.getString("FOODNAME");
                System.out.println("get food name: "+myFoodName);
                myPortionSize = rs3.getInt("PORTIONSIZE");
                System.out.println("get PORTIONSIZE: "+myPortionSize);
                myPrepTime = rs3.getInt("PREPTIME");
                System.out.println("get PREPTIME: "+myPrepTime);
                myFlavour = rs3.getString("FLAVOUR");
                System.out.println("get FLAVOUR: "+myFlavour);
                myDifficulty = rs3.getString("DIFFICULTY");
                System.out.println("get DIFFICULTY: "+myDifficulty);
                myEthnicity = rs3.getString("ETHNICITY");
                System.out.println("get ETHNICITY: "+myEthnicity);
                food = new Food(myFoodID,myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
                foodResult.add(food);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    @Override
    public String setFoodToFavouriteByUser(User user, String curr_id, boolean b){
        System.out.println("set user"+user.getUserName()+" foodid:"+curr_id+"to fav?: "+b);
        String values;
        cmdString = "";
        result = null;
        try{
            if(b){
                cmdString = "INSERT INTO USERSFAVOURITE VALUES(" + user.getUserID() + "," + curr_id + ")";
            }else{
                cmdString = "DELETE FROM USERSFAVOURITE WHERE USERID = '"+user.getUserID()+"' and FOODID = '"+curr_id+"'";
            }
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    @Override
    public boolean getFoodFavByUser(User user, Food food){
        Boolean ret = false;
        try{
            cmdString = "SELECT * FROM USERSFAVOURITE WHERE USERID = '"+user.getUserID()+"' and FOODID = '"+food.getFoodID()+"'";
            rs5 = st3.executeQuery(cmdString);
            if (rs5.next()){
                ret = true;
            }else
                ret = false;
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return ret;
    }



    public int getIDByFood(Food food){
        System.out.println("-----------");
        System.out.println("get id by food" );
        System.out.println("-----------");
        result = null;
        int myID = -1;
        try{
            cmdString = "Select * from Foods where Foods.foodname = '"+food.getFoodName();
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("FoodID");
            }
            rs5.close();
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return myID;
    }

    @Override
    public int getCategoryIDbyName(String categoryName){
        System.out.println("-----------");
        System.out.println("get categoryID by category name: "+categoryName );
        System.out.println("-----------");
        result = null;
        int myID = -1;
        try{
            cmdString = "Select * from Categorys where Categorys.categoryname = '"+categoryName+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("CATEGORYID");
            }
            rs5.close();
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        System.out.println("get: "+myID);
        return myID;
    }

    public FC addFoodCategory(int foodID, int categoryID){
        String values;

        FC addedFoodCategory = null;
        String result = null;
        try{
            if(foodID >= 1 && categoryID >=1) {
                cmdString = "INSERT INTO FOODSCATEGORY VALUES(" + foodID + "," + categoryID + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                addedFoodCategory = new FC(foodID, categoryID);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return addedFoodCategory;
    }

    @Override
    public User setNewUser(int id,String username){
        User setUser = null;
        System.out.println("in add new user");
        result = null;

        try{
            if(id >=1 && username!=null){
                cmdString = "Insert into USERS Values(" + id + ", '" + username + "')";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                setUser = new User(id, username);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return setUser;
    }

    @Override
    public String getIngredientByFood(Food food, List<Ingredient> ingredients){
        System.out.println("-----------");
        System.out.println("getIngrdientByFood()");
        System.out.println("-----------");
        Ingredient ingredient;
        int myIngredientID;
        String myIngredientName,myIngredientMeasurement;

        result = null;
        try{
            cmdString = "select INGREDIENT.* from INGREDIENT,FOODINGREDIENT where FOODINGREDIENT.INGREDIENTID = INGREDIENT.INGREDIENTID and FOODINGREDIENT.FOODID = '"+food.getFoodID()+"'";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myIngredientID = rs3.getInt("INGREDIENTID");
                System.out.println("get ID: "+myIngredientID);
                myIngredientName = rs3.getString("INGREDIENTNAME");
                System.out.println("get ingredient name: "+myIngredientName);
                myIngredientMeasurement = rs3.getString("INGREDIENTMEASUREMENT");
                System.out.println("get mingredientMeasurement: "+myIngredientMeasurement);
                ingredient = new Ingredient(myIngredientID,myIngredientName,myIngredientMeasurement);
                ingredients.add(ingredient);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    @Override
    public String getDirectionByFood(Food food, List<Direction> directions){
        System.out.println("-----------");
        System.out.println("getDirectionByFood()");
        System.out.println("-----------");
        Direction direction;
        int myDirectionId,myDirecitonSteps;
        String nyDirecitionDescription;

        result = null;
        try{
            cmdString = "select DIRECTION.* from DIRECTION,FOODDIRECTION where FOODDIRECTION.DIRECTIONID = DIRECTION.DIRECTIONID and FOODDIRECTION.FOODID = '"+food.getFoodID()+"'";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myDirectionId = rs3.getInt("DIRECTIONID");
                System.out.println("get ID: "+myDirectionId);
                nyDirecitionDescription = rs3.getString("DIRECTIONDESCRIPTION");
                System.out.println("get nyDirecitionDescription : "+nyDirecitionDescription);
                myDirecitonSteps = rs3.getInt("DIRECTIONSTEPNUMBER");
                System.out.println("get mingredientMeasurement: "+myDirecitonSteps);
                direction = new Direction(myDirectionId,nyDirecitionDescription,myDirecitonSteps);
                directions.add(direction);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }
        return result;
    }

    public String addFood(Food addFood){
        String values;

        result = null;
        try{
            cmdString = "Insert into Foods Values("+Integer.parseInt(addFood.getFoodID()) +", '"+addFood.getFoodName()+"','"+addFood.getFavourite()+")";//TODO append on new variables here
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    public String processSQLError(Exception e){
        String result = "*** SQL Error: " + e.getMessage();

        e.printStackTrace();
        System.out.println(result);

        return result;
    }

    public String checkWarning(Statement st, int updateCount){
        String result;
        result = null;
        try{
            SQLWarning warning = st.getWarnings();
            if (warning != null){
                result = warning.getMessage();
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        if (updateCount != 1){
            result = "Tuple not inserted correctly.";
        }
        return result;
    }



}
