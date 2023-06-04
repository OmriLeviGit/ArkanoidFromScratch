// 206573289 Omri Levi


package game.listener;

import game.gameFunction.Counter;
import game.shapes.circles.Ball;
import game.shapes.squares.Block;

/**
 * The Score tracking listener updates the score counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);           // remove the listener from the block
        currentScore.increase(5);                   // increase the score
    }
}
