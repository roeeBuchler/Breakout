//212067466 Roee Buchler
package Geometry;
/**
 * The Collidable interface specifies the methods that a collidable object should implement.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle.
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notifies the collidable object that it has been hit.
     *
     * @param hitter the ball that hit the collidable object
     * @param collisionPoint the point at which the collision occurred
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
