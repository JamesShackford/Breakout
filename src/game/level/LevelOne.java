package game.level;

import java.util.ArrayList;

import game.Field;
import game.StageBuilder;
import game.fieldobject.Bouncer;
import game.fieldobject.Paddle;
import game.fieldobject.brick.Brick;
import game.fieldobject.counter.Counter;

public class LevelOne extends Level
{

	private final double STARTDISTANCE = 130;

	public LevelOne(Field field, ArrayList<Counter> counters)
	{
		super(field, counters, Bouncer.NORMALSPEED);
	}

	@Override
	public Paddle makePaddle()
	{
		return new Paddle(50.0f, 55.0f, 0.0f, 60.0f, 200.0f, 200.0f);
	}

	@Override
	public ArrayList<Brick> makeBrickConfiguration()
	{
		String[] layer1 = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" };
		String[] layer2 = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1" };
		String[] layer3 = { "2", "2", "2", "2", "2", "2", "2" };
		ArrayList<String[]> brickLayout = new ArrayList<String[]>();
		brickLayout.add(layer1);
		brickLayout.add(layer2);
		brickLayout.add(layer3);
		return StageBuilder.buildBricks(STARTDISTANCE, brickLayout);
	}

}
