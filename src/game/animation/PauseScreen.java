// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);

        String text = "paused -- press space to continue";
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
