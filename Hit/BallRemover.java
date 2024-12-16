//212067466 Roee Buchler
package Hit;

import General.Counter;
import General.Game;
import Geometry.Ball;
import Geometry.Block;
/**
 * The BallRemover class is responsible for removing balls from the game when they hit a block.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter availableBalls;
    /**
     * Constructs a new BallRemover object.
     *
     * @param game the game from which the ball should be removed
     * @param availableBalls the counter tracking the number of available balls
     */
    public BallRemover(Game game, Counter availableBalls) {
        this.game = game;
        this.availableBalls = availableBalls;
    }
    /**
     * Handles the hit event when a ball hits a block.
     * Decreases the available balls counter and removes the ball from the game.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        availableBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}
