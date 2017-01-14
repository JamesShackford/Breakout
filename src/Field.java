import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
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
		Group root = new Group();
		// create a place to see the shapes
		myScene = new Scene(root, width, height, background);
		// make some shapes and set their properties
		for (FieldObject element : fieldElements) {
			root.getChildren().add(element.getNode());
			myScene.setOnKeyPressed(e -> element.onKeyPressed(e));
			myScene.setOnMouseClicked(e -> element.onMouseClicked(e.getX(), e.getY()));
		}
		return myScene;
	}

	public void addElement(FieldObject element)
	{
		fieldElements.add(element);
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
