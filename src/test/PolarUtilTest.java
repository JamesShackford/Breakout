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

	@Test
	public void getReflectedVectorTest()
	{
		double[] leftReflectedVector1 = PolarUtil.getReflectionVector(upwardVector, downLeftVector);
		Assert.assertEquals(rightVector[0], leftReflectedVector1[0], 0.01);
		Assert.assertEquals(rightVector[1], leftReflectedVector1[1], 0.01);

		double[] leftReflectedVector2 = PolarUtil.getReflectionVector(downwardVector, upLeftVector);
		Assert.assertEquals(leftVector[0], leftReflectedVector2[0], 0.01);
		Assert.assertEquals(leftVector[1], leftReflectedVector2[1], 0.01);

		double[] rightReflectedVector1 = PolarUtil.getReflectionVector(upwardVector, downRightVector);
		Assert.assertEquals(rightVector[0], rightReflectedVector1[0], 0.01);
		Assert.assertEquals(rightVector[1], rightReflectedVector1[1], 0.01);

		double[] rightReflectedVector2 = PolarUtil.getReflectionVector(downwardVector, upRightVector);
		Assert.assertEquals(rightVector[0], rightReflectedVector2[0], 0.01);
		Assert.assertEquals(rightVector[1], rightReflectedVector2[1], 0.01);

		double[] upReflectedVector1 = PolarUtil.getReflectionVector(rightVector, upLeftVector);
		Assert.assertEquals(upwardVector[0], upReflectedVector1[0], 0.01);
		Assert.assertEquals(upwardVector[1], upReflectedVector1[1], 0.01);

		double[] upReflectedVector2 = PolarUtil.getReflectionVector(leftVector, upRightVector);
		Assert.assertEquals(upwardVector[0], upReflectedVector2[0], 0.01);
		Assert.assertEquals(upwardVector[1], upReflectedVector2[1], 0.01);

		double[] downReflectedVector1 = PolarUtil.getReflectionVector(rightVector, downLeftVector);
		Assert.assertEquals(downwardVector[0], downReflectedVector1[0], 0.01);
		Assert.assertEquals(downwardVector[1], downReflectedVector1[1], 0.01);

		double[] downReflectedVector2 = PolarUtil.getReflectionVector(leftVector, downRightVector);
		Assert.assertEquals(downwardVector[0], downReflectedVector2[0], 0.01);
		Assert.assertEquals(downwardVector[1], downReflectedVector2[1], 0.01);
	}
}
