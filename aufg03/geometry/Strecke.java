package geometry;

import common.Point;
import java.util.ArrayList;
import java.util.Comparator;


public class Strecke implements Comparator<Strecke> {

	Point start;
	Point end;
	
	public Strecke(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public double getDistanceBetweenPoints(double x1, double y1, double x2, double y2) {
		return Math.hypot(Math.abs(y2 - y1), Math.abs(x2 - x1));
	}
	public double getDistanceBetweenPoints(Point p1, Point p2) {
		return Math.hypot(Math.abs(p2.getY() - p1.getY()), Math.abs(p2.getX() - p1.getX()));
	}
	
	boolean doIntersect(Strecke s2) {
        return Point.doIntersectHelper(this.getStart(), this.getEnd(),
         s2.getStart(), s2.getEnd());
    }

	@Override
	public int compare(Strecke strecke1, Strecke strecke2) {
		// TODO Auto-generated method stub
		return  Double.compare(strecke1.start.getX(), strecke2.start.getX());
	}
	
	public double yFuerX(double x) {
		double y1 = this.start.getY();
		double y2 = this.end.getY();
		double x1 = this.start.getX();
		double x2 = this.end.getX();
		
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
	
	public Point schnittPunkt(Strecke strecke) {
		double y1 = this.start.getY();
		double y2 = this.end.getY();
		double x1 = this.start.getX();
		double x2 = this.end.getX();
		double y3 = strecke.start.getY();
		double y4 = strecke.end.getY();
		double x3 = strecke.start.getX();
		double x4 = strecke.end.getX();
		
		double s1_x = x2 - x1;     
		double s1_y = y2 - y1;
		double s2_x = x4 - x3;     
		double s2_y = y4 - y3;
		
		double s, t;
	    s = (-s1_y * (x1 - x3) + s1_x * (y1 - y3)) / (-s2_x * s1_y + s1_x * s2_y);
	    t = ( s2_x * (y1 - y3) - s2_y * (x1 - x3)) / (-s2_x * s1_y + s1_x * s2_y);
	    
	    double schnittX = x1 + (t * s1_x);
        
        double schnittY = y1 + (t * s1_y);
        
        return new Point(schnittX, schnittY, "schnitt");
		
		/*
		double a1 = (y2-y1)/(x2-x1);
		double b1 = y1 - a1*x1;
		double a2 = (y4-y3)/(x4-x3);
		double b2 = y3 - a2*x3;
		*/
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}
}


