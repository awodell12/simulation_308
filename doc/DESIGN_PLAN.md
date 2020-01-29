# Simulation Design Plan
### Team Number 21
### Names Austin Odell, Robert Chen, Nevzat Sevim

## Introduction
The goal of this program is to animate any 2D cellular automata simulation. A cellular automata consists of a grid of cells
where each cell has a current state and a defined set of neighbors, normally adjacent cells. The cells go through generations
where there current state is updated based on the state of their neighbors. 

The primary design goal of this project is to adhere to object-oriented design principles to help make the project flexible.
This is most necessary in the set-up of the simulation where the program need to be able to configure itself based on
rules and initial values read in from a XML text file. 

The project will be broken up into three logical sections: Configuration, Simulation and Visualization

- Configuration
:The goal of this part of the program is to reads in and set the rules, create the grid, and set the initial values of the cells. 

- Simulation:
This handles most of the "back-end" of the program. It updates the values of the cells according to the rules it gets from the configuration.

- Visualization: This takes the current state of the board and displays it to the user. It will update the display after
 every generation. 
## Overview
![Grid CRC](resources/Grid.PNG)
![FileReader CRC](resources/FileReader.PNG)
![Cell CRC](resources/Cell.PNG)
![](resources/Main.PNG)
![](resources/Visualizer.PNG)

The Grid class is an abstract class that has a subclass for each of the simulation types, which will provide their own
implementations of rules. Classes and methods will be described in detail in Design Details. Everything else can be
found in the CRC cards.

## User Interface
![User Interface](resources/UI.PNG)
###Interaction Panel
There will be a panel on the left side of the window that has the buttons and menus for user input. 

- Simulation select: Drop down menu with different simulation types
- Play Button: Moves the simulation along at the selected speed
- Pause Button: Pauses the automatic changing of generations
- Speed Slider: Changes the speed at which the simulation moves through generations. 
- Step Button: Moves to next generation

###Grid Display
On the right half will be the visualization of the grid with different colors corresponding to different cell states.



###Bottom display
Along the bottom of the window there will be panel a that displays the legend, (i.e. what each color in the grid means). 
This bottom panel can also be used to display error messages to the user if the XML file is bad

## Design Details
### Grid 
To create the original grid, the main class will construct a new grid, passing in the size of the grid as well as the 
initial values for the cells. The grid will be implemented as a 2D array, or possibly a map. 
The grid will have lookup capabilities in which you pass it a coordinate pair and it will tell return if the cell need
to be updated by checking the values of the neighboring cells. This method will be able to check if the cell is on an edge.
If it is then it will adjust which cells to check as neighbors by checking fewer cells. Since Grid will be an abstract class, implemented by
each class of the five simulations, the implementation of update methods will be slightly different based on the rules of
each simulation.


### FileReader
Reads in the XML file according to choice chosen by the user via the UI drop-down. It would look up the relevant XML file
in the resources section then parse through it. Extracts which rule set to use based off game-type. The class interacts
with the Grid class.

### Cell
The Cell class is a potential class that we will have. Its responsibilities are to hold its current state, update its
state, and return its state. The class might also hold an image to represent what is in the cell to help with
visualization. Because the class isn't very active, we will continue to look for more functionalities of the class.

### Main
The Main class handles the step function, which represents each frame of the simulation. It does this by invoking a 
method in the Grid class to analyze each cell and see which ones need to be updated. The Main class also links the
classes together, so that Visualizer and Grid can communicate with each other to set the display. This will allow the
visualization to change when the user selects a different simulation type, as well as starting, stopping, and stepping
through the simulation.

### Visualizer
The Visualizer class displays what is read in from the xml file in the Grid class, and updates with each step in the
Main class. The visualizer class will be responsible for handling input from the user via the buttons and drop-down menu.
It will need to communicate with the Main simulation class to actually affect these parameters throughout the whole program.


### Use Cases
1. We will create a GameOfLifeGrid during configuration. The Grid update method will be called on all cells in the Grid.
Within the grid the checkNeighbors method will be invoked examining the eight neighbors,and (>4 will be alive) so then the rules will
be applied to determine the return value of dead. This cell and the new state will be put into a List and after 
every cell is checked all changed cells, including this will be updated. 

2. When the Grid update method is called there will be a check to determine this is an edge cell being looked at. Based on
which edge(s) the cell is on, and then only the 5 (or 3) in bounds neighbors will be checked. In this case 2 or 3 of these 
neighbors are in the on state, so the cell with its next state will be added to the updates List and after all cells are 
checked for updates this cell will get updated. 

3. After all cells are checked, the List of cells to be updated will be iterated through and all necessary updates will 
change the current state of the respective cells. After this method completes, the List will be passed to Main and the
 Visualizer updateDisplay(List) method will be invoked which will change the image for all cells that have been updated. 

4. In the FileReader class the XML Reader will parse this parameter out of the file (it will have its own unique tags)
 and then pass it to the grid upon calling the initializeGrid(Game Parameters) method. This will set the parameter within
 the GameOfLifeGrid.

5. 
The User will select a different simulation from the drop down menu in the UI. This will trigger an event that causes the 
Main class to create a new WatorGrid and get rid of the old SegregationGrid. It will get all of the settable parameters 
through the XML and call the initializeGrid method. The Main will then move into the step method to start stepping through 
the generations of the simulation. 


## Design Considerations

We need to decide how we set organize the connections between the different classes in order to make it so the Main class
is able to run any of the game types and the game specific implementation is done in the other classes (mostly the Grid 
subclasses). 

#### Having the checkNeighbors method in Grid class
We had to decide if we wanted to have the checkNeighbors method inside the cell class or the grid class. We eventually
decided to implement this method inside our grid class.
* pros: The biggest advantage is that conveniently check for every cell inside one class and store the next state of each
in a separate list before actually updating the cells themselves.
* cons: The grid class will be responsible for many methods and we fear that it might get a little too convoluted.

#### Having a Cell class
* pros: We can store images and states of each grid cell independently of the actual grid array. 
* cons: It introduces another layer of abstraction 

#### Inheritance hierarchy for the Grid class

* pros: It makes it easier to implement multiple types of games without having to change things that are used across all 
game types. By doing so it reduces code duplication. 
* cons: None of us have ever used inheritance in this way before so there will be a bit of a learning curve. c

## Team Responsibilities

 * Robert Chen
 primary: Configuration
 secondary: Simulation

 * Austin Odell
 primary: Simulation
 secondary: whatever is lacking

 * Nevzat Sevim
 primary: Visualization
 secondary: Simulation

