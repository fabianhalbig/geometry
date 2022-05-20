import java.util.ArrayList;

public class Bundesland {

    String name;
    ArrayList<Polygon> polygons;

    public Bundesland(String name, ArrayList<Polygon> polygons) {
        this.name = name;
        this.polygons = polygons;
    }

    public boolean bundeslandContainsCity(Point q) {
        for (Polygon p:polygons) {
            if (p.pointInPolygon(q)) {
                return true;
            }
        }
        return false;
    }

    public double getArea() {
        double sum = 0;
        for (Polygon p: getPolygons()) {
            sum = sum + p.getArea();
        }
        return Main2.round(sum, 3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Polygon> getPolygons() {
        return polygons;
    }

    public void getPolygons(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
    }
}
