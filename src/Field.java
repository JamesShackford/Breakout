import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Field
{
	public static final String TITLE = "Game";
	public static final int SIZE = 400;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final int KEY_INPUT_SPEED = 5;

	private Scene myScene;
	private Group root;
	private HashMap<FieldObject, ArrayList<FieldObject>> myMap;
	private ArrayList<FieldObject> fieldElements;

	public Field(Stage s, ArrayList<FieldObject> fieldElements)
	{
		this.fieldElements = fieldElements;
		Scene scene = setupGame(SIZE, SIZE, BACKGROUND);
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();
	}

	private Scene setupGame(int width, int height, Paint background)
	{
		// create one top level collection to organize the things in the scene
		root = new Group();
		// create a place to see the shapes
		myScene = new Scene(root, width, height, background);
		// make some shapes and set their properties
		for (FieldObject element : fieldElements) {
			root.getChildren().add(element.getNode());
			myScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> element.onKeyPressed(e));
			myScene.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> element.onMouseClicked(e.getX(), e.getY()));
			// e.getY()));
		}
		return myScene;
	}

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

	public Scene getScene()
	{
		return myScene;
	}

	public ArrayList<FieldObject> getFieldElements()
	{
		return fieldElements;
	}
}
