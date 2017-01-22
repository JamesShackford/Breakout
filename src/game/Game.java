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
	private Level level;
	private ArrayList<Counter> counters;

	@Override
	public void start(Stage stage) throws Exception
	{
		counters = new ArrayList<Counter>();
		counters.add(new LevelCounter());
		counters.add(new ScoreCounter());
		counters.add(new LifeCounter());
		field = new Field(stage);
		level = new LevelOne(field, counters);

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
				addedObjects.addAll(newAddedObjects);
			}
		}
		for (FieldObject obj : addedObjects) {
			field.addElement(obj);
		}
		if (checkLevelRestart()) {
			subtractLife();
		}
		field.refreshImages();
		if (this.level.levelComplete(field)) {
			nextLevel();
		}
	}

	private void nextLevel()
	{
		for (Counter counter : counters) {
			if (counter instanceof LevelCounter) {
				((LevelCounter) counter).add(1);
			}
		}
		if (level instanceof LevelOne) {
			level = new LevelTwo(field, counters);
		}
		if (level instanceof LevelTwo) {
			level = new LevelThree(field, counters);
		}
	}

	private boolean checkLevelRestart()
	{
		for (FieldObject elem : field.getFieldElements()) {
			if (elem instanceof Bouncer && !((Bouncer) elem).isDead()) {
				return false;
			}
		}
		return true;
	}

	private void subtractLife()
	{
		field.addElement(new Bouncer());
		for (FieldObject elem : field.getFieldElements()) {
			if (elem instanceof LifeCounter) {
				LifeCounter lifeCounter = (LifeCounter) elem;
				lifeCounter.add(-1);
				if (lifeCounter.getCount() <= 0) {
					field.getStage().close();
				}
			}
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
