import java.util.ArrayList;

public abstract class PowerUp extends FieldObject
{
	private boolean destroyed;

	public abstract ArrayList<FieldObject> action(Field field);

	public void setDestroyed(boolean destroyed)
	{
		this.getImage().setImage(null);
		this.destroyed = destroyed;
	}

	public abstract void setVelocity(double velocity);

	public boolean getDestroyed()
	{
		return destroyed;
	}

	public abstract double getVelocity();
}
