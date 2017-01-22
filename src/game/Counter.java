package game;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public abstract class Counter implements FieldObject
{
	private int count;
	private Label label = new Label("Count: ");
	private final Color TEXTCOLOR = Color.ANTIQUEWHITE;

	public Counter(int startCount)
	{
		this.count = startCount;
		label.setTextFill(TEXTCOLOR);
	}

	public void add(int addition)
	{
		count += addition;
	}

	public int getCount()
	{
		return count;
	}

	public Label getLabel()
	{
		return label;
	}

	public abstract void updateLabel();

	@Override
	public Node getNode()
	{
		return label;
	}

	@Override
	public void onMouseClicked(double x, double y)
	{

	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{

	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		updateLabel();
		return null;
	}

}