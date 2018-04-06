# [VOOGASalad](https://www2.cs.duke.edu/courses/compsci308/spring18/assign/04_voogasalad/index.php) : API Review Questions

## Part 1
1. What about your API is intended to be flexible?
    Aspects of a game element can be defined as anything by a user. The backend has a broad/flexible implementation that allows the user to have more freedom when designing characteristics of their game.

2. How is your API/design encapsulating your implementation decisions?
    The public API for the game authoring environment consists of very few methods, which shows how independent this part of the project is from others. It is rare that the authoring environment will have to pass information to other parts of VOOGA, allowing almost all of the methods to be private or protected.
    
3. How is your part linked to other parts of the project?
    The main part of the game authoring environment that directly involves other aspects of the program is reading out the game to game data. There are other parts of the authoring environment that are linked to the engine, but the main part of this link is to receive information rather than to give information.
    
4. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    When the authoring environment is using the implementation from the engine, it is possible that null pointer exceptions will result. If the object has not been fully initialized, the user will not be able to do certain things with it. The best way to solve this is to print a warning to the user that they cannot do the action they wanted until more of the object is initialized.
    
5. Why do you think your API/design is _good_ (also define what your measure of good is)?
    Our API/design is good because it is flexible. It allows the user to add a variety of different elements into their game and makes it easy for the programmers to implement more options in the backend as well. 
    
    
## Part 2

The main type of use cases we are responsible for are ones that involve allowing the user to create different aspects of a game. In order to make an entire use case functional, it will take a lot of time. This is because a lot of the pieces have to be working in order to make just one use case function as anticipated.
We are excited to work on learning scripting languages such as groovy.
We are nervous about saving and loading files from the authoring environment because this is one of the most important parts of the project that is required for everything else to work.
This weekend, we are working on how to display the game to the user. This includes a lot of Java FX features such as Camera for scrolling. In addition, we are going to research groovy and learn how to integrate that into the user interface.

