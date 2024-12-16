//212067466 Roee Buchler
package Hit;

import General.Game;
import Geometry.Ball;
import Geometry.Block;
import General.Counter;

/**
 * The BlockRemover class is responsible for removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    /**
     * Constructs a new BlockRemover object.
     *
     * @param game the game from which the blocks should be removed
     * @param remainingBlocks the counter tracking the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }


    /**
     * Handles the hit event when a ball hits a block.
     * Removes the block from the game and updates the remaining blocks counter.
     * Also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that was hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            this.remainingBlocks.decrease(1);
            hitter.setColor(beingHit.getColor());
        }
    }
}