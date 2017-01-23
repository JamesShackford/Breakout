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
	public static final Color FILLCOLOR1 = Color.ORANGE;
	public static final Color FILLCOLOR2 = Color.YELLOW;
	public static final Color FILLCOLOR3 = Color.WHITE;
	public static final Color STROKECOLOR1 = Color.ORANGERED;
	public static final Color STROKECOLOR2 = Color.PURPLE;
	public static final Color STROKECOLOR3 = Color.AZURE;

	private int hits;
	private final double POWER_UP_PROBABILITY = 1.10;
	private final boolean REQUIRED_TO_END = true;
	private final int POINTS = 300;

	public ThreeHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
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
				return this.destroy();
			} else {
				hits += 1;
				this.reflectBouncer(bouncer);
				if (hits == 1) {
					this.getSemiRing().setFill(FILLCOLOR2);
					this.getSemiRing().setStroke(STROKECOLOR2);
				} else if (hits == 2) {
					this.getSemiRing().setFill(FILLCOLOR3);
					this.getSemiRing().setStroke(STROKECOLOR3);
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
