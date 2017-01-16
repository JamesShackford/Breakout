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

		Paddle paddle = new Paddle();
		paddle.setX(100);
		paddle.setY(300);

		for (int i = 0; i < 10; i++) {
			RegularBrick brick = new RegularBrick();
			brick.setX(i * brick.getImage().getBoundsInLocal().getWidth());
			brick.setY(50);
			fieldElements.add(brick);
		}
		fieldElements.add(myBouncer);
		fieldElements.add(paddle);
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
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
