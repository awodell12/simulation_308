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
