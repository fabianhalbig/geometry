import java.util.ArrayList;

public class Polygon {

    ArrayList<Point> points;

    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }

    //calculate Area of polygon based on realtiv points
    public double getArea() {
        double area = 0.0;
        int j = this.points.size() - 1;
        for (int i = 0; i < this.getPoints().size(); i++) {
            area += (this.getPoints().get(j).getX() + this.getPoints().get(i).getX()) *
             (this.getPoints().get(j).getY() - this.getPoints().get(i).getY());
            j = i;
        }
        //Round on three decimal places + no negative area
        return Math.abs(Math.round(area / 2.0 * 1000.0) / 1000.0);
    }

    public boolean pointInPolygon(Point point) {
        boolean odd = false;
        ArrayList<Point> p = this.getPoints();

        for (int i = 0, j = p.size() - 1; i < p.size(); i++) {
            //Wenn Eckpunkt return true
            if (p.get(i).getX() == point.getX() && p.get(i).getY() == point.getY()) {
                return true;
            }
            //Überprüfe, ob Punkt in Polygon oder auf einer Kante (Raycasting Algorithm)
            if (((p.get(i).getY() > point.getY()) != (p.get(j).getY() > point.getY())) 
                && (point.getX() < (p.get(j).getX() - p.get(i).getX() * (point.getY() - p.get(i).getY()) 
                / (p.get(j).getY() - p.get(i).getY()) + p.get(i).getX()))) {
                odd = !odd;
            }
            j = i;
        }


        return odd;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    
    
}
