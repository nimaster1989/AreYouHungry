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
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;

public class DataAccessObject implements DataAccess{
    private Statement st1,st2,st3;
    private Connection c1;
    private ResultSet rs2,rs3,rs4,rs5;

    private String dbName;
    private String dbType;

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
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        }
        catch (Exception e){
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close(){
        try{
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

    public String getUserSequential(List<User> userResult){
        User user;
        int myUserID;
        String myUserName;
        myUserID = -1;
        myUserName= EOF;
        result = null;

        try{
            cmdString = "SELECT * FROM USERS";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myUserID = rs3.getInt("USERID");
                myUserName = rs3.getString("USERNAME");
                user = new User(myUserID,myUserName);
                userResult.add(user);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return result;
    }

    public User getUser(int userID){
        User userByID = null;
        int myID;
        String myUserName;
        myID = -1;
        myUserName= EOF;
        result = null;

        try{
            cmdString = "SELECT * FROM USERS  WHERE USERID = '"+userID+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("USERID");
                myUserName = rs5.getString("USERNAME");
                userByID = new User(userID,myUserName);
            }
            rs5.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return userByID;
    }

    public String getFoodSequential(List<Food> foodResult){
        Food food;
        int myFoodID,myPrepTime,myPortionSize;
        String myFoodName=EOF,myFlavour=EOF,myDifficulty=EOF,myEthnicity=EOF;
        result = null;

        try{
            cmdString = "SELECT DISTINCT FOODS.* FROM FOODS,FOODINGREDIENT,FOODDIRECTION WHERE FOODS.FOODID = FOODDIRECTION.FOODID AND FOODS.FOODID = FOODINGREDIENT.FOODID";
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
            while (rs5.next()){
                myID = rs5.getString("FOODID");
                if(!currentID.equals(myID)){
                    /* Checking if there is variables set to add to a food. */
                    if(!currentID.equals("-1")){
                        Food foundFood = new Food(Integer.valueOf(currentID), myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
                        foodResult.add(foundFood);
                        ingredients = new ArrayList<>();
                        directions = new ArrayList<>();
                    }
                    myID = rs5.getString("FOODID");
                    myFoodName = rs5.getString("FOODNAME");
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

                }
                else{
                    /* Checking if this result sets ingredient has been added or not. */
                    myIngredientName = rs5.getString("INGREDIENTNAME");
                    myIngredientId = rs5.getString("INGREDIENTID");
                    myIngredientMeasure = rs5.getString("INGREDIENTMEASUREMENT");
                    Ingredient newIngredient = new Ingredient(Integer.valueOf(myIngredientId), myIngredientName, myIngredientMeasure);
                    if (!ingredients.contains(newIngredient)){
                        ingredients.add(newIngredient);
                    }
                    /* Checking if this result sets direction has been added or not. */
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
            /* Adding last food returned to the query. */
            Food foundFood = new Food(Integer.valueOf(myID), myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
            foodResult.add(foundFood);
            rs5.close();
        }catch(Exception e){
            processSQLError(e);
        }
    }

    public String getFoodRandom(List<Food> foodResult){
        result = null;
        cmdString = "SELECT * FROM FOODS AS f INNER JOIN FOODINGREDIENT AS fi ON (f.FOODID = fi.FOODID) INNER JOIN INGREDIENT AS i ON(fi.INGREDIENTID = i.INGREDIENTID) INNER JOIN FOODDIRECTION AS fd ON (f.FOODID = fd.FOODID) INNER JOIN DIRECTION AS d ON(fd.DIRECTIONID = d.DIRECTIONID) ORDER BY RAND() LIMIT 1";

        fillFoodObject(cmdString,foodResult);

        return result;
    }
    
    public Food getFoodFromID(String foodID){
        Food foodByID = null;
        result = null;

        try{
            cmdString = "SELECT * FROM FOODS WHERE FOODS.FOODID = '"+foodID+"'";
            rs3 = st3.executeQuery(cmdString);
            while (rs3.next()){
                int myFoodID = rs3.getInt("FOODID");
                String myFoodName = rs3.getString("FOODNAME");
                int myPortionSize = rs3.getInt("PORTIONSIZE");
                int myPrepTime = rs3.getInt("PREPTIME");
                String myFlavour = rs3.getString("FLAVOUR");
                String myDifficulty = rs3.getString("DIFFICULTY");
                String myEthnicity = rs3.getString("ETHNICITY");
                foodByID = new Food(myFoodID,myFoodName,myPortionSize,myPrepTime,myFlavour,myDifficulty,myEthnicity);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return foodByID;
    }

    public int getFoodTableRow(){
        int myRow = 0;
        result = null;

        try{
            cmdString = "SELECT * FROM FOODS";
            rs5 = st3.executeQuery(cmdString);
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
            cmdString = "SELECT * FROM QUESTIONS";
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
            cmdString = "SELECT * FROM QUESTIONS";
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

    public User getDefault(){
        User myUser = null;
        int myUserID;
        String myUsername;
        myUserID = 0;
        myUsername = null;
        result = null;

        try{
            cmdString = "SELECT * FROM USERS WHERE USERID = 1";
            rs4 = st2.executeQuery(cmdString);
            while (rs4.next()){
                myUserID = rs4.getInt("USERID");
                myUsername = rs4.getString("USERNAME");
                myUser = new User(myUserID,myUsername);
            }
            rs4.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return myUser;
    }

    public String getFavouriteFoodByUserSequential(User user, List<Food> foodResult){
        Food food;
        int myFoodID,myPrepTime,myPortionSize;
        String myFoodName=EOF,myFlavour=EOF,myDifficulty=EOF,myEthnicity=EOF;
        result = null;

        try{
            cmdString = "SELECT DISTINCT FOODS.* FROM FOODS,FOODINGREDIENT,FOODDIRECTION,USERSFAVOURITE WHERE FOODS.FOODID = FOODDIRECTION.FOODID AND FOODS.FOODID = FOODINGREDIENT.FOODID AND FOODS.FOODID = USERSFAVOURITE.FOODID AND USERSFAVOURITE.USERID = '"+user.getUserID()+"'";
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

    public String setFoodToFavouriteByUser(User user, String curr_id, boolean b){
        String values;
        cmdString = "";
        result = null;

        try{
            if(b){
                cmdString = "INSERT INTO USERSFAVOURITE VALUES(" + user.getUserID() + "," + curr_id + ")";
            }else{
                cmdString = "DELETE FROM USERSFAVOURITE WHERE USERID = '"+user.getUserID()+"' AND FOODID = '"+curr_id+"'";
            }
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e){
            result = processSQLError(e);
        }

        return result;
    }

    public boolean getFoodFavByUser(User user, Food food){
        Boolean ret = false;

        try{
            cmdString = "SELECT * FROM USERSFAVOURITE WHERE USERID = '"+user.getUserID()+"' AND FOODID = '"+food.getFoodID()+"'";
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

    public int getCategoryIDbyName(String categoryName){
        int myID = -1;
        result = null;

        try{
            cmdString = "SELECT * FROM CATEGORIES WHERE CATEGORIES.CATEGORYNAME = '"+categoryName+"'";
            rs5 = st3.executeQuery(cmdString);
            while (rs5.next()){
                myID = rs5.getInt("CATEGORYID");
            }
            rs5.close();
        }
        catch (Exception e){
            result = processSQLError(e);
        }

        return myID;
    }

    public FoodCategory addFoodCategory(int foodID, int categoryID){
        String values;
        FoodCategory addedFoodCategory = null;
        result = null;

        try{
            if(foodID >= 1 && categoryID >=1){
                cmdString = "INSERT INTO FOODSCATEGORY VALUES(" + foodID + "," + categoryID + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
                addedFoodCategory = new FoodCategory(foodID, categoryID);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }

        if(result == null){
            return addedFoodCategory;
        }else{
            return null;
        }

    }


    public void deleteFoodCategory(int foodID, int categoryID){
        String values;
        cmdString = "";
        result = null;

        try{
            cmdString = "DELETE FROM FOODSCATEGORY WHERE FOODID = '"+foodID+"' AND CATEGORYID = '"+categoryID+"'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e){
            result = processSQLError(e);
        }
    }

    public void deleteUser(int userID){
        String values;
        cmdString = "";
        result = null;

        try{
            cmdString = "DELETE FROM USERS WHERE USERID = '"+userID+"'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e){
            result = processSQLError(e);
        }
    }

    @Override
    public int getIngredientRow(){
        int count = 0;
        result = null;

        try{
            cmdString = "SELECT * FROM INGREDIENT";
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
    public String addFoodIngredient(int foodid, int ingredientid){
        String values;
        result = null;

        try{
            if(foodid >= 1 && ingredientid >=1){
                cmdString = "INSERT INTO FOODINGREDIENT VALUES(" + foodid + "," + ingredientid + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    @Override
    public String addNewIngredient(Ingredient newIngredient){
        String values;
        result = null;

        try{
            if(newIngredient.getIngredientID() >=1){
                cmdString = "INSERT INTO INGREDIENT VALUES('"+newIngredient.getIngredientID()+"','"+newIngredient.getIngredientName()+"','"+newIngredient.getMeasurement()+"')";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    @Override
    public int getDirectionRow(){
        int count = 0;
        result = null;

        try{
            cmdString = "SELECT * FROM DIRECTION";
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
    public String addNewDirection(Direction newDirection){
        String values;
        result = null;

        try{
            if(newDirection.getDirectionID() >=1){
                cmdString = "INSERT INTO DIRECTION VALUES('"+newDirection.getDirectionID()+"','"+newDirection.getDirectionDescription()+"','"+newDirection.getStepNumber()+"')";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    @Override
    public String addFoodDirection(int foodid, int directionid){
        String values;
        result = null;

        try{
            if(foodid >= 1 && directionid >=1){
                cmdString = "INSERT INTO FOODDIRECTION VALUES(" + foodid + "," + directionid + ")";
                updateCount = st1.executeUpdate(cmdString);
                result = checkWarning(st1, updateCount);
            }
        }
        catch (Exception e){
            result = processSQLError(e);
        }
        return result;
    }

    @Override
    public String getIngredientSequential(List<Ingredient> ingredients){
        Ingredient ingredient;
        int myID;
        String myName= EOF, myMeasure = EOF;
        result = null;

        try{
            cmdString = "SELECT * FROM INGREDIENT";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myID = rs3.getInt("INGREDIENTID");
                myName = rs3.getString("INGREDIENTNAME");
                myMeasure = rs3.getString("INGREDIENTMEASUREMENT");
                ingredient = new Ingredient(myID,myName,myMeasure);
                ingredients.add(ingredient);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return result;
    }

    @Override
    public String searchFoodByCriteriaLists(ArrayList<String> prepTimeCriterias, ArrayList<String> flavourCriterias, ArrayList<String> difficutlyCriterias, ArrayList<String> ethnicityCriterias, ArrayList<Food> foodResult){
        String result = null;
        String cmd="SELECT * FROM FOODS WHERE ";
        int size1 = prepTimeCriterias.size();
        int size2 = flavourCriterias.size();
        int size3 = difficutlyCriterias.size();
        int size4 = ethnicityCriterias.size();

        for(String maxPrepTime:prepTimeCriterias){
            String minPrepTime = String.valueOf(Integer.parseInt(maxPrepTime)-15);
            if(Integer.parseInt(maxPrepTime) == 100){
                cmd += "FOODS.PREPTIME > 60 OR";
            }
            else {
                cmd += "(FOODS.PREPTIME <= " + maxPrepTime + "AND FOODS.PREPTIME >=" + minPrepTime + ") OR ";
            }
            System.out.println("time:"+minPrepTime+" ~ "+maxPrepTime);
        }
        if(size1 > 0){
            cmd = cmd.substring(0, cmd.length() - 3);
            if(size2 > 0 || size3 >0 || size4 >0 ) cmd += " AND ";
        }

        for(String flavour:flavourCriterias){
            if(flavour.equals("OtherFlavour")){
                cmd += "FOODS.FLAVOUR != 'Spicy' AND FOODS.FLAVOUR != 'Sweet' AND FOODS.FLAVOUR != 'Fresh' AND FOODS.FLAVOUR != 'Savoury' OR ";
            }else{
                cmd += "FOODS.FLAVOUR = '" + flavour + "' OR ";
            }
        }
        if(size2 > 0){
            cmd = cmd.substring(0, cmd.length() - 3);
            if(size3 > 0 || size4 > 0) cmd+= " AND ";
        }

        for(String difficuly:difficutlyCriterias){
            cmd+="FOODS.DIFFICULTY = '"+difficuly+"' OR ";
        }
        if(size3 > 0){
            cmd = cmd.substring(0, cmd.length() - 3);
            if(size4 > 0) cmd+=" AND ";
        }

        for(String ethnicity:ethnicityCriterias){
            if(ethnicity.equals("OtherEthnicity")){
                cmd += "FOODS.ETHNICITY != 'American' AND FOODS.ETHNICITY != 'Greek' AND FOODS.ETHNICITY != 'Italian' AND FOODS.ETHNICITY != 'Chinese' OR ";
            }
            else {
                cmd += "FOODS.ETHNICITY = '" + ethnicity + "' OR ";
            }
        }
        if(size4 >0 )cmd = cmd.substring(0, cmd.length() - 3);

        if(cmd != "SELECT * FROM FOODS WHERE "){
            Food food;
            int myFoodID, myPrepTime, myPortionSize;
            String myFoodName = EOF, myFlavour = EOF, myDifficulty = EOF, myEthnicity = EOF;

            try{
                rs3 = st2.executeQuery(cmd);
                while (rs3.next()){
                    myFoodID = rs3.getInt("FOODID");
                    myFoodName = rs3.getString("FOODNAME");
                    myPortionSize = rs3.getInt("PORTIONSIZE");
                    myPrepTime = rs3.getInt("PREPTIME");
                    myFlavour = rs3.getString("FLAVOUR");
                    myDifficulty = rs3.getString("DIFFICULTY");
                    myEthnicity = rs3.getString("ETHNICITY");
                    food = new Food(myFoodID, myFoodName, myPortionSize, myPrepTime, myFlavour, myDifficulty, myEthnicity);
                    foodResult.add(food);
                }
                rs3.close();
            } catch (Exception e){
                processSQLError(e);
            }
        }
        return null;
    }

    @Override
    public String getFoodSequentialByCategory(String category, ArrayList<Food> foodCategoryResult){
        result = null;
        Food food;
        try{
            cmdString = "select FOODSCATEGORY.FOODID from FOODSCATEGORY,CATEGORYS  where FOODSCATEGORY.CATEGORYID = CATEGORYS.CATEGORYID and CATEGORYS.CATEGORYNAME = '"+category+"'";
            rs3 = st2.executeQuery(cmdString);
            while(rs3.next()){
                int foodID = rs3.getInt("FOODID");
                food = getFoodFromID(String.valueOf(foodID));
                foodCategoryResult.add(food);
            }
        }catch (Exception e){
            result = processSQLError(e);
        }

        return null;

    }

    @Override
    public String getFoodsSequentialByIngredient(String ingredient, ArrayList<Food> foodIngredientResult){
        result = null;
        Food food;
        try{
            cmdString = "SELECT FOODID from FOODINGREDIENT where FOODINGREDIENT.INGREDIENTID = '"+ingredient+"'";
            rs3 = st2.executeQuery(cmdString);
            while(rs3.next()){
                int foodID = rs3.getInt("FOODID");
                System.out.println("get foodid:"+ foodID);
                food = getFoodFromID(String.valueOf(foodID));
                foodIngredientResult.add(food);
            }
        }catch (Exception e){
            result = processSQLError(e);
        }

        return null;

    }

    public int getTotalUser(){
        int count = 0;
        result = null;

        try{
            cmdString = "SELECT * FROM USERS";
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

    public User setNewUser(int id,String username){
        User setUser = null;
        result = null;

        try{
            if(id >=1 && username!=null){
                cmdString = "INSERT INTO USERS VALUES(" + id + ", '" + username + "')";
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

    public String getIngredientByFood(Food food, List<Ingredient> ingredients){
        Ingredient ingredient;
        int myIngredientID;
        String myIngredientName,myIngredientMeasurement;
        result = null;

        try{
            cmdString = "SELECT INGREDIENT.* FROM INGREDIENT,FOODINGREDIENT WHERE FOODINGREDIENT.INGREDIENTID = INGREDIENT.INGREDIENTID AND FOODINGREDIENT.FOODID = '"+food.getFoodID()+"'";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myIngredientID = rs3.getInt("INGREDIENTID");
                myIngredientName = rs3.getString("INGREDIENTNAME");
                myIngredientMeasurement = rs3.getString("INGREDIENTMEASUREMENT");
                ingredient = new Ingredient(myIngredientID,myIngredientName,myIngredientMeasurement);
                ingredients.add(ingredient);
            }
            rs3.close();
        }catch (Exception e){
            processSQLError(e);
        }

        return result;
    }

    public String getDirectionByFood(Food food, List<Direction> directions){
        Direction direction;
        int myDirectionId,myDirecitonSteps;
        String nyDirecitionDescription;
        result = null;

        try{
            cmdString = "SELECT DIRECTION.* FROM DIRECTION,FOODDIRECTION WHERE FOODDIRECTION.DIRECTIONID = DIRECTION.DIRECTIONID AND FOODDIRECTION.FOODID = '"+food.getFoodID()+"'";
            rs3 = st2.executeQuery(cmdString);
            while (rs3.next()){
                myDirectionId = rs3.getInt("DIRECTIONID");
                nyDirecitionDescription = rs3.getString("DIRECTIONDESCRIPTION");
                myDirecitonSteps = rs3.getInt("DIRECTIONSTEPNUMBER");
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
            cmdString = "INSERT INTO FOODS VALUES('"+addFood.getFoodID()+"','"+addFood.getFoodName()+"','"+addFood.getPortionSize()+"','"+addFood.getPrepTime()+"','"+addFood.getFlavour()+"','"+addFood.getDifficulty()+"','"+addFood.getEthnicity()+"')";
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
