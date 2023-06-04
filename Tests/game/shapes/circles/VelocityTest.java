package game.shapes.circles;

import game.miscellaneous.DoubleCompare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VelocityTest {

    @Test
    public void test() {
        Velocity v1 = new Velocity(3.5, 6.7);
        Velocity v2 = new Velocity(-3.5, 6.7);
        Velocity v3 = new Velocity(-3.5, -6.7);
        Velocity v4 = new Velocity(3.5, -6.7);
        Velocity v5 = new Velocity(0.0, 0.0);
        Velocity v6 = Velocity.fromAngleAndSpeed(90.0, 5.4);
        Velocity v7 = Velocity.fromAngleAndSpeed(0.0, 5.4);
        Velocity v8 = Velocity.fromAngleAndSpeed(30.0, 6.0);

        Point p1 = new Point(3.5, 7.8);
        Point p2 = new Point(-3.5, 7.8);
        Point p3 = new Point(-3.5, -7.8);
        Point p4 = new Point(3.5, -7.8);

        assert (v1.applyToPoint(p1).equals(new Point(7.0, 14.5)));
        assert (v1.applyToPoint(p2).equals(new Point(0.0, 14.5)));
        assert (v1.applyToPoint(p3).equals(new Point(0.0, -1.1)));
        assert (v1.applyToPoint(p4).equals(new Point(7.0, -1.1)));

        assert (v2.applyToPoint(p1).equals(new Point(0.0, 14.5)));
        assert (v2.applyToPoint(p2).equals(new Point(-7.0, 14.5)));
        assert (v2.applyToPoint(p3).equals(new Point(-7.0, -1.1)));
        assert (v2.applyToPoint(p4).equals(new Point(0.0, -1.1)));

        assert (v3.applyToPoint(p1).equals(new Point(0.0, 1.1)));
        assert (v3.applyToPoint(p2).equals(new Point(-7.0, 1.1)));
        assert (v3.applyToPoint(p3).equals(new Point(-7.0, -14.5)));
        assert (v3.applyToPoint(p4).equals(new Point(0.0, -14.5)));

        assert (v4.applyToPoint(p1).equals(new Point(7.0, 1.1)));
        assert (v4.applyToPoint(p2).equals(new Point(0.0, 1.1)));
        assert (v4.applyToPoint(p3).equals(new Point(0.0, -14.5)));
        assert (v4.applyToPoint(p4).equals(new Point(7.0, -14.5)));

        assert (v5.applyToPoint(p1).equals(new Point(3.5, 7.8)));
        assert (v5.applyToPoint(p2).equals(new Point(-3.5, 7.8)));
        assert (v5.applyToPoint(p3).equals(new Point(-3.5, -7.8)));
        assert (v5.applyToPoint(p4).equals(new Point(3.5, -7.8)));

//         Point p4 = new Point(3.5, -7.8);
        assert (v6.applyToPoint(p4).equals(new Point(8.9, -7.8)));
//        assert (v7.applyToPoint(p4).equals(new Point(3.5, -2.4)));
        assert (v7.applyToPoint(p4).equals(new Point(3.5, -13.2)));
//        assert (v8.applyToPoint(p4).equals(new Point(((6.0 * Math.sin(Math.PI/6)))
//                + 3.5, (6.0 * Math.sin(Math.PI / 3)) -7.8)));
        assert (v8.applyToPoint(p4).equals(new Point(((6.0 * Math.sin(Math.PI/6)))
                + 3.5, -(6.0 * Math.sin(Math.PI / 3)) -7.8)));
    }

    public boolean VelocitiesEqual(Velocity expected, Velocity actual) {
        double dx = expected.getDx();
        double dy = expected.getDy();

        double aDx = actual.getDx();
        double aDy = actual.getDy();

        boolean dxEquals = DoubleCompare.equals(dx, aDx);
        boolean dyEquals = DoubleCompare.equals(dy, aDy);

        return dxEquals && dyEquals;
    }
}