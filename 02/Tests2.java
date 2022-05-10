

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class Tests2 {

    public Tests2() {
    }

    @Test
    public void areaEasyPolygon() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2, 2));
        points.add(new Point(4, 1));
        points.add(new Point(4, 3));
        points.add(new Point(6, 4));
        points.add(new Point(3, 5));
        points.add(new Point(1, 3));
        points.add(new Point(2, 2));
        Polygon easy = new Polygon(points);
        assertEquals(9, easy.getArea(), 0.001);
    }

    @Test
    public void areaPolygon() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(34, 50));
        points.add(new Point(36, 44));
        points.add(new Point(48, 42));
        points.add(new Point(48, 50));
        points.add(new Point(54, 50));
        points.add(new Point(54, 54));
        points.add(new Point(48, 54));
        points.add(new Point(48, 58));
        points.add(new Point(58, 58));
        points.add(new Point(60, 50));
        points.add(new Point(62, 58));
        points.add(new Point(55, 60));
        points.add(new Point(10, 55));
        points.add(new Point(34, 50));
        Polygon p = new Polygon(points);
        assertEquals(358.5, p.getArea(), 0.001);
    }

    @Test
    public void areaPolygonDecimal() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(-124.805, -14.903));
        points.add(new Point(-127.680, 6.340));
        points.add(new Point(-112.186, 6.021));
        points.add(new Point(-127.520, 16.882));
        points.add(new Point(-91.901, 21.674));
        points.add(new Point(-108.193, -15.702));
        points.add(new Point(-200.195, -25.605));
        points.add(new Point(-187.577, 12.569));
        points.add(new Point(-124.805, -14.903));
        Polygon p = new Polygon(points);
        //delta 0.1, da viel gerundet bei punkteingabe
        assertEquals(2315.166, p.getArea(), 0.1);
    }


    public static ArrayList<Point> getBerlinAsPoylgon() throws FileNotFoundException {
        String pathBerlin = "\\data\\berlinInputTest.svg";
        //read in Berlin as test from other file 
        ArrayList<Point> pointBerlin = new ArrayList<Point>();
		int countList = -1;
		File file = new File(pathBerlin);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
			String line = scnr.nextLine();
			//Start/Enpunkt
			Double x = Double.parseDouble(line.substring(1).split(",")[0]);
			Double y = Double.parseDouble(line.substring(1).split(",")[1]);
			if (line.startsWith("l")) {
				x = pointBerlin.get(countList).getX() + x;
				y = pointBerlin.get(countList).getY() + y;
			}

			pointBerlin.add(new Point(x, y));
			System.out.println(x + "," + y);
			countList++;
		}
        scnr.close();
        return pointBerlin;
    }

    @Test
    public void areaTestBerlin() throws FileNotFoundException {
        Polygon p = new Polygon(getBerlinAsPoylgon());
        double result = p.getArea();
        assertEquals(766.233, result, 0.0);
    }

    @Test
    public void pointInEasyPolygon() {
        Point q = new Point(2, 3);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(2, 3));
        points.add(new Point(3, 1));
        points.add(new Point(6, 3));
        points.add(new Point(5, 6));
        points.add(new Point(2, 6));
        points.add(new Point(4, 4));
        points.add(new Point(2, 3));

        Polygon p = new Polygon(points);
        assertEquals(true, p.pointInPolygon(q));
    }


    @Test
    public void pointInPolygon() {
        Point q = new Point(18, 9);
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(12, 8));
        points.add(new Point(14, 9));
        points.add(new Point(11, 6));
        points.add(new Point(16, 3));
        points.add(new Point(18, 9));
        points.add(new Point(21, 9));
        points.add(new Point(21, 11));
        points.add(new Point(18, 11));
        points.add(new Point(18, 13));
        points.add(new Point(21, 13));
        points.add(new Point(18, 14));
        points.add(new Point(17, 11));
        points.add(new Point(12, 14));
        points.add(new Point(11, 13));
        points.add(new Point(5, 10));
        points.add(new Point(8, 5));
        points.add(new Point(12, 8));

        Polygon p = new Polygon(points);
        assertEquals(true, p.pointInPolygon(q));
    }

    @Test
    public void pointInBerlin() throws FileNotFoundException {
        Point berlin = new Point(477, 256);
        Polygon p = new Polygon(getBerlinAsPoylgon());
        assertEquals(true, p.pointInPolygon(berlin));
    }

    @Test
    public void polygonInPolygonWithPoint() {
        ArrayList<Polygon> other = new ArrayList<Polygon>();
		ArrayList<Point> outer = new ArrayList<Point>();
		ArrayList<Point> inner = new ArrayList<Point>();

		outer.add(new Point(0, 0));
		outer.add(new Point(0, 100));
        outer.add(new Point(100, 100));
		outer.add(new Point(100, 0));
		outer.add(new Point(0, 0));

		other.add(new Polygon(outer));

		inner.add(new Point(3.05, 3.87));
		inner.add(new Point(4, 3));
		inner.add(new Point(3, 2));
		inner.add(new Point(3.05, 3.87));

		Polygon test = new Polygon(inner);
	
		assertEquals(true, test.polygonInOtherPolygons(other));
    }
}
