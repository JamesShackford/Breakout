package game;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * The Field object represents the stage and elements on the stage.
 * 
 * @author jimmy
 *
 */
public class Field
{
	public static final String TITLE = "Game";
	public static final int SIZE = 400;
	public static final int CENTER_X = SIZE / 2;
	public static final int CENTER_Y = SIZE / 2;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final int KEY_INPUT_SPEED = 5;

	private Scene myScene;
	private Group root;
	private ArrayList<FieldObject> fieldElements;

	/**
	 * Create a new Field object based on the given stage, and add the given
	 * FieldObjects to the stage. Then, display the Field
	 * 
	 * @param s
	 *            Stage to base the Field off of
	 * @param fieldElements
	 *            Objects to add to the field
	 */
	public Field(Stage s, ArrayList<FieldObject> fieldElements)
	{
		this.fieldElements = fieldElements;
		Scene scene = setupGame(SIZE, SIZE, BACKGROUND);
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();
	}

	/**
	 * Set up the scene and add field elements to it.
	 * 
	 * @param width
	 *            Width of the Scene
	 * @param height
	 *            Height of the scene
	 * @param background
	 *            Background color
	 * @return Created scene
	 */
	private Scene setupGame(int width, int height, Paint background)
	{
		root = new Group();
		myScene = new Scene(root, width, height, background);

		// Add the FieldObjects to the stage and give them key/mouse listeners
		for (FieldObject element : fieldElements) {
			root.getChildren().add(element.getNode());
			myScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> element.onKeyPressed(e));
			myScene.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> element.onMouseClicked(e.getX(), e.getY()));
		}
		return myScene;
	}

	/**
	 * Add the given FieldObject to the Field. Give the FieldObject its own
	 * Key/Mouse listeners
	 * 
	 * @param element
	 *            FieldObject to add to the Field
	 */
	public void addElement(FieldObject element)
	{
		if (myScene == null) {
			fieldElements.add(element);
		} else {
			root.getChildren().add(element.getNode());
			myScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> element.onKeyPressed(e));
			myScene.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> element.onMouseClicked(e.getX(), e.getY()));
			fieldElements.add(element);
		}
	}

	public void refreshImages()
	{
		root.getChildren().clear();
		for (FieldObject elem : fieldElements) {
			root.getChildren().add(elem.getNode());
		}
	}

	/**
	 * Get the Scene depicted by this Field object
	 * 
	 * @return Scene
	 */
	public Scene getScene()
	{
		return myScene;
	}

	/**
	 * Get the FieldObjects that are on the Field
	 * 
	 * @return ArrayList of FieldObjects
	 */
	public ArrayList<FieldObject> getFieldElements()
	{
		return fieldElements;
	}
}
