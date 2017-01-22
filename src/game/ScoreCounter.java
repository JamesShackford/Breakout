package game;

public class ScoreCounter extends Counter
{

	public ScoreCounter()
	{
		this.getLabel().relocate(1 * Field.CENTER_X / 3, 0);
	}

	@Override
	public void updateLabel()
	{
		this.getLabel().setText("Score: " + this.getCount());
	}

}
