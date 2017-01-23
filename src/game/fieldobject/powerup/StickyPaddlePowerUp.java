package game.fieldobject.powerup;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.FieldObject;
import game.fieldobject.Paddle;

public class StickyPaddlePowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.1;

	public StickyPaddlePowerUp()
	{
		this.setImage("stickypaddlepower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Paddle) {
				((Paddle) obj).setSticky(true);
			}
		}
		return null;
	}
}
