//212067466 Roee Buchler
package Geometry;

/**
 * The Point class represents a point in 2D space with x and y coordinates.
 */
public class Point {
    // Fields
    private double x;
    private double y;

    // Constructor

    /**
     * Constructs a point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates and returns the distance between this point and another point.
     *
     * @param other the other point.
     * @return the distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point to compare.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (other != null) {
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate of this point.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate of this point.
     */
    public void setY(double y) {
        this.y = y;
    }
    /**
     * Returns a string representation of this point.
     *
     * @return a string representation of this point.
     */
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
