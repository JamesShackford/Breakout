import java.util.Random;

public class PowerUpUtil
{
	public static PowerUp getPowerUp(double getPowerUpProbability)
	{
		// Allow rand value to go over 1.0?
		Random rand = new Random();
		double randDouble = rand.nextDouble();
		double currVal = getPowerUpProbability * ExtraBallPower.PROBABILITY;
		if (randDouble < currVal) {
			return new ExtraBallPower();
		}
		return null;
	}
}