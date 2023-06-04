// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.gameFunction.GameLevel;

import java.awt.*;

public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        d.setColor(Color.BLACK);

        String text = "paused -- press space to continue";
        int textSize = 40;
        int xText = (GameLevel.WIDTH - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (GameLevel.HEIGHT + textSize) / 2;

        d.drawText(xText, yText, text, textSize);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
