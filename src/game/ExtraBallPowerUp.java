package game;

import java.util.ArrayList;

/*
 * Power Up which gives the player an extra ball.
 */
public class ExtraBallPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.1;

	public ExtraBallPowerUp()
	{
		this.setImage("extraballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		Bouncer bouncer = new Bouncer();
		ArrayList<FieldObject> addedObjs = new ArrayList<FieldObject>();
		addedObjs.add(bouncer);
		return addedObjs;
	}
}
