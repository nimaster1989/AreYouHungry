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
    private Map<Integer, String> imageURL;
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
        imageURL = new HashMap<>();
        Food_map = new HashMap<>();
        food = new Food(1, "Baked Salmon",3,20, "Savoury", "Easy", "American");
        foods.add(food);
        imageURL.put(1, "food1");
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(2, "Greek Salad",1,10, "Fresh", "Easy", "Greek");
        foods.add(food);
        imageURL.put(2, "food2");
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(3, "Spicy Spaghetti",5,10, "Spicy", "Easy", "Italian");
        foods.add(food);
        imageURL.put(3, "food3");
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(4, "Classic Cheesecake",7,40, "Sweet", "Hard", "American");
        foods.add(food);
        imageURL.put(4, "food4");
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(5, "Egg Fried Rice",3,30, "Savoury", "Medium", "Chinese");
        foods.add(food);
        imageURL.put(5, "food5");
        Food_map.put(String.valueOf(food.getFoodID()),food);
        food = new Food(6, "Banana Split", 1, 10, "Sweet", "Easy", "American");
        foods.add(food);
        imageURL.put(6, "food6");
        Food_map.put(String.valueOf(food.getFoodID()),food);

        categoriesList = new ArrayList<Categories>();
        categories = new Categories(1,"Meat");
        categoriesList.add(categories);
        categories = new Categories(2,"Vegetable");
        categoriesList.add(categories);
        categories = new Categories(3,"Grain");
        categoriesList.add(categories);
        categories = new Categories(4,"Dairy");
        categoriesList.add(categories);
        categories = new Categories(5,"Fruit");
        categoriesList.add(categories);


        foodCategories = new ArrayList<FoodCategory>();
        foodCategory = new FoodCategory(1,1);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(2,2);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(3,3);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(4,4);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(5,3);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(6,4);
        foodCategories.add(foodCategory);
        foodCategory = new FoodCategory(6,5);
        foodCategories.add(foodCategory);


        users = new ArrayList<User>();
        user = new User(1,"default user");
        users.add(user);
        user = new User(2,"sample user");
        users.add(user);
        user = new User(3,"new user");
        users.add(user);

        questions = new ArrayList<Question>();
        question = new Question("You are on fear factor and the following items are presented for you to feast on. A blue substance you cant tell is bleach or gatorade, An Ant Hill, Hot Rocks, or a Mystery Box the host insists is much worse.What do you choose?", "Its worth the risk for sweet sweet Gatorade", "Ants are like little chips... right?", "Mmmmm hot rocks go brrrr", "Ahhh A nice fresh box!");
        questions.add(question);
        question = new Question("Im so hungry i could eat a...", "Horse", "Slightly larger Horse", "Wow thats a big horse", "A horse bred so large for the purpose to stop this dumb statement");
        questions.add(question);
        question = new Question("Which would you rather do?", "Defuse a bomb with 10 seconds left", "Sit in the \"slow\" McDonalds line", "Spend four years getting a degree in theoretical phys-ed", "Play a game of Monopoly");
        questions.add(question);
        question = new Question("You invite someone over saying you will cook for them knowing very well that you infact cant cook. What do you make?", "Everyone likes Lucky Charms!", "A nice bowl of Kraft Dinner", "Something that requires to be cooked to a certain temperature", "The thing the Rat in Ratatouille made");
        questions.add(question);
        question = new Question("Ah yes the classic why did the chicken cross the road dilema, except the chicken is in the airport and is booking a flight to your favourite vacation spot which is...", "The place where the toilet water spins the other way", "Cant have a high amount of covid cases if we dont test land", "Mamma Mia!", "Breathing the Air here is like smoking a pack of cigs a day");
        questions.add(question);

        ingredients = new ArrayList<Ingredient>();
        ingredient = new Ingredient(1, "Salmon Fillets", "4");
        ingredients.add(ingredient);
        ingredient = new Ingredient(2, "Olive Oil", "2 tbsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(3, "Salt", "1/2 tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(4, "Black pepper", "1/4 tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(5, "Minced Garlic", "2tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(6, "Italian herb seasoning blend", "1tsps.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(7, "Lemon", "1/2");
        ingredients.add(ingredient);
        ingredient = new Ingredient(8, "Large vine tomatoes", "4");
        ingredients.add(ingredient);
        ingredient = new Ingredient(9, "Cucumber", "1");
        ingredients.add(ingredient);
        ingredient = new Ingredient(10, "Red onion", "1/2");
        ingredients.add(ingredient);
        ingredient = new Ingredient(11, "Kalamata olives", "16");
        ingredients.add(ingredient);
        ingredient = new Ingredient(12, "Oregano", "1 tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(13, "Feta cheese", "85g");
        ingredients.add(ingredient);
        ingredient = new Ingredient(14, "Extra virgin olive oil", "4 tbsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(15, "Spaghetti", "1 lb");
        ingredients.add(ingredient);
        ingredient = new Ingredient(16, "Cloves of garlic", "2-3");
        ingredients.add(ingredient);
        ingredient = new Ingredient(17, "Dried red chile pepper flakes", "1/2 tsp");
        ingredients.add(ingredient);
        ingredient = new Ingredient(18, "Graham cracker crumbs", "1 1/2 cups");
        ingredients.add(ingredient);
        ingredient = new Ingredient(19, "Ground cinnamon", "1/4 tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(20, "Unsalted butter", "1/3 cup");
        ingredients.add(ingredient);
        ingredient = new Ingredient(21, "Cream cheese", "8 oz.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(22, "Sugar", "1 1/4 cup");
        ingredients.add(ingredient);
        ingredient = new Ingredient(23, "Sour Cream", "1 cup");
        ingredients.add(ingredient);
        ingredient = new Ingredient(24, "Vanilla Extract", "2 tsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(25, "Large Eggs", "5");
        ingredients.add(ingredient);
        ingredient = new Ingredient(26, "Butter", "3 tbsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(27, "Eggs", "2");
        ingredients.add(ingredient);
        ingredient = new Ingredient(28, "Medium carrots", "2");
        ingredients.add(ingredient);
        ingredient = new Ingredient(29, "Small white onion", "1");
        ingredients.add(ingredient);
        ingredient = new Ingredient(30,"Frozen peas", "1/2 cup");
        ingredients.add(ingredient);
        ingredient = new Ingredient(31, "Cloves of garlic", "3");
        ingredients.add(ingredient);
        ingredient = new Ingredient(32, "Cooked rice", "4 cups");
        ingredients.add(ingredient);
        ingredient = new Ingredient(33, "Green onions", "3");
        ingredients.add(ingredient);
        ingredient = new Ingredient(34, "Soy sauce", "3-4 tbsp.");
        ingredients.add(ingredient);
        ingredient = new Ingredient(35, "Sesame oil", "1");
        ingredients.add(ingredient);
        ingredient = new Ingredient(36, "Banana", "1");
        ingredients.add(ingredient);
        ingredient = new Ingredient(37, "Vanilla ice cream", "1 scoop");
        ingredients.add(ingredient);
        ingredient = new Ingredient(38, "Chocolate ice cream", "1 scoop");
        ingredients.add(ingredient);
        ingredient = new Ingredient(39, "Strawberry ice cream", "1 scoop");
        ingredients.add(ingredient);
        ingredient = new Ingredient(40, "Chocolate syrup", "Garnish: ");
        ingredients.add(ingredient);
        ingredient = new Ingredient(41, "Whipped cream", "Garnish: ");
        ingredients.add(ingredient);


        foodIngredients = new ArrayList<>();
        ArrayList<Ingredient> tempIngredient = new ArrayList<>();

        for(int i = 0; i < 7; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(0),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        for(int i = 7; i < 14; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(1),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        for(int i = 14; i < 17; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(2),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        for(int i = 17; i < 25; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(3),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        for(int i = 25; i < 35; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(4),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        for(int i = 35; i < 41; i++){
            tempIngredient.add(ingredients.get(i));
        }
        foodIngredient = new FoodIngredient(foods.get(5),tempIngredient);
        foodIngredients.add(foodIngredient);
        tempIngredient.clear();

        directions = new ArrayList<>();
        direction = new Direction(1,"Preheat oven to 400 degree F and grease a large baking pan. Arrange salmon fillets on the baking sheet and season generously with salt and pepper.", 1);
        directions.add(direction);
        direction = new Direction(2, "Stir together olive oil, garlic, herbs, and juice of 1/2 lemon, and spoon mix over fillets.", 2);
        directions.add(direction);
        direction = new Direction(3, "Bake for 15-18 minutes until salmon is opaque and flaky", 3);
        directions.add(direction);
        direction = new Direction(4, "Garnish with fresh thyme or parsley if desired, and serve.", 4);
        directions.add(direction);
        direction = new Direction(5, "Cut the tomatoes into wedges.", 1);
        directions.add(direction);
        direction = new Direction(6, "Peel, de-seed, and chop the cucumbers", 2);
        directions.add(direction);
        direction = new Direction(7, "Thinly slice the red onion.", 3);
        directions.add(direction);
        direction = new Direction(8, "Throw all the ingredients in the bowl", 4);
        directions.add(direction);
        direction = new Direction(9, "Lightly season, and serve.", 5);
        directions.add(direction);
        direction = new Direction(10, "Set a large covered pot of water over high heat to boil. When it reaches a rolling boil, salt the water and add the spaghetti.", 1);
        directions.add(direction);
        direction = new Direction(11, "Mince garlic, and add onto skillet with olive oil over medium heat for about 1 minute until garlic is fragrant and lightly coloured.", 2);
        directions.add(direction);
        direction = new Direction(12, "Add the red pepper and chopped parsley and cook for 30 seconds until fragrant.", 3);
        directions.add(direction);
        direction = new Direction(13, "When your pasta is al dente, drain it, reserving a few tablespoons of the cooking water.", 4);
        directions.add(direction);
        direction = new Direction(14, "Toss the spaghetti with the oil and garlic until evenly coated. Add a little bit of the pasta cooking water, as needed.", 5);
        directions.add(direction);
        direction = new Direction(15, "Preheat oven to 475 degrees F. Place a large pan filled with 1/2 inch water in oven.", 1);
        directions.add(direction);
        direction = new Direction(16, "Building the crust: Mix graham cracker crumbs and cinnamon; add butter or margarine. Press crust onto bottom and 2/3 of the way up a 9-inch springform pan lined with parchment. Freeze until needed", 2);
        directions.add(direction);
        direction = new Direction(17, "Mix cream cheese, sugar, sour cream and vanilla until smooth and creamy. Whisk eggs in a bowl; add to cream cheese mixture. Blend just until eggs are incorporated.", 3);
        directions.add(direction);
        direction = new Direction(18, "Remove crust from freezer and pour in filling. Carefully place cheesecake into preheated water bath. Bake for 12 minutes; turn oven to 350 degrees F and bake until top of cheesecake turns golden, 50 to 60 minutes. Remove cake to cool.", 4);
        directions.add(direction);
        direction = new Direction(19, "Combine sour cream and some sugar to make topping to spread over cake. Cover and refrigerate at least 4 hours.", 5);
        directions.add(direction);
        direction = new Direction(20, "Heat 1/2 tablespoon of butter in a large saute pan over medium-high heat until melted. Whisk and add eggs, and cook until scrambled, stirring occasionally. Remove egg, and transfer to a separate plate.,", 1);
        directions.add(direction);
        direction = new Direction(21, "Add 1 of tablespoon butter to the pan and heat until melted. Add carrots, onion, peas and garlic, and season with a generous pinch of salt and pepper. Saut\\u00e9 for about 5 minutes or until the onion and carrots are soft.", 2);
        directions.add(direction);
        direction = new Direction(22, "Increase heat to high, add in the remaining 1 1/2 tablespoons of butter, and stir until melted.Immediately add the rice, green onions, soy sauce, and stir until combined. Continue stirring for 3 minutes to fry the rice.", 3);
        directions.add(direction);
        direction = new Direction(23, "When rice is fried, add in the eggs and stir to combine. Remove from heat, and stir in the sesame oil until combined. Taste and season with extra soy sauce, if needed.", 4);
        directions.add(direction);
        direction = new Direction(24, "Split the banana lengthwise, and place them side by side.", 1);
        directions.add(direction);
        direction = new Direction(25, "Place each of the ice cream scoops between the banana slices.", 2);
        directions.add(direction);
        direction = new Direction(26, "Squirt some chocolate syrup on the vanilla and strawberry ice cream.", 3);
        directions.add(direction);
        direction = new Direction(27, "Top off ice cream with whipped cream or any toppings of your choice.", 4);
        directions.add(direction);


        foodDirections = new ArrayList<>();
        ArrayList<Direction> tempDirection = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            tempDirection.add(directions.get(i));
        }
        foodDirection = new FoodDirection(foods.get(0),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        for (int i = 4; i < 9; i++){
            tempDirection.add(directions.get(i));
        }
        foodDirection = new FoodDirection(foods.get(1),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        for (int i = 9; i < 14; i++){
            tempDirection.add(directions.get(i));
        }
        foodDirection = new FoodDirection(foods.get(2),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        for (int i = 14; i < 19; i++){
            tempDirection.add(directions.get(i));
        }
        foodDirection = new FoodDirection(foods.get(3),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        for (int i = 19; i < 23; i++){
            tempDirection.add(directions.get(i));
        }
        foodDirection = new FoodDirection(foods.get(4),tempDirection);
        foodDirections.add(foodDirection);
        tempDirection.clear();

        for (int i = 23; i < 27; i++){
            tempDirection.add(directions.get(i));
        }
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
            if(!newFood.getFoodID().equals("") && Integer.parseInt(newFood.getFoodID()) > 0 && Integer.parseInt(newFood.getFoodID()) == foods.size()+1 && !newFood.getFoodName().equals("")){
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
    public int getIngredientRow(){
        return ingredients.size();
    }

    @Override
    public String addFoodIngredient(int foodid, int ingredientid){
        Iterator<FoodIngredient> foodIngredientIterator = foodIngredients.iterator();
        FoodIngredient foodIngredient;
        while(foodIngredientIterator.hasNext()){
            foodIngredient = foodIngredientIterator.next();
            if(foodIngredient.getFood().getFoodID().equals(String.valueOf(foodid))){
                foodIngredient.getIngredients().add(ingredients.get(ingredientid-1));
            }
        }
        return null;
    }

    @Override
    public String addNewIngredient(Ingredient newIngredient){
        if(newIngredient != null){
            ingredients.add(newIngredient);
        }
        return null;
    }

    @Override
    public int getDirectionRow(){
        return directions.size();
    }

    @Override
    public String addNewDirection(Direction newDirection){
        if(newDirection != null){
            directions.add(newDirection);
        }
        return null;
    }

    @Override
    public String addFoodDirection(int foodid, int directionid){
        Iterator<FoodDirection> foodDirectionIterator = foodDirections.iterator();
        FoodDirection foodDirection;
        while(foodDirectionIterator.hasNext()){
            foodDirection = foodDirectionIterator.next();
            if(foodDirection.getFood().getFoodID().equals(String.valueOf(foodid))){
                foodDirection.getDirections().add(directions.get(directionid-1));
            }
        }
        return null;
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

public String addFoodImage(int foodid, String imageurl){
        String result = null;
        if(foodid == foods.size()){
            imageURL.put(foodid, imageurl);
        }
        return null;
}

public String getImageByFood(int foodid){
        return imageURL.get(foodid);
}

    @Override
    public int getTotalUser(){
        return users.size();
    }

}
