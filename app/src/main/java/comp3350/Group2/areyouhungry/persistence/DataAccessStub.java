package comp3350.Group2.areyouhungry.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.objects.Categorys;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.FC;
import comp3350.Group2.areyouhungry.objects.Food;

import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.objects.User;
import comp3350.Group2.areyouhungry.persistence.DataAccess;

import static comp3350.Group2.areyouhungry.MainActivity.dbName;

public class DataAccessStub implements DataAccess{

    private String dbName;
    private String dbType = "stub";

    private ArrayList<Food> foods;
    private ArrayList<FC> fcs;
    private ArrayList<Categorys> categorysList;
    private ArrayList<User> users;
    private ArrayList<Question> questions;
    private Map<String,Food> Food_map;

    public DataAccessStub(String dbName){
        this.dbName = dbName;
    }

    public DataAccessStub(){
        this(MainActivity.dbName);
    }


    public void open(String dbPath){
        Food food;
        Categorys categorys;
        FC fc;
        User user;
        Question question;

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

        categorysList = new ArrayList<Categorys>();
        categorys = new Categorys(1,"Meat");
        categorysList.add(categorys);
        categorys = new Categorys(2,"Grain");
        categorysList.add(categorys);
        categorys = new Categorys(3,"Vegetable");
        categorysList.add(categorys);

        fcs = new ArrayList<FC>();
        fc = new FC(1,1);
        fcs.add(fc);
        fc = new FC(1,2);
        fcs.add(fc);
        fc = new FC(2,1);
        fcs.add(fc);
        fc = new FC(2,3);
        fcs.add(fc);
        fc = new FC(3,1);
        fcs.add(fc);
        fc = new FC(3,3);
        fcs.add(fc);

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
        System.out.println("Opened " +dbType +" database " +dbName);
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


    /* Generate the favourite list. */
    public String getFavouriteFoodSequential(List<Food> foodResult){
        Iterator<Food> foodIterator = foods.iterator(); /* This iterates through the foods list. */
        Food food;
        while(foodIterator.hasNext()){
            food = foodIterator.next();
            if(food.getFavourite()){
                foodResult.add(food);
            }
        }
        return null;
    }

    /* This function is used to generate a random food from the stub database
       and add it into the foodresult list. */
    public String getFoodRandom(List<Food> foodResult){
        Random random = new Random();
        foodResult.add(foods.get(random.nextInt(foods.size())));
        return null;
    }

    public String getFoodPreferred(List<Food> foodResult, String food){
        if(food != null){
            if (food.equals("Meat")){
                foodResult.add(foods.get(1));
                foodResult.add(foods.get(0));
            } else if (food.equals("Dessert")){
                foodResult.add(foods.get(2));
                foodResult.add(foods.get(3));
            }
        }
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
            if(newFood.getFoodID() != "" && newFood.getFoodName() != ""){
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
    public String addFoodCategory(int foodID, int categoryID){
        return null;
    }

    @Override
    public int getIDByFood(Food food){
        return 0;
    }

    @Override
    public int getCategoryIDbyName(String categoryName){
        return 0;
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

    public User getDefault() {
        return users.get(0);
    }

    public String getFavouriteFoodByUserSequential(User user, List<Food> favouriteFoodList){
        return null;
    }

    public String setFoodToFavouriteByUser(User user, String curr_id, boolean b){
        return null;
    }

    public boolean getFoodFavByUser(User user, Food food){
        return false;
    }

    public String getUserSequential(List<User> userResult){
        return null;
    }

    public User getUser(int userID){
        return null;
    }

    public User setNewUser(int userID, String username){
        return null;
    }

    @Override
    public String getIngredientByFood(Food food, List<Ingredient> ingredients) {
        return null;
    }

    @Override
    public String getDirectionByFood(Food food, List<Direction> directions) {
        return null;
    }
    // TODO: 27/07/20
    //add the access methods to stub database

}
