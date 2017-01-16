import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public abstract class FieldObject
{
	private ImageView image;

	public double getX()
	{
		return image.getX();
	}

	public double getY()
	{
		return image.getY();
	}

	public Node getNode()
	{
		return getImage();
	}

	public ImageView getImage()
	{
		return image;
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

	public void setImage(String imageString)
	{
		Image im = new Image(getClass().getClassLoader().getResourceAsStream(imageString));
		this.image = new ImageView(im);
	}

	public void onKeyPressed(KeyEvent key)
	{

	}

	public void onMouseClicked(double x, double y)
	{

	}

	public abstract ArrayList<FieldObject> step(double secondDelay, Field field);
}
