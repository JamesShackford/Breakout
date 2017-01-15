public class Bouncer extends FieldObject
{

	private double xSpeed;
	private double ySpeed;

	public double getXSpeed()
	{
		return xSpeed;
	}

	public double getYSpeed()
	{
		return ySpeed;
	}

	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	@Override
	public void step(double secondDelay, Field field)
	{
		this.getImage().setX(getX() + xSpeed * secondDelay);
		this.getImage().setY(getY() + ySpeed * secondDelay);

		if (getX() <= 0 || getX() + this.getImage().getBoundsInLocal().getHeight() >= field.getScene().getHeight()) {
			setXSpeed(xSpeed * -1);
		}

		if (getY() <= 0 || getY() + this.getImage().getBoundsInLocal().getWidth() >= field.getScene().getWidth()) {
			setYSpeed(ySpeed * -1);
		}
	}

}
