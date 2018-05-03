Part 1
* What about your API/design is intended to be flexible?

The flexibility of the design is increased by the fact that any entity can contain any combination of components and taking any of these components out can still result in working, compiling versions of the game.

* How is your API/design encapsulating your implementation decisions?

Systems all follow the same interface; they each provide different implementations of the act method. The fact that all of the logic on how a system acts on entities and components is contained within this one public method shows that a lot of the implementation on how these systems work is well-hidden. Also, components are encapsulated in the sense that they do not provide any verbose logic; they are simply glorified data structures that can be written to and read from from other game object.

* How is your part linked to other parts of the project?

The entities are created by the authoring environment along with the components it holds, so that when the values of a component need to be changed, or a new component added to an entity to give it new functionality, the front end must be able to access these back end classes. The data then needs to know how to serialize the classes, followed by the player creating a game loop which interacts with all the systems and managers found in the engine. 
	
* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Systems having to communicate with other systems depending on certain game interactions

Order of system call (ex: jumping can not occur if you are in mid-air, therefore you will have to have multiple systems talking to each other to make sure that certain actions can occur, i.e. jumping)

* Why do you think your API/design is good (also define what your measure of good is)?
	I think our API is good because it clearly defines what values are accessible by other parts of the program at a given time, restricting the potential for bad data accesses. This is done as the components are only modifiable by their values when the authoring environment has access. 
	
Part 2
* Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?

descriptive: yes, gives wide variety of possible combinations

appropriate: in some sense, as we are unsure of how the other modules will function

reasonably sized: sometimes, as there are some basic (user falls on a block) and some more complicated (flying monster shooting fireballs)
	
* Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?

This will take a couple weeks to fully harness our game engine

Multiple components of the same type and event handling between systems (communication/dependencies between systems) makes life uncertain for us.

* What feature/design problem are you most excited to work on?

We are most excited for collision detection

* What feature/design problem are you most worried about working on?

also collision detection

events that occur between systems/system communication

* What major feature do you plan to implement this weekend?

Garbage collection

Collision detection

Creating the framework for moving a character (mobile entity object) around on a “world” with stable entity objects
