import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

public interface FieldObject
{
	public void onKeyPressed(KeyEvent key);

	public void onMouseClicked(double x, double y);

	public void step(double secondDelay, Field field);

	public Node getNode();
}
