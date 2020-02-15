# Simulation Design Final
### Austin Odell, Robert Chen, and Nevzat Sevim

## Team Roles and Responsibilities

 * Robert - Configuration and XML

 * Nevzat - Visualization

 * Austin - Simulation and back end

## Design goals
One design goal of the project was to use inheritance to make it easy to add in new types of simulations.
Another design goal was to use encapsulation for communication between the three parts of the project. 
Also to handle exceptions so the program doesn't crash. 

#### What Features are Easy to Add
It is easy to add a new type of Game. This is because all that needs to be done is to create a new grid
subclass and write the checkForUpdates method, create a new XML, very similar to the others, and then 
add new control logic(extra if statement) to display this type of simulation. 

It would be easy to add different arrangements of neighbors. All you would need to do is add the coordinates
of each neighbor relative to the current cell to the XML file for initialization. 

It would not be easy to add different shaped grids. 

## High-level Design

Upon start-up of the program, the MainFx creates a new Simulation Panel, which calls the XML Configuration 
class to read in the type of simulation and the initial values. The XML Parser is also called whenever
a new simulation is loaded. The Panel class then constructs the right type of grid class, and calls
the updateCells and checkForUpdates to make the Grid update. It then receives a copy of the grid so that
it can display the states of the cells in the grid. 

#### Core Classes
* Grid: The parent class for all grids that are the backbone for a cellular automata simulation.
       Maintains the grid of cells. Is extended to make grids for specific types of simulations.
* Cell: Cell objects are what fill up the grid and hold all of the variables that a cell may need to 
remember so it can interact with other cells.
* XMLParser: The purpose of this class is to extract the necessary information from the XML files based on the fields
              specified in Configuration.java, and return a Configuration object that contains all of that information. 
              It also interacts with SimulationPanel in the createGridFromXML method. 
* Reader: Create a FileChooser that allows the user to select an XML file to read.
* MainFX: It creates the Simulation Panel and sets the initial scene and then calls launch which begins the animation
* SimulationPanelFX: This combines everything together. It interacts with the XML classes to initialize
the Grid and pass it the right parameters. It then calls the methods in Grid to move through the generations
and update the cells within the grid. Finally it displays the current state of the grid to the user and
It also creates the ToolBar. Lastly at allows the user to interact with the grid to override the state 
of a cell. 
* ToolBar: This handles all of the input from the user by creating the buttons and other selections and 
then calling the correct methods to implement the desired result of these interactions. 

## Assumptions that Affect the Design
- We assumed that the Grid would always be rectangular. 
- We assumed that XML files would be formatted following our standard
- Assumed the size of the display windows
- Not many errors would occur
- The Visualization would have access to the Cells in the Grid

#### Features Affected by Assumptions
- Making grids out of shapes other than squares.
- Extra XML files need to adhere to these conventions in the existing files
- Doesn't fit well on some screens and part of the screen gets cut off
- If new features caused errors they might not be properly caught
- XML files that weren't formatted correctly would cause the simulation to run wrong.

## New Features HowTo
* For styling aspects of the simulation, have a button that allows users to read in a stylesheet XML file, which is
formatted in a certain way that specifies what aspect should be changed and what the value should be changed to, one
in the tag, one in the content. For example, <gridThickness> 2 </gridThickness>. Create a new Style class in the xml
package, which would be very similar to Configuration, but would instead look for the corresponding style tags. Create
a new method in XMLParser called getStyle that extracts the values out of the xml file. Pass the values to visualization
so that the styling can be applied.

* To save the current state, create a new class XMLWriter, get the state of each cell from the grid, and for cells of
type one or two encode the coordinate under a typeOne or typeTwo root accordingly. Also, get the title and type tags of
the xml file so that it can be written into the new xml file.

* To set initial configuration randomly, add a <probOne> and if applicable a <probTwo> tag in the xml files. Account
for this in the XMLParser. In SimulationPanel, in the createGridFromXML method, allow the grid to be created from
probabilities. To set based on concentration, do pretty much the same thing, and just set the probability to however
many was specified over the total number of cells.

* Error checking is somewhat implemented (poorly and not complete), but just add in other methods in XMLException that
covers the cases listed in complete so that they can be handled more specifically.

#### Easy to Add Features
1. Implementing a grid out of triangles wouldn't be too hard because an x,y coordinate system like the square
one would work for it and then neighbors wouldn't need any adjusting. You would have to decide to enumerate 
the horizontal axis either along the top of a row or the bottom.

#### Other Features not yet Done
1. Implementing a grid out hexagons would be difficult because they don't lend themselves as well to an
x,y coordinate system. With some medium-sized changes this could be done and still utilize a lot of the
current functionality. It would just need an effective coordinate system mapping. 
2. Having the grid expand would be a pretty big overhaul on the back-end but probably could be done on 
the front-end. This is because the front end already allows the size of the grid to change (though this 
resets the simulation), it could be expanded for a growing grid. The back-end of the grid
utilizes a fixed size array so it would not be easy to have this expand well. The core functions of the
program would still work, though a different grid data structure implementation would probably have to be used
one that allows readily for changes in size. 