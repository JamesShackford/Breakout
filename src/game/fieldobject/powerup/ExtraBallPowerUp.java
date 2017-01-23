package game.fieldobject.powerup;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.Bouncer;
import game.fieldobject.FieldObject;

/*
 * Power Up which gives the player an extra ball.
 */
public class ExtraBallPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.05;

	public ExtraBallPowerUp()
	{
		this.setImage("extraballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		// add a bouncer to the field
		Bouncer bouncer = new Bouncer();
		ArrayList<FieldObject> addedObjs = new ArrayList<FieldObject>();
		addedObjs.add(bouncer);
		return addedObjs;
	}
}
