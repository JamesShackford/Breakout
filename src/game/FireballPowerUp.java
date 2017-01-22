package game;

import java.util.ArrayList;

public class FireballPowerUp extends PowerUp
{
	public static final double PROBABILITY = 0.5;

	public FireballPowerUp()
	{
		this.setImage("fireballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> action(Field field)
	{
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Bouncer && ((Bouncer) obj).getImage() != null && !((Bouncer) obj).isFireball()) {
				((Bouncer) obj).setFireball(true);
				return null;
			}
		}
		return null;
	}
}
