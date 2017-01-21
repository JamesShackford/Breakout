package game;

import javafx.scene.paint.Color;

/**
 * A regular brick can only be hit once before it is destroyed. It has a low
 * chance of dropping a power-up.
 * 
 * @author jimmy
 *
 */
public class RegularBrick extends Brick
{

	private final double POWER_UP_PROBABILITY = 1.0;

	RegularBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.ROSYBROWN,
				Color.SADDLEBROWN);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			this.reflectBouncer(bouncer);
			return this.destroy();
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return POWER_UP_PROBABILITY;
	}

}
