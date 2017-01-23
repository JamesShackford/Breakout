package game;

import java.util.ArrayList;

import game.fieldobject.brick.Brick;
import game.fieldobject.brick.IceBrick;
import game.fieldobject.brick.RegularBrick;
import game.fieldobject.brick.ThreeHitBrick;
import game.fieldobject.brick.TwoHitBrick;
import game.fieldobject.brick.UnbreakableBrick;

public class StageBuilder
{
	/**
	 * Build a configuration of bricks. "1" -> RegularBrick "2" -> TwoHitBrick
	 * "3" -> ThreeHitBrick "U" -> UnbreakableBrick "I" -> IceBrick
	 * 
	 * @param startingRadius
	 * @param brickLayout
	 *            Array of Strings representing rows of bricks to be added to
	 *            the stage.
	 * @return Array of bricks to be added
	 */
	public static ArrayList<Brick> buildBricks(double startingRadius, ArrayList<String[]> brickLayout)
	{
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		double currRadius = startingRadius;
		for (String[] brickLine : brickLayout) {
			double currAngle = 0;
			double angleIncrement = 360.0 / brickLine.length;
			for (String currBrick : brickLine) {
				Brick addedBrick = null;
				if (currBrick.equals("1")) {
					addedBrick = new RegularBrick(currRadius, currRadius + Brick.BRICK_THICKNESS, currAngle,
							currAngle + angleIncrement, Field.CENTER_X, Field.CENTER_Y);
				} else if (currBrick.equals("2")) {
					addedBrick = new TwoHitBrick(currRadius, currRadius + Brick.BRICK_THICKNESS, currAngle,
							currAngle + angleIncrement, Field.CENTER_X, Field.CENTER_Y);
				} else if (currBrick.equals("3")) {
					addedBrick = new ThreeHitBrick(currRadius, currRadius + Brick.BRICK_THICKNESS, currAngle,
							currAngle + angleIncrement, Field.CENTER_X, Field.CENTER_Y);
				} else if (currBrick.equals("U")) {
					addedBrick = new UnbreakableBrick(currRadius, currRadius + Brick.BRICK_THICKNESS, currAngle,
							currAngle + angleIncrement, Field.CENTER_X, Field.CENTER_Y);
				} else if (currBrick.equals("I")) {
					addedBrick = new IceBrick(currRadius, currRadius + Brick.BRICK_THICKNESS, currAngle,
							currAngle + angleIncrement, Field.CENTER_X, Field.CENTER_Y);
				}
				currAngle += angleIncrement;
				bricks.add(addedBrick);
			}
			currRadius += Brick.BRICK_THICKNESS;
		}
		return bricks;
	}
}
