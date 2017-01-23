package game.fieldobject.counter;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

	@Override
	public void onKeyPressed(KeyEvent key)
	{
		// add a life if "L" is pressed
		if (key.getCode() == KeyCode.L) {
			this.add(1);
		}
	}
}
