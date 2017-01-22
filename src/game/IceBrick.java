package game;

import javafx.scene.paint.Color;

public class IceBrick extends Brick
{
	private final double POWER_UP_PROBABILITY = 1.20;
	private final boolean REQUIRED_TO_END = false;

	IceBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.LIGHTSKYBLUE,
				Color.DEEPSKYBLUE);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				return this.destroy();
			} else {
				this.reflectBouncer(bouncer);
			}
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return 0;
	}

	@Override
	public boolean requiredToEnd()
	{
		return REQUIRED_TO_END;
	}

}