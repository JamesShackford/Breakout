package game;

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
	private int hits;

	private final double POWER_UP_PROBABILITY = 1.10;
	private final boolean REQUIRED_TO_END = true;

	ThreeHitBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.ORANGE,
				Color.ORANGERED);
		hits = 0;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				return this.destroy();
			} else {
				hits += 1;
				this.reflectBouncer(bouncer);
				if (hits == 1) {
					this.getSemiRing().setFill(Color.YELLOW);
					this.getSemiRing().setStroke(Color.PURPLE);
				} else if (hits == 2) {
					this.getSemiRing().setFill(Color.WHITE);
					this.getSemiRing().setStroke(Color.AZURE);
				} else {
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
		// TODO Auto-generated method stub
		return false;
	}

}
