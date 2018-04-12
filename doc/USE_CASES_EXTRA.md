Use Cases -- Complete Implementation
==
## Authoring Environment
### Camera and Scroll
1. Infinite scroll (or, background image regeneration for scrolling)
2. Enable users to scroll horizontally or vertically
3. Scrollable view within canvas camera

### Loading
4. Generate thumbnails for already-created games
6. Load the authoring environment with a partially-created game

### Levels
6. Add music to a level
9. Create a way to have level-change events (e.g., user hits a flag and the level continues)
10. Implement Storyboard pane to allow a user to create and reorder multiple levels

### Functionality
9. Be able to kill an entity during the game
10. Be able to give an entity some initial health at the beginning of each level
11. Give entities the ability to collect coins

### Utility and Aesthetics
12. Live editing to modify game during play
13. Enable full-screen throughout game editing and play
14. Fonts are both SegoeUIL and Segoe Regular weight
15. Eliminate white lines around border of titled-panes
16. Turn EntityPane views into ScrollPanes to avoid additional entity-creation modifying window size


## Engine
### Collisions
17. User can create hitboxes of any regular convex polygon
18. An entity can be set to "die" on collision with a specific entity
19. An entity can trigger a level change when it "collides" with a certain location on the screen
20. An entity can lose health points when hit by a bullet entity from only the right side
21. Health points can be dynamic (e.g. small Mario will die if it is hit once, but if small Mario eats a mushroom and becomes big Mario, its first hit will make it small Mario and a second hit will cause him to die)

### Gameplay
22. User can end a level by picking up the Nth coin.
23. User is limited to 2 jumps that reset every time they hit the ground.
24. An enemy can pace back and forth.
25. A user is given a score at the end of the level, describing how well or poorly they performed.
26. The game ends if the player fails to achieve a goal before a time threshold
27. The player can switch between using different powerups (e.g. switch from throwing fireballs to shooting bullets)
28. The player can use multiple power-ups at once (e.g. throwing fireballs and invincibility at the same time)

## Data
29. Saving multiple files to represent different games
30. Integration with player to work with level folder selection
31. Game folder hierarchy for meta data and extraneous non-core information
32. Special save state folders, to separate between "game saves" and "gamer saves"
33. Asset and image handling within folder hierarchy

## Player
34. Player can change volume of game through Slider
35. Player can change brightness of game screen through slider
36. Player can find Networked games that are starting
37. Player can start Networked game with specific friends
38. Player can change background music of game
39. Player can choose and switch between multiple games
40. Player can save and load game states at any time
41. Player can set certain key preferences to each game
42. Player can choose to reset a game and restart it, clearing previous saved data
43. Player can reset preferencencence