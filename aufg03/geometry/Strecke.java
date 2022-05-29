package geometry;

import java.util.ArrayList;
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
	
	public double yFuerX(double x) {
		double y1 = this.start.y;
		double y2 = this.end.y;
		double x1 = this.start.x;
		double x2 = this.end.x;
		
		/*
		System.out.println("y1"+y1);
		System.out.println("y2"+y2);
		System.out.println("x1"+x1);
		System.out.println("x2"+x2);
		System.out.println("x"+x);
		System.out.println((x2-x1)*x);
		*/
		
		double result = (((y2-y1)/(x2-x1)*x)+(((x2*y1)-(x1*y2))/(x2-x1)));
		
		//System.out.println(result);
		
		return result;
	}
	
	public Punkt schnittPunkt(Strecke strecke) {
		double y1 = this.start.y;
		double y2 = this.end.y;
		double x1 = this.start.x;
		double x2 = this.end.x;
		double y3 = strecke.start.y;
		double y4 = strecke.end.y;
		double x3 = strecke.start.x;
		double x4 = strecke.end.x;
		
		double s1_x = x2 - x1;     
		double s1_y = y2 - y1;
		double s2_x = x4 - x3;     
		double s2_y = y4 - y3;
		
		double s, t;
	    s = (-s1_y * (x1 - x3) + s1_x * (y1 - y3)) / (-s2_x * s1_y + s1_x * s2_y);
	    t = ( s2_x * (y1 - y3) - s2_y * (x1 - x3)) / (-s2_x * s1_y + s1_x * s2_y);
	    
	    double schnittX = x1 + (t * s1_x);
        
        double schnittY = y1 + (t * s1_y);
        
        return new Punkt(schnittX, schnittY, "schnitt");
		
		/*
		double a1 = (y2-y1)/(x2-x1);
		double b1 = y1 - a1*x1;
		double a2 = (y4-y3)/(x4-x3);
		double b2 = y3 - a2*x3;
		*/
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

