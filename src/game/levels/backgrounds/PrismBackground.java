// 206573289 Omri Levi


package game.levels.backgrounds;

import biuoop.DrawSurface;
import game.gameFunction.environment.Sprite;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The background of the level Prism of type Sprite.
 */
public class PrismBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // prism
        int length = 100;
        int cosLen = (int) (Math.cos(Math.toRadians(60)) * length);
        int sinLen = (int) (Math.sin(Math.toRadians(60)) * length);
        int leftPoint = 75;
        int height = 400;

        d.setColor(new Color(0, 200, 200));
        d.drawLine(leftPoint, height, leftPoint + length, height);              // bottom line
        d.drawLine(leftPoint, height, leftPoint + cosLen, height - sinLen);     // left line

        for (int i = 0; i < 5; i++) {
            d.drawLine(leftPoint + length + i, height, leftPoint + cosLen + i, height - sinLen - i);    // right line
        }

        // light
        d.setColor(Color.GRAY);
        d.drawLine(GameLevel.BORDER_THICKNESS * 2, height - (int) (0.1 * length),
                leftPoint + (int) (0.5 * length), height - (int) (0.5 * length));

        Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};

        for (int i = 0; i < color.length; i++) {
            d.setColor(color[i]);
            d.drawLine(leftPoint + (int) (0.5 * length), height - (int) (0.5 * length), 180 + i * 50, 120 + i * 25);
        }
    }
}


