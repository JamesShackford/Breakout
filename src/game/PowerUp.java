package game;
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
	private double[] velocityDirection;

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
					if (this.getImage().getBoundsInLocal().intersects(obj.getNode().getBoundsInLocal())) {
						this.setDestroyed(true);
						return action(field);
					}
				}
				if (obj instanceof Planet) {
					double radialDistance = PolarUtil.toPolar(this.getX() - Field.CENTER_X,
							this.getY() - Field.CENTER_Y)[0];
					if (radialDistance <= ((Planet) obj).getRadius()) {
						this.setDestroyed(true);
						return null;
					}
				}
			}
			// update the position of the power up based on its velocity
			this.setX(this.getX() + this.getSpeed() * this.getDirection()[0] * secondDelay);
			this.setY(this.getY() + this.getSpeed() * this.getDirection()[1] * secondDelay);
		}
		return null;
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

	public void setDirection(double[] direction)
	{
		this.velocityDirection = PolarUtil.getUnitVector(direction);
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

	public double[] getDirection()
	{
		return this.velocityDirection;
	}
}
