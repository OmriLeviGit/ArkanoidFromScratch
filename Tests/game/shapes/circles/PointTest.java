package game.shapes.circles;

import game.miscellaneous.DoubleCompare;
import org.junit.jupiter.api.Test;

class PointTest {

    public boolean isInRangeOfEpsilon(double x, double y) {
        return DoubleCompare.difference(x, y) == 0;
    }

    @Test void test() {

        Point p1 = new Point(3.5, 4.6);
        Point p2 = new Point(3.5, 4.6);
        Point p3 = new Point(5.4, 7.8);
        Point p4 = new Point(-5.4, 7.8);
        Point p5 = new Point(-5.4, -7.8);
        Point p6 = new Point(5.4, -7.8);
        Point p7 = new Point(0.0, 0.0);

        assert (p7.getY() == 0);
        assert (p1.getX() == 3.5);
        assert (p1.getY() == 4.6);
        assert (p1.equals(p1));
        assert (p1.equals(p2));
        assert (!p1.equals(p3));
        assert (p1.distance(p1) == 0.0);
        assert (p1.distance(p2) == 0.0);

        assert(isInRangeOfEpsilon(p1.distance(p3),Math.sqrt(Math.pow(1.9, 2) + Math.pow(3.2, 2))));
        assert ((int)p1.distance(p3) == 3);
        assert(isInRangeOfEpsilon(p1.distance(p4),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(3.2, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p5),Math.sqrt(Math.pow(-8.9, 2) + Math.pow(-12.4, 2))));
        assert(isInRangeOfEpsilon(p1.distance(p6),Math.sqrt(Math.pow(1.9, 2) + Math.pow(-12.4, 2))));
    }
}