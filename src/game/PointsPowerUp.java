package game;

import java.util.ArrayList;

public class PointsPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.5;
	public static final int POINTS = 500;

	public PointsPowerUp()
	{
		this.setImage("pointspower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof ScoreCounter) {
				((ScoreCounter) obj).add(POINTS);
			}
		}
		return null;
	}
}
