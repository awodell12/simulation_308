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


### FileReader


### Cell


### Main


### Visualizer


### Use Cases
1.
2.
3.
4.
5.



## Design Considerations

#### Components



## Team Responsibilities

 * Team Member #1

 * Team Member #2

 * Team Member #3

