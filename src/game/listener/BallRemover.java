// 206573289 Omri Levi


package game.listener;

import game.gameFunction.Counter;
import game.gameFunction.GameLevel;
import game.shapes.circles.Ball;
import game.shapes.squares.Block;

/**
 * The Ball remover is in charge of removing balls, and updating a ball counter.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game        the game
     * @param ballCounter the ball counter
     */
    public BallRemover(GameLevel game, Counter ballCounter) {
        this.game = game;
        this.remainingBalls = ballCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);             // remove the block from the game
        remainingBalls.decrease(1);                    // decrease the remaining blocks counter by 1
    }
}
