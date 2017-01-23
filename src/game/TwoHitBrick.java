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
	public static final Color FILLCOLOR1 = Color.CRIMSON;
	public static final Color FILLCOLOR2 = Color.DARKMAGENTA;
	public static final Color STROKECOLOR1 = Color.TOMATO;
	public static final Color STROKECOLOR2 = Color.BLACK;

	private int hits;
	private final double POWER_UP_PROBABILITY = 1.05;
	private final boolean REQUIRED_TO_END = true;
	private final int POINTS = 150;

	TwoHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILLCOLOR1, STROKECOLOR1);
		hits = 0;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				scoreCounter.add(POINTS);
				return this.destroy();
			} else {
				hits += 1;
				this.reflectBouncer(bouncer);
				if (hits == 1) {
					this.getSemiRing().setFill(FILLCOLOR2);
					this.getSemiRing().setStroke(STROKECOLOR2);
				} else {
					scoreCounter.add(POINTS);
					return this.destroy();
				}
			}
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
