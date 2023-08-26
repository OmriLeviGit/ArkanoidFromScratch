// 206573289 Omri Levi


package game.levels.backgrounds;

import biuoop.DrawSurface;
import game.levels.gameFunction.environment.Sprite;

import java.awt.Color;

/**
 * The background of the level Bullseye of type Sprite.
 */
public class BullseyeBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        int widthMid = d.getWidth() / 2;

        // background
        Color background = new Color(50, 100, 0);
        d.setColor(background);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // target
        d.setColor(Color.RED);
        d.fillCircle(widthMid, d.getHeight() / 3, 100);

        d.setColor(Color.WHITE);
        d.fillCircle(widthMid, d.getHeight() / 3, 75);

        d.setColor(Color.RED);
        d.fillCircle(widthMid, d.getHeight() / 3, 50);

        d.setColor(Color.WHITE);
        d.fillCircle(widthMid, d.getHeight() / 3, 25);

        // bow
        d.setColor(new Color(150, 75, 0));
        d.drawCircle(widthMid, d.getHeight() * 7 / 8, 100);

        d.setColor(background);
        d.fillRectangle(d.getWidth() / 4, (int) (d.getHeight() * 6.5 / 8), d.getWidth() * 3 / 4, d.getHeight() / 3);

        // bowstring
        d.setColor(Color.GRAY);
        d.drawLine(widthMid, d.getHeight() * 7 / 8, widthMid - 92, d.getHeight() * 6 / 8 + 37);
        d.drawLine(widthMid, d.getHeight() * 7 / 8, widthMid + 92, d.getHeight() * 6 / 8 + 37);

        // arrow
        d.setColor(Color.BLACK);
        d.drawLine(widthMid, d.getHeight() * 7 / 8, widthMid, d.getHeight() * 5 / 8);
        d.drawLine(widthMid, d.getHeight() * 5 / 8, widthMid + 10, d.getHeight() * 5 / 8 + 10);
        d.drawLine(widthMid, d.getHeight() * 5 / 8, widthMid - 10, d.getHeight() * 5 / 8 + 10);

        d.drawLine(widthMid, d.getHeight() * 7 / 8, widthMid + 10, d.getHeight() * 7 / 8 + 10);
        d.drawLine(widthMid, d.getHeight() * 7 / 8, widthMid - 10, d.getHeight() * 7 / 8 + 10);
    }
}
