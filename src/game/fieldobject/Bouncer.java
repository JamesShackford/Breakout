package game.fieldobject;

import java.util.ArrayList;

import game.Field;
import game.Game;
import game.PolarUtil;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bouncer extends FieldCartesianObject
{
	private final double SPEED_INCREASE = 1.1;
	public static final double NORMAL_SPEED = Field.SIZE / (200 * Game.SECOND_DELAY);

	private double speed;
	// x direction is index 0, y direction is index 1
	// this must be a unit vector
	private double[] velocityDirection;
	// bouncer will stick to paddle if this is true
	private boolean stickingToPaddle;

	private boolean isFireball;
	// true if bouncer hit the planet
	private boolean isDead;

	public Bouncer()
	{
		this.stickingToPaddle = true;
		this.setImage("ball.gif");
		this.setDirection(new double[] { 1.0, 1.0 });
		this.setSpeed(NORMAL_SPEED);
	}

	public double getXSpeed()
	{
		return velocityDirection[0] * speed;
	}

	public double getYSpeed()
	{
		return velocityDirection[1] * speed;
	}

	public double getSpeed()
	{
		return Math.sqrt((Math.pow(this.getXSpeed(), 2)) + (Math.pow(this.getYSpeed(), 2)));
	}

	/**
	 * True if this bouncer has begun to move. If the spacebar is pressed while
	 * the bouncer is stopped, the bouncer will begin to move
	 * 
	 * @return true if bouncer is moving, false if bouncer is stuck to paddle
	 */
	public boolean getStickingToPaddle()
	{
		return stickingToPaddle;
	}

	public double[] getDirection()
	{
		return velocityDirection;
	}

	public boolean isDead()
	{
		return isDead;
	}

	public boolean isFireball()
	{
		return isFireball;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public void setDirection(double[] direction)
	{
		this.velocityDirection = PolarUtil.getUnitVector(direction);
	}

	public void setDead(boolean isDead)
	{
		this.isDead = isDead;
	}

	public void setFireball(boolean isFireball)
	{
		this.isFireball = isFireball;
		double currX = this.getImage().getX();
		double currY = this.getImage().getY();
		this.setImage("fireball.gif");
		this.getImage().setX(currX);
		this.getImage().setY(currY);
		this.setSpeed(this.getSpeed() * 2);

	}

	/**
	 * Allow the ball to begin moving.
	 * 
	 * @param started
	 *            true-> ball can move, false->ball can't move and is instead
	 *            attached to the paddle
	 */
	public void setStickingToPaddle(boolean stickingToPaddle)
	{
		this.stickingToPaddle = stickingToPaddle;
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		// Start the ball when the spacebar is pressed.
		if (key.getCode().equals(KeyCode.SPACE) && this.getStickingToPaddle()) {
			this.setDirection(
					PolarUtil.getNormalVector(this.getX(), this.getY(), Field.CENTER_X, Field.CENTER_Y, false));
			this.setX(this.getX() + 4 * Game.SECOND_DELAY * (this.getXSpeed()));
			this.setY(this.getY() + 4 * Game.SECOND_DELAY * (this.getYSpeed()));
			this.setStickingToPaddle(false);
		}
		// F --> increase speed of ball
		if (key.getCode().equals(KeyCode.F)) {
			this.setSpeed(this.getSpeed() * SPEED_INCREASE);
		}
		// S --> decrease speed of ball
		if (key.getCode().equals(KeyCode.S)) {
			this.setSpeed(this.getSpeed() / SPEED_INCREASE);
		}
		// R --> reset original ball position
		if (key.getCode().equals(KeyCode.R)) {
			this.setStickingToPaddle(true);
		}
	}

	/**
	 * Put the ball in the middle of the paddle and make the ball follow the
	 * paddle wherever it moves
	 * 
	 * @param paddle
	 */
	public void stickToPaddle(Paddle paddle)
	{
		double middleDegree = 90 - (paddle.getDegreeBegin() + paddle.getDegreeEnd()) / 2;
		double[] cartesianCoords = PolarUtil.toCartesian(paddle.getOuterRadius() + this.getRadius(), middleDegree);
		this.setX(Field.CENTER_X + cartesianCoords[0]);
		this.setY(Field.CENTER_Y - cartesianCoords[1]);
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		// If the ball has not started moving yet, the ball will move with the
		// paddle (it will remain attached to the top of the paddle)
		if (this.getStickingToPaddle()) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					stickToPaddle((Paddle) obj);
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

		// if the ball hits the Planet, the ball disappears and becomes "dead"
		double radialDistance = PolarUtil.toPolar(this.getX() - Field.CENTER_X, this.getY() - Field.CENTER_Y)[0];
		for (FieldObject elem : field.getFieldElements()) {
			if (elem instanceof Planet && radialDistance <= ((Planet) elem).getRadius()) {
				this.setImage(new ImageView());
				this.setDead(true);
			}
		}

		// if the ball is dead, it should not have an image
		if (isDead()) {
			this.setImage(new ImageView());
		}

		return null;
	}

}
