// 206573289 Omri Levi


package game.levels.gameFunction.indicatorsAndCounters;

import biuoop.DrawSurface;
import game.levels.gameFunction.environment.Sprite;
import game.levels.gameFunction.GameLevel;

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
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);

        int textSize = 15;
        int xText = GameLevel.BORDER_THICKNESS + GameLevel.OFFSET * 2;
        int yText = (GameLevel.BORDER_THICKNESS + textSize) / 2;

        d.drawText(xText, yText, this.levelName, textSize);
    }
}