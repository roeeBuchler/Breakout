//212067466 Roee Buchler
package Geometry;

/**
 * The Velocity class specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //fields
    private double dx = 0;
    private double dy = 0;
    // constructor
    /**
     * Constructs a Velocity with the specified change in position on the x and y axes.
     *
     * @param dx the change in position on the x-axis
     * @param dy the change in position on the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Takes a point with position (x, y) and returns a new point with position (x+dx, y+dy).
     *
     * @param p the original point
     * @return a new point with the updated position
     */
    public Point applyToPoint(Point p) {
        p = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return p;
    }
    /**
     * Returns the change in position on the x-axis.
     *
     * @return the change in position on the x-axis
     */
    public double getDx() {
        return dx;
    }
    /**
     * Returns the change in position on the y-axis.
     *
     * @return the change in position on the y-axis
     */

    public double getDy() {
        return dy;
    }

    /**
     * Creates a Velocity from the specified angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed
     * @return a Velocity object representing the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Sets the change in position on the x-axis.
     *
     * @param dx the new change in position on the x-axis
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Sets the change in position on the y-axis.
     *
     * @param dy the new change in position on the y-axis
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * Returns the angle of the velocity vector.
     *
     * @return the angle of the velocity vector
     */
    public double getAngle() {
        double radians = Math.atan2(dy, dx);  // Get the angle in radians
        double degrees = Math.toDegrees(radians);  // Convert it to degrees
        return degrees;  // Return the angle in degrees
    }
    /**
     * Returns the speed of the velocity vector.
     *
     * @return the speed of the velocity vector
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);  // Calculate and return the magnitude of the velocity vector
    }
    /**
     * Sets the angle of the velocity vector.
     *
     * @param angle the new angle of the velocity vector
     */
    public void setAngle(int angle) {
        double rad = Math.toRadians(angle);
        double speed = Math.hypot(this.dx, this.dy);
        this.dx = speed * Math.cos(rad);
        this.dy = speed * Math.sin(rad);
    }


}
