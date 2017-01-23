package game.level;

import java.util.ArrayList;

import game.Field;
import game.StageBuilder;
import game.fieldobject.Bouncer;
import game.fieldobject.Paddle;
import game.fieldobject.brick.Brick;
import game.fieldobject.counter.Counter;

public class LevelTwo extends Level
{

	private final double STARTDISTANCE = 115;

	public LevelTwo(Field field, ArrayList<Counter> counters)
	{
		super(field, counters, 1.1 * Bouncer.NORMAL_SPEED);
	}

	@Override
	public Paddle makePaddle()
	{
		return new Paddle(50.0f, 55.0f, 0.0f, 50.0f, 200.0f, 200.0f);
	}

	@Override
	public ArrayList<Brick> makeBrickConfiguration()
	{
		String[] layer1 = { "2", "2", "I", "2", "2", "2", "2", "2", "I", "I", "2", "I" };
		String[] layer2 = { "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2" };
		String[] layer3 = { "2", "2", "1", "1", "2", "2", "1", "1", "2", "2" };
		String[] layer4 = { "U", "3", "3", "3", "U", "U", "3", "U", "3" };
		ArrayList<String[]> brickLayout = new ArrayList<String[]>();
		brickLayout.add(layer1);
		brickLayout.add(layer2);
		brickLayout.add(layer3);
		brickLayout.add(layer4);
		return StageBuilder.buildBricks(STARTDISTANCE, brickLayout);
	}

}
