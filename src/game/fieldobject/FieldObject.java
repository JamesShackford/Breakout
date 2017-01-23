package game.fieldobject;

import java.util.ArrayList;

import game.Field;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public interface FieldObject
{
	/**
	 * Get the node that this field object represents
	 * 
	 * @return node object
	 */
	public Node getNode();

	/**
	 * The action performed on this FieldObject whenever the mouse is clicked.
	 * 
	 * @param x
	 *            X position where the mouse was clicked
	 * @param y
	 *            Y position where the mouse was clicked
	 */
	public void onMouseClicked(double x, double y);

	/**
	 * The action performed on this FieldObject whenever a keyboard button is
	 * pressed.
	 * 
	 * @param key
	 *            KeyEvent which represents the pressing of a button
	 */
	public void onKeyPressed(KeyEvent key);

	/**
	 * The action that this FieldObject performs every clock cycle. This method
	 * should return all of the objects that need to be added to the field after
	 * this clock cycle
	 * 
	 * @param secondDelay
	 *            Delay between actions
	 * @param field
	 *            Field object which describes the boundary of the field and the
	 *            objects in it
	 * @return ArrayList of FieldObjects which need to be added to the field
	 *         after this clock cycle
	 */
	public abstract ArrayList<FieldObject> step(double secondDelay, Field field);
}
