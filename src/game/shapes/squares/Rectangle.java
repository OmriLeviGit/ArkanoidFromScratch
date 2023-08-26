// 206573289 Omri Levi


package game.shapes.squares;

import biuoop.DrawSurface;
import game.levels.gameFunction.environment.Sprite;
import game.shapes.circles.Point;
import game.shapes.lines.Line;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class represents a rectangle, implementing the Sprite interface.
 * <p>
 * It contains information about the rectangle's width, height, color, and upper left point.
 * The class also includes methods to get the rectangle's intersection points with a line, as well as its edges.
 * </p>
 */
public class Rectangle implements Sprite {
    private final double width;
    private final double height;
    private Color color;
    private final Point upperLeft;

    /**
     * Creates a new Rectangle with the given width and height, and an upper left point at (0, 0) as default.
     *
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double width, double height) {
        this(new Point(0, 0), width, height);
    }

    /**
     * Creates a new Rectangle with the given width, height, and upper left point coordinates.
     *
     * @param x      the x coordinate of the upper left point of the rectangle.
     * @param y      the y coordinate of the upper left point of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * Creates a new Rectangle with the given width, height, and upper left point.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points between the Rectangle and a given Line.
     *
     * @param line the line to check for intersection with the rectangle.
     * @return a list of intersection points, or an empty list if no intersections exist.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> interArray = new ArrayList<>();     // create an array for the intersection points
        Line[] rectangleEdges = {this.getLeftEdge(), this.getTopEdge(), this.getRightEdge(), this.getBottomEdge()};

        // get the intersection point with the edges of the rectangle, and insert to a list
        for (Line rectangleEdge : rectangleEdges) {
            Point interPoint = rectangleEdge.intersectionWith(line);

            if (interPoint == null) {       // skip if there is no intersection with that edge
                continue;
            }

            // skip point if it was just inserted (occurs when line intersects with a corner of the rectangle)
            if (!interArray.isEmpty() && interPoint.equals(interArray.get(interArray.size() - 1))) {
                continue;
            }

            interArray.add(interPoint);     // add intersection point to the array
        }

        return interArray;
    }

    /**
     * Returns the x-coordinate of the upper left point of the rectangle.
     *
     * @return the x-coordinate of the upper left point of the rectangle
     */
    public double getX() {
        return this.upperLeft.getX();
    }

    /**
     * Returns the y-coordinate of the upper left point of the rectangle.
     *
     * @return the y-coordinate of the upper left point of the rectangle
     */
    public double getY() {
        return this.upperLeft.getY();
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.getX() + this.width, this.getY());
    }

    /**
     * Returns the lower left point of the rectangle.
     *
     * @return the lower left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.getX(), this.getY() + this.height);
    }

    /**
     * Returns the lower right point of the rectangle.
     *
     * @return the lower right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(this.getX() + this.width, this.getY() + this.height);
    }

    /**
     * Returns the top edge of the rectangle.
     *
     * @return the top edge of the rectangle
     */
    public Line getTopEdge() {
        return new Line(this.upperLeft, this.getUpperRight());
    }

    /**
     * Returns the bottom edge of the rectangle.
     *
     * @return the bottom edge of the rectangle
     */
    public Line getBottomEdge() {
        return new Line(getLowerLeft(), getLowerRight());
    }

    /**
     * Returns the left edge of the rectangle.
     *
     * @return the left edge of the rectangle
     */
    public Line getLeftEdge() {
        return new Line(this.upperLeft, getLowerLeft());
    }

    /**
     * Returns the right edge of the rectangle.
     *
     * @return the right edge of the rectangle
     */
    public Line getRightEdge() {
        return new Line(getUpperRight(), getLowerRight());
    }

    /**
     * Draws the rectangle on a given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the rectangle
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) this.upperLeft.getX();
        int y = (int) this.upperLeft.getY();
        int width = (int) this.width;
        int height = (int) this.height;

        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the new color of the rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
