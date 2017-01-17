import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bouncer extends FieldCartesianObject
{
	private final double xStartPosition = Field.SIZE / 2;
	private final double yStartPosition = Field.SIZE / 2;
	public static final double normalSpeed = Field.SIZE / (200 * Game.SECOND_DELAY);

	private double speed;
	// x direction is index 0, y direction is index 1
	// this must be a unit vector
	private double[] velocityDirection;

	private boolean started;

	public Bouncer()
	{
		this.started = false;
		this.setImage("ball.gif");
		this.setDirection(new double[] { 1.0, 1.0 });
		this.setSpeed(normalSpeed);
	}

	public double getXSpeed()
	{
		return velocityDirection[0] * speed;
	}

	public double getYSpeed()
	{
		return velocityDirection[1] * speed;
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

	public double[] getDirection()
	{
		return velocityDirection;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public void setDirection(double[] direction)
	{
		this.velocityDirection = PolarUtil.getUnitVector(direction);
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
			this.setDirection(
					PolarUtil.getNormalVector(this.getX(), this.getY(), Field.CENTER_X, Field.CENTER_Y, false));
			this.setX(this.getX() + 4 * this.getXSpeed() * Game.SECOND_DELAY);
			this.setY(this.getY() + 4 * this.getYSpeed() * Game.SECOND_DELAY);
			this.setStarted(true);
		}
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		// If the ball has not started moving yet, the ball will move with the
		// paddle (it will remain attached to the top of the paddle)
		if (this.getStarted() == false) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					Paddle paddle = (Paddle) obj;
					double middleDegree = 90 - (paddle.getDegreeBegin() + paddle.getDegreeEnd()) / 2;
					double[] cartesianCoords = PolarUtil.toCartesian(paddle.getOuterRadius() + this.getRadius(),
							middleDegree);
					this.setX(Field.CENTER_X + cartesianCoords[0]);
					this.setY(Field.CENTER_Y - cartesianCoords[1]);
				}
			}
		} else {
			// update the position of the bouncer based on its speed
			this.setX(this.getX() + this.getXSpeed() * secondDelay);
			this.setY(this.getY() + this.getYSpeed() * secondDelay);
		}

		// if the ball hits the left or right borders of the field, the ball
		// will reflect off of it
		if (getX() - this.getRadius() <= 0 || getX() + this.getRadius() >= field.getScene().getHeight()) {
			this.setDirection(new double[] { -1 * this.getDirection()[0], this.getDirection()[1] });
		}

		// if the ball hits the top or bottom borders of the field, the ball
		// will reflect off of it
		if (getY() - this.getRadius() <= 0 || getY() + this.getRadius() >= field.getScene().getWidth()) {
			this.setDirection(new double[] { this.getDirection()[0], -1 * this.getDirection()[1] });
		}
		return null;
	}

}
