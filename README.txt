COMP 3350 Software Engineering Project - Cole, David, Yang, Qiqi, Jonathan

Project Name: Are you Hungry

Tested on: Nexus 7 API 23 Running Marshmallow Android 6 x86

Github Repo: https://github.com/nimaster1989/AreYouHungry

External Resources:
    Glide:
        We used this for adding images to the food when we add a new food.
        Link: https://github.com/bumptech/glide
    Espresso:
        We used this for our automated acceptance tests.
        Link: https://developer.android.com/training/testing/espresso

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
    - comp3350.Group2.areyouhungry.persistence
      This package is in charge of the persistence layer that takes what was passed to it from the
      business layer and gets from the "database" what is required. Right now there is a stub database
      and a HSQL database that tests can swap between but only the HSQL one is used for storage
      for the application.
    - comp3350.Group2.areyouhungry.objects
      This is the domain specific object package and is what holds our food object which is what the
      project uses as its answer to the users requests.

Major source code files:

    comp3350.Group2.areyouhungry.business: This class is the middle man between the persistence
    storage and the presentation layer. It contains all the different ways to add and retrieve
    food from the "database".

    comp3350.Group2.areyouhungry.objects: This class is our food object which is what is stored
    in the database and is presented to the user.

    comp3350.Group2.areyouhungry.persistence: This class is our database right now. It stores all
    of our food objects and has methods for accessing them.

    comp3350.Group2.areyouhungry.ui.*: These classes are our front end, they're responsible for
    providing a way for users to interact with our application to start the process to retrieve
    food/recipes from the backend. They have buttons and list to help the user easily see what
    they're searching for and be able to store it into their favourites.


Developer Log:
    Our developer log is multipart, first we used Trello to keep track of who was assigned what.
    From there once we finish the task what we did was put it all inside
    of Google Doc in chronological order. For our group meetings we also put inside of the Google doc
    as well.
    The Log: https://docs.google.com/document/d/1a8yhKzSlzY0D8_UWBL0q6TMm_ceQJbOEeWVaKGaYHDc/
    Trello board: https://trello.com/b/ETfh9VNT/are-you-hungry

Major changes in this iteration and where to find them:
    1. First off the largest change to this iteration was how we updated our testing suite. We added
       automated JUnit integration tests across each seam in our architecture. The persistence layer
       tests are able to swap between the stub database and the real database by commenting out some
       lines of code. Also we added an automated acceptance test for each of our completed big user
       story using Espresso. Each test thoroughly goes through the customer stories and does all
       actions and responses.
    2. We changed how we add food into our app. First we changed it
       from a one page has everything to multiple pages. Additionally we changed the formatting for
       how certain fields are entered to be less restrictive. We also added the ability to remove
       ingredients and instructions when they are added just in case an error is made.
    3. We added a search by criteria feature, which allows the user to search food in our application
       by various criteria for our existing foods in the database. The search also uses fuzzy search
       which has the ability to match your input text with some ingredient, even if you spell it
       wrong or its not the exact same.
    4. We added the feature to like our dislike a food suggestion. This allows the user to provide
       feedback for us whether the app is working correctly or not by either liking or disliking
       a food suggestion.
    5. We did some visual changes to the application like giving it a picture for the app itself.
       And changes the colours as well throughout the application to make it feel more clean.

Changes in this iteration that we were unable to implement:
    1. We have not added any feature to sort any of the foods. Whether it be favourites or from the
       search results.
