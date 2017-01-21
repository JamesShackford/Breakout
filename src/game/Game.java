package game;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application
{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private Field field;

	@Override
	public void start(Stage stage) throws Exception
	{
		ArrayList<FieldObject> fieldElements = new ArrayList<FieldObject>();
		Bouncer myBouncer = new Bouncer();
		Planet planet = new Planet();
		Paddle paddle = new Paddle(50.0f, 55.0f, 0.0f, 40.0f, 200.0f, 200.0f);

		// for (int i = 0; i < 10; i++) {
		// RegularBrick brick = new RegularBrick(75.0f, 95.0f, 0.0f, 20.0f,
		// 0.0f, 0.0f);
		// fieldElements.add(brick);
		// }

		double[] incidentVector = new double[] { 0, 1 };
		double[] normalVector = PolarUtil.getUnitVector(new double[] { 1, 1 });
		double[] reflectedVector = PolarUtil.getReflectionVector(incidentVector, normalVector);
		System.out.println(reflectedVector[0] + ", " + reflectedVector[1]);

		String[] layer1 = { "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1" };
		// String[] layer1 = { "I", "I", "I", "I" };
		// String[] layer1 = { "1", "1", "1", "1" };
		ArrayList<String[]> brickLayout = new ArrayList<String[]>();
		brickLayout.add(layer1);
		ArrayList<Brick> addedBricks = StageBuilder.buildBricks(100, brickLayout);
		fieldElements.addAll(addedBricks);

		fieldElements.add(myBouncer);
		fieldElements.add(paddle);
		fieldElements.add(planet);
		field = new Field(stage, fieldElements);
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step(double SECOND_DELAY)
	{
		ArrayList<FieldObject> addedObjects = new ArrayList<FieldObject>();
		for (FieldObject myElement : field.getFieldElements()) {
			ArrayList<FieldObject> newAddedObjects = myElement.step(SECOND_DELAY, field);
			if (newAddedObjects != null && newAddedObjects.size() != 0) {
				System.out.println("Hi");
				addedObjects.addAll(newAddedObjects);
			}
		}
		for (FieldObject obj : addedObjects) {
			field.addElement(obj);
		}
		field.refreshImages();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
