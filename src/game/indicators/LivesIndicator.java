// 206573289 Omri Levi


package game.indicators;

import biuoop.DrawSurface;
import game.environment.Sprite;
import game.gameFunction.GameLevel;

import java.awt.Color;

public class LivesIndicator implements Sprite {
    private final int numOfLives;

    /**
     * Instantiates a new "lives" indicator.
     *
     * @param numOfLives the number of lives
     */
    public LivesIndicator(int numOfLives) {
        this.numOfLives = numOfLives;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);

        String text = "Lives: " + this.numOfLives;
        int textSize = 15;
        int xText = GameLevel.WIDTH - (GameLevel.BORDER_THICKNESS + (textSize * text.length()) / 2);
        int yText = (GameLevel.BORDER_THICKNESS + textSize) / 2;

        surface.drawText(xText, yText, text, textSize);
    }
}
