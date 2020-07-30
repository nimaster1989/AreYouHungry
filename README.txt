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
    - comp3350.Group2.areyouhungry.persistence
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

Major changes in this iteration and where to find them:
    1. First off in this iteration we added the ability to have persistent storage with a Database.
       We kept the stub one around too and have the ability to swap between them for testing.
       However only the persistent one is used in the app.
    2. The next big change was we added a multiple choice style questionnaire to the app with "fun"
       questions to get how the user is currently feeling. Based off their answers we then coorelate
       them to a food in our database and retrieve it and display it to them. We went with this
       approach as apposed to the previous one with just the user entering different things they had
       and what type of food they're feeling because it seem more fun and would possibly cause the
       user to use it more. As the app progressed more questions could be added to further get
       what the user is feeling.
    3. We removed the random food suggestion. As pointed out from our last iteration the random
       option might actually be worse then looking up recipes online so we decided to remove it.
    4. We tried to separate our project a lot more by having more objects and trying to make each
       class do a specific thing. With the addition of the these new classes we were able to get
       more tests as well as compared to the last iteration.
    5. A big thing we tried to focus on was consistency. So we tried to keep all of the comments
       similar as well as bracket styling and how we developed overall.
    6. We added the ability to swap between users. Although not mentioned in our initial plan. We
       having the functionality to swap between different users and have them contain their own
       favourited food was a nice adjustment.
    7. We added more to the food detail page. Now as promised the food description page has a picture
       of the food itself. Additionally the page contains the ingredients to make it and how much
       of each and the directions itself to make it.

Changes in this iteration that we were unable to implement:
    1. We have not added any feature to sort any of the foods. Whether it be favourites or from the
       search results.
    2. We also didn't have add the feature to search for similar foods as the one suggested.
