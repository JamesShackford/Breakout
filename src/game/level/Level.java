package game.level;

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

	public Level(Field field, ArrayList<Counter> counters)
	{
		field.getFieldElements().clear();
		field.refreshImages();
		Bouncer myBouncer = new Bouncer();
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
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Brick && ((Brick) obj).requiredToEnd() && !((Brick) obj).getDestroyed()) {
				return false;
			}
		}
		return true;
	}

}
