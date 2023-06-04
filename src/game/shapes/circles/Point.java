// 206573289 Omri Levi


package game.shapes.circles;


import game.miscellaneous.DoubleCompare;
import game.shapes.lines.Line;
import game.shapes.squares.Rectangle;

/**
 * The Point class represents a point in 2D space, defined by its x and y coordinates.
 * It provides methods for calculating the distance between two points and checking equality between points.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a new Point object with the given x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and the other point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();

        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2))); // distance equation
    }

    /**
     * Returns the x coordinate of this point.
     *
     * @return the x coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of this point.
     *
     * @return the y coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Compares this point to another point for equality.
     * Two points are considered equal if their x and y coordinates are equal
     * or the difference between them is negligible according to the DoubleCompare class.
     *
     * @param other the other point to compare with
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean xIsEqual = DoubleCompare.equals(this.getX(), other.getX());
        boolean yIsEqual = DoubleCompare.equals(this.getY(), other.getY());

        return xIsEqual && yIsEqual;
    }

    /**
     * Finds if a point is between two other points.
     *
     * @param start  the starting point
     * @param end    the ending point
     * @return true if the middle point is between the starting and ending points, false otherwise.
     */
    public boolean isBetween(Point start, Point end) {
        boolean betweenX = DoubleCompare.between(start.getX(), this.getX(), end.getX());
        boolean betweenY = DoubleCompare.between(start.getY(), this.getY(), end.getY());

        return betweenX && betweenY;
    }

    /**
     * Determines if a point is inside a given Rectangle object.
     *
     * @param rectangle the Rectangle object to check if the point is inside
     * @return true if the line is completely inside the rectangle, false otherwise
     */
    public boolean isInsideRect(Rectangle rectangle) {
        Point lowerRight = rectangle.getLowerRight();       // the lower right point of the rectangle

        // calculate if the center point is inside the collidable
        boolean xIsBetween = DoubleCompare.subtract(lowerRight.getX(), this.x) >= 0
                && DoubleCompare.subtract(this.x, rectangle.getX()) >= 0;

        boolean yIsBetween = DoubleCompare.subtract(lowerRight.getY(), this.y) >= 0
                && DoubleCompare.subtract(this.y, rectangle.getY()) >= 0;

        // Return true if both the start and end points are inside the rectangle, false otherwise
        return xIsBetween && yIsBetween;
    }

    /**
     * Determines if a point is on one of the corners of a given Rectangle object.
     *
     * @param rect the rectangle
     * @return true if the point is on one of the corners, false otherwise
     */
    public boolean isOnCorner(Rectangle rect) {
        Line[] edges = {rect.getTopEdge(), rect.getBottomEdge(), rect.getLeftEdge(), rect.getRightEdge()};

        for (Line edge : edges) {       // check if the collision point is at the vertices of the edges
            if (this.equals(edge.start()) || this.equals(edge.end())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds if a point is on a given parallel to axes line.
     *
     * @param line the point
     * @return the boolean
     */
    public boolean isOnLine(Line line) {
        return this.isBetween(line.start(), line.end());
    }
}