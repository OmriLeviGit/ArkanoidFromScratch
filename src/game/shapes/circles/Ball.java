// 206573289 Omri Levi


package game.shapes.circles;

import biuoop.DrawSurface;
import game.gameFunction.environment.Collidable;
import game.gameFunction.environment.CollisionInfo;
import game.gameFunction.environment.GameEnvironment;
import game.gameFunction.environment.Sprite;
import game.gameFunction.GameLevel;
import game.shapes.lines.Line;
import java.awt.Color;


/**
 * The Ball class represents a ball object in a game, with a center point, radius, color, and velocity.
 * <p>
 * It implements the Sprite interface, and can be drawn and moved on a surface.
 * </p>
 */
public class Ball implements Sprite {
    private GameEnvironment gameEnvironment;
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity = new Velocity(0, 0);

    /**
     * Constructs a new Ball with given center point, radius and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        this(center.getX(), center.getY(), radius, color);
    }

    /**
     * Constructs a new Ball with given center coordinates, radius and color.
     *
     * @param x      the x coordinate of the center point of the ball
     * @param y      the y coordinate of the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        this.center = new Point((int) x, (int) y);  // the assignment requires the center coordinates to be of type int
        this.radius = radius;
        this.color = color;
    }

    /**
     * Returns the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Returns the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Draws the ball on the given surface by filling a circle shape with the ball's color and location.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Notifies the ball that time has passed by calling the moveOneStep method.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param v the given velocity
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * Sets the velocity of the ball to the given velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the game environment that the ball belongs to.
     *
     * @param environment The game environment to set.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     * <p>
     * Move the ball one step according to its current velocity.
     * If the ball does not collide with any object in the game environment, it moves
     * to the point it would have reached after one step.
     * If the ball collides with an object, it moves slightly before the collision point,
     * and its velocity is updated based on the hit calculation of the collided object.
     * </p>
     */
    public void moveOneStep() {
        Point start = this.center;   // point after applying current velocity
        Point futurePoint = this.getVelocity().applyToPoint(this.center);   // point after applying current velocity
        Line trajectory = new Line(this.center, futurePoint);               // the trajectory the ball will move at

        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);

        if (collisionInfo == null) {        // if no collision occurred
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }

        Point collisionPoint = collisionInfo.getCollisionPoint();               // point of collision
        Collidable collisionObject = collisionInfo.getCollisionObject();        // object of collision

        // move the ball just slightly outside the object
        if (this.center.isInsideRect(collisionObject.getCollisionRectangle())) {
            double newX;

            if (start.getX() < collisionPoint.getX()) {
                newX = collisionPoint.getX() + 1;
            } else {
                newX = collisionPoint.getX() - 1;
            }

            this.center = new Point(newX, collisionPoint.getY());   // move the ball before the collision point

        } else {

            // calculate the distance just before the collision
            double trajectoryLength = trajectory.length();
            double distanceToCollision = this.center.distance(collisionPoint);
            double beforeCollision = (distanceToCollision / trajectoryLength) * 0.99;

            // use the distance to the point before collision to calculate a new center point
            double newX = this.center.getX() + velocity.getDx() * beforeCollision;     // new x before collision
            double newY = this.center.getY() + velocity.getDy() * beforeCollision;     // new y before collision

            this.center = new Point(newX, newY);                     // move the ball before the collision point
        }

        // calculate new velocity and move the ball
        Velocity hitVelocity = collisionObject.hit(this, collisionPoint, this.getVelocity());
        this.setVelocity(hitVelocity);

        moveOneStep();  // call recursively to check for a new collision
    }

    /**
     * Remove the ball from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}