// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Animate one frame using a given surface.
     *
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop is in charge of the stopping conditions.
     *
     * @return true if the animation should stop
     */
    boolean shouldStop();
}
