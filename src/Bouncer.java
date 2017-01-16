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

	public boolean getStarted()
	{
		return started;
	}

	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	public void setStarted(boolean started)
	{
		this.started = started;
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		if (key.getCode().equals(KeyCode.SPACE)) {
			this.setStarted(true);
		}
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		if (this.getStarted() == false) {
			for (FieldObject obj : field.getFieldElements()) {
				if (obj instanceof Paddle) {
					this.setX(obj.getX() + obj.getImage().getBoundsInLocal().getWidth() / 2);
					this.setY(obj.getY() - this.getImage().getBoundsInLocal().getHeight());
				}
			}
		}
		this.getImage().setX(getX() + xSpeed * secondDelay);
		this.getImage().setY(getY() + ySpeed * secondDelay);

		if (getX() <= 0 || getX() + this.getImage().getBoundsInLocal().getHeight() >= field.getScene().getHeight()) {
			setXSpeed(xSpeed * -1);
		}

		if (getY() <= 0 || getY() + this.getImage().getBoundsInLocal().getWidth() >= field.getScene().getWidth()) {
			setYSpeed(ySpeed * -1);
		}
		return null;
	}

}
