import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import common.Point;

public class MainAufgabe1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		String pathZuStrecken = "";
		String s1000 = "aufg01\\strecken\\s_1000_1.dat";
		String s10000 = "aufg01\\strecken\\s_10000_1.dat";
		String s100000 = "aufg01\\strecken\\s_100000_1.dat";

		ArrayList<Strecke> strecken = einlesen(pathZuStrecken + s1000);

		int streckencount = 0;
		
		long starttime = System.nanoTime();

		while(strecken.size() > 1) {
			for(int i = 1; i < strecken.size(); i++) {
				if (strecken.get(0).doIntersect(strecken.get(i))) {
					streckencount += 1;
				}
			}
			strecken.remove(0);
		}
		long endtime = System.nanoTime() - starttime;
		
		System.out.println("Anzahl an Schnittpunkten: "+ streckencount + "\n" 
		+  "Aufgewendete Zeit: " + TimeUnit.MILLISECONDS.convert(endtime, TimeUnit.NANOSECONDS) + " ms");		
		
	}

	public static ArrayList<Strecke> einlesen(String path) throws FileNotFoundException{
		ArrayList<Strecke> strecken = new ArrayList<Strecke>();
		
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
		   String line = scnr.nextLine();
		   String[] points = line.split(" ");
		   Strecke s = new Strecke(
			   new Point(Double.parseDouble(points[0]), Double.parseDouble(points[1])),
			   new Point(Double.parseDouble(points[2]), Double.parseDouble(points[3])));
		   strecken.add(s);
		}
		scnr.close();
		return strecken;
	}
	
}
