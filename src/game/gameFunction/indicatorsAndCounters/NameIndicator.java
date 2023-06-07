// 206573289 Omri Levi


package game.gameFunction.indicatorsAndCounters;

import biuoop.DrawSurface;
import game.gameFunction.environment.Sprite;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class NameIndicator implements Sprite {
    private final String levelName;

    /**
     * Instantiates a new level name indicator.
     *
     * @param levelName the name of the level
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);

        int textSize = 15;
        int xText = GameLevel.BORDER_THICKNESS + GameLevel.OFFSET * 2;
        int yText = (GameLevel.BORDER_THICKNESS + textSize) / 2;

        surface.drawText(xText, yText, this.levelName, textSize);
    }
}