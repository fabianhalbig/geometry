
public class Strecke {
	
	double x1;
	double y1;
	double x2;
	double y2;
	
	public Strecke(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.hypot(Math.abs(y2 - y1), Math.abs(x2 - x1));
	}
	
	public boolean ccw(Strecke s2) {
		
		double q1 = this.x1;
		double q2 = this.y1;
		double p1 = this.x2;
		double p2 = this.y2;
		double r1 = s2.x1;
		double r2 = s2.y1;
		double r3 = s2.x2;
		double r4 = s2.y2;
		
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
	
	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}
}
