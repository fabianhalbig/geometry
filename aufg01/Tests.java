import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {
 
    public Tests() {
    }

    @Test
    public void noIntersection() {
        Strecke lineSegment1 = new Strecke(new Point(1, 3), new Point(2, 4));
        Strecke lineSegment2 = new Strecke(new Point(5, 5), new Point(8, 6));
        assertEquals(false, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void usualIntersection() {
        Strecke lineSegment1 = new Strecke(new Point(-6, 3), new Point(-1, 5));
        Strecke lineSegment2 = new Strecke(new Point(-5, 5), new Point(-2, 2));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void collinearNoOverlap() {
        Strecke lineSegment1 = new Strecke(new Point(-6, 0), new Point(-10, -2));
        Strecke lineSegment2 = new Strecke(new Point(2, 4), new Point(6, 6));
        assertEquals(false, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void collinearOverlap1() {
        Strecke lineSegment1 = new Strecke(new Point(-18, 2), new Point(-2, 10));
        Strecke lineSegment2 = new Strecke(new Point(-14, 4), new Point(-6, 8));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void collinearOverlap2() {
        Strecke lineSegment1 = new Strecke(new Point(0, 15), new Point(10, -15));
        Strecke lineSegment2 = new Strecke(new Point(5, 0), new Point(15, -30));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void collinearOverlap3() {
        Strecke lineSegment1 = new Strecke(new Point(-35, 5), new Point(-5, 15));
        Strecke lineSegment2 = new Strecke(new Point(-5, 15), new Point(10, 20));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void equalPoints() {
        Strecke lineSegment1 = new Strecke(new Point(10, 10), new Point(10, 10));
        Strecke lineSegment2 = new Strecke(new Point(10, 10), new Point(10, 10));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test 
    public void pointNotOnline() {
        Strecke lineSegment1 = new Strecke(new Point(-25, 5), new Point(-25, 5));
        Strecke lineSegment2 = new Strecke(new Point(-20, 15), new Point(-25, -5));
        assertEquals(false, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void pointOnLine() {
        Strecke lineSegment1 = new Strecke(new Point(3, 3), new Point(7, 5));
        Strecke lineSegment2 = new Strecke(new Point(5, 4), new Point(5, 4));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

    @Test
    public void poointOnEndofLine() {
        Strecke lineSegment1 = new Strecke(new Point(3, 3), new Point(5, 4));
        Strecke lineSegment2 = new Strecke(new Point(5, 4), new Point(5, 4));
        assertEquals(true, lineSegment1.doIntersect(lineSegment2));
    }

}
