// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.environment.SpriteCollection;
import game.gameFunction.GameLevel;

import java.awt.Color;

/**
 * The CountdownAnimation will show a countdown to 1
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private final long sleepDuration;
    private int countFrom;
    private boolean firstIteration = true;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds the countdown will actually take
     * @param countFrom    the count from
     * @param gameScreen   the game screen that will continue when the counting ends
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom + 1;     // i believe there is a bug drawing the surface, the number always 1 step backwards // TODO check it
        this.gameScreen = gameScreen;
        this.sleepDuration = (long) ((numOfSeconds / countFrom) * 1000);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);

        String text = "Game starts in: " + (this.countFrom - 1);
        int textSize = 30;
        int xText = (GameLevel.WIDTH - textSize * text.length() / 2) / 2 + GameLevel.BORDER_THICKNESS;
        int yText = (GameLevel.HEIGHT + textSize) / 2;

        d.drawText(xText, yText, text, textSize);

        this.countFrom--;

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