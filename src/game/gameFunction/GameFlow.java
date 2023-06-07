// 206573289 Omri Levi


package game.gameFunction;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.KeyPressStoppableAnimation;
import game.animation.LosingScreen;
import game.animation.WinningScreen;
import game.gameFunction.indicatorsAndCounters.Counter;
import game.levels.LevelInformation;

import java.util.List;

/**
 * Responsible for creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboard;
    private final Counter blocksRemained = new Counter();
    private final Counter score = new Counter();

    /**
     * Instantiates a new class.
     *
     * @param animationRunner the animation runner
     * @param keyboard        the keyboard
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboard) {
        this.animationRunner = animationRunner;
        this.keyboard = keyboard;
    }

    /**
     * Run initializes and runs each level. when the game is over, shows the result screen.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            Counter ballsRemained = new Counter();          // counter for balls remained
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboard, this.animationRunner, this.score, ballsRemained, this.blocksRemained);

            level.initialize();
            level.run();

            if (ballsRemained.getValue() == 0) {
                this.animationRunner.run(
                        new KeyPressStoppableAnimation(
                                keyboard, keyboard.SPACE_KEY, new LosingScreen(score)));    // run losing animation
                return;
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(
                keyboard, keyboard.SPACE_KEY, new WinningScreen(score)));   // run winning animation
    }
}