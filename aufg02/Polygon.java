import java.util.ArrayList;

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
            area += (oneStartEnd.get(j).getX() + oneStartEnd.get(i).getX()) *
                (oneStartEnd.get(j).getY() - oneStartEnd.get(i).getY());
            j = i; 
        }
        return Math.abs(Main2.round((area / 2.0), 3));
    }
 
    // The Helper-function for point in Polygon
    // True when lines intersect

 
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

            if(Point.doIntersectHelper(p.get(i), p.get(next), point, absolutePoint)) {
                if (Point.orientationHelper(p.get(i), point, p.get(next))==0) {
                    return Point.onSegmentHelper(p.get(i), point, p.get(next));
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
