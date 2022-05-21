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

    public double getAreaOfState() {
        double area = 0;
        for(Polygon p: getPolygons()) {
            area = area + p.getArea();
        }

        if (getPolygons().size() > 1) {
            for(Polygon p: getPolygons()) {
                for(Polygon q: getPolygons()) {
                    if (p.pointInPolygon(q.getPoints().get(0))&& p!=q) {
                        //minus 2 times, as polygon already added once in previous step
                        area = area - 2*q.getArea();
                    }
                }
            }
        }
        return Main2.round(area, 3);
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
