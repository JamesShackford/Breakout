import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class PolarUtil
{
	public static Path makeSemiRing(double innerRadius, double outerRadius, double degreeBegin, double degreeEnd,
			double centerX, double centerY, Color fillColor, Color strokeColor)
	{
		double radianBegin = Math.toRadians(degreeBegin);
		double radianEnd = Math.toRadians(degreeEnd);

		double x1 = centerX + innerRadius * Math.sin(radianBegin);
		double y1 = centerY - innerRadius * Math.cos(radianBegin);

		double x2 = centerX + innerRadius * Math.sin(radianEnd);
		double y2 = centerY - innerRadius * Math.cos(radianEnd);

		double x3 = centerX + outerRadius * Math.sin(radianEnd);
		double y3 = centerY - outerRadius * Math.cos(radianEnd);

		double x4 = centerX + outerRadius * Math.sin(radianBegin);
		double y4 = centerY - outerRadius * Math.cos(radianBegin);

		Path path = new Path();
		MoveTo moveTo = new MoveTo();
		moveTo.setX(x1);
		moveTo.setY(y1);
		ArcTo innerArc = new ArcTo();
		innerArc.setRadiusX(innerRadius);
		innerArc.setRadiusY(innerRadius);
		innerArc.setX(x2);
		innerArc.setY(y2);
		innerArc.setSweepFlag(true);
		LineTo outerLine = new LineTo();
		outerLine.setX(x3);
		outerLine.setY(y3);
		ArcTo outerArc = new ArcTo();
		outerArc.setRadiusX(outerRadius);
		outerArc.setRadiusY(outerRadius);
		outerArc.setX(x4);
		outerArc.setY(y4);
		outerArc.setSweepFlag(false);
		LineTo innerLine = new LineTo();
		innerLine.setX(x1);
		innerLine.setY(y1);
		path.getElements().add(moveTo);
		path.getElements().add(innerArc);
		path.getElements().add(outerLine);
		path.getElements().add(outerArc);
		path.getElements().add(innerLine);
		path.setFill(fillColor);
		path.setStroke(strokeColor);
		path.setFillRule(FillRule.EVEN_ODD);

		return path;
	}

	public static double[] toCartesian(double radius, double degree)
	{
		double radian = Math.toRadians(degree);
		double x = radius * Math.cos(radian);
		double y = radius * Math.sin(radian);
		double[] cartesianCoordinates = { x, y };
		return cartesianCoordinates;
	}

	public static double[] toPolar(double x, double y)
	{
		double radius = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double angleRadians = Math.atan(y / x);
		// add 90 because want 0 degrees to be at the top
		double angleDegrees = 90 + Math.toDegrees(angleRadians);
		if (x < 0) {
			angleDegrees += 180;
		}
		double[] polarCoordinates = { radius, angleDegrees };
		return polarCoordinates;
	}

	public static double[] getUnitVector(double[] vector)
	{
		double[] unitVector = new double[vector.length];
		double vectorSum = 0;
		for (double currDouble : vector) {
			vectorSum += Math.pow(currDouble, 2);
		}
		for (int i = 0; i < vector.length; i++) {
			unitVector[i] = vector[i] / Math.sqrt(vectorSum);
		}
		return unitVector;
	}

	public static double dotProduct(double[] vector1, double[] vector2)
	{
		double dotProduct = 0;
		for (int i = 0; i < vector1.length; i++) {
			dotProduct += vector1[i] * vector2[i];
		}
		return dotProduct;
	}

	public static double[] getReflectionVector(double[] incidentVector, double[] normalVector)
	{
		double[] reflectionVector = new double[2];
		// negative direction is up
		incidentVector[1] = -1 * incidentVector[1];
		reflectionVector[0] = incidentVector[0] - 2 * dotProduct(incidentVector, normalVector) * normalVector[0];
		reflectionVector[1] = incidentVector[1] - 2 * dotProduct(incidentVector, normalVector) * normalVector[1];
		// negative direction is up
		reflectionVector[1] = -1 * reflectionVector[1];
		return reflectionVector;
	}

	public static double[] getNormalVector(double x, double y, double centerX, double centerY, boolean towardsCenter)
	{
		double[] normalVector = new double[2];
		normalVector[0] = x - centerX;
		normalVector[1] = centerY - y;
		if (towardsCenter) {
			normalVector[0] *= -1;
			normalVector[1] *= -1;
		}
		normalVector = getUnitVector(normalVector);
		return normalVector;
	}

	public static double[] getTangentVector(double x, double y, double centerX, double centerY, boolean towardsCenter)
	{
		double[] tangentVector = new double[2];
		double[] normalVector = getNormalVector(x, y, centerX, centerY, towardsCenter);
		tangentVector[0] = normalVector[1];
		tangentVector[1] = -1 * normalVector[0];
		return tangentVector;
	}
}
