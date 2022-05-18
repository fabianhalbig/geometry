package geometry;

public class Punkt {
	
	double x;
    double y;
    String punkTyp;

    public Punkt(double x, double y, String punkTyp) {
        this.x = x;
        this.y = y;
        this.punkTyp = punkTyp;
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
}
