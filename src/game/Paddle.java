package game;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

/**
 * The Paddle object is able to move around with the arrow keys and can reflect
 * bouncers.
 * 
 * @author jimmy
 *
 */
public class Paddle extends FieldPolarObject
{
	private double speed;
	private final Color FILL_COLOR = Color.CORAL;
	private final Color STROKE_COLOR = Color.CRIMSON;
	private final double INCREASE_AMOUNT = 30; // degrees
	private double centerX;
	private double centerY;
	private boolean sticky;
	private boolean increasedSize;

	public Paddle(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.centerX = centerX;
		this.centerY = centerY;
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, FILL_COLOR, STROKE_COLOR);
		this.sticky = false;
		this.increasedSize = false;
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

	public boolean getIncreasedSize()
	{
		return increasedSize;
	}

	public boolean getSticky()
	{
		return sticky;
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

	public void setIncreasedSize(boolean increasedSize)
	{
		this.increasedSize = increasedSize;
	}

	public void setSticky(boolean sticky)
	{
		this.sticky = sticky;
	}

	public void increaseSize()
	{
		if (!this.getIncreasedSize()) {
			this.setSemiRing(this.getInnerRadius(), this.getOuterRadius(), this.getDegreeBegin() - INCREASE_AMOUNT / 2,
					this.getDegreeEnd() + INCREASE_AMOUNT / 2, centerX, centerY, FILL_COLOR, STROKE_COLOR);
		}
		this.setIncreasedSize(true);
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
				if (this.intersects(thisBouncer) && !thisBouncer.getStickingToPaddle()) {
					// what happens if the ball hits the left/right edge of the
					// paddle?
					if (this.getSticky()) {
						thisBouncer.setStickingToPaddle(true);
					}
					double bouncerRad = PolarUtil.toPolar(thisBouncer.getX() - centerX,
							thisBouncer.getY() - centerY)[0];
					double[] normalVector;
					if (bouncerRad >= this.getInnerRadius() && bouncerRad <= this.getOuterRadius()) {
						normalVector = PolarUtil.getTangentVector(thisBouncer.getX(), thisBouncer.getY(), centerX,
								centerY, false);
					} else {
						normalVector = PolarUtil.getNormalVector(thisBouncer.getX(), thisBouncer.getY(), centerX,
								centerY, false);
					}
					double[] reflectionVector = PolarUtil.getReflectionVector(thisBouncer.getDirection(), normalVector);
					thisBouncer.setDirection(reflectionVector);
				}
			}
		}
		// update the position of the paddle based on its speed
		Path initialPath = this.getSemiRing();
		this.setSemiRing(this.getInnerRadius(), this.getOuterRadius(), this.getDegreeBegin() + speed * secondDelay,
				this.getDegreeEnd() + speed * secondDelay, centerX, centerY, FILL_COLOR, STROKE_COLOR);
		// slow the paddle down until it reaches 0 speed when no keys are being
		// pressed
		if (this.getSpeed() != 0) {
			this.setSpeed(this.getSpeed() - 3 * Math.signum(this.getSpeed()));
		}
		return addedObjects;
	}

}
