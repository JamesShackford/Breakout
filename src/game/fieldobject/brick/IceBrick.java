package game.fieldobject.brick;

import game.fieldobject.Bouncer;
import game.fieldobject.counter.ScoreCounter;
import game.fieldobject.powerup.PowerUp;
import javafx.scene.paint.Color;

public class IceBrick extends Brick
{
	public final static Color FILL_COLOR = Color.LIGHTSKYBLUE;
	public final static Color STROKE_COLOR = Color.DEEPSKYBLUE;

	private final double POWER_UP_PROBABILITY = 1.20;
	private final boolean REQUIRED_TO_END = false;
	private final int POINTS = 500;

	public IceBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		super();
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILL_COLOR, STROKE_COLOR);
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer, ScoreCounter scoreCounter)
	{
		// ice brick only destroyed if hit by fireball
		if (this.intersects(bouncer) && !getDestroyed()) {
			if (bouncer.isFireball()) {
				scoreCounter.add(POINTS);
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
