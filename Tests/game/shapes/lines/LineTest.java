package game.shapes.lines;

import game.shapes.circles.Point;
import org.junit.jupiter.api.Test;


class LineTest {

   @Test
   void lineTest() {
       Line f = new Line(49.45, 68.3,93.23, 27.3);
       Point c1 = new Point(49.45, 68.3);
       Point c2 = new Point(93.23, 27.3);
       // is same as lf
       Line l2f = new Line(c1, c2);
       Line g = new Line(151.94528, 65.76442,54.76838, 16.41678);
       // l4 and l5 parallel to y axis but don't touch
       Line h = new Line(20.0, 140.0,20.0, 120.0);
       Line i = new Line(20.0, 100.0,20.0, 60.0);

       Line j = new Line(71.72, 55.3,80.07, 64.24);
       Line k = new Line(70.52, 93.75,65.46, 62.00);

       // l8, l9 are parallel to Y axis and share one point
       Line l = new Line(40.0, 140.0,40.0, 100.0);
       Line m = new Line(40.0, 100.0,40.0, 80.0);

       // l10, l11 are parallel to X axis and share one point
       Line n = new Line(60.0, 40.0,40.0, 40.0);
       Line p = new Line(40.0, 40.0,20.0, 40.0);

       // l12, l13 are parallel to X axis and don't share any points
       Line q = new Line(60.0, 120.0,80.0, 120.0);
       Line r = new Line(100.0, 120.0,120.0, 120.0);

       Line s = new Line(102.04805, 77.17004,119.96, 49.52);

//            Line t = new Line(136.24, 57.79,180.0, 80.04);

       // a is on y axis
       Line a = new Line(0.0, 0.0,0.0, 40.0);
       Line b = new Line(9.13, 31.2,-23.16, 16.44);
       // j1 is on x axis
       Line j1 = new Line(0.0,0.0,160.0,0.0);
       //intersects with x axis
       Line k1 = new Line(120.0,20.0,120.0,-20.0);
       Line l1 = new Line(100.0,20.0,160.0,-20.0);

       Line m1 = new Line(40.0,60.0,40.0,20.0);
       Line n1 = new Line(00.0,80.0,40.0,80.0);


       Line f1 = new Line(80.0, 164.0,90.0, 184.0);
       Line g1 = new Line(85.0, 174.0,95.0, 194.0);

       Point t1 = new Point(100.0, 160.0);
       Point s1 = new Point(120.0, 160.0);
       Point r1 = new Point(1400.0, 160.0);

       //are the same but start point and end point are opposite
       Line h1 = new Line(t1,s1);
       Line lh1 = new Line(s1,t1);

       //are the same but start point and end point are opposite
       Line i1 = new Line(t1,r1);
       Line li1 = new Line(r1,t1);


       assert (f.start().equals(c1));
       assert (f.end().equals(c2));
       assert(f.middle().equals(new Point(71.34, 47.8)));
       assert (f.length() == c1.distance(c2));
       assert (f.equals(f));
       assert (f.equals(l2f));
       assert (!f.equals(g));

       assert (f.isIntersecting(l2f));//same line
       assert (f.isIntersecting(g));
// same but opposite order
       assert (g.isIntersecting(f));
// both parallel to y but don't touch
       assert (!h.isIntersecting(i));
// parallel to each other
       assert (!h.isIntersecting(l));
// perpendicular to each other, but don't touch
       assert (!i.isIntersecting(p));

// have one in common**********************************************
       assert (l.isIntersecting(m));
// have one in common
       assert (n.isIntersecting(p));

       assert (!q.isIntersecting(r));
       assert (!l2f.isIntersecting(k));
       assert (!l2f.isIntersecting(j));
       assert (s.isIntersecting(g));
//            assert (l15.isIntersecting(l3));
       assert(f1.isIntersecting(g1));

       assert (!k.isIntersecting(q));
       assert (b.isIntersecting(a));

       assert (f.intersectionWith(f) == null);
       assert (f.intersectionWith(l2f) == null);
//            assert (l3.intersectionWith(l15) == null);
       assert (f1.intersectionWith(g1) == null);
       assert (k.intersectionWith(f) == null);

// lines that have other line completely in them,
// wildest sharing a start or end point
       assert(h1.intersectionWith(lh1) == null);
       assert(h1.intersectionWith(i1) == null);
       assert(h1.intersectionWith(li1) == null);

       assert(lh1.intersectionWith(h1) == null);
       assert(lh1.intersectionWith(i1) == null);
       assert(lh1.intersectionWith(li1) == null);

       assert(i1.intersectionWith(h1) == null);
       assert(i1.intersectionWith(lh1) == null);
       assert(i1.intersectionWith(li1) == null);

       assert(li1.intersectionWith(lh1) == null);
       assert(li1.intersectionWith(i1) == null);
       assert(li1.intersectionWith(h1) == null);

       assert (n.intersectionWith(p).equals(new Point(40.0, 40.0)));
       assert (s.intersectionWith(g).equals(new Point(119.95907554822693, 49.521427043314844)));
       assert (f.intersectionWith(g).equals(new Point(87.242330580114, 32.907456514740204)));
       assert (a.intersectionWith(b).equals(new Point(0.0, 27.02660885723134)));

//added tests
       assert(a.isIntersecting(j1));
       assert(a.intersectionWith(j1).equals(new Point(0.0,0.0)));

       assert(k1.isIntersecting(j1));
       assert(k1.intersectionWith(j1).equals(new Point(120.0,0.0)));

       assert(k1.isIntersecting(l1));
       assert(k1.intersectionWith(l1).equals(new Point(120.0,6.666666666666657)));

       assert(j1.isIntersecting(l1));
       assert(j1.intersectionWith(l1).equals(new Point(130.0,0.0)));

       assert(m1.isIntersecting(p));
       assert(m1.intersectionWith(p).equals(new Point(40.0,40.0)));

       assert(n1.isIntersecting(i));
       assert(n1.intersectionWith(i).equals(new Point(20.0,80.0)));
   }
}