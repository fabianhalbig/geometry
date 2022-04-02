import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Tests {
 
    public Tests() {
    }

    @Test
    public void noIntersection() {
        Strecke lineSegment1 = new Strecke(1, 3, 2, 4);
        Strecke lineSegment2 = new Strecke(5, 5, 8, 6);
        assertEquals(false, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void usualIntersection() {
        Strecke lineSegment1 = new Strecke(-6, 3, -1, 5);
        Strecke lineSegment2 = new Strecke(-5, 5, -2, 2);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearNoOverlap() {
        Strecke lineSegment1 = new Strecke(-6, 0, -10, -2);
        Strecke lineSegment2 = new Strecke(2, 4, 6, 6);
        assertEquals(false, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap1() {
        Strecke lineSegment1 = new Strecke(-18, 2, -2, 10);
        Strecke lineSegment2 = new Strecke(-14, 4, -6, 8);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap1Reverse() {
        Strecke lineSegment2 = new Strecke(-18, 2, -2, 10);
        Strecke lineSegment1 = new Strecke(-14, 4, -6, 8);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap2() {
        Strecke lineSegment1 = new Strecke(0, 15, 10, -15);
        Strecke lineSegment2 = new Strecke(5, 0, 15, -30);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap2Reverse() {
        Strecke lineSegment2 = new Strecke(0, 15, 10, -15);
        Strecke lineSegment1 = new Strecke(5, 0, 15, -30);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap3() {
        Strecke lineSegment1 = new Strecke(-35, 5, -5, 15);
        Strecke lineSegment2 = new Strecke(-5, 15, 10, 20);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void collinearOverlap3Reverse() {
        Strecke lineSegment2 = new Strecke(-35, 5, -5, 15);
        Strecke lineSegment1 = new Strecke(-5, 15, 10, 20);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void equalPoints() {
        Strecke lineSegment1 = new Strecke(10, 10, 10, 10);
        Strecke lineSegment2 = new Strecke(10, 10, 10, 10);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test 
    public void pointNotOnline() {
        Strecke lineSegment1 = new Strecke(-25, 5, -25, 5);
        Strecke lineSegment2 = new Strecke(-20, 15, -25, -5);
        assertEquals(false, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void pointOnLine() {
        Strecke lineSegment1 = new Strecke(3, 3, 7, 5);
        Strecke lineSegment2 = new Strecke(5, 4, 5, 4);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void poointOnEndofLine() {
        Strecke lineSegment1 = new Strecke(3, 3, 5, 4);
        Strecke lineSegment2 = new Strecke(5, 4, 5, 4);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

    @Test
    public void poointOnEndofLineReverse() {
        Strecke lineSegment1 = new Strecke(7, 5, 5, 4);
        Strecke lineSegment2 = new Strecke(5, 4, 5, 4);
        assertEquals(true, lineSegment1.ccw(lineSegment2));
    }

}
