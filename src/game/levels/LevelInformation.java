// 206573289 Omri Levi


package game.levels;

import game.levels.gameFunction.environment.Sprite;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The List of initial velocities of each ball.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed.
     *
     * @return the speed.
     */
    int paddleSpeed();

    /**
     * Paddle width.
     *
     * @return the width
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the int
     */
    default int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
