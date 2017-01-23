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

	public boolean intersects(FieldCartesianObject obj)
	{
		boolean hitCurve = hitCurve(obj.getX(), obj.getY(), obj.getRadius());
		boolean topLeftHitSide = hitSide(obj.getX() - obj.getRadius(), obj.getY() - obj.getRadius());
		boolean bottomRightHitSide = hitSide(obj.getX() + obj.getRadius(), obj.getY() + obj.getRadius());
		return hitCurve || topLeftHitSide || bottomRightHitSide;
	}

	public boolean hitCurve(double x, double y, double imageRadius)
	{
		double[] polarCoords = PolarUtil.toPolar(x - Field.CENTER_X, y - Field.CENTER_Y);
		double distToOrigin = polarCoords[0];
		double angleFromOrigin = polarCoords[1];
		boolean inRadiusRange = distToOrigin + imageRadius >= this.getInnerRadius()
				&& distToOrigin - imageRadius <= this.getOuterRadius();
		boolean inDegreeRange = (angleFromOrigin >= this.getDegreeBegin() && angleFromOrigin <= this.getDegreeEnd());
		return inRadiusRange && inDegreeRange;
	}

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
