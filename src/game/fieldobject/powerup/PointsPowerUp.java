package game.fieldobject.powerup;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.FieldObject;
import game.fieldobject.counter.ScoreCounter;

public class PointsPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.1;
	public static final int POINTS = 500;

	public PointsPowerUp()
	{
		this.setImage("pointspower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		// add points to the player's score
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof ScoreCounter) {
				((ScoreCounter) obj).add(POINTS);
			}
		}
		return null;
	}
}
