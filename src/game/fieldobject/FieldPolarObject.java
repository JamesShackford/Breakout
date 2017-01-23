package game.fieldobject;

import game.Field;
import game.PolarUtil;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public abstract class FieldPolarObject implements FieldObject
{
	private double innerRadius, outerRadius, degreeBegin, degreeEnd;
	private Path semiRing;

	public void setSemiRing(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd,
			double centerX, double centerY, Color fillColor, Color strokeColor)
	{
		// degrees should be between 0 and 360
		while (degreeBegin > 360 || degreeEnd > 360) {
			degreeBegin -= 360;
			degreeEnd -= 360;
		}
		while (degreeBegin < 0 || degreeEnd < 0) {
			degreeBegin += 360;
			degreeEnd += 360;
		}
		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
		this.degreeBegin = degreeBegin;
		this.degreeEnd = degreeEnd;
		this.semiRing = PolarUtil.makeSemiRing(innerRadius, outerRadius, degreeBegin, degreeEnd, centerX, centerY,
				fillColor, strokeColor);
	}

	@Override
	public Node getNode()
	{
		return semiRing;
	}

	public Path getSemiRing()
	{
		return semiRing;
	}

	public double getInnerRadius()
	{
		return innerRadius;
	}

	public double getOuterRadius()
	{
		return outerRadius;
	}

	public double getDegreeBegin()
	{
		return degreeBegin;
	}

	public double getDegreeEnd()
	{
		return degreeEnd;
	}

	// intersects a Cartesian object if the object is within its radial
	// boundaries and within its degree boundaries
	public boolean intersects(FieldCartesianObject obj)
	{
		boolean hitCurve = hitCurve(obj.getX(), obj.getY(), obj.getRadius());
		// check if top-left part of colliding object hit this polar object
		boolean topLeftHitSide = hitSide(obj.getX() - obj.getRadius(), obj.getY() - obj.getRadius());
		// check if bottom-right part of colliding object hit this polar object
		boolean bottomRightHitSide = hitSide(obj.getX() + obj.getRadius(), obj.getY() + obj.getRadius());
		return hitCurve || topLeftHitSide || bottomRightHitSide;
	}

	/**
	 * True if hit the curved portion of the SemiRing
	 * 
	 * @param x
	 * @param y
	 * @param imageRadius
	 * @return
	 */
	public boolean hitCurve(double x, double y, double imageRadius)
	{
		double[] polarCoords = PolarUtil.toPolar(x - Field.CENTER_X, y - Field.CENTER_Y);
		double distToOrigin = polarCoords[0];
		double angleFromOrigin = polarCoords[1];
		// check if colliding object's coordinates are within the radial
		// boundaries of this polar object
		boolean inRadiusRange = distToOrigin + imageRadius >= this.getInnerRadius()
				&& distToOrigin - imageRadius <= this.getOuterRadius();
		// include the cases where one part of the PolarObject is in quadrant 4
		// and the other is in quadrant 4
		if (this.getDegreeBegin() >= 360 || this.getDegreeEnd() >= 360) {
			angleFromOrigin += 360;
		} else if (this.getDegreeBegin() <= 0 || this.getDegreeEnd() <= 0) {
			angleFromOrigin -= 360;
		}
		boolean inDegreeRange = (angleFromOrigin >= this.getDegreeBegin() && angleFromOrigin <= this.getDegreeEnd());
		return inRadiusRange && inDegreeRange;
	}

	// hit the side of the semiRing (the straight portion)
	public boolean hitSide(double x, double y)
	{
		double[] polarCoords = PolarUtil.toPolar(x - Field.CENTER_X, y - Field.CENTER_Y);
		double distToOrigin = polarCoords[0];
		double angleFromOrigin = polarCoords[1];
		boolean hitSide = distToOrigin >= this.getInnerRadius() && distToOrigin <= this.getOuterRadius()
				&& angleFromOrigin >= this.getDegreeBegin() && angleFromOrigin <= this.getDegreeEnd();
		return hitSide;
	}

	public void delete()
	{
		// remove path
		semiRing = new Path();
	}

	@Override
	public void onMouseClicked(double x, double y)
	{

	}

	@Override
	public void onKeyPressed(KeyEvent key)
	{

	}
}
