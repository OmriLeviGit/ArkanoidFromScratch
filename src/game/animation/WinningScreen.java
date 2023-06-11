// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import game.gameFunction.indicatorsAndCounters.Counter;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Winning screen.
 */
public class WinningScreen implements Animation {
    private final Counter score;

    /**
     * Instantiates a new Winning screen.
     *
     * @param score the score
     */
    public WinningScreen(Counter score) {
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        int textSize = 40;
        String text = "You Win! Your score is " + this.score.getValue();
        int xText = (d.getWidth() - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (d.getHeight() + textSize) / 2;

        d.setColor(Color.GREEN);
        d.drawText(xText, yText, text, textSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
