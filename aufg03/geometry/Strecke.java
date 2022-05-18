package geometry;

import java.util.Comparator;

public class Strecke implements Comparator<Strecke> {

	Punkt start;
	Punkt end;
	
	public Strecke(Punkt start, Punkt end) {
		this.start = start;
		this.end = end;
	}

	public double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.hypot(Math.abs(y2 - y1), Math.abs(x2 - x1));
	}
	public double getDistanceBetweenPoints(Punkt p1, Punkt p2) {
		return Math.hypot(Math.abs(p2.y - p1.y), Math.abs(p2.x - p1.x));
	}
	
	public boolean ccw(Strecke s2) {
		
		double q1 = this.start.x;
		double q2 = this.start.y;
		double p1 = this.end.x;
		double p2 = this.end.y;
		double r1 = s2.start.x;
		double r2 = s2.start.y;
		double r3 = s2.end.x;
		double r4 = s2.end.y;
		
		double ccw1 = (p1*q2-p2*q1)+(q1*r2-q2*r1)+(p2*r1-p1*r2);
		double ccw2 = (p1*q2-p2*q1)+(q1*r2-q2*r1)+(p2*r3-p1*r4);
		
		if (ccw1*ccw2 > 0) {
			return false;
		} else if (ccw1*ccw2 < 0) {
			return true;
		} else if (ccw1*ccw2 == 0) {
			double QP = this.getDistanceBetweenPoints(p1, p2, q1, q2);
			double QR1 = this.getDistanceBetweenPoints(q1, q2, r1, r2);
			double PR1 = this.getDistanceBetweenPoints(p1, p2, r1, r2);
			double QR2 = this.getDistanceBetweenPoints(q1, q2, r3, r4);
			double PR2 = this.getDistanceBetweenPoints(p1, p2, r3, r4);
			double R1R2 = this.getDistanceBetweenPoints(r1, r2, r3, r4);

			if (PR1 + QR1 == QP || PR2 + QR2 == QP || PR1 + QR1 == R1R2 || PR2 + QR2 == R1R2) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compare(Strecke strecke1, Strecke strecke2) {
		// TODO Auto-generated method stub
		return  Double.compare(strecke1.start.x, strecke2.start.x);
	}

	public Punkt getStart() {
		return start;
	}

	public void setStart(Punkt start) {
		this.start = start;
	}

	public Punkt getEnd() {
		return end;
	}

	public void setEnd(Punkt end) {
		this.end = end;
	}
}


