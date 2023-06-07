// 206573289 Omri Levi


package game.gameFunction.listeners;

import game.shapes.circles.Ball;
import game.shapes.squares.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {

    /**
     * Hit event occurs whenever the beingHit object is hit.
     *
     * @param beingHit the block that's being hit
     * @param hitter   the ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
