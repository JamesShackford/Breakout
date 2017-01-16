import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
		for (FieldObject currElem : objects) {
			if (currElem instanceof Bouncer) {
				PowerUp power = bouncerHit((Bouncer) currElem);
				if (power != null) {
					power.setImage("extraballpower.gif");
					power.setX(this.getX() + (this.getImage().getBoundsInLocal().getWidth() / 2));
					power.setY(this.getY() + (this.getImage().getBoundsInLocal().getHeight() / 2));
					power.setVelocity(100);
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
			if (bouncer.getX() + bouncerXRadius >= this.getX() + this.getImage().getBoundsInLocal().getWidth()
					|| bouncer.getX() + bouncerXRadius <= this.getX()) {
				bouncer.setXSpeed(bouncer.getXSpeed() * -1);
				bouncer.setX(bouncer.getX() + Math.signum(bouncer.getXSpeed()));
			} else {
				bouncer.setYSpeed(bouncer.getYSpeed() * -1);
				// update position so can't hit 2 bricks at once
				bouncer.setY(bouncer.getY() + Math.signum(bouncer.getYSpeed()));
			}
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
