// 206573289 Omri Levi


package game.environment;

import game.miscellaneous.DoubleCompare;
import game.shapes.circles.Point;
import game.shapes.lines.Line;
import game.shapes.squares.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class is responsible for managing and detecting collisions between objects in the game.
 */
public class GameEnvironment {
    /**
     * List of all collidable objects in the game environment.
     */
    private final List<Collidable> collidables = new ArrayList<>();

    /**
     * Adds a collidable object to the list of collidables in the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the list of collidables in the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collision point and object for a given trajectory of a ball.
     *
     * @param trajectory the trajectory of the ball in the game
     * @return the CollisionInfo object containing information about the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closedCollision = null;               // closed collision point
        Collidable collisionObject;                 // closed collision object

        ArrayList<CollisionInfo> infoList = new ArrayList<>();        // holds collision info of each valid object

        for (Collidable collidable : collidables) {
            Rectangle collisionRect = collidable.getCollisionRectangle();                           // collision Rect
            Point currentCollision = trajectory.closestIntersectionToStartOfLine(collisionRect);    // current point
            Point start = trajectory.start();                                                       // start point

            // if the paddle has moved onto the ball
            if (start.isInsideRect(collisionRect)) {
                double collisionEdgeX;       // the x value of the edge that the ball originally collided with
                double rightEdgeX = collisionRect.getRightEdge().start().getX();

                double distanceToLeftEdge = DoubleCompare.difference(collisionRect.getX(), start.getX());
                double distanceToRightEdge = DoubleCompare.difference(rightEdgeX, start.getX());

                if (distanceToRightEdge > distanceToLeftEdge) {
                    collisionEdgeX = collisionRect.getX();          // set the x value as the left edge
                } else {
                    collisionEdgeX = rightEdgeX;                    // set the x value as the right edge
                }

                // set the collision point as the point the ball had entered the paddle
                closedCollision = new Point(collisionEdgeX, start.getY());
                collisionObject = collidable;

                return new CollisionInfo(closedCollision, collisionObject);
            }

            if (currentCollision == null) {     // if the found point is not a collision point
                continue;
            }

            // if there is no collision point, or there is one closer than the first
            if (closedCollision == null
                    || (currentCollision.distance(start) <= closedCollision.distance(start))) {

                // set new collision point and object
                closedCollision = currentCollision;
                collisionObject = collidable;
                infoList.add(0, new CollisionInfo(closedCollision, collisionObject));   // add in ascending order
            }
        }

        if (infoList.isEmpty()) {
            return null;
        }

        CollisionInfo collisionInfo = infoList.get(0);      // initialize a default info

        for (CollisionInfo info : infoList) {   // return the first object that is hit at the corner
            if (closedCollision.isOnCorner(info.getCollisionObject().getCollisionRectangle())) {
                return info;
            }
        }

        return collisionInfo;
    }
}
