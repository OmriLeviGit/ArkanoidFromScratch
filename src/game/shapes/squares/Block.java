// 206573289 Omri Levi


package game.shapes.squares;

import biuoop.DrawSurface;
import game.gameFunction.GameLevel;
import game.gameFunction.environment.Collidable;
import game.gameFunction.environment.Sprite;
import game.gameFunction.listeners.HitListener;
import game.gameFunction.listeners.HitNotifier;
import game.shapes.circles.Ball;
import game.shapes.circles.Point;
import game.shapes.circles.Velocity;
import game.shapes.lines.Line;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Block class represents a block that can collide with other objects and can be drawn on a surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    /**
     * The rectangle representing the block.
     */
    private final Rectangle rectangle;

    private final List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Creates a new Block object from an upper left point and the width and height of the block.
     *
     * @param upperLeft the upper left point of the block.
     * @param width     the width of the block.
     * @param height    the height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this(new Rectangle(upperLeft, width, height));
    }

    /**
     * Creates a new Block object from a rectangle.
     *
     * @param rectangle the rectangle representing the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Returns the rectangle representing the block.
     *
     * @return the rectangle representing the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Determines the new velocity of an object after collision with the block.
     * Changes the velocity according to the angle of impact with the block edges.
     *
     * @param collisionPoint the point of collision with the block.
     * @param currentVelocity the velocity of the object before collision.
     * @return the new velocity of the object after collision with the block.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        Line topEdge = this.rectangle.getTopEdge();
        Line bottomEdge = this.rectangle.getBottomEdge();
        Line leftEdge = this.rectangle.getLeftEdge();
        Line rightEdge = this.rectangle.getRightEdge();

        if (collisionPoint.isOnLine(topEdge) || collisionPoint.isOnLine(bottomEdge)) {
            dy = -dy;
        }

        if (collisionPoint.isOnLine(leftEdge) || collisionPoint.isOnLine(rightEdge)) {
            dx = -dx;
        }

        this.notifyHit(hitter);

        return new Velocity(dx, dy);
    }

    /**
     * Adds the block to the game environment.
     *
     * @param g the game environment to add the block to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Draws the block on the given surface.
     *
     * @param surface the surface to draw the block on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rectangle.getX();
        int y = (int) this.rectangle.getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        surface.setColor(this.rectangle.getColor());
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Remove the block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
