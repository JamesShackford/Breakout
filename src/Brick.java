import javafx.scene.image.ImageView;

public abstract class Brick implements FieldObject
{
	private boolean destroyed;
	private ImageView image;

	public abstract void bouncerHit(Bouncer bouncer);

	public void setDestroyed(boolean destroyed)
	{
		this.destroyed = destroyed;
	}

	public void setX(double x)
	{
		image.setX(x);
	}

	public void setY(double y)
	{
		image.setY(y);
	}

	public void setImage(ImageView image)
	{
		this.image = image;
	}

	public boolean getDestroyed()
	{
		return destroyed;
	}

	public double getX()
	{
		return image.getX();
	}

	public double getY()
	{
		return image.getY();
	}

	public ImageView getImage()
	{
		return image;
	}
}
