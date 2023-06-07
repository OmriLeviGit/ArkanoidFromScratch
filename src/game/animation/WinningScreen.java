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
    private final int finalScore;
    private final boolean stop;

    /**
     * Instantiates a new Winning screen.
     *
     * @param score the score
     */
    public WinningScreen(Counter score) {
        this.finalScore = score.getValue();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);
        String text = "You Win! Your score is " + this.finalScore;
        int textSize = 40;
        int xText = (d.getWidth() - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (d.getHeight() + textSize) / 2;

        d.drawText(xText, yText, text, textSize);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
