import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Tests2 {

    public Tests2() {
    }

    @Test
    public void areaEasyPolygon() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2, 2));
        points.add(new Point(7, 2));
        points.add(new Point(8, 3));
        points.add(new Point(10, 3));
        points.add(new Point(10, 4));
        points.add(new Point(7, 3));
        points.add(new Point(4, 8));
        Polygon easy = new Polygon(points);
        assertEquals(18.5, easy.getArea(), 0.0);
    }

    @Test
    public void areaEasyPolygon1() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2, 2));
        points.add(new Point(7, 2));
        points.add(new Point(8, 8));
        points.add(new Point(10, 3));
        points.add(new Point(8, 14));
        points.add(new Point(4, 4));
        points.add(new Point(4, 8));
        Polygon easy = new Polygon(points);
        assertEquals(37, easy.getArea(), 0.0);
    }

    @Test
    public void areaEasyPolygonFloat() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(33.875, easy.getArea(), 0.005);
    }

    @Test
    public void pointInPolygonInside() {
        Point p = new Point(0 ,8);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonEdge1() {
        Point p = new Point(-1.825 ,10.593);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonEdge2() {
        Point p = new Point(2.253,8.630);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonEdge3() {
        Point p = new Point(0,5.281);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonEdge4() {
        Point p = new Point(-8.236,8.480);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonVertice1() {
        Point p = new Point(-2.630, 12.552);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointInPolygonVertice2() {
        Point p = new Point(-8.264, 8.490);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(true, easy.pointInPolygon(p));
    }

    @Test
    public void pointOutside1() {
        Point p = new Point(0, 0);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(false, easy.pointInPolygon(p));
    }

    @Test
    public void pointOutside2() {
        Point p = new Point(14, 4);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(false, easy.pointInPolygon(p));
    }

    @Test
    public void pointOutside3() {
        Point p = new Point(0.679, 5.017);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0.679, 5.018));
        points.add(new Point(3.234, 10.881));
        points.add(new Point(-2.630, 12.552));
        points.add(new Point(-1.122, 8.883));
        points.add(new Point(-8.264, 8.490));
        Polygon easy = new Polygon(points);
        assertEquals(false, easy.pointInPolygon(p));
    }
    
}
