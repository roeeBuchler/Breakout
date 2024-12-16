//212067466 Roee Buchler
package General;
import Geometry.Line;
import Geometry.Collidable;
import Geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class represents the environment in which the game takes place.
 * It includes methods to add collidables to the environment and detect collisions between objects.
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidables;
    /**
     * Constructs a new GameEnvironment object with a list of initial collidables.
     *
     * @param collidables a list of objects implementing the Collidable interface.
     */
    public GameEnvironment(List<? extends Collidable> collidables) {
        this.collidables = new ArrayList<>(collidables);
    }
    /**
     * Adds a collidable object to the environment. This object will now be considered
     * in collision detections within the environment.
     *
     * @param c the collidable object to add to the environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Returns the closest collision that occurs between the given trajectory and the collidables in the environment.
     *
     * @param trajectory the trajectory of the object
     * @return the closest collision that occurs between the trajectory and the collidables in the environment
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> data = new ArrayList<CollisionInfo>();
        for (Collidable c : this.collidables) {
            if (trajectory.closestIntersectionToStartOfLine((Rectangle) c) != null) {
                data.add(new CollisionInfo(trajectory.closestIntersectionToStartOfLine((Rectangle) c), c));
            }
        }
        if (data.isEmpty()) {
            return null;
        }
        CollisionInfo info = data.get(0);
        for (CollisionInfo d : data) {
            if (d.collisionPoint().distance(trajectory.start()) < info.collisionPoint().distance(trajectory.start())) {
                info = d;
            }
        }
        return info;
    }
    /**
     * Returns the list of collidables in the environment.
     *
     * @return the list of collidables
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
}
