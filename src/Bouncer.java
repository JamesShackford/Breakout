import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bouncer extends FieldObject
{
	private final double xStartPosition = Field.SIZE / 2;
	private final double yStartPosition = Field.SIZE / 2;
	public static final double normalSpeed = Field.SIZE / (200 * Game.SECOND_DELAY);

	private double xSpeed;
	private double ySpeed;

	private boolean started;

	public Bouncer()
	{
		this.started = false;
		this.setImage("ball.gif");
		this.setXSpeed(normalSpeed);
		this.setYSpeed(-normalSpeed);
	}

	public double getXSpeed()
	{
		return xSpeed;
	}

	public double getYSpeed()
	{
		return ySpeed;
	}

	/**
	 * True if this bouncer has begun to move. If the spacebar is pressed while
	 * the bouncer is stopped, the bouncer will begin to move
	 * 
	 * @return true if bouncer is moving, false if bouncer is stuck to paddle
	 */
	public boolean getStarted()
	{
		return started;
	}

	/**
	 * set the x speed of the bouncer
	 * 
	 * @param xSpeed
	 *            x speed
	 */
	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	/**
	 * set the y speed of the bouncer
	 * 
	 * @param ySpeed
	 *            y speed
	 */
	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	/**
	 * Allow the ball to begin moving.
	 * 
	 * @param started
	 *            true-> ball can move, false->ball can't move and is instead
	 *            attached to the paddle
	 */
	public void setStarted(boolean started)
	{
		this.started = started;
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		// Start the ball when the spacebar is pressed.
		if (key.getCode().equals(KeyCode.SPACE)) {
			this.setStarted(true);
		}
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		// If the ball has not started moving yet, the ball will move with the
		// paddle (it
		// will remain attached to the top of the paddle
		if (this.getStarted() == false) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					this.setX(obj.getX() + obj.getImage().getBoundsInLocal().getWidth() / 2);
					this.setY(obj.getY() - this.getImage().getBoundsInLocal().getHeight());
				}
			}
		}
		// update the position of the bouncer based on its speed
		this.getImage().setX(getX() + xSpeed * secondDelay);
		this.getImage().setY(getY() + ySpeed * secondDelay);

		// if the ball hits the left or right borders of the field, the ball
		// will reflect off of it
		if (getX() <= 0 || getX() + this.getImage().getBoundsInLocal().getHeight() >= field.getScene().getHeight()) {
			setXSpeed(xSpeed * -1);
		}

		// if the ball hits the top or bottom borders of the field, the ball
		// will reflect off of it
		if (getY() <= 0 || getY() + this.getImage().getBoundsInLocal().getWidth() >= field.getScene().getWidth()) {
			setYSpeed(ySpeed * -1);
		}
		return null;
	}

}
