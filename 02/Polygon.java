import java.util.ArrayList;

public class Polygon {

    ArrayList<Point> points;

    public Polygon(ArrayList<Point> points) {
        this.points = points;
    }

    public double getArea() {
        double x = 0.0;
        double y = 0.0;
        double area = 0.0;
        int j = this.points.size() - 1;
        //create final values of points from differences
        ArrayList<Point> finalPoints = new ArrayList<Point>();
        for (int k = 0; k < this.points.size(); k++) {
            double newX = this.points.get(k).getX();
            double newY = this.points.get(k).getY();
            finalPoints.add(new Point(x + newX, y + newY));
            x += newX;
            y += newY;
        }
        //calcualte area based on created point list
        for (int i = 0; i < finalPoints.size(); i++) {
            area += (finalPoints.get(j).getX() + finalPoints.get(i).getX()) * (finalPoints.get(j).getY() - finalPoints.get(i).getY());
            j = i;
        }
        return Math.abs(Math.round(area / 2.0 * 1000.0) / 1000.0);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }
    
    
}
