Part 1
What about your API/design is intended to be flexible?
We designed our code in such as way that it would be really easy for a user of the VOOGA application to create new game elements in the authoring environment and allows these users to inherit certain properties(behaviors), and respond to events that occur in the game in unique ways defined by event responses. This design architecture is formally names an entity component system where the game element is the entity, and the behaviour is defined by the events and event responses. How is your API/design encapsulating your implementation decisions?
How is your part linked to other parts of the project?
Our code is well designed because it prevents game elements from having to inherit only the behaviours (properties associated with element) that it deems necessary. It links with the display because each object will contain its position and Imageview. In addition, the code works well with Data and Authoring Environment by designing the code in a way that only allows the user to select event responses authenticated by the behaviors chosen for the element. For example, if a game element doesn’t have the movable behavior( x-velocity, y-velocity), it cannot inherit event responses such as moveleftright or others associated with motion of object.
What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Error handling will be associated with preventing code from creating objects that don’t exists or preventing the user to add event responses that aren’t part of the behaviors. 
Why do you think your API/design is good (also define what your measure of good is)?
Look at part A. I explained why our design is flexible and “good”. Our measure of good design is making it really easy for a 3rd party coder to allow objects to respond to different types of events in unique ways. (defined by authoring environment). In addition, the elements shouldn’t be hardcoded into the code allowing users to create custom elements. 
Part 2
Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?
Most use cases are concise and have a clear objective. The description of the use cases tackle both simple and complicated issues of the game.
Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?
Some use cases will take a relatively short amount of time if the API is well-designed. Simple cases like creating an Entity (any object in the game) will require an instantiation of the object and the display of the object (a connection between the engine, authoring, and data). However, other cases (such as handling multiple events) can take a bit longer. For example, if Mario collects 10 coins and then the boss appears, this is a series of triggers and events (Mario collecting the 10 coins and the appearance of the boss). Handling the sequences of events may take more time because of the complexity of the issue.
What feature/design problem are you most excited to work on?
I’m most excited to work on the entity-component system design, in which we will define the proper systems to handle one or more components of an entity. 
What feature/design problem are you most worried about working on?
I’m most worried about handling multiple events because that may require Entity containing other Entities or other design decisions to be made.
What major feature do you plan to implement this weekend?
I plan to create the basic components and systems of an entity, and have the game author be able to create an entity and move it using key input. Furthermore, the game should be able to handle different kinds of collisions and basic level changes.
