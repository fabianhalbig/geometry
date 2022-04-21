import java.util.ArrayList;

public class Bundesland {

    String name;
    ArrayList<Polygon> polygons;

    public Bundesland(String name, ArrayList<Polygon> polygons) {
        this.name = name;
        this.polygons = polygons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Polygon> getPoints() {
        return polygons;
    }

    public void setPoints(ArrayList<Polygon> polygons) {
        this.polygons = polygons;
    }
}
