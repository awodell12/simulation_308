## Design Exercise for Simulation

###Inheritance from Game project

####Bricks
Could have a general abstract brick class with methods:

- public void removeIfDead //if the health is 0 no longer paint brick and make it no longer able to collide
- public ballVelocity collideWithBall // Changes ball movement and removes health from Brick
-


Then have subclasses normal brick, diamond brick, and strong brick

####Power-Ups
Abstract power-up class with methods:

- public boolean collidesWithPaddle () // check if a power-up has been encountered
- public void activatePowerUP() // Enables the power-up to begin
- public void deactivatePowerUP // Turns off the effects of the power-up

Unique subclass for each type of power-up

#### Levels
Abstract class with methods:

- public void setUpBlock // places blocks on playing field
- public boolean isBeaten // if max score achieved (or all blocks gone) returns true
- public boolean isGameOver // if you've run out of levels, go to game-over screen

unique sublcass for each level


### Design Planning Questions
A Cellular Automata is a simulation of a group of cells that interact with each other based on rules.
The cells all have an initial value and then change based on their neighbors states


####Configuration
Reads in and sets the rules and sets the initial values of the cells also sets size of grid

####Simulation
This handles the meat of the program by changing the values of the cells according to the rules it gets from the configuration.


####Visualization
This takes the current state of the board and displays it to the user. It will update the display after
every generation. 

####Questions
1. A cell can now about its neighbors by looking up the state of the cells at neighboring indices in the grid. 
You can update the cells by creating a next-generation grid and then once every cell here is filled in, then you update the original grid with these values.
2. The relationship is the rules determine how the cell will change. 
3. The grid is a 2D array of states. Its most important behavior is to return the state at a given cell location in the grid. 
The grid needs to be known about by the visualization as well as the simulation classes. 
4. The rules for the simulation, the grid dimensions, and the initial states for the cells
5. Iterate through every cell and paint each cell based on its current state

###Use Cases
