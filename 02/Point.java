import java.util.ArrayList;

public class Point {

    double x;
    double y;

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

    public static double getMaxX(ArrayList<Point> points) {
        double maxPoint = Double.MIN_VALUE;
        for (Point point:points) {
            if (point.getX() > maxPoint) {
                maxPoint = point.getX();
            }
        }
        return maxPoint;
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
