// 206573289 Omri Levi


package game.listener;

import game.gameFunction.Counter;
import game.gameFunction.GameLevel;
import game.shapes.circles.Ball;
import game.shapes.squares.Block;

/**
 * The block remover is in charge of removing blocks from the game, and keeping count of the number of remaining blocks.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);               // remove the listener from the block
        beingHit.removeFromGame(this.game);             // remove the block from the game
        remainingBlocks.decrease(1);                    // decrease the remaining blocks counter by 1
    }
}