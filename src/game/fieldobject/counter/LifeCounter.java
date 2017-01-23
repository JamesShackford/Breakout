package game.fieldobject.counter;

public class LifeCounter extends Counter
{
	public LifeCounter()
	{
		super(3);
		this.getLabel().relocate(0, 0);
	}

	@Override
	public void updateLabel()
	{
		this.getLabel().setText("Lives: " + this.getCount());
	}
}
