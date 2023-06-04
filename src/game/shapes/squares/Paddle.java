// 206573289 Omri Levi


package game.shapes.squares;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.environment.Collidable;
import game.environment.Sprite;
import game.gameFunction.Game;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.lines.Line;
import java.awt.Color;
import java.util.List;

/**
 * The Paddle class represents a rectangular paddle that the player controls using the keyboard
 * <p>
 * is used in the game as a collidable object and a sprite.
 * </p>
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle rectangle;                // shape of the paddle
    private final KeyboardSensor keyboard;      // the keyboard sensor
    private final Color color = Color.YELLOW;   // the color of the paddle
    private final int stepSize = 7;             // how many pixels the paddle moves in each passage of time.

    /**
     * Constructor for the Paddle class.
     *
     * @param rectangle the rectangle representing the paddle
     * @param keyboard  the keyboard sensor used to move the paddle
     */
    public Paddle(Rectangle rectangle, KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.keyboard = keyboard;
    }

    /**
     * Implementation of the timePassed method from the Sprite interface.
     * <p>
     * Checks for keyboard input and moves the paddle accordingly.
     * </p>
     */
    @Override
    public void timePassed() {
        boolean moveLeft1 = this.keyboard.isPressed("a") || this.keyboard.isPressed("A");
        boolean moveLeft2 = this.keyboard.isPressed(KeyboardSensor.LEFT_KEY);
        boolean moveRight1 = this.keyboard.isPressed("d") || this.keyboard.isPressed("D");
        boolean moveRight2 = this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY);

        if (moveLeft1) {
            System.out.println("the 'a' key is pressed");
            moveLeft();
        } else if (moveLeft2) {
            System.out.println("the 'left arrow' key is pressed");
            moveLeft();
        } else if (moveRight1) {
            System.out.println("the 'd' key is pressed");
            moveRight();
        } else if (moveRight2) {
            System.out.println("the 'right arrow' key is pressed");
            moveRight();
        }
    }

    /**
     * Moves the paddle to the left by the step size.
     * <p>
     * The paddle's movement is restricted by the game's borders.
     * </p>
     */
    public void moveLeft() {
        double upperLeftX = this.rectangle.getX();
        double upperLeftY = this.rectangle.getY();
        double xAfterMoving = upperLeftX - stepSize;
        double leftBorder = Game.BORDER_THICKNESS + Game.OFFSET;

        upperLeftX = Math.max(leftBorder, xAfterMoving);    // makes sure the paddle does not exit the boundaries

        Point newUpperLeft = new Point(upperLeftX, upperLeftY);         // the upper left point of the new paddle
        double width = this.rectangle.getWidth();                       // width of the paddle
        double height = this.rectangle.getHeight();                     // height of the paddle

        this.rectangle = new Rectangle(newUpperLeft, width, height);
    }

    /**
     * Moves the paddle to the right by the step size.
     * <p>
     * The paddle's movement is restricted by the game's borders.
     * </p>
     */
    public void moveRight() {
        double upperLeftX = this.rectangle.getX();
        double upperLeftY = this.rectangle.getY();
        double xAfterMoving = upperLeftX + stepSize;

        // offset the right border using the width of the paddle
        double rightBorder = Game.WIDTH - (Game.BORDER_THICKNESS + this.rectangle.getWidth() + Game.OFFSET);

        upperLeftX = Math.min(xAfterMoving, rightBorder);   // makes sure the paddle does not exit the boundaries

        Point newUpperLeft = new Point(upperLeftX, upperLeftY);         // the upper left point of the new paddle
        double width = this.rectangle.getWidth();                       // width of the paddle
        double height = this.rectangle.getHeight();                     // height of the paddle

        this.rectangle = new Rectangle(newUpperLeft, width, height);
    }

    /**
     * Draws the paddle on the given draw surface.
     * <p>
     * Implementation of the drawOn method from the Sprite interface.
     * </p>
     *
     * @param surface the draw surface to draw the paddle on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rectangle.getX();
        int y = (int) this.rectangle.getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Calculates and returns the new velocity of the ball after hitting the paddle.
     *
     * @param collisionPoint the point where the ball hit the paddle.
     * @param currentVelocity the current velocity of the ball before hitting the paddle.
     * @return the new velocity of the ball after hitting the paddle.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // if the collision point is not on the top of the paddle
        if (!collisionPoint.isOnLine(this.rectangle.getTopEdge())) {
            if (collisionPoint.isOnLine(this.rectangle.getLeftEdge())) {
                dx = -Math.abs(dx);     // collision with the left edge should always result in -dx
            }

            if (collisionPoint.isOnLine(this.rectangle.getRightEdge())) {
                dx = Math.abs(dx);      // collision with the right edge should always result in +dx
            }

            if (collisionPoint.isOnLine(this.rectangle.getBottomEdge())) {
                dy = -dy;
            }

            return new Velocity(dx, dy);
        }

        List<Line> regions = this.rectangle.getTopEdge().divideLinesInto(5);    // divide into 5 regions
        int regionNum = 1;                                                      // current number of regions

        for (Line region : regions) {
            if (collisionPoint.isOnLine(region)) {
                break;
            }
            regionNum++;
        }

        double speed = Math.sqrt((dx * dx) + (dy * dy));    // distance equation to calculate speed vector
        double angle = 0;

        switch (regionNum) {        // set the correct exit angle
            case 1:
                angle = -60;
                break;
            case 2:
                angle = -30;
                break;
            case 3:
                return new Velocity(dx, -dy);               // retain the x velocity and invert to y velocity
            case 4:
                angle = 30;
                break;
            case 5:
                angle = 60;
                break;
            default:
                break;
        }

        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Adds the paddle to the given game, as a sprite and as a collidable object.
     *
     * @param g the game to add the paddle to.
     */
    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
