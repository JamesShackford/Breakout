package game;

import javafx.scene.paint.Color;

public class IceBrick extends Brick
{
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
			this.reflectBouncer(bouncer);
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return 0;
	}
}
