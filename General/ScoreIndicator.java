//212067466 Roee Buchler

package General;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * The ScoreIndicator class represents a score indicator in the game.
 * It includes methods to draw the score on the screen and update the score.
 */
public class ScoreIndicator implements Sprite {
    //fields
    private Counter score;
    private Game game;
    /**
     * Constructs a new ScoreIndicator with the specified game and score counter.
     *
     * @param game  the game to which the score belongs
     * @param score the score counter
     */
    public ScoreIndicator(Game game, Counter score) {
        this.score = score;
        this.game = game;
    }

    /**
     * Draw the sprite to the screen.
     * This method is called whenever the sprite needs to be displayed.
     *
     * @param d the draw surface on which the sprite will be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 15);
        d.setColor(Color.black);
        d.drawText(400, 13, "Score:" + this.score.getValue(), 13);
    }

    /**
     * Notify the sprite that a unit of time has passed.
     * This method is used to update the sprite's state (position, animation frame).
     */
    @Override
    public void timePassed() {

    }
}
