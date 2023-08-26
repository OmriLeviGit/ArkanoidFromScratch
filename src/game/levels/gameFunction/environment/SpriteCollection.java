// 206573289 Omri Levi


package game.levels.gameFunction.environment;

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * A class that stores information about the sprites in the game.
 */
public class SpriteCollection {
    private final ArrayList<Sprite> spriteCollection = new ArrayList<>();

    /**
     * Adds a sprite to the sprite collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        spriteCollection.add(s);
    }

    /**
     * Removes a sprite from the sprite collection.
     *
     * @param s the sprite to remove
     */
    public void removeS(Sprite s) {
        spriteCollection.remove(s);
    }

    /**
     * Calls the timePassed method on all sprites in the collection.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> spriteCollection = new ArrayList<>(this.spriteCollection);
        for (Sprite sprite : spriteCollection) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn method on all sprites in the collection.
     *
     * @param d the DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollection) {
            sprite.drawOn(d);
        }
    }
}