import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A regular brick can only be hit once before it is destroyed. It has a low
 * chance of dropping a power-up.
 * 
 * @author jimmy
 *
 */
public class RegularBrick extends Brick
{

	private final double POWER_UP_PROBABILITY = 1.0;

	RegularBrick()
	{
		Image im = new Image(getClass().getClassLoader().getResourceAsStream("brick3.gif"));
		ImageView view = new ImageView(im);
		view.setPreserveRatio(true);
		view.setFitHeight(15);
		setImage(view);

	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		ArrayList<FieldObject> objects = field.getFieldElements();
		ArrayList<FieldObject> newObjects = new ArrayList<FieldObject>();
		// check if a bouncer is hitting this brick, and if it is, then destroy
		// the block and possibly create a PowerUp
		for (FieldObject currElem : objects) {
			if (currElem instanceof Bouncer) {
				PowerUp power = bouncerHit((Bouncer) currElem);
				if (power != null) {
					power.setX(this.getX() + (this.getImage().getBoundsInLocal().getWidth() / 2));
					power.setY(this.getY() + (this.getImage().getBoundsInLocal().getHeight() / 2));
					power.setSpeed(100);
					newObjects.add(power);
				}
			}
		}
		return newObjects;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{

		if (!getDestroyed() && bouncer.getImage().getBoundsInLocal().intersects(this.getImage().getBoundsInLocal())) {
			double bouncerXRadius = bouncer.getImage().getBoundsInLocal().getWidth() / 2;
			/*
			 * If a bouncer is hitting the right/left edges of the brick, then
			 * reflect the bouncer along that edge
			 */
			if (bouncer.getX() + bouncerXRadius >= this.getX() + this.getImage().getBoundsInLocal().getWidth()
					|| bouncer.getX() + bouncerXRadius <= this.getX()) {
				bouncer.setXSpeed(bouncer.getXSpeed() * -1);
				bouncer.setX(bouncer.getX() + Math.signum(bouncer.getXSpeed()));
			}
			/*
			 * If the bouncer is hitting the top/bottom edges of the brick, then
			 * reflect the bouncer off that edge.
			 */
			else {
				bouncer.setYSpeed(bouncer.getYSpeed() * -1);
				// update position so can't hit 2 bricks at once
				bouncer.setY(bouncer.getY() + Math.signum(bouncer.getYSpeed()));
			}
			// destroy this brick and return a PowerUp if one was produced
			return this.destroy();
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return POWER_UP_PROBABILITY;
	}

}
