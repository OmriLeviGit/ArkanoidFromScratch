// 206573289 Omri Levi


package game.animationAndScreens;

import biuoop.DrawSurface;
import game.levels.gameFunction.indicatorsAndCounters.Counter;

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

        d.setColor(Color.GREEN);
        String text = "You Win!";
        int textSize = 40;
        int xText = d.getWidth() / 2 - 70;
        int yText = (d.getHeight() + textSize) / 2 - 20;
        d.drawText(xText, yText, text, textSize);

        text = "Your score is " + this.score.getValue();
        textSize = 30;
        xText = d.getWidth() / 2 - 105;
        yText = (d.getHeight() + textSize) / 2 + 20;
        d.drawText(xText, yText, text, textSize);


        d.setColor(Color.GRAY);
        text = "Press 'Space' to end";
        textSize = 25;
        xText = d.getWidth() / 2 - 100;
        yText = (d.getHeight() + textSize) / 2 + 130;
        d.drawText(xText, yText, text, textSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
