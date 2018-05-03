API_REVIEW_hy115_lp139

#### Part 1

1.  What about your API/design is intended to be flexible?
    Liam: Have wrapper classes, have ability to quickly create and integrate new front end components. Establishing a hierarchy for GUI components and where they can be placed, etc. Will make it very easy to add stuff when they need to. 
    Hemanth: Toolbar view, component view, game environment view. 
        - Game Environment, split into properties view and level, etcetera. 
        - Inheritance hierarchies 
2.  How is your API/design encapsulating your implementation decisions?
    Liam: Have two classes outside the main tree structure that have access to all the front end components. These controller classes control interactions between the components.
    Hemanth: Each frontend component can interact with the Game Engine if need be, however only certain objects can actually get values from the Game Engine. There's a Game State object which manages all interaction with Game Data -- nothing else can do so.
3.  How is your part linked to other parts of the project?
    Liam: EntityCreator and LevelCreator interact with the Game Engine. EntityCreator will send collection of LevelObjects to GameData. 
    Hemanth: 
    - Game State object handles all data passing to Game Data
    - Front-end components can generally interact with the Game Engine if need be (i.e. get possible objects and properties, etc.)
    - No interaction at all with Game player
4.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    Hemanth: Still not certain
    - If Object can't take a certain property (brick can't have velocity or player sprite), throw to engine
    - Validation of object locations, etc.
    - Possible to play 
    Liam: Same as above. There is a chance for user to define a property that the object cannot handle. File loading, can't load excel file, for example. 
5.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    Liam: It is good because it is flexible and it is encapsulated. Making good use of inheritance hierarchies such that it is easy to add a new front end component to the authoring environment. 
    Hemanth: 
    - Game Authoring separate from Game Engine and Data
    - Only have to interact with other components when necessary (i.e. Game State object, etc.at)
    
#### Part 2

1.  Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
    Hemanth:  
    - Adding a new level, defining its properties
    - Removing a level
    - Properties view, where you add properties to an object
    We think these are all descriptive, appropriate, and reasonably sized. 
    Liam:
    - Designing the entity building menu, similar to Hemanth with the properties view. 
    - Creating the drag and drop utility for the objects
    - Create Canvas for newly built Entities to be explained
    These are also all descriptive, appropriate, and reasonably sized. 
2.  Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
    Hemanth: 
    - Add Level: 2 hours because that seems like a reasonable amount of time. Adding a new level is simply adding a new segment to data and updating the requisite views. Removing a level will be easy once adding a level is done
    - The properties view will probably take at least 8 hours because of the number of components involved and the level of back-end integration required
    Liam: 
    - I think creating the drag and drop utility will take a few hours, for sure. It involves moving things across panes and out of JavaFX Nodes, which could be difficult.
    - The entity building menu will take a couple of hours. It'll be difficult to arrange the elements in a sensible way.
    Estimating these tasks is uncertain because we are not sure how all the components fit together, GUI wise and data wise. We also do not know how the game state object will look like yet.

3.  What feature/design problem are you most excited to work on?
    Hemanth:
    - I am most excited to work on adding the properties view and working with the GameState data
    Liam:
    - I am most excited to work on the event listening features and to construct a nice hierarchy of event relationships.
4.  What feature/design problem are you most worried about working on?
    Hemanth:
    - I am most worried about working with the Controller classes because it uses event listeners which I am not comfortable with yet. 
    Liam:
    - I am most worried about working on the Drag-and-Drop utility because I think it will involve the most pure JavaFX work, which I'm not a huge fan of.
6.  What major feature do you plan to implement this weekend?
    Hemanth:
    - I plan to implement the properties feature over the weekend. 
    Liam:
    - I plan to implement the Entity-Creator menu over the weekend.

