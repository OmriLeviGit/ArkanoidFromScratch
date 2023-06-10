// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import game.gameFunction.indicatorsAndCounters.Counter;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Losing screen.
 */
public class LosingScreen implements Animation {
    private final Counter score;

    /**
     * Instantiates a new Losing screen.
     *
     * @param score the score
     */
    public LosingScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        String text = "Game Over. Your score is " + this.score.getValue();
        int textSize = 40;
        int xText = (d.getWidth() - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (d.getHeight() + textSize) / 2;

        d.setColor(Color.BLACK);
        d.drawText(xText, yText, text, textSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}

