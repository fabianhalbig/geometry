import java.util.ArrayList;

public class Bundesland {

    String name;
    ArrayList<Polygon> polygons;

    public Bundesland(String name, ArrayList<Polygon> polygons) {
        this.name = name;
        this.polygons = polygons;
    }

    public boolean bundeslandContainsCity(Point q) {
        for (Polygon p:this.polygons) {
            if (p.pointInPolygon(q)) {
                return true;
            }
        }
        return false;
    }

    public double getArea() {
        double area = 0.0;
        if (this.getPolygons().size() == 1) {
            for(Polygon p: this.getPolygons()) {
                area = area + p.getArea();
            }
        } else {
            for(Polygon p: this.getPolygons()) {
                if (p.polygonInOtherPolygons(this.getPolygons())) {
                    area = area - p.getArea();
                } else {
                    area = area + p.getArea();
                }
            }
        }
        return Main2.round(Math.abs(area), 3);
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
