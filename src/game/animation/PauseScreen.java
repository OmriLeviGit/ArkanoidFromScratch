// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {
        String text = "paused -- press space to continue";
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
