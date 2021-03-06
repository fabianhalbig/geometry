package geometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import common.Point;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String pathZuStrecken = "C:\\Users\\Cornelius\\Desktop\\Master Informatik Notebooks\\Computational_Geometry";
		//String pathZuStrecken = "C:\\Users\\Cornelius\\Desktop\\HM Master\\Computational_Geometry";

		String s1000 = "\\s_1000_10.dat";

		ArrayList<Strecke> strecken = einlesen(pathZuStrecken + s1000);
		
		long starttime = System.nanoTime();
		
		ArrayList<Point> schnittPunkte2 = sweepLine(strecken);

		
		long endtime = System.nanoTime() - starttime;
		
		System.out.println("Aufgewendete Zeit: " + TimeUnit.MILLISECONDS.convert(endtime, TimeUnit.NANOSECONDS) + " ms");	

		System.out.println("Es wurden " + schnittPunkte2.size() + " Schnittpunkte gefunden");
		
	}

	public static ArrayList<Strecke> einlesen(String path) throws FileNotFoundException{
		ArrayList<Strecke> strecken = new ArrayList<Strecke>();
		
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
		   String line = scnr.nextLine();
		   String[] points = line.split(" ");
		   Point start = new Point(Double.parseDouble(points[0]),Double.parseDouble(points[1]), "start");
		   Point end = new Point(Double.parseDouble(points[2]),Double.parseDouble(points[3]), "end");
		   Strecke s = new Strecke(start, end);
		   strecken.add(s);
		}
		scnr.close();
		return strecken;
	}
	
	public static Strecke findByStart(ArrayList<Strecke> listStrecke, Point punkt) {
	    return listStrecke.stream().filter(strecke -> punkt.equals(strecke.start)).findFirst().orElse(null);
	}
	
	public static Strecke findByEnd(ArrayList<Strecke> listStrecke, Point punkt) {
	    return listStrecke.stream().filter(strecke -> punkt.equals(strecke.end)).findFirst().orElse(null);
	}
	
	public static boolean almostEqual(double a, double b, double eps){
	    return Math.abs(a-b)<eps;
	}
	
	public static ArrayList<Point> sweepLine(ArrayList<Strecke> strecken){		
		// Alle Start und Endpunkt als EVents hinzuf???gen und sortieren
		ArrayList<Point> eventPunkte = new ArrayList<Point>();
		for(Strecke strecke : strecken){
			eventPunkte.add(strecke.start);
			eventPunkte.add(strecke.end);
		}
		
		Collections.sort(eventPunkte, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return Double.compare(p1.getX(), p2.getX());
			}
		});
		
		
		// Es gibt Strecken mit Ende vor dem Startpunkt
		for(Strecke strecke : strecken){
			if(eventPunkte.indexOf(strecke.end) < eventPunkte.indexOf(strecke.start)){
				int indexStart = eventPunkte.indexOf(strecke.start);
				int indexEnd = eventPunkte.indexOf(strecke.end);
				int streckenIndex = strecken.indexOf(strecke);
				
				Point newStart = new Point(strecke.end.getX(), strecke.end.getY(), "start");
				Point newEnd = new Point(strecke.start.getX(), strecke.start.getY(), "end");
				
				eventPunkte.set(indexEnd, newStart);
				eventPunkte.set(indexStart, newEnd);
				
				Strecke ersatzStrecke =  new Strecke(newStart, newEnd);
				
				strecken.set(streckenIndex, ersatzStrecke);
			}
		}
		
		ArrayList<Strecke> sweepLine = new ArrayList<Strecke>();
		ArrayList<Point> schnittPunkte = new ArrayList<Point>();
		
		// Durchlaufen aller angelegter Eventpunkt
		for(int i = 0; i < eventPunkte.size(); i++)
		{
			Point events = eventPunkte.get(i);
			
			if(events.getPunkTyp() == "start"){
				
				/*
				Let segE = E's segment;
				Add segE to SL;
				Let segA = the segment above segE in SL;
				Let segB = the segment below segE in SL;				
				If (I = Intersect( segE with segA) exists)
				Insert I into x;
				If (I = Intersect( segE with segB) exists)
				Insert I into x;
				 */
				
				Strecke segA = null;
				Strecke segB = null;
				
				Strecke segment = findByStart(strecken, events);
				sweepLine.add(segment);
				
				//Neu Sortieren nach y mit Betrachtung an x
				Collections.sort(sweepLine, new Comparator<Strecke>() {
					double x = segment.start.getX();
					@Override
					public int compare(Strecke o1, Strecke o2) {
						// TODO Auto-generated method stub
						return Double.compare(o1.yFuerX(x), o2.yFuerX(x));
					}
				});
				
				//Anlegen von segA und B je nach Position des aktuellen segment in sweepLine
				if(sweepLine.size() > 1) {
					if(sweepLine.size() > 2 && sweepLine.indexOf(segment) != 0 && sweepLine.indexOf(segment) != sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segment)-1);
						segB = sweepLine.get(sweepLine.indexOf(segment)+1);
					}
					else if (sweepLine.indexOf(segment) == 0) {
						segA = sweepLine.get(sweepLine.indexOf(segment)+1);
					}
					else if (sweepLine.indexOf(segment) == sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segment)-1);
					}
				}
				
				
				//???berpr???fen ob es einen Schnitt mit A oder B gibt
				if(segA != null && segment.doIntersect(segA)){
					eventPunkte.add(eventPunkte.indexOf(events)+1, segment.schnittPunkt(segA));
				}
				if(segB != null && segment.doIntersect(segB)){
					eventPunkte.add(eventPunkte.indexOf(events)+1, segment.schnittPunkt(segB));
				}
			}
			
			
			if(events.getPunkTyp() == "end"){
							/*
							TreatRightEndpoint() {
							Let segE = E's segment;
							Let segA = the segment above segE in SL;
							Let segB = the segment below segE in SL;
							Remove segE from SL;
							If (I = Intersect( segA with segB) exists)
							If (I is not in x already) Insert I into x;
							 */
				
				Strecke segA = null;
				Strecke segB = null;
				
				Strecke segment = findByEnd(strecken, events);

				
				//Anlegen von segA und B je nach Position des aktuellen segment in sweepLine
				if(sweepLine.size() > 1) {
					if(sweepLine.size() > 2 && sweepLine.indexOf(segment) != 0 && sweepLine.indexOf(segment) != sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segment)-1);
						segB = sweepLine.get(sweepLine.indexOf(segment)+1);
					}
					else if (sweepLine.indexOf(segment) == 0) {
						segA = sweepLine.get(sweepLine.indexOf(segment)+1);
					}
					else if (sweepLine.indexOf(segment) == sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segment)-1);
					}
				}
				
				
				//???berpr???fen von Schnitt zwischen A und B
				if(segA != null && segB != null && segB.doIntersect(segA)){
					Point schnitt = segB.schnittPunkt(segA);
					if(!schnittPunkte.contains(schnitt)){
						eventPunkte.add(eventPunkte.indexOf(events)+1, segB.schnittPunkt(segA));
					}
				}
				
				sweepLine.remove(segment);
				
				//Neu Sortieren nach y mit Betrachtung an x
				Collections.sort(sweepLine, new Comparator<Strecke>() {
					double x = segment.end.getX();
					@Override
					public int compare(Strecke o1, Strecke o2) {
						// TODO Auto-generated method stub
						return Double.compare(o1.yFuerX(x), o2.yFuerX(x));
					}
				});
			}
			
			
			if(events.getPunkTyp() == "schnitt"){
				schnittPunkte.add(events);
				
				ArrayList<Strecke> schnittStrecken = new ArrayList<Strecke>();
				Strecke segE1 = null;
				Strecke segE2 = null;
				Strecke segA = null;
				Strecke segB = null;
				
				for(Strecke line:sweepLine){
					if(almostEqual(line.yFuerX(events.getX()),  events.getY(), 0.001)){
						schnittStrecken.add(line);						
					}
					if(schnittStrecken.size() == 2){
						segE1 = schnittStrecken.get(0);
						segE2 = schnittStrecken.get(1);
					}
					if(schnittStrecken.size() > 2){
						System.out.println("Fehler, Schnittpunkt mit mehr als zwei Geraden");
					}
				}
				
				
				//Neu Sortieren nach y mit Betrachtung an x
				Collections.sort(sweepLine, new Comparator<Strecke>() {
					double x = (events.getX() + eventPunkte.get(eventPunkte.indexOf(events) + 1).getX()) /2;
					@Override
					public int compare(Strecke o1, Strecke o2) {
						// TODO Auto-generated method stub
						return Double.compare(o1.yFuerX(x), o2.yFuerX(x));
					}
				});
				
				
				//Anlegen von segA und B je nach Position des aktuellen segment in sweepLine
				if(sweepLine.size() > 1) {
					if(sweepLine.size() > 2 && sweepLine.indexOf(segE1) != 0 && sweepLine.indexOf(segE2) != sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segE2)+1);
						segB = sweepLine.get(sweepLine.indexOf(segE1)-1);
					}
					else if (sweepLine.indexOf(segE2) == 0) {
						segA = sweepLine.get(sweepLine.indexOf(segE2)+1);
						
						if(segA != null && segE2.doIntersect(segA)){
							Point schnitt = segE2.schnittPunkt(segA);
							if(!schnittPunkte.contains(schnitt)){
								eventPunkte.add(eventPunkte.indexOf(events)+1, segE2.schnittPunkt(segA));
							}
						}
					}
					else if (sweepLine.indexOf(segE1) == sweepLine.size()-1) {
						segA = sweepLine.get(sweepLine.indexOf(segE1)-1);
						
						if(segA != null && segE1.doIntersect(segA)){
							Point schnitt = segE1.schnittPunkt(segA);
							if(!schnittPunkte.contains(schnitt)){
								eventPunkte.add(eventPunkte.indexOf(events)+1, segE1.schnittPunkt(segA));
							}
						}
					}
				}
					
				
				
				/*
				Add E to the output list L;
				Let segE1 above segE2 be E's intersecting segments in SL;
				Swap their positions so that segE2 is now above segE1;
				Let segA = the segment above segE2 in SL;
				Let segB = the segment below segE1 in SL;
				If (I = Intersect(segE2 with segA) exists)
				If (I is not in x already) Insert I into x;
				If (I = Intersect(segE1 with segB) exists)
				If (I is not in x already) Insert I into x;
				 */
			}
		}
		
		System.out.println("verbleibende Gr??????e der Sweepline "+sweepLine.size());
		
		if((strecken.size()*2 + schnittPunkte.size()) == eventPunkte.size()){
			System.out.println("Alle Punkte abgearbeitet!");
		}else{
			System.out.println("Fehler");
		}
		
		return schnittPunkte;
	}
}
