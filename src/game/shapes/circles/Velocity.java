// 206573289 Omri Levi


package game.shapes.circles;


/**
 * The Velocity class represents a two-dimensional velocity in terms of its dx (change in x) and dy (change in y)
 * components. It also provides methods for generating a new Velocity instance from an angle and speed and applying the
 * velocity to a given Point to obtain a new Point.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a new Velocity instance with the given dx and dy values.
     *
     * @param dx the change in x
     * @param dy the change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns the dx value of this Velocity.
     *
     * @return the dx value of this Velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the dy value of this Velocity.
     *
     * @return the dy value of this Velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns a new Velocity instance with dx and dy values calculated from the given angle and speed.
     *
     * @param angle the angle in degrees
     * @param speed the speed of the Velocity
     * @return a new Velocity instance with dx and dy values calculated from the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed; // calculate x velocity
        double dy = -(Math.cos(Math.toRadians(angle)) * speed); // calculate y velocity, invert to normalize y axis

        return new Velocity(dx, dy);
    }

    /**
     * Applies Velocity to Point, returning a new Point with modified coordinates.
     *
     * @param p the Point to which this Velocity should be applied
     * @return a new Point with the x and y coordinates modified by dx and dy
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}