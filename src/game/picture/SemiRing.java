// This entire file is part of my masterpiece.
// Jimmy Shackford
/**
 * The purpose of this class is to describe how the Picture class could be used to replace SemiRings in the
 * FieldPolarObject class. Effectively, a new type of shape (semi-ring) is defined, and can be used to replace
 * the instance of semi-rings in FieldPolarObject. This class is well-defined because all of the variable names
 * and methods are readable, and it is split up into multiple methods to ensure that the code is easy to follow.
 */
package game.picture;

import game.Field;
import game.PolarUtil;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

public class SemiRing implements Picture
{
	private double innerRadius, outerRadius, degreeBegin, degreeEnd;
	private Path semiRing;

	public SemiRing(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd, double centerX,
			double centerY, Color fillColor, Color strokeColor)
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

	@Override
	public Node getPicture()
	{
		return this.semiRing;
	}

	@Override
	public boolean intersect(Picture picture)
	{
		double leftX = picture.getPicture().getBoundsInParent().getMinX();
		double rightX = picture.getPicture().getBoundsInParent().getMaxX();
		double topY = picture.getPicture().getBoundsInLocal().getMinY();
		double bottomY = picture.getPicture().getBoundsInLocal().getMaxY();
		double midX = (rightX + leftX) / 2;
		double midY = (topY + bottomY) / 2;

		boolean hitCurve = hitCurve(midX, midY, rightX - midX);
		boolean topLeftHitSide = hitSide(leftX, topY);
		boolean topRightHitSide = hitSide(rightX, topY);
		boolean bottomLeftHitSide = hitSide(leftX, bottomY);
		boolean bottomRightHitSide = hitSide(rightX, bottomY);

		return hitCurve || topLeftHitSide || topRightHitSide || bottomLeftHitSide || bottomRightHitSide;
	}

	public boolean hitCurve(double x, double y, double imageRadius)
	{
		double[] polarCoords = PolarUtil.toPolar(x - Field.CENTER_X, y - Field.CENTER_Y);
		double distToOrigin = polarCoords[0];
		double angleFromOrigin = polarCoords[1];
		// include the cases where one part of the PolarObject is in quadrant 4
		// and the other is in quadrant 1
		if (this.getDegreeBegin() >= 360 || this.getDegreeEnd() >= 360) {
			angleFromOrigin += 360;
		} else if (this.getDegreeBegin() <= 0 || this.getDegreeEnd() <= 0) {
			angleFromOrigin -= 360;
		}
		return betweenBoundary(this.getInnerRadius(), this.getOuterRadius(), distToOrigin + imageRadius,
				distToOrigin - imageRadius)
				&& betweenBoundary(this.getDegreeBegin(), this.getDegreeEnd(), angleFromOrigin, angleFromOrigin);
	}

	// hit the side of the semiRing (the straight portion)
	public boolean hitSide(double x, double y)
	{
		double[] polarCoords = PolarUtil.toPolar(x - Field.CENTER_X, y - Field.CENTER_Y);
		double distToOrigin = polarCoords[0];
		double angleFromOrigin = polarCoords[1];
		boolean hitSide = betweenBoundary(this.getInnerRadius(), this.getOuterRadius(), distToOrigin, distToOrigin)
				&& betweenBoundary(this.getDegreeBegin(), this.getDegreeEnd(), angleFromOrigin, angleFromOrigin);
		return hitSide;
	}

	public boolean betweenBoundary(double innerBoundary, double outerBoundary, double innerObjectLocation,
			double outerObjectLocation)
	{
		return innerObjectLocation >= innerBoundary && outerObjectLocation <= outerBoundary;
	}
}
