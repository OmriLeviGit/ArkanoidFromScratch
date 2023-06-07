// 206573289 Omri Levi


package game.levels;

import game.gameFunction.environment.Sprite;
import game.gameFunction.GameLevel;
import game.levels.backgrounds.BullseyeBackground;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;
import game.shapes.squares.Rectangle;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

/**
 * Holds all the information of the Bullseye level.
 */
public class Bullseye implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Collections.singletonList(new Velocity(0, 5));
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level: Bull's-Eye";
    }

    @Override
    public Sprite getBackground() {
        return new BullseyeBackground();
    }

    @Override
    public List<Block> blocks() {
        int size = 15;
        Rectangle target = new Rectangle((double) (GameLevel.WIDTH - size) / 2,
                (double) (GameLevel.HEIGHT - size) / 3 - 3, size, size);
        target.setColor(Color.RED);
        return Collections.singletonList(new Block(target));
    }
}
