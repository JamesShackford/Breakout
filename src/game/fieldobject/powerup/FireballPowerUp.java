package game.fieldobject.powerup;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.Bouncer;
import game.fieldobject.FieldObject;

public class FireballPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.03;

	public FireballPowerUp()
	{
		this.setImage("fireballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		// turn one bouncer into a fireball. The bouncer that turns into a
		// fireball
		// is determined by when it was added to the field.
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Bouncer && ((Bouncer) obj).getImage() != null && !((Bouncer) obj).isFireball()) {
				((Bouncer) obj).setFireball(true);
				return null;
			}
		}
		return null;
	}
}
