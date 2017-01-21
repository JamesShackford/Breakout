package game;

import javafx.scene.paint.Color;

/**
 * A regular brick can only be hit once before it is destroyed. It has a low
 * chance of dropping a power-up.
 * 
 * @author jimmy
 *
 */
public class TwoHitBrick extends Brick
{
	private int hits;

	private final double POWER_UP_PROBABILITY = 1.05;

	TwoHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.CRIMSON,
				Color.TOMATO);
		hits = 0;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			hits += 1;
			this.reflectBouncer(bouncer);
			if (hits == 1) {
				this.getSemiRing().setFill(Color.DARKMAGENTA);
				this.getSemiRing().setStroke(Color.BLACK);
			} else {
				return this.destroy();
			}
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return POWER_UP_PROBABILITY;
	}

}
