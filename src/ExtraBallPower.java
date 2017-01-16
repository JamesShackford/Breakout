import java.util.ArrayList;

public class ExtraBallPower extends PowerUp
{
	public static final double PROBABILITY = 0.1;
	public double velocity;

	public ExtraBallPower()
	{
		this.setImage("extraballpower.gif");
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		if (!this.getDestroyed()) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					if (this.getImage().getBoundsInLocal().intersects(obj.getImage().getBoundsInLocal())) {
						this.setDestroyed(true);
						return action(field);
					}
				}
			}
			this.setY(this.getY() + this.getVelocity() * secondDelay);
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

	@Override
	public void setVelocity(double velocity)
	{
		this.velocity = velocity;
	}

	@Override
	public double getVelocity()
	{
		return velocity;
	}

}
