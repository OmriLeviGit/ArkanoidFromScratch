// 206573289 Omri Levi


package game.environment;

import game.gameFunction.GameLevel;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.squares.Rectangle;

/**
 * The Collidable interface represents an object that can collide with other objects in the game.
 */
public interface Collidable {
    /**
     * Returns the collision shape of the object.
     *
     * @return the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a hitter collided with it at a specified collision point, with the specified velocity.
     *
     * @param hitter          the hitting ball
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the velocity of the object when the collision occurred
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Adds the collidable to the specified game by calling the game's addToGame method.
     *
     * @param g the game to add the collidable to
     */
    default void addToGame(GameLevel g) {
        g.addCollidable(this);
    }
}
