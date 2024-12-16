//212067466 Roee Buchler
package General;
import Geometry.Point;
import Geometry.Collidable;
/**
 * The CollisionInfo class represents a collision between a ball and a collidable object.
 * It contains the point at which the collision occurs and the collidable object involved in the collision.
 */
public class CollisionInfo {
    //fields
    private Point collisionPoint;
    private Collidable collidableObject;
    // the point at which the collision occurs.

    /**
     * Constructs a CollisionInfo object.
     *
     * @param collisionPoint   the point at which the collision occurs
     * @param collidableObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * returns the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collidableObject;
    }
}