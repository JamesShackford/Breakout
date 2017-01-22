package game;

public class LevelCounter extends Counter
{

	public LevelCounter()
	{
		super();
		this.getLabel().relocate(2 * Field.CENTER_X / 3, 0);
	}

	@Override
	public void updateLabel()
	{
		this.getLabel().setText("Level: " + this.getCount());
	}

}
