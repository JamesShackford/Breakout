import java.util.ArrayList;

/**
 * Abstractly define a PowerUp as an object that, when it touches a paddle,
 * performs an action. A PowerUp has a velocity. Whenever it touches the paddle,
 * it is destroyed and can't perform the action again.
 * 
 * @author jimmy
 *
 */
public abstract class PowerUp extends FieldCartesianObject
{
	private boolean destroyed;
	private double speed;

	/**
	 * The action that is performed when the PowerUp touches the paddle.
	 * 
	 * @param field
	 *            Field object which represents the stage and the objects on the
	 *            stage.
	 * @return ArrayList of Field Objects to add to the Field.
	 */
	public abstract ArrayList<FieldObject> action(Field field);

	/**
	 * States whether or not the PowerUp has already performed an action and is
	 * destroyed.
	 * 
	 * @param destroyed
	 *            True if the PowerUp is destroyed, false if it isn't
	 */
	public void setDestroyed(boolean destroyed)
	{
		this.getImage().setImage(null);
		this.destroyed = destroyed;
	}

	/**
	 * Set the velocity of the PowerUp
	 * 
	 * @param velocity
	 *            speed that the PowerUp moves at
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	/**
	 * Gets whether the PowerUp has been destroyed or not.
	 * 
	 * @return True if the PowerUp has been destroyed, false if it has not.
	 */
	public boolean getDestroyed()
	{
		return destroyed;
	}

	/**
	 * Gets the velocity of the PowerUp.
	 * 
	 * @return velocity of the PowerUp
	 */
	public double getSpeed()
	{
		return speed;
	}
}
