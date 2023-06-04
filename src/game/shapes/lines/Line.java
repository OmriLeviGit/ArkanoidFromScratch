// 206573289 Omri Levi


package game.shapes.lines;

import game.miscellaneous.DoubleCompare;
import game.shapes.circles.Point;
import game.shapes.squares.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The Line class represents a line defined by two endpoints - start and end.
 * <p>
 * The class provides methods to calculate the length of the line, get the middle point, get the starting and
 * ending points, check whether the line intersects with another line and find the intersection point.
 * </p>
 */
public class Line {
    private final Point invalidPoint = new Point(-1, -1);   // signifies an infinite amount of intersection points
    private Point start;
    private Point end;
    private final double slope;
    private final double intercept;

    /**
     * Constructs a new Line object using two points.
     *
     * @param start the starting point
     * @param end   the ending point
     */
    public Line(Point start, Point end) {
        this(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Constructs a new Line object using two x and y coordinates.
     *
     * @param x1 the x of the starting point
     * @param y1 the y of the starting point
     * @param x2 the x of the ending point
     * @param y2 the y of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.slope = (y2 - y1) / (x2 - x1);
        this.intercept = this.start.getY() - this.slope * this.start.getX();
    }

    /**
     * Calculates the length of the line.
     *
     * @return the length
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point
     */
    public Point middle() {
        double xCoordinate = (this.start.getX() + this.end.getX()) / 2; // the average of the two x coordinates
        double yCoordinate = (this.start.getY() + this.end.getY()) / 2; // the average of the two y coordinates

        return new Point(xCoordinate, yCoordinate);
    }

    /**
     * Returns the starting point of the line.
     *
     * @return the starting point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the ending point of the line.
     *
     * @return the ending point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks whether this line intersects with another line.
     *
     * @param other the other line
     * @return true if this line intersects with the other line, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return this.findIntersection(other) != null;
    }

    /**
     * Finds the intersection point between this line and another line.
     *
     * @param other the other line
     * @return the intersection point, a null point if the lines do not intersect,
     * or an error point if an infinite number of intersection points exists
     */
    public Point intersectionWith(Line other) {
        Point point = findIntersection(other);

        // if there is an infinite amount of intersection points, or no intersection at all
        if (point == null || point.equals(invalidPoint)) {
            return null;
        }

        return point;
    }

    /**
     * Helper method to find the intersection point between this line and another line.
     *
     * @param other the other line
     * @return the intersection point, or null if the lines do not intersect or an error point if an
     * infinite number of intersection points exists
     */
    private Point findIntersection(Line other) {

        // set the starting point as the one with the lower (x,y) values
        Line temp1 = this.setOrientation();
        Line temp2 = other.setOrientation();

        // set temp1 as the one with the lower start values
        setOrientation(temp1, temp2);

        // new lines are created so other fields will be automatically calculated according to the new points
        Line line1 = new Line(temp1.start, temp1.end);
        Line line2 = new Line(temp2.start, temp2.end);

        // assign line1 fields to variables
        Point start1 = line1.start();
        Point end1 = line1.end();
        double slope1 = line1.slope;
        double intercept1 = line1.intercept;

        // assign line2 fields to variables
        Point start2 = line2.start();
        Point end2 = line2.end();
        double slope2 = line2.slope;
        double intercept2 = line2.intercept;

        // calculate the difference between the slopes and the intercepts
        double deltaSlope = DoubleCompare.subtract(slope1, slope2);
        double deltaIntercept = DoubleCompare.subtract(intercept1, intercept2);

        // initialize current x and y values;
        double x = deltaIntercept / -deltaSlope;
        double y = x * slope1 + intercept1;

        // if the slope of the lines is equal
        if (deltaSlope == 0) {
            if (line1.equals(line2)) {
                return invalidPoint;      // infinite amount of intersection points
            }

            // check if there is a cross-over between the lines
            if (deltaIntercept == 0 && start2.isBetween(start1, end1)) {
                if (start2.equals(end1)) {
                    return start2;
                }

                return invalidPoint;      // infinite amount of intersection points
            }

            return null;    // if the edges of the lines don't touch
        }

        // if both of the lines are parallel to the y-axis
        if (Double.isInfinite(slope1) && Double.isInfinite(slope2)) {
            if (!DoubleCompare.equals(start1.getX(), start2.getX())) {
                return null;
            }

            // check if there is a cross-over between the lines
            if (DoubleCompare.between(start1.getY(), start2.getY(), end1.getY())) {
                if (start2.equals(end1)) {
                    return start2;
                }

                return invalidPoint;      // infinite amount of intersection points
            }

            return null;    // if the edges of the lines don't touch
        }

        if (Double.isInfinite(slope1)) {    // if only line1 is parallel to the y-axis, set new (x, y) values
            x = line1.start().getX();
            y = x * slope2 + intercept2;
        }

        if (Double.isInfinite(slope2)) {    // if only line2 is parallel to the y-axis, set new (x, y) values
            x = line2.start().getX();
            y = x * slope1 + intercept1;
        }

        Point intersection = new Point(x, y);

        // check if found point is intersecting
        if (intersection.isBetween(start1, end1) && intersection.isBetween(start2, end2)) {
            return intersection;
        }

        return null;
    }

    /**
     * Sets the orientation of two given lines based on their starting points.
     * <p>
     * Sets line1 to have the lower starting point by the x value. If the x value is the same between
     * the two lines, the method sets line1 to have the lower starting point by the y value instead.
     * </p>
     *
     * @param line1 the first line whose orientation needs to be set
     * @param line2 the second line whose orientation needs to be set
     */
    private void setOrientation(Line line1, Line line2) {
        // if (x1 > x2 || (x1 == x2 && y1 > y2)), swap between line1 and line2
        if (DoubleCompare.subtract(line1.start.getX(), line2.start.getX()) > 0
                || (DoubleCompare.equals(line1.start.getX(), line2.start.getX())
                && DoubleCompare.subtract(line1.start.getY(), line2.start.getY()) > 0)) {

            // swap the start point
            Point temp = line1.start;
            line1.start = line2.start;
            line2.start = temp;

            // swap the end point
            temp = line1.end;
            line1.end = line2.end;
            line2.end = temp;
        }
    }

    /**
     * Sets the orientation of two given lines based on their starting points.
     * <p>
     * This method sets the starting point of a line to have the lower x value.
     * If the x value is the same between the two points, then it sets the line based on their y values instead.
     * </p>
     * @return the line with the adjusted orientation
     */
    private Line setOrientation() {
        // assign line fields to variables
        Point start = this.start();
        Point end = this.end();

        // if (x1 > x2 || (x1 == x2 && y1 > y2)), swap the start and end points of line1
        if (DoubleCompare.subtract(this.start().getX(), end.getX()) > 0
                || (DoubleCompare.equals(this.start().getX(), end.getX())
                && DoubleCompare.subtract(this.start().getY(), end.getY()) > 0)) {
            Point tempPoint = this.start();
            start = end;
            end = tempPoint;
        }

        return new Line(start, end);
    }

    /**
     * Checks if 2 lines are equal or the difference between them is negligible.
     *
     * @param other the other line.
     * @return the boolean
     */
    public boolean equals(Line other) {
        return (start.equals(other.start()) && end.equals(other.end()))
                || (start.equals(other.end()) && end.equals(other.start()));
    }

    /**
     * Finds the closest intersection point to the start of the line with a given rectangle.
     *
     * @param rect the rectangle to check for intersections with
     * @return the closest intersection point to the start of the line, or null if there are no intersection points
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interPoints = rect.intersectionPoints(this);        // a list of intersection points

        if (interPoints.size() == 0) {
            return null;
        }

        Point closestPoint = interPoints.get(0);                            // set the first point as the closest
        double smallestDistance = this.start.distance(closestPoint);        // distance from the first point

        for (int i = 1; i < interPoints.size(); i++) {
            double tempDistance = this.start.distance(interPoints.get(i));

            // if there is a closer point to the start of the line, set the new closest point and smallest distance
            if (DoubleCompare.subtract(tempDistance, smallestDistance) < 0) {
                closestPoint = interPoints.get(i);
                smallestDistance = tempDistance;
            }
        }

        return closestPoint;
    }

    /**
     * Divides the line into a given number of equally sized smaller lines, and returns them in an ArrayList.
     *
     * @param num the number of smaller lines to divide the line into
     * @return an ArrayList of the smaller lines
     * @throws IllegalArgumentException if num is zero or negative
     */
    public List<Line> divideLinesInto(int num) {

        if (num == 0) {     // return null if the num is zero
            return null;
        }

        double regionLength = this.length() / num;      // Calculate the length of each region

        List<Line> lines = new ArrayList<>();      // Create a new ArrayList to store the smaller lines

        // Initialize the x and y coordinates of the start point of each smaller line
        double previousX = this.start.getX();
        double previousY = this.start.getY();

        for (int i = 0; i < num; i++) {

            // Calculate the x and y coordinates of the end point of the current smaller line
            double currentX = previousX + regionLength * Math.cos(this.slope);
            double currentY = previousY + regionLength * Math.sin(this.slope);

            // Create a new Line object representing the current smaller line
            Line newLine = new Line(previousX, previousY, currentX, currentY);

            // Add the new Line object to the ArrayList of smaller lines
            lines.add(newLine);

            // Update the x and y coordinates of the start point of the next smaller line
            previousX = currentX;
            previousY = currentY;
        }

        // Return the ArrayList of smaller lines
        return lines;
    }
}