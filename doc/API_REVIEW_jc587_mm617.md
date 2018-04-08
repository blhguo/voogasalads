
## Part 1

Martin is responsible for Engine (event handling)

   **What about your API/design is intended to be flexible?**
    Similar to ECS. Each element in the game contains a list of behaviors and responses to certain events. Events occur that cause these behaviors. They heavily use composition. 
    
   **How is your API/design encapsulating your implementation decisions?**
  Authoring environment doesn't really see the game engine, only the interface exposed to it. They are using two interfaces, one for authoring and one for editing, and authoring doesn't have access to the actual engine, but can create objects taht will turn into things in the engine.
  
   **How is your part linked to other parts of the project?**
	Martin is writing game events and event handlers, which closely interact with game elements, and will have to interface w/ UI elements like buttons for certain macro game state.
	
   **What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**
Martin will have to check for improper initialization/ bad object data, where the user might put in improper data when creating or editing objects.

   **Why do you think your API/design is good (also define what your measure of good is)?**
  We think our API designs are good, because our heavy use of composition allows for easy extensibility, as well as backwards compatibility. If done conscientiously, it would minimize api changes that would create issues with other parts fo the project with dependencies on parts of engine.

## Part 2
  **Discuss the use cases/issues that you are responsible for: are they descriptive, appropriate, reasonably sized?**
 Martin is responsible for event handling and creating events for the game. Martin is working on collisions and creating collision events.

  **Estimate how long you think each will take and why. What, if anything, makes estimating these tasks uncertain?**
He thinks he can build basic collision event handling within several days.

 **What feature/design problem are you most excited to work on?**
 Martin is excited to create animation loops and more complex movements.
 
 **What feature/design problem are you most worried about working on?**
 Smooth movement, with continuous collisions. Parallelizing computing events. 
 
  **What major feature do you plan to implement this weekend?**
  All of us are working on Collisions. 