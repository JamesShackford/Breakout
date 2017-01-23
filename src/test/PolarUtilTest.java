package test;

import org.junit.Assert;
import org.junit.Test;

import game.PolarUtil;

public class PolarUtilTest
{
	double[] downwardVector = new double[] { 0, 1 };
	double[] upwardVector = new double[] { 0, -1 };
	double[] leftVector = new double[] { -1, 0 };
	double[] rightVector = new double[] { 1, 0 };
	double[] upLeftVector = PolarUtil.getUnitVector(new double[] { -1, -1 });
	double[] upRightVector = PolarUtil.getUnitVector(new double[] { 1, -1 });
	double[] downLeftVector = PolarUtil.getUnitVector(new double[] { -1, 1 });
	double[] downRightVector = PolarUtil.getUnitVector(new double[] { 1, 1 });

	@Test
	public void getNormalVectorTest()
	{
		double[] normalDownwardVector = PolarUtil.getNormalVector(200, 100, 200, 200, true);
		Assert.assertEquals(downwardVector[0], normalDownwardVector[0], 0.01);
		Assert.assertEquals(downwardVector[1], normalDownwardVector[1], 0.01);

		double[] normalUpwardVector = PolarUtil.getNormalVector(200, 300, 200, 200, true);
		Assert.assertEquals(upwardVector[0], normalUpwardVector[0], 0.01);
		Assert.assertEquals(upwardVector[1], normalUpwardVector[1], 0.01);

		double[] normalRightVector = PolarUtil.getNormalVector(100, 200, 200, 200, true);
		Assert.assertEquals(rightVector[0], normalRightVector[0], 0.01);
		Assert.assertEquals(rightVector[1], normalRightVector[1], 0.01);

		double[] normalLeftVector = PolarUtil.getNormalVector(300, 200, 200, 200, true);
		Assert.assertEquals(leftVector[0], normalLeftVector[0], 0.01);
		Assert.assertEquals(leftVector[1], normalLeftVector[1], 0.01);

		double[] normalUpLeftVector = PolarUtil.getNormalVector(400, 400, 200, 200, true);
		Assert.assertEquals(upLeftVector[0], normalUpLeftVector[0], 0.01);
		Assert.assertEquals(upLeftVector[1], normalUpLeftVector[1], 0.01);

		double[] normalUpRightVector = PolarUtil.getNormalVector(0, 400, 200, 200, true);
		Assert.assertEquals(upRightVector[0], normalUpRightVector[0], 0.01);
		Assert.assertEquals(upRightVector[1], normalUpRightVector[1], 0.01);

		double[] normalDownLeftVector = PolarUtil.getNormalVector(400, 0, 200, 200, true);
		Assert.assertEquals(downLeftVector[0], normalDownLeftVector[0], 0.01);
		Assert.assertEquals(downLeftVector[1], normalDownLeftVector[1], 0.01);

		double[] normalDownRightVector = PolarUtil.getNormalVector(0, 0, 200, 200, true);
		Assert.assertEquals(downRightVector[0], normalDownRightVector[0], 0.01);
		Assert.assertEquals(downRightVector[1], normalDownRightVector[1], 0.01);
	}

}
