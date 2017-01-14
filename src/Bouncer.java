import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Bouncer implements FieldObject
{

	private ImageView image;
	private double xSpeed;
	private double ySpeed;

	public double getX()
	{
		return image.getX();
	}

	public double getY()
	{
		return image.getY();
	}

	public double getXSpeed()
	{
		return xSpeed;
	}

	public double getYSpeed()
	{
		return ySpeed;
	}

	public ImageView getImage()
	{
		return image;
	}

	public void setX(double x)
	{
		this.getImage().setX(x);
	}

	public void setY(double y)
	{
		this.getImage().setY(y);
	}

	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	public void setImage(ImageView image)
	{
		this.image = image;
	}

	public void setImage(String imageString)
	{
		Image im = new Image(getClass().getClassLoader().getResourceAsStream(imageString));
		this.image = new ImageView(im);
	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{

	}

	@Override
	public void onMouseClicked(double x, double y)
	{

	}

	@Override
	public void step(double secondDelay, Field field)
	{
		image.setX(getX() + xSpeed * secondDelay);
		image.setY(getY() + ySpeed * secondDelay);

		if (getX() <= 0 || getX() + image.getBoundsInLocal().getHeight() >= field.getScene().getHeight()) {
			setXSpeed(xSpeed * -1);
		}

		if (getY() <= 0 || getY() + image.getBoundsInLocal().getWidth() >= field.getScene().getWidth()) {
			setYSpeed(ySpeed * -1);
		}
	}

	@Override
	public Node getNode()
	{
		return getImage();
	}

}
