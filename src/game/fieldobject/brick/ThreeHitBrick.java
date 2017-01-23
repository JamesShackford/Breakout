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
public class ThreeHitBrick extends Brick
{
	public static final Color FILL_COLOR1 = Color.ORANGE;
	public static final Color FILL_COLOR2 = Color.YELLOW;
	public static final Color FILL_COLOR3 = Color.WHITE;
	public static final Color STROKE_COLOR1 = Color.ORANGERED;
	public static final Color STROKE_COLOR2 = Color.PURPLE;
	public static final Color STROKE_COLOR3 = Color.AZURE;

	private int hits;
	private final double POWER_UP_PROBABILITY = 1.10;
	private final boolean REQUIRED_TO_END = true;
	private final int POINTS = 300;

	public ThreeHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILL_COLOR1,
				STROKE_COLOR1);
		hits = 0;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
	{
		// requires 3 hits by a bouncer before it is destroyed
		// if hit by a fireball, it is instantly destroyed
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				return this.destroy();
			} else {
				hits += 1;
				this.reflectBouncer(bouncer);
				if (hits == 1) {
					this.getSemiRing().setFill(FILL_COLOR2);
					this.getSemiRing().setStroke(STROKE_COLOR2);
				} else if (hits == 2) {
					this.getSemiRing().setFill(FILL_COLOR3);
					this.getSemiRing().setStroke(STROKE_COLOR3);
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
