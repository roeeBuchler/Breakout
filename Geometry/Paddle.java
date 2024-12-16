//212067466 Roee Buchler
package Geometry;

import biuoop.KeyboardSensor;
import java.awt.Color;
import General.Sprite;
/**
 * The Paddle class represents a paddle in the game.
 * The paddle is controlled by the left and right arrow keys on the keyboard.
 */
public class Paddle extends Block implements Sprite, Collidable {
    //fields
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddleZone1;
    private Paddle paddleZone2;
    private Paddle paddleZone3;
    private Paddle paddleZone4;
    private Paddle paddleZone5;
    private Line topLine;
    private Point topRight;
    private Line leftLine;
    private Line rightLine;

    /**
     * Constructs a new Paddle with the specified upper-left point, width, height, and keyboard sensor.
     *
     * @param upperLeft the upper-left point of the paddle
     * @param width the width of the paddle
     * @param height the height of the paddle
     * @param keyboard the keyboard sensor that controls the paddle
     */
    public Paddle(Point upperLeft, int width, int height, KeyboardSensor keyboard) {
        super(upperLeft, width, height, Color.BLACK);
        this.keyboard = keyboard;
        this.topLine = new Line(getUpperLeft(), getTopRight());
        this.leftLine = new Line(getBottomLeft(), getUpperLeft());
        this.rightLine = new Line(getBottomRight(), getTopRight());
        this.topRight = getTopRight();
    }

    /**
     * when the paddle crosses the border of the screen, update it to the other side.
     */
    public void locationUpdate() {
        if (this.getTopRight().getX() <= 0) {
            setUpperLeft(new Point(699, getUpperLeft().getY()));
            setBottomRight(new Point(799, getBottomRight().getY()));
        }
        if (this.getTopRight().getX() >= 800) {
            setUpperLeft(new Point(0, getUpperLeft().getY()));
            setBottomRight(new Point(100, getBottomRight().getY()));
        }
    }

    /**
     * Moves the paddle left or right according to the arrow keys pressed.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            setUpperLeft(new Point(getUpperLeft().getX() - 5, getUpperLeft().getY()));
            setBottomRight(new Point(getBottomRight().getX() - 5, getBottomRight().getY()));
            locationUpdate();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            setUpperLeft(new Point(getUpperLeft().getX() + 5, getUpperLeft().getY()));
            setBottomRight(new Point(getBottomRight().getX() + 5, getBottomRight().getY()));
            locationUpdate();
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        paddleZone1 = new Paddle(this.getUpperLeft(), getWidth() / 5, getHeight(), this.keyboard);
        paddleZone2 = new Paddle(paddleZone1.topRight, getWidth() / 5, getHeight(), this.keyboard);
        paddleZone3 = new Paddle(paddleZone2.topRight, getWidth() / 5, getHeight(), this.keyboard);
        paddleZone4 = new Paddle(paddleZone3.topRight, getWidth() / 5, getHeight(), this.keyboard);
        paddleZone5 = new Paddle(paddleZone4.topRight, getWidth() / 5, getHeight(), this.keyboard);
        if (this.getLeftLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            return currentVelocity;
        }
        if (this.getRightLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
            return currentVelocity;
        }
        if (paddleZone1.topLine.isOnTheLine(collisionPoint)) {
            currentVelocity.setAngle(300 - 90);
            return currentVelocity;
        }
        if (paddleZone2.topLine.isOnTheLine(collisionPoint)) {
            currentVelocity.setAngle(330 - 90);
            return currentVelocity;
        }
        if (paddleZone3.topLine.isOnTheLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
            return currentVelocity;
        }
        if (paddleZone4.topLine.isOnTheLine(collisionPoint)) {
            currentVelocity.setAngle(30 - 90);
            return currentVelocity;
        }
        if (paddleZone5.topLine.isOnTheLine(collisionPoint)) {
            currentVelocity.setAngle(60 - 90);
            return currentVelocity;
        }
        return currentVelocity;
    }
}