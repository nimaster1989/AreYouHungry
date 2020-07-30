package comp3350.Group2.areyouhungry.tests.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Categories;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.Food;

import comp3350.Group2.areyouhungry.objects.FoodDirection;
import comp3350.Group2.areyouhungry.objects.FoodIngredient;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;

public class DataAccessStub implements DataAccess{

    private String dbName;
    private String dbType = "stub";

    private ArrayList<Food> foods;
    private ArrayList<FoodCategory> foodCategories;
    private ArrayList<Categories> categoriesList;
    private ArrayList<User> users;
    private ArrayList<Question> questions;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Direction> directions;
    private Map<String,Food> Food_map;
    private ArrayList<FoodIngredient> foodIngredients;
    private ArrayList<FoodDirection> foodDirections;
    //stub map referencing user_favourite table in the HSQLDB database
    private ArrayList<Map<Integer,Integer>>  User_favourite_map_list;

    public DataAccessStub(String dbName){
        this.dbName = dbName;
    }

    public DataAccessStub(){
        this(MainActivity.dbName);
    }


    public void open(String dbPath){
        Food food;
        Categories categories;
        FoodCategory foodCategory;
        User user;
        Question question;
        Ingredient ingredient;
        Direction direction;
        FoodIngredient foodIngredient;
        FoodDirection foodDirection;

        foods = new ArrayList<Food>();
        Food_map = new HashMap<>();
        food = new Food(1, "Fish and Chip",1,10, "Savory", "Easy", "American");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(2, "California Burger",1,20, "Sweet", "Medium", "Australian");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(3, "Pad Thai",3,30, "Spicy", "Hard", "American");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(4, "Japan Ramen",3,10, "Sweet", "Expert", "Japanese");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(5, "Jimgatang",5,20, "Savory", "Medium", "American");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(6, "Ceaser Salad",7,30, "Other", "Hard", "Vietnamese");
        foods.add(food);
        Food_map.put(String.valueOf(food.getFoodID()),food);

        categoriesList = new ArrayList<Categories>();
        categories = new Categories(1,"Meat");
        categoriesList.add(categories);
        categories = new Categories(2,"Grain");
        categoriesList.add(categories);
        categories = new Categories(3,"Vegetable");
        categoriesList.add(categories);

        foodCategories = new ArrayList<FoodCategory>();
        foodCategory = new FoodCategory(1,1);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(1,2);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(2,1);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(2,3);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(3,1);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(3,3);
        foodCategories.add(foodCategory);

        users = new ArrayList<User>();
        user = new User(1,"default user");
        users.add(user);
        user = new User(2,"sample user");
        users.add(user);
        user = new User(3,"new user");
        users.add(user);

        questions = new ArrayList<Question>();
        question = new Question("You are on fear factor and the following items are presented for you to feast on. A blue substance you cant tell is bleach or gatorade, An Ant Hill, Hot Rocks, or a Mystery Box the host insists is much worse.What do you choose?", "Its worth the risk for sweet sweet Gatorade", "Ants are like little chips... right?", "Mmmmm hot rocks go brrrr", "The box cant possibly be worse!");
        questions.add(question);
        question = new Question("Im so hungry i could eat a...", "Horse", "Slightly larger Horse", "Wow thats a big horse", "A horse bred so large for the purpose to stop this dumb statement");
        questions.add(question);
        question = new Question("Which would you rather do?", "Defuse a bomb with 10 seconds left", "Sit in the \"slow\" McDonalds line", "Spend four years getting a degree in theoretical phys-ed", "Play a game of Monopoly");
        questions.add(question);
        question = new Question("You invite someone over saying you will cook for them knowing very well that you infact cant cook. What do you make?", "Everyone likes Lucky Charms!", "A nice bowl of Kraft Dinner", "Something that requires to be cooked to a certain temperature", "The thing the Rat in Ratatouille made");
        questions.add(question);
        question = new Question("Ah yes the classic why did the chicken cross the road dilema, except the chicken is in the airport and is booking a flight to your favourite vacation spot which is...", "The place where the toilet water spins the other way", "Cant have a high amount of covid cases if we dont test land", "Japan c:", "The only country that beat USA in a war");
        questions.add(question);

        ingredients = new ArrayList<Ingredient>();
        ingredient = new Ingredient(1, "Halibut", "256lb");
        ingredients.add(ingredient);
        ingredient = new Ingredient(2, "Salt", "60g");
        ingredients.add(ingredient);
        ingredient = new Ingredient(3, "Wagyu Beef", "57g");
        ingredients.add(ingredient);
        ingredient = new Ingredient(4, "Iceberg Lettuce", "3 pieces");
        ingredients.add(ingredient);
        ingredient = new Ingredient(5, "American Cheese", "1 slice");
        ingredients.add(ingredient);
        ingredient = new Ingredient(6, "Lettuce", "1 head of lettuce");
        ingredients.add(ingredient);
        ingredient = new Ingredient(7, "Noodles", "500grams");
        ingredients.add(ingredient);
        ingredient = new Ingredient(8, "Ramen Noodles", "2 pounds");
        ingredients.add(ingredient);
        ingredient = new Ingredient(9, "Bread", "2 slices");
        ingredients.add(ingredient);

        foodIngredients = new ArrayList<>();
        ArrayList<Ingredient> tempIngredient = new ArrayList<>();
        tempIngredient.add(ingredients.get(0));
        tempIngredient.add(ingredients.get(1));
        foodIngredient = new FoodIngredient(foods.get(0),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();
        tempIngredient.add(ingredients.get(2));
        tempIngredient.add(ingredients.get(3));
        tempIngredient.add(ingredients.get(4));
        foodIngredient = new FoodIngredient(foods.get(1),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();
        tempIngredient.add(ingredients.get(6));
        foodIngredient = new FoodIngredient(foods.get(2),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();
        tempIngredient.add(ingredients.get(7));
        foodIngredient = new FoodIngredient(foods.get(3),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();
        tempIngredient.add(ingredients.get(8));
        foodIngredient = new FoodIngredient(foods.get(4),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();
        tempIngredient.add(ingredients.get(5));
        foodIngredient = new FoodIngredient(foods.get(5),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        directions = new ArrayList<>();
        direction = new Direction(1, "Cook the Halibut in a fire for 7 hours.", 1);
        directions.add(direction);
        direction = new Direction(2, "Add in all of the salt", 2);
        directions.add(direction);
        direction = new Direction(3, "Cook the Wagyu Beef for 10 minutes or until well done", 1);
        directions.add(direction);
        direction = new Direction(4, "Blend the lettuce, and spread it on the beef", 2);
        directions.add(direction);
        direction = new Direction(5, "Add a piece of processed cheese, and serve!", 3);
        directions.add(direction);
        direction = new Direction(6, "Put lettuce in a bowl", 1);
        directions.add(direction);
        direction = new Direction(7, "Fry the noodles", 1);
        directions.add(direction);
        direction = new Direction(8, "Cook the noodles", 1);
        directions.add(direction);
        direction = new Direction(9, "Wrap the bread", 1);
        directions.add(direction);

        foodDirections = new ArrayList<>();
        ArrayList<Direction> tempDirection = new ArrayList<>();
        tempDirection.add(directions.get(0));
        tempDirection.add(directions.get(1));
        foodDirection = new FoodDirection(foods.get(0),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();
        tempDirection.add(directions.get(2));
        tempDirection.add(directions.get(3));
        tempDirection.add(directions.get(4));
        foodDirection = new FoodDirection(foods.get(1),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();
        tempDirection.add(directions.get(6));
        foodDirection = new FoodDirection(foods.get(2),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();
        tempDirection.add(directions.get(7));
        foodDirection = new FoodDirection(foods.get(3),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();
        tempDirection.add(directions.get(8));
        foodDirection = new FoodDirection(foods.get(4),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();
        tempDirection.add(directions.get(5));
        foodDirection = new FoodDirection(foods.get(5),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        User_favourite_map_list = new ArrayList<Map<Integer,Integer>>();
    }


    public void close(){
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    /* Add on function to get the food map for FoodDetail Activity. */
    public Map getFoodMap(Map ret_food_map){
        ret_food_map.putAll(Food_map);
        return null;
    }

    /* This function is from sample project, used to add food to a list that contain
       all foods in the stub database. */
    public String getFoodSequential(List<Food> foodResult){
        foodResult.addAll((foods));
        return null;
    }


    /* This function is used to generate a random food from the stub database
       and add it into the foodresult list. */
    public String getFoodRandom(List<Food> foodResult){
        Random random = new Random();
        foodResult.add(foods.get(random.nextInt(foods.size())));
        return null;
    }

    @Override
    public Food getFoodFromID(String foodID){
        Iterator<Food> foodIterator = foods.iterator(); /* This iterates through the foods list. */
        Food food;
        while(foodIterator.hasNext()){
            food = foodIterator.next();
            if(food.getFoodID().equals(foodID)){
                return food;
            }
        }
        return null;
    }

    public String addFood(Food newFood){
        if(newFood != null){
            if(newFood.getFoodID() != "" && Integer.parseInt(newFood.getFoodID()) > 0 && Integer.parseInt(newFood.getFoodID()) == foods.size()+1 && newFood.getFoodName() != ""){
                foods.add(newFood);
                Food_map.put(newFood.getFoodID(), newFood);
            }
        }
        return null;
    }


    @Override
    public int getFoodTableRow(){
        return foods.size();
    }

    @Override
    public FoodCategory addFoodCategory(int foodID, int categoryID){
        if(foodID > 0 && categoryID > 0){
            if(foodID <= foods.size() && categoryID <= categoriesList.size()){
                FoodCategory newFoodCategory = new FoodCategory(foodID,categoryID);
                for(int i = 0; i<foodCategories.size(); i++){
                    if(newFoodCategory.equals(foodCategories.get(i))){
                        return null;
                    }
                }
                foodCategories.add(newFoodCategory);
                return newFoodCategory;
            }
        }
        return null;
    }

    @Override
    public void deleteFoodCategory(int foodID, int categoryID){
        Iterator<FoodCategory> foodCategoryIterator = foodCategories.iterator(); /* This iterates through the foods list. */
        FoodCategory foodCategory;
        while(foodCategoryIterator.hasNext()){
            foodCategory = foodCategoryIterator.next();
            if(foodCategory.getFoodID() == foodID && foodCategory.getCategoryID() == categoryID){
                foodCategoryIterator.remove();
                break;
            }
        }
    }

    @Override
    public void deleteUser(int userID){
        Iterator<User> userIterator = users.iterator(); /* This iterates through the foods list. */
        User user;
        while(userIterator.hasNext()){
            user = userIterator.next();
            if(user.getUserID() == userID){
                userIterator.remove();
                break;
            }
        }
    }


    @Override
    public int getCategoryIDbyName(String categoryName){
        for (Categories category:categoriesList){
            if (category.getCategoryName().equals(categoryName))return category.getCategoryID();
        }
        return -1;
    }

    @Override
    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        Question question;
        for(int i =0; i<5; i++){
            question = questions.get(i);
            questionList.add(question);
        }
        return questionList;
    }

    @Override
    public int getTotalQuestions(){
        return questions.size();
    }

    public User getDefault(){
        return users.get(0);
    }

    public String getFavouriteFoodByUserSequential(User user, List<Food> favouriteFoodList){
        Iterator<Map<Integer,Integer>> mapIterator = User_favourite_map_list.iterator(); /* This iterates through the foods list. */
        Map map;
        Food food;
        int userID = user.getUserID();
        while(mapIterator.hasNext()){
            map = mapIterator.next();
            if(map.containsKey(userID)){
                int foodID = (int)map.get(userID);
                food = getFoodFromID(String.valueOf(foodID));
                favouriteFoodList.add(food);
            }
        }
        return null;
    }

    public String setFoodToFavouriteByUser(User user, String curr_id, boolean b){
        Iterator<Map<Integer,Integer>> mapIterator = User_favourite_map_list.iterator(); /* This iterates through the foods list. */
        Map<Integer,Integer> map;
        Food food;
        int userID = user.getUserID();
        int foodID = Integer.parseInt(curr_id);
        while(mapIterator.hasNext()){
            map = mapIterator.next();
            if(map.containsKey(userID) && ((int)map.get(userID)) == (foodID)){
                if(b){
                    return null;
                }else{
                    mapIterator.remove();
                    return null;
                }
            }
        }
        map = new HashMap<>();
        map.put(userID,foodID);
        User_favourite_map_list.add(map);
        return null;
    }

    public boolean getFoodFavByUser(User user, Food food){
        Iterator<Map<Integer,Integer>> mapIterator = User_favourite_map_list.iterator(); /* This iterates through the foods list. */
        Map<Integer,Integer> map;
        int userID = user.getUserID();
        int foodID = Integer.parseInt(food.getFoodID());
        while(mapIterator.hasNext()){
            map = mapIterator.next();
            if(map.containsKey(userID) && ((int)map.get(userID)) == (foodID)){
                return true;
            }
        }
        return false;
    }

    public String getUserSequential(List<User> userResult){
        userResult.clear();
        userResult.addAll((users));
        return null;
    }

    public User getUser(int userID){
        for(User user:users){
            if(user.getUserID() == userID) return user;
        }
        return null;
    }

    public User setNewUser(int userID, String username){
        if(userID > 0 && userID == users.size()+1 && !username.equals("")){
            User newUser = new User(userID,username);
            users.add(newUser);
            return newUser;
        }
        return null;
    }

    @Override
    public String getIngredientByFood(Food food, List<Ingredient> ingredients){
        for (FoodIngredient fi:foodIngredients){
            if(fi.getFood().getFoodID().equals(food.getFoodID())){
                ingredients.clear();
                ingredients.addAll(fi.getIngredients());
            }
        }
        return null;
    }

    @Override
    public String getDirectionByFood(Food food, List<Direction> directions){
        for (FoodDirection fd:foodDirections){
            if(fd.getFood().getFoodID().equals(food.getFoodID())){
                directions.clear();
                directions.addAll(fd.getDirections());
            }
        }
        return null;
    }

    @Override
    public int getTotalUser(){
        return users.size();
    }

}
