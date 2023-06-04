// 206573289 Omri Levi


package game.gameFunction;

import biuoop.DrawSurface;
import game.environment.Sprite;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(Game.WIDTH / 2 - 13, Game.BORDER_THICKNESS / 2 + 7, "Score: " + score.getValue(), 15);
    }
}
