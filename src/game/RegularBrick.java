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
	public static final Color FILLCOLOR = Color.ROSYBROWN;
	public static final Color STROKECOLOR = Color.SADDLEBROWN;
	private final double POWER_UP_PROBABILITY = 1.0;
	private final boolean REQUIRED_TO_END = true;
	private final int POINTS = 50;

	RegularBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILLCOLOR, STROKECOLOR);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (!bouncer.isFireball()) {
				this.reflectBouncer(bouncer);
			}
			scoreCounter.add(POINTS);
			return this.destroy();
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return POWER_UP_PROBABILITY;
	}

	@Override
	public boolean requiredToEnd()
	{
		return REQUIRED_TO_END;
	}

}
