package game;
import java.util.ArrayList;

import javafx.scene.paint.Color;

/**
 * A regular brick can only be hit once before it is destroyed. It has a low
 * chance of dropping a power-up.
 * 
 * @author jimmy
 *
 */
public class RegularBrick extends Brick
{

	private final double POWER_UP_PROBABILITY = 1.0;

	RegularBrick(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY)
	{
		this.setSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY, Color.ROSYBROWN,
				Color.SADDLEBROWN);
	}

	@Override
	public ArrayList<FieldObject> step(double secondDelay, Field field)
	{
		ArrayList<FieldObject> objects = field.getFieldElements();
		ArrayList<FieldObject> newObjects = new ArrayList<FieldObject>();
		// check if a bouncer is hitting this brick, and if it is, then destroy
		// the block and possibly create a PowerUp
		for (FieldObject currElem : objects) {
			if (currElem instanceof Bouncer) {
				PowerUp power = bouncerHit((Bouncer) currElem);
				if (power != null) {
					double middleDegree = (this.getDegreeBegin() + this.getDegreeEnd()) / 2;
					double[] cartesianCoords = PolarUtil.toCartesian(this.getInnerRadius(), middleDegree - 90);
					System.out.println(this.getInnerRadius() + ", " + middleDegree + "-->" + cartesianCoords[0] + ","
							+ cartesianCoords[1]);
					power.setX(cartesianCoords[0] + Field.CENTER_X);
					power.setY(cartesianCoords[1] + Field.CENTER_Y);
					power.setSpeed(20);
					power.setDirection(PolarUtil.getNormalVector(power.getX(), power.getY(), Field.CENTER_X,
							Field.CENTER_Y, true));
					newObjects.add(power);
				}
			}
		}
		return newObjects;
	}

	@Override
	public PowerUp bouncerHit(Bouncer bouncer)
	{
		if (this.intersects(bouncer) && !getDestroyed()) {
			// if (!getDestroyed() &&
			// bouncer.getImage().getBoundsInParent().intersects(this.getNode().getBoundsInParent()))
			// {
			double bouncerXRadius = bouncer.getImage().getBoundsInLocal().getWidth() / 2;
			/*
			 * If a bouncer is hitting the right/left edges of the brick, then
			 * reflect the bouncer along that edge
			 */
			double[] polarCoords = PolarUtil.toPolar(bouncer.getX(), bouncer.getY());

			double[] normalVector;
			normalVector = PolarUtil.getNormalVector(bouncer.getX(), bouncer.getY(), Field.CENTER_X, Field.CENTER_Y,
					false);
			// if (this.hitCurve(bouncer.getX(), bouncer.getY(),
			// bouncer.getRadius())) {
			// normalVector = PolarUtil.getNormalVector(bouncer.getX(),
			// bouncer.getY(), Field.CENTER_X, Field.CENTER_Y,
			// false);
			// } else {
			// normalVector = PolarUtil.getTangentVector(bouncer.getX(),
			// bouncer.getY(), Field.CENTER_X,
			// Field.CENTER_Y, false);
			// System.out.println("hello");
			// }
			double[] reflectionVector = PolarUtil.getReflectionVector(bouncer.getDirection(), normalVector);
			bouncer.setDirection(reflectionVector);
			// if (polarCoords[1] <= this.getDegreeBegin() || polarCoords[1] >=
			// this.getDegreeBegin()) {
			// bouncer.setDirection(new double[] { bouncer.getDirection()[0] *
			// -1, bouncer.getDirection()[1] });
			// bouncer.setX(bouncer.getX() + Math.signum(bouncer.getXSpeed()));
			// }
			// /*
			// * If the bouncer is hitting the top/bottom edges of the brick,
			// then
			// * reflect the bouncer off that edge.
			// */
			// else {
			// bouncer.setDirection(new double[] { bouncer.getDirection()[0],
			// bouncer.getDirection()[1] * -1 });
			// // update position so can't hit 2 bricks at once
			// bouncer.setY(bouncer.getY() + Math.signum(bouncer.getYSpeed()));
			// }
			// destroy this brick and return a PowerUp if one was produced
			return this.destroy();
		}
		return null;
	}

	@Override
	public double getPowerUpProbability()
	{
		return POWER_UP_PROBABILITY;
	}

}
