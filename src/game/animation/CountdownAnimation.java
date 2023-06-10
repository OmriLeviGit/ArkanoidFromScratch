// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.gameFunction.environment.SpriteCollection;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The CountdownAnimation will show a countdown to 1.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private final long sleepDuration;
    private int countFrom;
    private boolean firstIteration = true;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param totalDuration the num of seconds the countdown will actually take
     * @param countFrom    the count from
     * @param gameScreen   the game screen that will continue when the counting ends
     */
    public CountdownAnimation(double totalDuration, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom + 1;
        this.gameScreen = gameScreen;
        this.sleepDuration = (long) ((totalDuration / countFrom) * 1000);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.countFrom--;

        String text = "Game starts in: " + (this.countFrom);
        int textSize = 30;
        int xText = (d.getWidth() - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (d.getHeight() + textSize) / 2;

        d.setColor(Color.WHITE);
        d.drawText(xText, yText, text, textSize);

        if (!firstIteration) {
            new Sleeper().sleepFor(sleepDuration);
        }

        this.firstIteration = false;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom <= 0;
    }
}