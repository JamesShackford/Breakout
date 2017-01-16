import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * The Paddle object is able to move around with the arrow keys and can reflect
 * bouncers.
 * 
 * @author jimmy
 *
 */
public class Paddle extends FieldObject
{
	private double speed;

	public Paddle()
	{
		this.setImage("paddle.gif");
	}

	/**
	 * Get the speed of the paddle
	 * 
	 * @return double speed
	 */
	public double getSpeed()
	{
		return speed;
	}

	/**
	 * Set the speed of the paddle
	 * 
	 * @param speed
	 *            doubls speed
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		/**
		 * When the right key is pressed, increase the velocity of the paddle
		 * until it reaches a threshold velocity.
		 */
		if (key.getCode().equals(KeyCode.RIGHT)) {
			// if the paddle is moving left, stop it
			if (this.getSpeed() < 0) {
				this.setSpeed(0);
			} else {
				this.setSpeed(this.getSpeed() + 50 * (1 - (this.getSpeed()) / 200));
			}
		}
		/*
		 * When the left key is pressed, decrease the velocity of the paddle
		 * until it reaches a threshold velocity.
		 */
		else if (key.getCode().equals(KeyCode.LEFT)) {
			// if the paddle is moving right, stop it
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
				// if a bouncer hits the paddle, reflect the ball
				if (this.getImage().getBoundsInLocal().intersects(thisBouncer.getImage().getBoundsInLocal())) {
					// what happens if the ball hits the left/right edge of the
					// paddle?
					thisBouncer.setYSpeed(thisBouncer.getYSpeed() * -1);
				}
			}
		}
		// update the position of the paddle based on its speed
		this.setX(this.getX() + this.getSpeed() * secondDelay);
		// slow the paddle down until it reaches 0 speed when no keys are being
		// pressed
		if (this.getSpeed() != 0) {
			this.setSpeed(this.getSpeed() - 3 * Math.signum(this.getSpeed()));
		}
		return addedObjects;
	}

}
