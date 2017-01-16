import javafx.scene.image.ImageView;

public abstract class Brick extends FieldObject
{
	private boolean destroyed;
	private ImageView image;

	public abstract PowerUp bouncerHit(Bouncer bouncer);

	public abstract double getPowerUpProbability();

	public PowerUp destroy()
	{
		setDestroyed(true);
		getImage().setImage(null);
		PowerUp power = PowerUpUtil.getPowerUp(getPowerUpProbability());
		return power;
	}

	public void setDestroyed(boolean destroyed)
	{
		this.destroyed = destroyed;
	}

	public boolean getDestroyed()
	{
		return destroyed;
	}
}
