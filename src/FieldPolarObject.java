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
		double[] middlePolarCoords = PolarUtil.toPolar(obj.getX() - Field.CENTER_X, obj.getY() - Field.CENTER_Y);
		double distMiddleToOrigin = middlePolarCoords[0];
		double angleMiddleFromOrigin = middlePolarCoords[1];
		if (this.getOuterRadius() == 55.0) {
			System.out.println(distMiddleToOrigin - obj.getRadius() + " < " + this.getOuterRadius());
			System.out.println(distMiddleToOrigin + obj.getRadius() + " > " + this.getInnerRadius());
			System.out.println(angleMiddleFromOrigin + ">=" + this.getDegreeBegin());
		}

		// boolean middleHitCurve = distMiddleToOrigin + obj.getRadius() >=
		// this.getInnerRadius()
		// && distMiddleToOrigin - obj.getRadius() <= this.getOuterRadius()
		// && angleMiddleFromOrigin >= this.getDegreeBegin() &&
		// angleMiddleFromOrigin <= this.getDegreeEnd();
		// double[] topLeftPolarCoords = PolarUtil.toPolar(obj.getX() -
		// obj.getRadius() - Field.CENTER_X,
		// obj.getY() - obj.getRadius() - Field.CENTER_Y);
		// double distTopLeftToOrigin = topLeftPolarCoords[0];
		// double angleTopLeftFromOrigin = topLeftPolarCoords[1];
		// boolean topLeftHitSide = distTopLeftToOrigin >= this.getInnerRadius()
		// && distTopLeftToOrigin <= this.getOuterRadius() &&
		// angleTopLeftFromOrigin >= this.getDegreeBegin()
		// && angleTopLeftFromOrigin <= this.getDegreeEnd();
		// double[] bottomRightPolarCoords = PolarUtil.toPolar(obj.getX() +
		// obj.getRadius() - Field.CENTER_X,
		// obj.getY() + obj.getRadius() - Field.CENTER_Y);
		// double distBottomRightToOrigin = bottomRightPolarCoords[0];
		// double angleBottomRightFromOrigin = bottomRightPolarCoords[1];
		// boolean bottomRightHitSide = distBottomRightToOrigin >=
		// this.getInnerRadius()
		// && distBottomRightToOrigin <= this.getOuterRadius()
		// && angleBottomRightFromOrigin >= this.getDegreeBegin()
		// && angleBottomRightFromOrigin <= this.getDegreeEnd();
		// if (middleHitCurve || topLeftHitSide || bottomRightHitSide) {
		// return true;
		// }
		// return false;

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
		boolean hitCurve = distToOrigin + imageRadius >= this.getInnerRadius()
				&& distToOrigin - imageRadius <= this.getOuterRadius() && angleFromOrigin >= this.getDegreeBegin()
				&& angleFromOrigin <= this.getDegreeEnd();
		return hitCurve;
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
		innerRadius = 0;
		outerRadius = 0;
		degreeBegin = 0;
		degreeEnd = 0;
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
