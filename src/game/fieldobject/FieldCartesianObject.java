package game.fieldobject;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * A FieldObject describes any object which is represented by an x-coordinate, a
 * y-coordinate, and an image.
 * 
 * @author jimmy
 *
 */
public abstract class FieldCartesianObject implements FieldObject
{
	private ImageView image;

	public double getX()
	{
		return image.getX() + this.getRadius();
	}

	public double getY()
	{
		return image.getY() + this.getRadius();
	}

	public double getRadius()
	{
		return image.getBoundsInLocal().getWidth() / 2;
	}

	/**
	 * Get the node that this field object represents
	 * 
	 * @return node object
	 */
	@Override
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
		image.setX(x - this.getRadius());
	}

	public void setY(double y)
	{
		image.setY(y - this.getRadius());
	}

	public void setImage(ImageView image)
	{
		this.image = image;
	}

	/**
	 * Set the image for this field object with a string. The string should be
	 * the path to an image in the "images" directory
	 * 
	 * @param imageString
	 *            Path to image in "images" directory
	 */
	public void setImage(String imageString)
	{
		Image im = new Image(getClass().getClassLoader().getResourceAsStream(imageString));
		this.image = new ImageView(im);
	}

	/**
	 * The action performed on this FieldObject whenever a keyboard button is
	 * pressed.
	 * 
	 * @param key
	 *            KeyEvent which represents the pressing of a button
	 */
	@Override
	public void onKeyPressed(KeyEvent key)
	{

	}

	/**
	 * The action performed on this FieldObject whenever the mouse is clicked.
	 * 
	 * @param x
	 *            X position where the mouse was clicked
	 * @param y
	 *            Y position where the mouse was clicked
	 */
	@Override
	public void onMouseClicked(double x, double y)
	{

	}
}
