// 206573289 Omri Levi


package game.levels.gameFunction.environment;

import biuoop.DrawSurface;
import game.levels.gameFunction.GameLevel;

/**
 * The Sprite interface represents an object that can be drawn on a surface and notified of time passing.
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified surface.
     *
     * @param d the surface to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     */
    default void timePassed() {

    }

    /**
     * Adds the sprite to the specified game by calling the game's addSprite method.
     *
     * @param g the game to add the sprite to
     */
    default void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

