package game;

import game.fieldobject.brick.IceBrick;
import game.fieldobject.brick.RegularBrick;
import game.fieldobject.brick.ThreeHitBrick;
import game.fieldobject.brick.TwoHitBrick;
import game.fieldobject.brick.UnbreakableBrick;
import game.fieldobject.powerup.ExtraBallPowerUp;
import game.fieldobject.powerup.FireballPowerUp;
import game.fieldobject.powerup.PointsPowerUp;
import game.fieldobject.powerup.SizePowerUp;
import game.fieldobject.powerup.StickyPaddlePowerUp;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SplashScreen
{
	private Group root;
	private Scene myScene;
	private int screenNumber;
	private final Color TITLECOLOR = Color.MAROON;
	private final Color TEXTCOLOR = Color.BLUEVIOLET;
	private final int LEFTIMAGEX = 20;
	private final int LEFTLABELX = 30;
	private final int BRICKSIZE = 20;
	private final Font TITLEFONT = Font.font(30);
	private final Font TEXTFONT = Font.font(15);

	public SplashScreen(Stage s)
	{
		screenNumber = 0;
		root = new Group();
		myScene = new Scene(root, Field.SIZE, Field.SIZE, Color.BEIGE);
		s.setScene(myScene);
		s.show();
		titleScreen();
	}

	public void titleScreen()
	{
		Label planetProtectorTitle = createLabel("PLANET PROTECTOR", TITLEFONT, TITLECOLOR, Field.CENTER_X / 4, 0);

		String introText = "Welcome to Planet Protector! The goal of this game \nis to progress through three levels\nby destroying blocks and without"
				+ " having a ball hit\nyour planet.";
		Label planetProtectorIntro = createLabel(introText, TEXTFONT, TEXTCOLOR, 0, 50);

		String winText = "You win a level by destroying all of the\nbreakable blocks on the screen. You can\nreflect"
				+ " the ball with your paddle, which can be \nmoved with the arrow keys";
		Label winInstructions = createLabel(winText, TEXTFONT, TEXTCOLOR, 0, 150);

		String powerUpText = "Press right to view the power ups";
		Label powerUpLabel = createLabel(powerUpText, TEXTFONT, TEXTCOLOR, 0, 350);

		String startGameText = "Press space to start the game";
		Label startGameLabel = createLabel(startGameText, TEXTFONT, TEXTCOLOR, 0, 375);

		root.getChildren().add(planetProtectorTitle);
		root.getChildren().add(planetProtectorIntro);
		root.getChildren().add(winInstructions);
		root.getChildren().add(powerUpLabel);
		root.getChildren().add(startGameLabel);
	}

	public void powerUpScreen()
	{
		Label powerUpTitle = createLabel("Power Ups", TITLEFONT, TITLECOLOR, Field.CENTER_X / 4, 0);

		ExtraBallPowerUp extraBallPower = new ExtraBallPowerUp();
		extraBallPower.setX(LEFTIMAGEX);
		extraBallPower.setY(70);

		Label extraBallLabel = createLabel("Extra ball power: gives you an extra ball\nto help destroy blocks with",
				TEXTFONT, TEXTCOLOR, LEFTLABELX, 60);

		StickyPaddlePowerUp stickyPaddlePowerUp = new StickyPaddlePowerUp();
		stickyPaddlePowerUp.setX(LEFTIMAGEX);
		stickyPaddlePowerUp.setY(125);

		Label stickyPaddleLabel = createLabel(
				"Sticky paddle power: causes all balls\nto stick to the paddle on contact. Press space to\nrelease them.",
				TEXTFONT, TEXTCOLOR, LEFTLABELX, 115);

		SizePowerUp sizePowerUp = new SizePowerUp();
		sizePowerUp.setX(LEFTIMAGEX);
		sizePowerUp.setY(200);

		Label sizePowerUpLabel = createLabel("Size power: Increases the size of the paddle", TEXTFONT, TEXTCOLOR,
				LEFTLABELX, 190);

		PointsPowerUp pointsPowerUp = new PointsPowerUp();
		pointsPowerUp.setX(LEFTIMAGEX);
		pointsPowerUp.setY(240);

		Label pointsPowerUpLabel = createLabel("Points power: Adds 500 points to your score", TEXTFONT, TEXTCOLOR,
				LEFTLABELX, 230);

		FireballPowerUp fireballPowerUp = new FireballPowerUp();
		fireballPowerUp.setX(LEFTIMAGEX);
		fireballPowerUp.setY(270);

		Label fireballPowerUpLabel = createLabel(
				"Fireball power: sets one ball on fire. This\nspeeds the ball up, but allows the ball\nto break through any blocks\n(except unbreakable ones) in its path",
				TEXTFONT, TEXTCOLOR, LEFTLABELX, 260);

		String brickText = "Press right to view the different bricks";
		Label brickLabel = createLabel(brickText, TEXTFONT, TEXTCOLOR, 0, 350);

		String startGameText = "Press space to start the game";
		Label startGameLabel = createLabel(startGameText, TEXTFONT, TEXTCOLOR, 0, 375);

		root.getChildren().add(powerUpTitle);
		root.getChildren().add(extraBallPower.getNode());
		root.getChildren().add(extraBallLabel);
		root.getChildren().add(stickyPaddlePowerUp.getNode());
		root.getChildren().add(stickyPaddleLabel);
		root.getChildren().add(sizePowerUp.getNode());
		root.getChildren().add(sizePowerUpLabel);
		root.getChildren().add(pointsPowerUp.getNode());
		root.getChildren().add(pointsPowerUpLabel);
		root.getChildren().add(fireballPowerUp.getNode());
		root.getChildren().add(fireballPowerUpLabel);
		root.getChildren().add(brickLabel);
		root.getChildren().add(startGameLabel);
	}

	public void brickScreen()
	{
		Label brickTitle = createLabel("Bricks", TITLEFONT, TITLECOLOR, Field.CENTER_X / 4, 0);

		Rectangle oneHitBrick = makeBrickImage(BRICKSIZE, LEFTIMAGEX, 40, RegularBrick.FILLCOLOR,
				RegularBrick.STROKECOLOR);
		Label oneHitBrickLabel = createLabel("One-Hit Brick: This brick only takes one\nhit from a ball to destroy.",
				TEXTFONT, TEXTCOLOR, LEFTLABELX + BRICKSIZE, 30);

		Rectangle twoHitBrick1 = makeBrickImage(BRICKSIZE, LEFTIMAGEX, 80, TwoHitBrick.FILLCOLOR1,
				TwoHitBrick.STROKECOLOR1);
		Rectangle twoHitBrick2 = makeBrickImage(BRICKSIZE, LEFTIMAGEX + BRICKSIZE + 2, 80, TwoHitBrick.FILLCOLOR2,
				TwoHitBrick.STROKECOLOR2);
		Label twoHitBrickLabel = createLabel("Two-Hit Brick: This brick takes two hits\nto destroy.", TEXTFONT,
				TEXTCOLOR, LEFTLABELX + 2 * BRICKSIZE, 70);

		Rectangle threeHitBrick1 = makeBrickImage(BRICKSIZE, LEFTIMAGEX, 120, ThreeHitBrick.FILLCOLOR1,
				ThreeHitBrick.STROKECOLOR1);
		Rectangle threeHitBrick2 = makeBrickImage(BRICKSIZE, LEFTIMAGEX + BRICKSIZE + 2, 120, ThreeHitBrick.FILLCOLOR2,
				ThreeHitBrick.STROKECOLOR2);
		Rectangle threeHitBrick3 = makeBrickImage(BRICKSIZE, LEFTIMAGEX + 2 * BRICKSIZE + 4, 120,
				ThreeHitBrick.FILLCOLOR3, ThreeHitBrick.STROKECOLOR3);
		Label threeHitBrickLabel = createLabel("Three-Hit Brick: This brick takes three\nhits to destroy", TEXTFONT,
				TEXTCOLOR, LEFTLABELX + 3 * BRICKSIZE, 110);

		Rectangle iceBrick = makeBrickImage(BRICKSIZE, LEFTIMAGEX, 160, IceBrick.FILLCOLOR, IceBrick.STROKECOLOR);
		Label iceBrickLabel = createLabel(
				"Ice Brick: This brick can only be destroyed\nwhen a fireball hits it. It gives\nthe most points and has the highest chance\nof producing a power up.",
				TEXTFONT, TEXTCOLOR, LEFTLABELX + BRICKSIZE, 150);

		Rectangle unbreakableBrick = makeBrickImage(BRICKSIZE, LEFTIMAGEX, 240, UnbreakableBrick.FILLCOLOR,
				UnbreakableBrick.STROKECOLOR);
		Label unbreakableBrickLabel = createLabel("Unbreakable Brick: This brick can never\nbe destroyed", TEXTFONT,
				TEXTCOLOR, LEFTLABELX + BRICKSIZE, 235);

		String startGameText = "Press space to start the game";
		Label startGameLabel = createLabel(startGameText, TEXTFONT, TEXTCOLOR, 0, 375);

		root.getChildren().add(brickTitle);
		root.getChildren().add(oneHitBrick);
		root.getChildren().add(oneHitBrickLabel);
		root.getChildren().add(twoHitBrick1);
		root.getChildren().add(twoHitBrick2);
		root.getChildren().add(twoHitBrickLabel);
		root.getChildren().add(threeHitBrick1);
		root.getChildren().add(threeHitBrick2);
		root.getChildren().add(threeHitBrick3);
		root.getChildren().add(threeHitBrickLabel);
		root.getChildren().add(iceBrick);
		root.getChildren().add(iceBrickLabel);
		root.getChildren().add(unbreakableBrick);
		root.getChildren().add(unbreakableBrickLabel);
		root.getChildren().add(startGameLabel);
	}

	public Rectangle makeBrickImage(int size, int x, int y, Color fillColor, Color strokeColor)
	{
		Rectangle brick = new Rectangle();
		brick.setHeight(size);
		brick.setWidth(size);
		brick.setX(x);
		brick.setY(y);
		brick.setFill(fillColor);
		brick.setStroke(strokeColor);
		return brick;
	}

	public void nextScreen()
	{
		root.getChildren().clear();
		if (screenNumber < 2) {
			screenNumber += 1;
		}
		if (screenNumber == 1) {
			powerUpScreen();
		} else if (screenNumber >= 2) {
			brickScreen();
		} else {
			titleScreen();
		}
	}

	public void lastScreen()
	{
		root.getChildren().clear();
		if (screenNumber > 0) {
			screenNumber -= 1;
		}
		if (screenNumber == 1) {
			powerUpScreen();
		} else if (screenNumber <= 0) {
			titleScreen();
		} else {
			brickScreen();
		}
	}

	public Label createLabel(String text, Font font, Color textColor, int xPosition, int yPosition)
	{
		Label label = new Label();
		label.setText(text);
		label.setFont(font);
		label.setTextFill(textColor);
		label.relocate(xPosition, yPosition);
		return label;
	}

	public Scene getScene()
	{
		return myScene;
	}

}
