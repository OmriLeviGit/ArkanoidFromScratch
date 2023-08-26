// 206573289 Omri Levi


package game.animationAndScreens;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);
        String text = "Paused";
        int textSize = 40;
        int xText = d.getWidth() / 2 - 65;
        int yText = (d.getHeight() + textSize) / 2 - 40;
        d.drawText(xText, yText, text, textSize);

        text = "press 'Space' to continue";
        textSize = 30;
        xText = d.getWidth() / 2 - 170;
        yText = (d.getHeight() + textSize) / 2;
        d.drawText(xText, yText, text, textSize);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
