package game;

import java.util.ArrayList;

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
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Paddle) {
				((Paddle) obj).increaseSize();
			}
		}
		return null;
	}
}
