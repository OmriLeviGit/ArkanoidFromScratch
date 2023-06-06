// 206573289 Omri Levi


package game.levels;

import game.environment.Sprite;
import game.gameFunction.GameLevel;
import game.shapes.circles.Velocity;
import game.shapes.squares.Block;
import game.shapes.squares.Rectangle;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return Collections.singletonList(new Velocity(0, 7));
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
        return "Bull's-Eye";
    }

    @Override
    public Sprite getBackground() {
        Rectangle background = new Rectangle(GameLevel.WIDTH, GameLevel.HEIGHT);
        background.setColor(new Color(50, 100, 0));
        return background;
    }

    @Override
    public List<Block> blocks() {
        int size = 10;
        Block block = new Block(new Rectangle((double) (GameLevel.WIDTH - size) / 2, 50, size, size));
        block.getCollisionRectangle().setColor(Color.RED);
        return Collections.singletonList(block);
    }
}
