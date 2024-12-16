//212067466 Roee Buchler
package Hit;
import Geometry.Ball;
import Geometry.Block;
/**
 * The PrintingHitListener class implements the HitListener interface
 * and prints a message whenever a block is hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * Prints a message indicating that a block was hit.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
