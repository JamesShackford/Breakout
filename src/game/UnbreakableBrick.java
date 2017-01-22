package game;

import javafx.scene.paint.Color;

public class UnbreakableBrick extends Brick
{
	private final boolean REQUIRED_TO_END = false;

	UnbreakableBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.GRAY, Color.SILVER);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			this.reflectBouncer(bouncer);
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
