// 206573289 Omri Levi


package game.gameFunction.indicatorsAndCounters;

import biuoop.DrawSurface;
import game.gameFunction.environment.Sprite;
import game.gameFunction.GameLevel;

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
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);

        String text = "Score: " + score.getValue();
        int textSize = 15;
        int xText = (d.getWidth() - textSize * text.length() / 2) / 2;
        int yText = (GameLevel.BORDER_THICKNESS + textSize) / 2;

        d.drawText(xText, yText, text, textSize);
    }
}
