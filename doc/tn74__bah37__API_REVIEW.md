#### Part 1

1.  What about your API/design is intended to be flexible?
    - Our designs are quite similar to each other. We both have a single GameElement/Entity object that is the composition of a specific entity's attributes. The composition structure of this basic element is ths source of flexibility in our program allowing us to create elements with an arbitrary set of properties.
    
3.  How is your API/design encapsulating your implementation decisions?
     - For both of us, we have components called Engine/EventManager that loop over modules, instructing them to act on their behavior (with no knowledge of how they are actually implemented). This allows us to add new components at a later time to extend the capabilities of our project, without the Engine/EventManagers being aware.
5.  How is your part linked to other parts of the project?
    - Our modules interact in very similar ways: with the front-end, we both expose our Entity/GameElement objects that have methods on them for adding various behaviors/components. The front-end then has the power to render those using a String filename that the Entity objects store. Within the game loop, the main modules get called to update themselves. 
    
7.  What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    - One exception we throw when an object tries to access a component/behavior of an Entity/GameElement that the Entity/GameElement does not have. No object should try to access a non-existitent component so we will thrown an exception. If the user tries to enter bad input into some components we may also throw an exception - ie [0, 0] for a direction vector.
    - 
8.  Why do you think your API/design is _good_ (also define what your measure of good is)?
    - Our design is good because it is flexible and encapusalted. The reasons our design is flexible and encapsualted is detailed in questions 1 and 2.

#### Part 2

1.  Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
    - The use cases we are responsible for are appropriately sized for the job. One example use case we have is trying to get an object to move according to a certain amount of time. This is reasonably sized and is a relatively simple objective, but the action does stretch across multiple classes for each. It is a good inital test use case. The other use cases we are responsible are very similar - how do we make an element of the game respond to events that happen in the game? 
    -
3.  Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
    - Each use case should not take long once the framework we have to build it is working. Since we have defined a very flexible structure for our Game Engines, building the groundwork for implementing Behaviors/Components and EventResponses/Systems will take a substantial amount of initial time. Once the groundwork is built, adding additional behaviors/components and responses/systems should be simple. The uncertainty arises from what challenges we discover trying to build this initial design. There is also uncertainty in us potentially finding an behavior/component that does not integrate well into our system. In such a case, we would have to understand how the action we want could better fit into our system, perhaps by divinding into smaller components.  
5.  What feature/design problem are you most excited to work on?
    - We’re most excited to get the basic functionality of our game up and running: an Entity that moves around and jumps fluidly. Once we have the basics down, we think it will be really fun to add things on top of that. Nailing the simple stuff is important, however, and represents a good challenge for us both.
6.  What feature/design problem are you most worried about working on?
    - We’re most worried about not being able to refactor — once others start to use our API (notably Game Authoring and Game Player), there isn’t a lot of room for flexibility and rewriting code. That would break our teammates’ code in major ways, and we want to avoid that at all costs. It represents a challenging problem for us that we’ve been conscious of as we design our API.

8.  What major feature do you plan to implement this weekend?
    - This weekend, we plan to get our movement internal logic down. Again, having a player up and running makes things easier to test for the other groups and represents a meaningful milestone in the life cycle of our project.

