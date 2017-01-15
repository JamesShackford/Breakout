import javafx.scene.image.ImageView;

public abstract class Brick extends FieldObject
{
	private boolean destroyed;
	private ImageView image;

	public abstract void bouncerHit(Bouncer bouncer);

	public void setDestroyed(boolean destroyed)
	{
		this.destroyed = destroyed;
	}

	public boolean getDestroyed()
	{
		return destroyed;
	}
}
