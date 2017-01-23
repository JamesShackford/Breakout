package game.fieldobject.powerup;

import java.util.Random;

/**
 * Utility class for determining if a PowerUp is produced from a broken block
 * 
 * @author jimmy
 *
 */
public class PowerUpUtil
{
	public static PowerUp getPowerUp(double getPowerUpProbability)
	{
		// The summation of the probabilities of all power ups must be less than
		// 1.
		// Determine which power up to produce at Random according to the
		// block's
		// probability (getPowerUpProbability) and the power-up's probability
		// (ProbabilityClazz.PROBABILITY)
		Random rand = new Random();
		double randDouble = rand.nextDouble();
		double currVal = getPowerUpProbability * ExtraBallPowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new ExtraBallPowerUp();
		}
		currVal += getPowerUpProbability * StickyPaddlePowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new StickyPaddlePowerUp();
		}
		currVal += getPowerUpProbability * SizePowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new SizePowerUp();
		}
		currVal += getPowerUpProbability * FireballPowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new FireballPowerUp();
		}
		currVal += getPowerUpProbability * PointsPowerUp.PROBABILITY;
		if (randDouble < currVal) {
			return new PointsPowerUp();
		}
		return null;
	}
}
