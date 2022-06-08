import common.Point;

public class Strecke {

	Point p;
	Point q;

	public Strecke(Point p, Point q) {
		this.p = p;
		this.q = q;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public Point getQ() {
		return q;
	}

	public void setQ(Point q) {
		this.q = q;
	}
    
    boolean doIntersect(Strecke s2) {
        return Point.doIntersectHelper(this.getP(), this.getQ(),
         s2.getP(), s2.getQ());
    }

}
