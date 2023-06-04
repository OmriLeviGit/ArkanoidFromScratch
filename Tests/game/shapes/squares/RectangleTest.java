package game.shapes.squares;

import game.shapes.circles.*;
import game.shapes.lines.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    public void testIntersection1() {
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        var interPoint = rect.intersectionPoints(new Line(75, 30, 75, 75));
        Point actual = interPoint.get(0);
        Point expected = new Point(75 ,50);
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testIntersection2() {
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        var interPoint = rect.intersectionPoints(new Line(75, 30, 75, 130));


        Point actual = interPoint.get(0);
        Point expected = new Point(75 ,50);
        boolean t1 = actual.equals(expected);       // p1

        actual = interPoint.get(1);
        expected = new Point(75 ,100);
        boolean t2 = actual.equals(expected);       // p2

        assertTrue(t1 && t2);
    }

    @Test
    public void testCornerIntersection1() {
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        var interPoint = rect.intersectionPoints(new Line(25, 25, 75, 75));

        Point actual = interPoint.get(0);
        Point expected = new Point(50 ,50);

        assertTrue(expected.equals(actual));
    }

    @Test
    public void testCornerIntersection2() {
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        var interPoint = rect.intersectionPoints(new Line(25, 25, 125, 125));

        assertEquals(2, interPoint.size());

        Point actual = interPoint.get(0);
        Point expected = new Point(50 ,50);
        boolean t1 = actual.equals(expected);

        actual = interPoint.get(1);
        expected = new Point(100 ,100);
        boolean t2 = actual.equals(expected);

        assertTrue(t1 && t2);
    }

    @Test
    public void testNoIntersection() {
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        var interPoint = rect.intersectionPoints(new Line(40, 30, 80, 30));
        assertTrue(interPoint.isEmpty());
    }
}