package game.fieldobject.powerup;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.FieldObject;
import game.fieldobject.Paddle;

public class SizePowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.05;

	public SizePowerUp()
	{
		this.setImage("sizepower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		// increase the size of the paddle
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Paddle) {
				((Paddle) obj).increaseSize();
			}
		}
		return null;
	}
}
