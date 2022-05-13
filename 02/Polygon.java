import java.util.ArrayList;
import java.util.Map;

public class Polygon {

    ArrayList<Point> points;

    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }

    //calculate Area of polygon based on absolute points
    public double getArea() {
        double area = 0.0;
        ArrayList<Point> oneStartEnd = getPoints();
        oneStartEnd.remove(oneStartEnd.size() - 1);
        int j = oneStartEnd.size() - 1;
        
        for (int i = 0; i < oneStartEnd.size(); i++) {
            area += (oneStartEnd.get(j).getX() + oneStartEnd.get(i).getX()) * (oneStartEnd.get(j).getY() - oneStartEnd.get(i).getY());
            j = i; 
        }

        return Math.abs(Math.round(area / 2.0 * 1000.0) / 1000.0);
    }

    static boolean onSegmentHelper(Point p, Point q, Point r)
    {
        if (q.x <= Math.max(p.x, r.x) &&
            q.x >= Math.min(p.x, r.x) &&
            q.y <= Math.max(p.y, r.y) &&
            q.y >= Math.min(p.y, r.y))
        {
            return true;
        }
        return false;
    }
 
    //Helper function for ccw
    // 0 --> collinear
    // 1 --> cw
    // 2 --> ccw
    static double orientationHelper(Point p, Point q, Point r)
    {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX())
                - (q.getX() - p.getX()) * (r.getY() - q.getY());
 
        if (val == 0)
        {
            return 0; // collinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }
 
    // The Helper-function for point in Polygon
    // True when lines intersect
    static boolean doIntersectHelper(Point p1, Point q1,
                            Point p2, Point q2)
    {
        double o1 = orientationHelper(p1, q1, p2);
        double o2 = orientationHelper(p1, q1, q2);
        double o3 = orientationHelper(p2, q2, p1);
        double o4 = orientationHelper(p2, q2, q1);
 
        if (o1 != o2 && o3 != o4)
        {
            return true;
        }
 
        // Special Cases
        // p1, q1 and p2 are collinear and
        if (o1 == 0 && onSegmentHelper(p1, p2, q1))
        {
            return true;
        }
        if (o2 == 0 && onSegmentHelper(p1, q2, q1))
        {
            return true;
        }
        if (o3 == 0 && onSegmentHelper(p2, p1, q2))
        {
            return true;
        }
        if (o4 == 0 && onSegmentHelper(p2, q1, q2))
        {
            return true;
        }
 
        return false;
    }
 
    public boolean pointInPolygon(Point point) {
        ArrayList<Point> p = getPoints();

        if (p.size() - 1 < 3) {
            return false;
        }

        p.remove(p.size() - 1);

        double xMax = Point.getMaxX(p);
        double yMax = Point.getMaxX(p);

        //Get maximum point 
        Point absolutePoint = new Point(xMax + 1, yMax + 1);

        int count = 0, i = 0;
        do {
            int next = (i+1) % p.size();

            if(doIntersectHelper(p.get(i), p.get(next), point, absolutePoint)) {
                if (orientationHelper(p.get(i), point, p.get(next))==0) {
                    return onSegmentHelper(p.get(i), point, p.get(next));
                }
                count++;
            }
            i = next;
        } while (i != 0);
        return (count % 2 == 1);
    }

    public boolean polygonInOtherPolygons(ArrayList<Polygon> other) {
        Point startPoint = getPoints().get(0);
        for (Polygon p: other) {
            if (p.pointInPolygon(startPoint)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    
    
}
