import java.util.ArrayList;

/*
 * Power Up which gives the player an extra ball.
 */
public class ExtraBallPower extends PowerUp
{
	public static final double PROBABILITY = 1.0;

	public ExtraBallPower()
	{
		this.setImage("extraballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		// If the power up hasn't already been destroyed (activated), then check
		// if it is touching the paddle and if it
		// is, then destroy the power up and return an ArrayList which contains
		// the new bouncer to add to the field.
		if (!this.getDestroyed()) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					if (this.getImage().getBoundsInLocal().intersects(obj.getImage().getBoundsInLocal())) {
						this.setDestroyed(true);
						return action(field);
					}
				}
			}
			// update the position of the power up based on its velocity
			this.setY(this.getY() + this.getSpeed() * secondDelay);
		}
		return null;
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
