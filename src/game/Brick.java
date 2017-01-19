package game;
/*
 * Class which abstractly defines what a brick is. A brick must have methods for:
 * 1. bouncerHit: determining what happens when a bouncer hits it
 * 2. getPowerUpProbability: determining the probability that it will drop a power-up (harder to break bricks
 * should have a higher probability of dropping a power-up)
 * 3. Destroy: determining what happens when the brick is destroyed
 * 4. Set/Get Destroyed: Set/Get the destroyed property
 */
public abstract class Brick extends FieldPolarObject
{
	public static final double BRICK_THICKNESS = 20;

	private boolean destroyed;

	/**
	 * Determines whether a bouncer has hit this brick and returns the PowerUp
	 * that was released, if one was released.
	 * 
	 * @param bouncer
	 *            Bouncer on the Field
	 * @return PowerUp that was released
	 */
	public abstract PowerUp bouncerHit(Bouncer bouncer);

	/**
	 * Get the multiplicative probability that this brick will release a PowerUp
	 * 
	 * @return double probability
	 */
	public abstract double getPowerUpProbability();

	/**
	 * Destroy the block. Destroying a blcok consists of setting it's
	 * 'destroyed' property to false and checking whether or not a PowerUp was
	 * released (the PowerUp will be returned)
	 * 
	 * @return PowerUp released from the block
	 */
	public PowerUp destroy()
	{
		setDestroyed(true);
		this.delete();
		PowerUp power = PowerUpUtil.getPowerUp(getPowerUpProbability());
		return power;
	}

	/**
	 * Set the destroyed property of the brick.
	 * 
	 * @param destroyed
	 *            true if the brick has been destroyed, false if it has not
	 */
	public void setDestroyed(boolean destroyed)
	{
		this.destroyed = destroyed;
	}

	/**
	 * Get whether or not this brick has been destroyed.
	 * 
	 * @return true if the brick has been destroyed. False if it has not.
	 */
	public boolean getDestroyed()
	{
		return destroyed;
	}
}
