// 206573289 Omri Levi

package game.levels.gameFunction.environment;

import game.shapes.circles.Point;

/**
 * A class that stores information about a collision between two objects.
 */
public class CollisionInfo {

    /**
     * The point at which the collision occurs.
     */
    private final Point collisionPoint;

    /**
     * The collidable object involved in the collision.
     */
    private final Collidable collisionObject;

    /**
     * Constructs a new CollisionInfo object.
     *
     * @param collisionPoint  the point at which the collision occurs
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }
}