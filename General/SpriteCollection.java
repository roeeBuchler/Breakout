//212067466 Roee Buchler
package General;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The SpriteCollection class represents a collection of sprites.
 * It includes methods to add a sprite to the collection, notify all sprites that time has passed, and draw all sprites.
 */
public class SpriteCollection {
    //fields
    private List<Sprite> sprites;
    /**
     * Constructs a new SpriteCollection with the specified list of sprites.
     *
     * @param sprites the list of sprites
     */
    public SpriteCollection(List<? extends Sprite> sprites) {
        this.sprites = new ArrayList<>(sprites);
    }
    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * Adds the specified sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Call the timePassed() method on all sprites in the collection.
     * This method should be invoked once per frame to update the state of each sprite based on time passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }
    /**
     * Draw all sprites in the collection to the provided DrawSurface.
     * This method should be called once per frame to render all sprites on the screen.
     *
     * @param d the DrawSurface on which sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
    /**
     * Returns the list of sprites in the collection.
     *
     * @return the list of sprites in the collection
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}
