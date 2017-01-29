// This entire file is part of my masterpiece.
// Jimmy Shackford
/**
 * The purpose of this class is to describe how the Picture class could be used to replace ImageViews in the
 * FieldCartesianObject class. The instance of ImageView in FieldCartesianObject can be replaced by this object.
 * This class defines many helper methods that were previously defined in FieldCartesianObject. This class is
 * well-defined because it has short, simple methods and several methods that would make it easy for a developer
 * to use.
 */
package game.picture;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageObj implements Picture
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

	@Override
	public Node getPicture()
	{
		return image;
	}

	@Override
	public boolean intersect(Picture picture)
	{
		return picture.getPicture().getBoundsInParent().intersects(image.getBoundsInParent());
	}

}
