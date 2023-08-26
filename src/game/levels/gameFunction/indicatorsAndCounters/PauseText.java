// 206573289 Omri Levi


package game.levels.gameFunction.indicatorsAndCounters;

import biuoop.DrawSurface;
import game.levels.gameFunction.environment.Sprite;
import game.levels.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class PauseText implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String text = "Press 'P' to pause";

        int textSize = 15;
        int xText = GameLevel.WIDTH - (textSize * 10);
        int yText = (GameLevel.BORDER_THICKNESS + textSize) / 2;

        d.drawText(xText, yText, text, textSize);
    }
}
