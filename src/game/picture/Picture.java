// This entire file is part of my masterpiece.
// Jimmy Shackford
/*
 * The purpose of this code is to describe any image/picture that can be displayed in the Field, and
 * it requires a method (intersect) to describe how different pictures can interact with each other.
 * This class is important and well-designed because, in practice, it would essentially remove my need
 * to have both a FieldCartesianObject class and a FieldPolarObject class, and I could instead make one
 * 'FieldObjectImpl' class that would work for any type of Picture.. Moreover, it would allow
 * other developers to add new types of pictures/objects at ease (like a Rectangle, as done in lab_bounce).
 */

package game.picture;

import javafx.scene.Node;

public interface Picture
{
	public Node getPicture();

	public boolean intersect(Picture picture);
}
