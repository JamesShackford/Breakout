package game;
import java.util.ArrayList;

public class Planet extends FieldCartesianObject
{

	Planet()
	{
		this.setImage("planet.gif");
		this.getImage().setPreserveRatio(true);
		this.getImage().setFitHeight(60);
		this.setX(Field.CENTER_X);
		this.setY(Field.CENTER_Y);
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		return null;
	}

	public void setRadius(double radius)
	{
		this.getImage().setFitHeight(radius * 2);
	}

}
