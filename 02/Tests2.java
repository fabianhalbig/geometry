import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Tests2 {

    public Tests2() {
    }

    @Test 
    public void areaEasyPolygon() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(-9, 1));
        points.add(new Point(-0.38, -2.55));
        points.add(new Point(-0.58, -1.32));
        points.add(new Point(9.96, 2.87));
        points.add(new Point(-7, 1));
        Polygon easy = new Polygon(points);
        assertEquals(18.406, easy.getArea(), 0.0);
    }
    
}
