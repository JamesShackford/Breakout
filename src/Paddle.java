import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Paddle extends FieldObject
{
	private double speed;

	public Paddle()
	{
		this.setImage("paddle.gif");
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		if (key.getCode().equals(KeyCode.RIGHT)) {
			if (this.getSpeed() < 0) {
				this.setSpeed(0);
			} else {
				this.setSpeed(this.getSpeed() + 50 * (1 - (this.getSpeed()) / 200));
			}
		} else if (key.getCode().equals(KeyCode.LEFT)) {
			if (this.getSpeed() > 0) {
				this.setSpeed(0);
			} else {
				this.setSpeed(this.getSpeed() - 50 * (1 + (this.getSpeed()) / 200));
			}
		}
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		ArrayList<FieldObject> addedObjects = new ArrayList<FieldObject>();
		for (FieldObject obj : field.getFieldElements()) {
			if (obj instanceof Bouncer) {
				Bouncer thisBouncer = (Bouncer) obj;
				if (this.getImage().getBoundsInLocal().intersects(thisBouncer.getImage().getBoundsInLocal())) {
					// what happens if the ball hits the left/right edge of the
					// paddle?
					thisBouncer.setYSpeed(thisBouncer.getYSpeed() * -1);
				}
			}
		}
		this.setX(this.getX() + this.getSpeed() * secondDelay);
		if (this.getSpeed() != 0) {
			this.setSpeed(this.getSpeed() - 3 * Math.signum(this.getSpeed()));
		}
		return addedObjects;
	}

}
