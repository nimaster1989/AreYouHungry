COMP 3350 Software Engineering Project - Cole, David, Yang, Qiqi, Jonathan

Project Name: Are you Hungry

Tested on: Nexus 7 API 23 Running Marshmallow Android 6 x86

Github Repo: https://github.com/nimaster1989/AreYouHungry

How to run:
    Open the project in android studio. Download a Nexus 7 tablet emulator with Marshmallow on it.
    Before you run it make sure it says app to the left where it says what emulator you are using.
    This insures that the project loaded in correctly.
    If it doesnt say app, go to the projects directory to the left navigate to the folder that says
    Android and has the little alien to the left of it.


Packages:
    - comp3350.Group2.areyouhungry.ui
      this package is in charge of the presentation layer, it contains all the code for the gui and
      contains all the onclick triggers to put the business logic layer to work
    - comp3350.Group2.areyouhungry.business
      this package is in charge of the business logic layer, it does what logic is required to
      grab what food is requested and passes that to that persistence layer for it to grab it
      from the database.
    - comp3350.Group2.areyouhungry.presistence //I think this is spelt wrong
      This package is in charge of the persistence layer that takes what was passed to it from the
      business layer and gets from the"database" what is required. Right now there is no actual
      database so it just fetches it from an array list.
    - comp3350.Group2.areyouhungry.objects
      This is the domain specific object package and is what holds our food object which is what the
      project uses as its answer to the users requests.

Major source code files:

    comp3350.Group2.areyouhungry.business: This class is the middle man between the persistence
    storage and the presentation layer. It contains all the different ways to add and retrieve
    food from the "database".

    comp3350.Group2.areyouhungry.objects: This class is our food object which is what is stored
    in the "database" and is presented to the user. Will eventually contain things like recipes

    comp3350.Group2.areyouhungry.presistence: This class is our database right now. It stores all
    of our food objects and has methods for accessing them

    comp3350.Group2.areyouhungry.ui.*: These classes are our front end, they're responsible for
    providing a way for users to interact with our application to start the process to retrieve
    food/recipes from the backend. They have buttons and list to help the user easily see what
    they're searching for and be able to store it into their favourites.


Developer Log:
    Our developer log is multipart, first we used Trello to keep track of who was assigned what,
    we also agreed to keep track of our progress and issues we had in the comments section of the
    Trello cards themselves. From there once we finish the task what we did was put it all inside
    of Google Doc in chronological order. For our group meetings we put those straight into the
    Google Doc as opposed to a Trello card first.
    The Log: https://docs.google.com/document/d/1a8yhKzSlzY0D8_UWBL0q6TMm_ceQJbOEeWVaKGaYHDc/
    Trello board: https://trello.com/b/ETfh9VNT/are-you-hungry

Major implemented features and where to find them:
    1. The first major feature that we implemented was the options to generate a food from the
       database. This can be seen on the initial start up page where you are greeted with two
       ways to do this. You can either preference search or have the app pick for you.
       Preference search:
        If you select preference search it takes you to another page where you can make a preference
        for what kind of you food you like and generate based off that. This will become way more
        complex in later iterations, we just wanted to get our groundwork set first for this
        iteration.
       Pick for me:
        If you select pick for me the app will randomly grab a food for you. However we plan on
        removing this in a later iteration as it goes against our vision statement, that is creating
        something that can help you pick a food better than just randomly google searching. The
        randomness contradicts what we have.
    2.  The second major feature is the favourite option. This can be seen in the middle portion
        of the navigation bar. It allows the user to store the food that are their favourite and
        they enjoyed making. The user can add favourites in two ways, either by going to the
        favourite section and selecting add favourite, or by viewing all the food and selecting a
        favourite that way. The second way will be explained in greater detail next.
    3. The third is the viewing all the foods and recipes button. This can be found in the more
       section of the navigation bar. When you click this All foods and recipes option it displays
       all foods and recipes stored inside the app. From there the user can add more food/recipes
       to the database. Additionally they can double click a food/recipe for more detail on it.
       Right now the more details portion of the food/recipe blank. However from within the more
       details portion you can favourite the food by clicking the blue heart button.

