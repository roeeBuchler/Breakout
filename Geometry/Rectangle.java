//212067466 Roee Buchler
package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle defined by its top-left and bottom-right points.
 * Provides methods to get and set the corners of the rectangle,
 * calculate its dimensions, and check if a point is within its bounds.
 */
public class Rectangle implements Collidable {
    // Fields
    private Point upperLeft;
    private Point bottomRight;
    private Point topRight;
    private Point bottomleft;
    private int width;
    private int height;
    private Point collisonPoint;
    private Velocity hitVelocity;

    // Constructors

    /**
     * Constructs a rectangle with the given top-left and bottom-right points.
     *
     * @param bottomRight the bottom-right point of the rectangle
     * @param upperLeft   the top-left point of the rectangle
     */
    public Rectangle(Point bottomRight, Point upperLeft) {
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
        this.width = (int) Math.abs(bottomRight.getX() - upperLeft.getX());
        this.height = (int) Math.abs(upperLeft.getY() - bottomRight.getY());
    }

    /**
     * Constructs a rectangle with the given top-left point, width, and height.
     *
     * @param upperLeft the top-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, int width, int height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
        this.bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        this.topRight = new Point(upperLeft.getX() + width, upperLeft.getY());
    }

    // Getters and setters

    /**
     * Returns the top-right point of the rectangle.
     *
     * @return the top-right point of the rectangle
     */
    public Point getTopRight() {
        return new Point(bottomRight.getX(), upperLeft.getY());
    }

    /**
     * Sets the top-right point of the rectangle.
     *
     * @param topRight the new top-right point of the rectangle
     */
    public void setTopRight(Point topRight) {
        this.topRight = new Point(topRight.getX(), topRight.getY());
    }

    /**
     * Returns the bottom-left point of the rectangle.
     *
     * @return the bottom-left point of the rectangle
     */
    public Point getBottomLeft() {
        return new Point(upperLeft.getX(), bottomRight.getY());
    }

    /**
     * Sets the bottom-left point of the rectangle.
     *
     * @param bottomLeft the new bottom-left point of the rectangle
     */
    public void setBottomLeft(Point bottomLeft) {
        this.bottomleft = new Point(bottomLeft.getX(), bottomLeft.getY());
    }

    /**
     * Returns the top-left point of the rectangle.
     *
     * @return the top-left point of the rectangle
     */
    public Point getUpperLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY());
    }
    /**
     * sets the upper left point of the rectangle.
     * @param upperLeft the new upper left point of the rectangle
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
    }

    /**
     * Returns the bottom-right point of the rectangle.
     *
     * @return the bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return new Point(bottomRight.getX(), bottomRight.getY());
    }

    /**
     * Sets the bottom-right point of the rectangle.
     * @param bottomRight the new bottom-right point of the rectangle
     */
    public void setBottomRight(Point bottomRight) {
        this.bottomRight = new Point(bottomRight.getX(), bottomRight.getY());
    }

    // Other methods

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        return new Line(getBottomLeft(), getBottomRight());
    }

    /**
     * Returns the top line of the rectangle.
     *
     * @return the top line of the rectangle
     */
    public Line getTopLine() {
        return new Line(upperLeft, getTopRight());
    }

    /**
     * Returns the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        return new Line(getBottomLeft(), upperLeft);
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        return new Line(bottomRight, getTopRight());
    }

    /**
     * Checks if a given point is within the bounds of the rectangle.
     *
     * @param point the point to check
     * @return true if the point is within the bounds of the rectangle, false otherwise
     */
    public boolean isWithinBounds(Point point) {
        return point.getX() >= upperLeft.getX() && point.getX() <= bottomRight.getX()
                && point.getY() >= upperLeft.getY() && point.getY() <= bottomRight.getY();
    }
    /**
     * Returns a list of intersection points with the specified line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<Point>();
        if (line.isIntersecting(this.getRightLine())) {
            intersectionPoints.add(line.intersectionWith(this.getRightLine()));
        }
        if (line.isIntersecting(this.getTopLine())) {
            intersectionPoints.add(line.intersectionWith(this.getTopLine()));
        }
        if (line.isIntersecting(this.getBottomLine())) {
            intersectionPoints.add(line.intersectionWith(this.getBottomLine()));
        }
        if (line.isIntersecting(this.getLeftLine())) {
            intersectionPoints.add(line.intersectionWith(this.getLeftLine()));
        }
        //check for corners
        return checkForCorners(intersectionPoints);
    }
    /**
     * Returns a list of unique points from the specified list.
     * makes sure there are no duplicate points in the list.
     *
     * @param list the list to check for unique points
     * @return a list of unique points from the specified list
     */
    private List<Point> checkForCorners(java.util.List<Point> list) {
        List<Point> uniquePoints = new ArrayList<>();
        for (Point current : list) {
            boolean isDuplicate = false;
            for (Point uniquePoint : uniquePoints) {
                if (current.equals(uniquePoint)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                uniquePoints.add(current);
            }
        }
        return uniquePoints;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    /**
     * Notifies the collidable object that it has been hit.
     *
     * @param hitter the ball that hit the collidable object
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //Notify the object it has been hit
        this.collisonPoint = collisionPoint;
        this.hitVelocity = currentVelocity;
        //horizontal
        if (this.getBottomLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (this.getTopLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //vertical
        if (this.getLeftLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (this.getRightLine().isOnTheLine(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }

        return currentVelocity;
    }
}
