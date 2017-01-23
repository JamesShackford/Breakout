package game.fieldobject.counter;

import game.Field;

public class ScoreCounter extends Counter
{

	public ScoreCounter()
	{
		super(0);
		this.getLabel().relocate(2 * Field.CENTER_X / 3, 0);
	}

	@Override
	public void updateLabel()
	{
		this.getLabel().setText("Score: " + this.getCount());
	}

}
