package game.level;

/**
 * Abstractly define what a level is. A level must have an associated Field
 * and it must be able to say when it has been complete.
 */
import java.util.ArrayList;

import game.Field;
import game.fieldobject.Bouncer;
import game.fieldobject.FieldObject;
import game.fieldobject.Paddle;
import game.fieldobject.Planet;
import game.fieldobject.brick.Brick;
import game.fieldobject.counter.Counter;

public abstract class Level
{
	private Field field;

	public Level(Field field, ArrayList<Counter> counters, double bouncerSpeed)
	{
		// add bouncer, planet, paddle, and counters to the level.
		field.getFieldElements().clear();
		field.refreshImages();
		Bouncer myBouncer = new Bouncer();
		myBouncer.setSpeed(bouncerSpeed);
		Planet planet = new Planet();
		Paddle paddle = makePaddle();
		ArrayList<Brick> brickConfiguration = makeBrickConfiguration();

		field.addElement(myBouncer);
		field.addElement(planet);
		field.addElement(paddle);
		for (Brick elem : brickConfiguration) {
			field.addElement(elem);
		}
		for (Counter counter : counters) {
			field.addElement(counter);
		}
	}

	public Field getField()
	{
		return this.field;
	}

	public abstract Paddle makePaddle();

	public abstract ArrayList<Brick> makeBrickConfiguration();

	public boolean levelComplete(Field field)
	{
		// a level is complete when all bricks that need to be destroyed have
		// been destroyed
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Brick && !((Brick) obj).getDestroyed() && ((Brick) obj).requiredToEnd()) {
				return false;
			}
		}
		return true;
	}

}
