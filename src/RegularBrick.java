import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegularBrick extends Brick
{

	RegularBrick()
	{
		Image im = new Image(getClass().getClassLoader().getResourceAsStream("brick3.gif"));
		ImageView view = new ImageView(im);
		view.setPreserveRatio(true);
		view.setFitHeight(15);
		setImage(view);

	}

	@Override
	public void step(double secondDelay, Field field)
	{
		ArrayList<FieldObject> objects = field.getFieldElements();
		for (FieldObject currElem : objects) {
			if (currElem instanceof Bouncer) {
				bouncerHit((Bouncer) currElem);
			}
		}
	}

	@Override
	public void bouncerHit(Bouncer bouncer)
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
			setDestroyed(true);
			getImage().setImage(null);
		}

	}

}
