// 206573289 Omri Levi


package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation is used to wrap animations that are stopped by a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean stop = false;

    /**
     * Instantiates a new stoppable animation.
     *
     * @param keyboard  the keyboard
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        if (this.keyboard.isPressed(this.key)) {
            this.stop = !this.stop;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}