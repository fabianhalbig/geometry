import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainAufgabe1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		String path = "";

		ArrayList<Strecke> strecken1 = einlesen(path + "\\s_1000_1.dat");
		ArrayList<Strecke> strecken2 = einlesen(path + "\\s_10000_1.dat");
		ArrayList<Strecke> strecken3 = einlesen(path + "\\s_100000_1.dat");
		
		int streckencount = 0;
		
		long starttime = System.nanoTime();

		while(strecken1.size() > 1) {
			
			for(int i = 1; i < strecken1.size(); i++) {
				
				if(strecken1.get(0).ccw(strecken1.get(i))) {
					streckencount += 1;
				}
			}
			strecken1.remove(0);
			
		}
		
		long endtime = System.nanoTime() - starttime;
		
		System.out.println("Anzahl sich schneidender Geraden "+ streckencount+" in einer Zeit von " + TimeUnit.MILLISECONDS.convert(endtime, TimeUnit.NANOSECONDS) + " Millisekunden");
		
		
	}

	public static ArrayList<Strecke> einlesen(String path) throws FileNotFoundException{
		ArrayList<Strecke> strecken = new ArrayList<Strecke>();
		
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
		   String line = scnr.nextLine();
		   String[] points = line.split(" ");
		   Strecke s = new Strecke(
				   Double.parseDouble(points[0]),
				   Double.parseDouble(points[1]),
				   Double.parseDouble(points[2]),
				   Double.parseDouble(points[3]));
		   strecken.add(s);
		}
		scnr.close();
		return strecken;
	}
	
}
