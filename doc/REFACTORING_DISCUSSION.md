## Code Review
### Austin Odell, Robert Chen, and Nevzat Sevim
### 2/6/20 (lab)

##We pushed our changes directly to master

###Issues: 
1. Lots of issues with naming conventions being inconsistent
2. Longest method: checkNeighbors, it looks at all eight neighbors each using a bunch of conditionals.
3. Some issues with nested control sequences (ifs, double for loops)
4. "Use Short circuit logic in boolean contexts" confused us, specifically for this line
```java
booolean step; 
if (step && other logic){
// some stuff
step = !step 
}
```
5. In method declarations using collection interfaces rather than specific implementation.
In this case it was having a method return a List rather than an ArrayList
6. Having public member variables, instead we will switch to getter methods, since this isn't great but
is better than it was
7. Simplifying control sequences
- factor out some of the control into booleans outside the if statement
- Then have the boolean in the if statement
8. 