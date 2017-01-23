package game.fieldobject.brick;

import game.fieldobject.Bouncer;
import game.fieldobject.counter.ScoreCounter;
import game.fieldobject.powerup.PowerUp;
import javafx.scene.paint.Color;

public class UnbreakableBrick extends Brick
{
	public static final Color FILLCOLOR = Color.GRAY;
	public static final Color STROKECOLOR = Color.SILVER;

	private final boolean REQUIRED_TO_END = false;

	public UnbreakableBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd,
			double centerX, double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILLCOLOR, STROKECOLOR);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
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
