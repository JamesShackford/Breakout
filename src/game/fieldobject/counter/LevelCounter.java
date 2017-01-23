package game.fieldobject.counter;

import game.Field;

public class LevelCounter extends Counter
{

	public LevelCounter()
	{
		super(1);
		this.getLabel().relocate(4 * Field.CENTER_X / 3, 0);
	}

	@Override
	public void updateLabel()
	{
		this.getLabel().setText("Level: " + this.getCount());
	}

}
