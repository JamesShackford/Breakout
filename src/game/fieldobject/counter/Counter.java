package game.fieldobject.counter;

import java.util.ArrayList;

import game.Field;
import game.fieldobject.FieldObject;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public abstract class Counter implements FieldObject
{
	private int count;
	private Label label;
	private final Color TEXTCOLOR = Color.ANTIQUEWHITE;

	public Counter(int startCount)
	{
		this.count = startCount;
		label = new Label();
		label.setTextFill(TEXTCOLOR);
	}

	public void set(int count)
	{
		this.count = count;
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
