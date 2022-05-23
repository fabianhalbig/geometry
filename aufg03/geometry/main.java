package geometry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//String pathZuStrecken = "C:\\Users\\Cornelius\\Desktop\\Master Informatik Notebooks\\Computational_Geometry";
		String pathZuStrecken = "C:\\Users\\Cornelius\\Desktop\\HM Master\\Computational_Geometry";

		String s1000 = "\\s_1000_10.dat";

		ArrayList<Strecke> strecken = einlesen(pathZuStrecken + s1000);
		
		/*
		//Sortieren des x-Wertes des Anfangpunktes 
		Collections.sort(strecken, new Comparator<Strecke>() {
			@Override
			public int compare(Strecke strecke1, Strecke strecke2) {
				// TODO Auto-generated method stub
				return Double.compare(strecke1.start.x, strecke2.start.x);
			}
		});
		*/

		// Alle Start und Endpunkt als EVents hinzufügen und sortieren
		ArrayList<Punkt> eventPunkte = new ArrayList<Punkt>();
		for(Strecke strecke : strecken){
			eventPunkte.add(strecke.start);
			eventPunkte.add(strecke.end);
		}
		
		Collections.sort(eventPunkte, new Comparator<Punkt>() {
			@Override
			public int compare(Punkt p1, Punkt p2) {
				return Double.compare(p1.x, p2.x);
			}
		});
		
		ArrayList<Strecke> sweepLine = new ArrayList<Strecke>();
		ArrayList<Punkt> schnittPunkte = new ArrayList<Punkt>();
		
		for(Punkt events:eventPunkte){
			if(events.punkTyp == "start"){
				Strecke segment = findByStart(strecken, events);
				sweepLine.add(segment);
				
				//Neu Sortieren mit Betrachtung an x
				Collections.sort(sweepLine, new Comparator<Strecke>() {
					double x = segment.start.x;
					@Override
					public int compare(Strecke o1, Strecke o2) {
						// TODO Auto-generated method stub
						return Double.compare(o1.yFuerX(x), o2.yFuerX(x));
					}
				});
				
				if(sweepLine.size() < 1) {
					if(sweepLine.size() < 2 && sweepLine.indexOf(segment) != 0 && sweepLine.indexOf(segment) != sweepLine.size()-1) {
						Strecke segA = sweepLine.get(sweepLine.indexOf(segment)-1);
						Strecke segB = sweepLine.get(sweepLine.indexOf(segment)+1);
					}
				}
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
			}
			if(events.punkTyp == "end"){
							/*
							TreatRightEndpoint() {
							Let segE = E's segment;
							Let segA = the segment above segE in SL;
							Let segB = the segment below segE in SL;
							Remove segE from SL;
							If (I = Intersect( segA with segB) exists)
							If (I is not in x already) Insert I into x;
}
							 */
						}
			if(events.punkTyp == "schnitt"){
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

		long starttime = System.nanoTime();
		long endtime = System.nanoTime() - starttime;
		
		System.out.println("Aufgewendete Zeit: " + TimeUnit.MILLISECONDS.convert(endtime, TimeUnit.NANOSECONDS) + " ms");	
	}

	public static ArrayList<Strecke> einlesen(String path) throws FileNotFoundException{
		ArrayList<Strecke> strecken = new ArrayList<Strecke>();
		
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
		   String line = scnr.nextLine();
		   String[] points = line.split(" ");
		   Punkt start = new Punkt(Double.parseDouble(points[0]),Double.parseDouble(points[1]), "start");
		   Punkt end = new Punkt(Double.parseDouble(points[2]),Double.parseDouble(points[3]), "end");
		   Strecke s = new Strecke(start, end);
		   strecken.add(s);
		}
		scnr.close();
		return strecken;
	}
	
	public static Strecke findByStart(ArrayList<Strecke> listStrecke, Punkt punkt) {
	    return listStrecke.stream().filter(strecke -> punkt.equals(strecke.start)).findFirst().orElse(null);
	}
	
	public static Strecke findByEnd(ArrayList<Strecke> listStrecke, Punkt punkt) {
	    return listStrecke.stream().filter(strecke -> punkt.equals(strecke.end)).findFirst().orElse(null);
	}
}
