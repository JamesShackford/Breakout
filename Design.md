#Design
###Provide the high-level design goals of your project
The design goals of my project were to make a game where a Paddle, Bouncer, Power-Up, Brick, Counter, and Level were all well-defined and allowed easy addition of new features. Moreover, I wanted an easy way to keep track of everything on the screen at a given time, and have a simple solution for adding new objects to the screen. I wanted to have a Splash screen which described the rules of the game and smoothly transitioned to the game when the player was ready to begin. Finally, I wanted an easy way to set up levels and add new ones to the game.
###Explain how to add new features to your project

 - Brick:
	 - Add a class which extends the Brick.java class. Your new brick class must define the following methods:
		 - constructor: calls super() and sets the semiRing to one with a given innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, and characteristic stroke/fill colors which do not match those of another block.
		 - bouncerHit(): checks if a bouncer is intersecting the brick, and if it is, perform some action (such as destroy the brick.) If the brick is destroyed, you must return this.destroy() (so that the necessary power-up can be generated, if applicable), else you should return null to indicate that no power-up has been generated.
		 - getPowerUpProbability(): return the increased likelihood (a double greater than 0) that this brick will release a power-up. The equation used to calculate the power-up that is released is: PowerUp.Probability * brick.getPowerUpProbability()
		 - requiredToEnd(): returns true if this brick must be destroyed in order to end a level, else return false.
Then, in some level, you must have makeBrickConfiguration() method return an arrayList with this brick in order to add it to the level.
 - PowerUp: 
	 - Add a class which extends the PowerUp.java class. Your new power-up class must have a final double called PROBABILITY that is equal to a double value between 0-1 and defines the likelihood that this power-up will be released from a brick. Moreover, your class must define the following methods: 
	 - constructor: sets the image of the power-up with the this.setImage() function.
	 - action(): defines the action that occurs when this power-up collides with a paddle (for example the ExtraBallPowerUp action() method adds an extra Bouncer to the field.
Finally, within PowerUpUtil.java, you must add code to the end of the getPowerUpFunction() so that the new power-up can actually be dropped from a brick. You must add the following code:
```
		currVal += getPowerUpProbability * AddedPowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new AddedPowerUp();
		}
```
This code determines if the power-up was randomly generated, and returns it if it was.
 - Counter: Add a class which extends the Counter.java class. Your class must define the following functions:
	 - constructor: call super(countStartingValue) and then this.getLabel.relocate() to set the position of the counter.
	 - updateLabel(): updates the text of the counter label by calling this.getLabel.setText("new text")
In order to add a counter to the game, add it to the counters ArrayList in Game.java and allow it to be updated appropriately in Game.java's step() method.
 - Level: Create a class which extends Level.java. This class must implement the following methods:
	 - constructor: calls super(field, counters, bouncerSpeed) to define what speed the Bouncer will move at.
	 - makePaddle(): return a new Paddle instance which has a position and size.
	 - makeBrickConfiguration(): Create a Brick layout by instantiating multiple Bricks, setting their locations, and adding them to an arrayList. This method returns the arrayList. Usually, the Bricks are created by using StageBuilder.BuildBricks(), which takes in an arrayList of Strings[] which specify what each brick should be.
 - To add any other object to the screen for a class that implements FieldObject, create an instance of the class and call Field.addElement(obj) in a step() function or in the start() function in Game.java. 
###Justify major design choices, including trade-offs made in your project
 - While breaking up all of the major parts of the program into different classes increases the total amount of code within the project, it greatly increases readability and makes code easier to follow. Moreover, it is much more flexible and updatable.
 - I made FieldCartesianObject and FieldPolarObject to represent objects that have different images. This wasn't the greatest decision because it isn't too flexible and it would be difficult to add a new object that represents a foreign image type. The pros for making two different classes is that the developer can easily determine what type of image the object has based on their class.
 - The probabilities of all the power-ups must sum to a number less than 1, or else some power-ups won't be able to be created. The pros of this are: the probabilities described in the power-up classes actually represent their likelihood of appearing, which increases code readability. The cons are that if the programmer messes up, some power-ups cannot be created.
