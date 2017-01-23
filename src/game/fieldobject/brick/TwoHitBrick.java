package game.fieldobject.brick;

import game.fieldobject.Bouncer;
import game.fieldobject.counter.ScoreCounter;
import game.fieldobject.powerup.PowerUp;
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
	public static final Color FILL_COLOR1 = Color.CRIMSON;
	public static final Color FILL_COLOR2 = Color.DARKMAGENTA;
	public static final Color STROKE_COLOR1 = Color.TOMATO;
	public static final Color STROKE_COLOR2 = Color.BLACK;

	private int hits;
	private final double POWER_UP_PROBABILITY = 1.05;
	private final boolean REQUIRED_TO_END = true;
	private final int POINTS = 150;

	public TwoHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILL_COLOR1,
				STROKE_COLOR1);
		hits = 0;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
	{
		// requires 2 hits by a bouncer before it is destroyed
		// if hit by a fireball, it is destroyed automatically
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				scoreCounter.add(POINTS);
				return this.destroy();
			} else {
				hits += 1;
				this.reflectBouncer(bouncer);
				if (hits == 1) {
					this.getSemiRing().setFill(FILL_COLOR2);
					this.getSemiRing().setStroke(STROKE_COLOR2);
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
