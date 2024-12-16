//212067466 Roee Buchler
package General;

import biuoop.DrawSurface;
/**
 * Represents drawable and updatable objects in the game.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * This method is called whenever the sprite needs to be displayed.
     *
     * @param d the draw surface on which the sprite will be drawn
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the sprite that a unit of time has passed.
     * This method is used to update the sprite's state (position, animation frame).
     */
    void timePassed();
}