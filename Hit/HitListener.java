//212067466 Roee Buchler
package Hit;

import Geometry.Ball;
import Geometry.Block;
/**
 * The HitListener interface specifies the method that should be implemented by any class
 * that wants to be notified when a block is hit by a ball.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
