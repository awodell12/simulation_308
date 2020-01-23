### Design Exercise

RPS Design:
1) A class for each of the weapons, which inherit from an abstract Weapon class. They take in parameters that indicate
what they are defeated by, what they defeat, and what weapon they are playing against.

2) The Weapon class is the parent class for all the weapons, and has two Lists that represent what weapons it beats
and what weapons it is beaten by. The responsibilities are to check if it is beaten or if it wins. The collaborator is
Main class.

3) 
* We would need a resetScore method in the Main class and an instance variable in the same class that holds the score.
* We would take user input from the console and create a new subclass based on that
* We need a method that checks who won the round by accessing the weapon's defeatedBy list and then increment the score
accordingly
* We need to update each subclass's lists of defeatedBy and defeats.
* We'd change the lists of defeatedBy and defeats and possibly change some subclass names accordingly
