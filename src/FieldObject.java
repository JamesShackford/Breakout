import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public interface FieldObject
{
	public Node getNode();

	public void onMouseClicked(double x, double y);

	public void onKeyPressed(KeyEvent key);

	/**
	 * The action that this FieldObject performs every clock cycle.
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
