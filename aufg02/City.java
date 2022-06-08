import common.Point;

public class City {

    String name;
    Point p; 

    public City(String name, Point p) {
        this.name = name;
        this.p = p;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return p;
    }
    
}
