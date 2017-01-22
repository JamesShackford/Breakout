package game;

import java.util.ArrayList;

public class LevelTwo extends Level
{

	public LevelTwo(Field field)
	{
		super(field);
	}

	@Override
	public Paddle makePaddle()
	{
		return new Paddle(50.0f, 55.0f, 0.0f, 40.0f, 200.0f, 200.0f);
	}

	@Override
	public ArrayList<Brick> makeBrickConfiguration()
	{
		// String[] layer1 = { "1", "I", "I", "I", "2", "2", "2", "3", "3", "3",
		// "U", "U", "U", "1", "1", "1", "1", "1",
		// "1", "1", "1", "1", "1", "1" };
		String[] layer1 = { "1", "1" };
		ArrayList<String[]> brickLayout = new ArrayList<String[]>();
		brickLayout.add(layer1);
		return StageBuilder.buildBricks(100, brickLayout);
	}

}
