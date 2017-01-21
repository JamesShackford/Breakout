package game;

import java.util.ArrayList;

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

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		ArrayList<FieldObject> objects = field.getFieldElements();
		ArrayList<FieldObject> newObjects = new ArrayList<FieldObject>();
		// check if a bouncer is hitting this brick, and if it is, then destroy
		// the block and possibly create a PowerUp
		for (FieldObject currElem : objects) {
			if (currElem instanceof Bouncer) {
				PowerUp power = bouncerHit((Bouncer) currElem);
				if (power != null) {
					double middleDegree = (this.getDegreeBegin() + this.getDegreeEnd()) / 2;
					double[] cartesianCoords = PolarUtil.toCartesian(this.getInnerRadius(), middleDegree - 90);
					System.out.println(this.getInnerRadius() + ", " + middleDegree + "-->" + cartesianCoords[0] + ","
							+ cartesianCoords[1]);
					power.setX(cartesianCoords[0] + Field.CENTER_X);
					power.setY(cartesianCoords[1] + Field.CENTER_Y);
					power.setSpeed(20);
					power.setDirection(PolarUtil.getNormalVector(power.getX(), power.getY(), Field.CENTER_X,
							Field.CENTER_Y, true));
					newObjects.add(power);
				}
			}
		}
		return newObjects;
	}

	public void reflectBouncer(Bouncer bouncer)
	{
		double bouncerXRadius = bouncer.getImage().getBoundsInLocal().getWidth() / 2;
		/*
		 * If a bouncer is hitting the right/left edges of the brick, then
		 * reflect the bouncer along that edge
		 */
		double[] polarCoords = PolarUtil.toPolar(bouncer.getX(), bouncer.getY());

		double[] normalVector;
		normalVector = PolarUtil.getNormalVector(bouncer.getX(), bouncer.getY(), Field.CENTER_X, Field.CENTER_Y, false);
		double[] reflectionVector = PolarUtil.getReflectionVector(bouncer.getDirection(), normalVector);
		bouncer.setDirection(reflectionVector);
	}

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
