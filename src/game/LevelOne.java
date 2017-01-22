package game;

import java.util.ArrayList;

public class LevelOne extends Level
{

	public LevelOne(Field field, ArrayList<Counter> counters)
	{
		super(field, counters);
	}

	@Override
	public Paddle makePaddle()
	{
		return new Paddle(50.0f, 55.0f, 0.0f, 40.0f, 200.0f, 200.0f);
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
		return StageBuilder.buildBricks(100, brickLayout);
	}

}
