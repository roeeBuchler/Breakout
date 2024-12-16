//212067466 Roee Buchler
package Geometry;

import General.Game;
import General.Sprite;
import Hit.HitListener;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Hit.HitNotifier;

/**
 * The Block class represents a block in the game.
 * Blocks are rectangles that can be hit by the ball.
 */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {
    //fields
    private Point collisonPoint;
    private Velocity hitVelocity;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * Constructs a new Block with the specified upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the block
     * @param width     the width of the block
     * @param height    the height of the block
     * @param color     the color of the block
     */
    public Block(Point upperLeft, int width, int height, Color color) {
        super(upperLeft, width, height);
        this.color = color;
        hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this;
    }

    /**
     * draws the block on the given DrawSurface.
     *
     * @param drawSurface the DrawSurface to draw the block on
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), getWidth(), getHeight());
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), getWidth(), getHeight());
    }

    /**
     * Notify the block that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adds the block to the game.
     *
     * @param game the game to add the block to
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * Checks if the color of the ball matches the color of the block.
     *
     * @param ball the ball to check color against
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.color);
    }
    /**
     * Removes the block from the game.
     *
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return super.hit(hitter, collisionPoint, currentVelocity);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }
    /**
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }
}
