import java.util.ArrayList;

public class Point {

    double x;
    double y;
    String punkTyp;

    public Point(double x, double y, String punkTyp) {
        this.x = x;
        this.y = y;
        this.punkTyp = punkTyp;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    static boolean onSegmentHelper(Point p, Point q, Point r) {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
            q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY())) {
                return true;
            }    
        return false;
    }

    static int orientationHelper(Point p, Point q, Point r)
    {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());
    
        if (val == 0) return 0; // collinear
    
        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    public static double getMaxX(ArrayList<Point> points) {
        double maxPoint = Double.MIN_VALUE;
        for (Point point:points) {
            if (point.getX() > maxPoint) {
                maxPoint = point.getX();
            }
        }
        return maxPoint;
    }

    static boolean doIntersectHelper(Point p1, Point q1, Point p2, Point q2) {
        double o1 = Point.orientationHelper(p1, q1, p2);
        double o2 = orientationHelper(p1, q1, q2);
        double o3 = orientationHelper(p2, q2, p1);
        double o4 = orientationHelper(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        // Special Cases
        // p1, q1 and p2 are collinear and
        if (o1 == 0 && onSegmentHelper(p1, p2, q1)) {
            return true;
        }
        if (o2 == 0 && onSegmentHelper(p1, q2, q1)) {
            return true;
        }
        if (o3 == 0 && onSegmentHelper(p2, p1, q2)) {
            return true;
        }
        if (o4 == 0 && onSegmentHelper(p2, q1, q2)) {
            return true;
        }
        return false;
    }

    public static double getMaxY(ArrayList<Point> points) {
        double maxPoint = Double.MIN_VALUE;
        for (Point point:points) {
            if (point.getY() > maxPoint) {
                maxPoint = point.getY();
            }
        }
        return maxPoint;
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        if (this.getX() == p.getX() && this.getY() == p.getY()) {
            return true;
        }
        return false;
    }
    
}
