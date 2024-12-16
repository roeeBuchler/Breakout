//212067466 Roee Buchler
package Hit;

import General.Counter;
import General.Game;
import Geometry.Ball;
import Geometry.Block;
/**
 * The ScoreTrackingListener class is responsible for tracking the score in the game.
 * The score increases when a block is hit by a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter score;
    private Game game;

    /**
     * Constructs a new ScoreTrackingListener with the specified game and score counter.
     *
     * @param game  the game to track
     * @param score the score counter
     */
    public ScoreTrackingListener(Game game, Counter score) {
        this.score = score;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            score.increase(5);
        }
        System.out.println("score is:" + score.getValue());
    }
}
