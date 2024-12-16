//212067466 Roee Buchler
package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;

import General.Sprite;
import General.Game;
import General.GameEnvironment;
import General.CollisionInfo;


/**
 * The Ball class represents a ball with a center point, radius, color, and velocity.
 * It includes methods to move the ball, detect and resolve collisions with rectangles and screen boundaries,
 * and draw the ball on a given surface.
 */
public class Ball implements Sprite {
    private static final double EPSILON = 0.000001;
    private Point center;
    private final int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a new Ball with the specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball with the specified coordinates for the center point, radius, and color.
     *
     * @param x     the x-coordinate of the center point
     * @param y     the y-coordinate of the center point
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball with the specified center point, radius, color and game environment.
     *
     * @param center          the center point of the ball
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment of the ball
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets the x-coordinate of the center point.
     *
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y-coordinate of the center point.
     *
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the size of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        return 2 * this.radius;
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Sets the color of the ball.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * gets the game environment of the ball.
     *
     * @return the game environment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * sets the game environment of the ball.
     *
     * @param gameEnvironment the game environment to set
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Adds the ball to the specified game.
     *
     * @param game the game to add the ball to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with specified dx and dy values.
     *
     * @param dx the change in x per unit time
     * @param dy the change in y per unit time
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.setVelocity(v);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Moves the ball one step, considering screen boundaries.
     *
     * @param width  the width of the screen
     * @param height the height of the screen
     */
    public void moveOneStep(int width, int height) {
        if ((this.center.getX() + this.radius) >= width || (this.center.getX() - this.radius) <= 0) {
            this.setVelocity(-(this.velocity.getDx()), (this.velocity.getDy()));
        }
        if ((this.center.getY() + this.radius) >= height || (this.center.getY() - this.radius) <= 0) {
            this.setVelocity((this.velocity.getDx()), -(this.velocity.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Ensures the ball is inside the bounds of the specified rectangle.
     * If the ball is outside, it adjusts the ball's position to be inside the rectangle.
     *
     * @param bottomRight the bottom-right corner of the rectangle
     * @param topLeft     the top-left corner of the rectangle
     */
    private void ensureInsideBounds(Point bottomRight, Point topLeft) {
        double x = this.center.getX();
        double y = this.center.getY();

        if ((x + this.radius + EPSILON) > bottomRight.getX()) {
            x = bottomRight.getX() - this.radius - EPSILON;
        } else if ((x - this.radius - EPSILON) < topLeft.getX()) {
            x = topLeft.getX() + this.radius + EPSILON;
        }

        if ((y + this.radius + EPSILON) > bottomRight.getY()) {
            y = bottomRight.getY() - this.radius - EPSILON;
        } else if ((y - this.radius - EPSILON) < topLeft.getY()) {
            y = topLeft.getY() + this.radius + EPSILON;
        }

        this.center = new Point(x, y);
    }

    /**
     * Moves the ball one step, considering collisions with the screen boundaries and rectangles.
     */
    public void moveOneStep() {
        Point lastGoodLocation = this.center;
        double xEnd = this.getCenter().getX() + this.velocity.getDx();
        double yEnd = this.getCenter().getY() + this.velocity.getDy();
        Point end = new Point(xEnd, yEnd);
        Line trajectoryLine = new Line(this.center, end);
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectoryLine);
        if (info == null) {
            this.center = new Point(xEnd, yEnd);
        } else {
            Point update = info.collisionPoint();
            this.center = new Point(
                    lastGoodLocation.getX(),
                    lastGoodLocation.getY()
            );
            this.velocity = info.collisionObject().hit(this, update, this.velocity);
            makeSureOutsideRec(info.collisionObject(), info.collisionObject().getCollisionRectangle().getTopLine());
        }
    }

    /**
     * makes sure the ball is outside the paddle (or any other moving collidable).
     *
     * @param collidable the collidable object
     * @param topLine    the top line of the collidable object
     */
    public void makeSureOutsideRec(Collidable collidable, Line topLine) {
        Rectangle rectangle = (Rectangle) collidable;
        if (rectangle.isWithinBounds(this.center)) {
            if (this.center.distance(topLine.start()) < this.center.distance(topLine.end())) {
                this.center = new Point(topLine.start().getX() - 8, topLine.start().getY() - 8);
            }
            if (this.center.distance(topLine.end()) < this.center.distance(topLine.start())) {
                this.center = new Point(topLine.end().getX() + 8, topLine.start().getY() + 8);
            }
        }
    }
    /**
     * Removes the ball from the specified game.
     *
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
