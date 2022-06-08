import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import common.Point;



public class Main2 {

    public static void main(String[] args) throws IOException {

    	String path = "aufg02\\data\\DeutschlandMitStaedten.svg";

		
		ArrayList<Bundesland> states = readFile(path);
		
		ArrayList<City> cities = getCities(path);
		sysoutAreaOfState(states);
		System.out.println("----------------------------");
		Collections.shuffle(cities); //make sure output does not depend on order of input
		sysoutCityAndState(states, cities);

	}





	public static void sysoutAreaOfState(ArrayList<Bundesland> states) {
		for (Bundesland s: states) {
			System.out.println(s.getName() + "|" + s.getAreaOfState());
		}
	}

	//Map cities on smallest state (smaller polygons are overwring first value of bigger polygons)
	public static void sysoutCityAndState(ArrayList<Bundesland> states, ArrayList<City> cities) {
		Map <City, Bundesland> cityInState = new HashMap<City, Bundesland>();
		while(cities.size() > 0 ) {
			for(Bundesland b:states) {
				if (b.bundeslandContainsCity(cities.get(0).getPoint())) {
					if (cityInState.get(cities.get(0)) != null &&
					 cityInState.get(cities.get(0)).getAreaOfState() > b.getAreaOfState()) {
						cityInState.put(cities.get(0), b);
					}  
					if (cityInState.put(cities.get(0), b) == null) {
						cityInState.put(cities.get(0), b);
					}
				}
			}
			cities.remove(0);
		}

		for (Map.Entry<City, Bundesland> k:cityInState.entrySet()) {
			System.out.println("Hauptstadt: " +  k.getKey().name + " | " + "Bundesland: "+  k.getValue().getName());
		}
	}
    
    public static ArrayList<Bundesland> readFile(String path) throws FileNotFoundException{
		ArrayList<Bundesland> states = new ArrayList<Bundesland>();
		ArrayList<Point> pointsForPolygon = new ArrayList<Point>();
		ArrayList<Polygon> polygonForBundesland = new ArrayList<Polygon>();
		Polygon newPolygon = null;
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		String[] bundeslandElements = null;
		String[] polygons = null;
		String[] points = null;
		String bundeslandName ="";
		String fileString = "";
		int iteration = 0;
		
		try {
			fileString = readFile(path,StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fileString = fileString.substring(fileString.indexOf("<g>") );
		fileString = fileString.substring(0, fileString.indexOf("</g>"));
		bundeslandElements = fileString.split("\"/>");
		bundeslandElements = Arrays.copyOf(bundeslandElements, bundeslandElements.length-1);
		
		for (String bElements : bundeslandElements){
			if(bElements.contains("id=")){
				bundeslandName = bElements.substring(bElements.indexOf("id="));
				bundeslandName = bundeslandName.substring(0, bundeslandName.indexOf("fill"));
				bundeslandName = bundeslandName.split("\"")[1];
				polygons = bElements.split("\nz");
				polygons = Arrays.copyOf(polygons, polygons.length-1);
			}
			for (String poly : polygons){
				iteration +=1 ;
				if(poly.length()>3){
					if(poly.contains(" d=\"")){
						points = poly.substring(poly.indexOf(" d=\""))
						.split("\"")[1].split("\n");
					} else{
						points = poly.split("\n");
					}
				}
				if(points[0].isEmpty()){
					List<String> list = new ArrayList<String>(Arrays.asList(points));
					list.remove("");
					points = list.toArray(new String[0]);
				}
				Point startPunkt = null;
				Point standPunkt = null;
				for (String point : points){
					if(!point.trim().isEmpty()){
						if(point.startsWith("M")) {
							startPunkt = new Point(Double.parseDouble(point.substring(1)
							.split(",")[0]) , Double.parseDouble(point.substring(1)
							.split(",")[1]));
							pointsForPolygon.add(startPunkt);
							standPunkt = new Point(startPunkt.getX(), startPunkt.getY()); 
						}
						else {
							if(point.contains("H")) {
								point= point.split("H")[0];
							}
							if(point.startsWith("L")) {
								Point newPoint = new Point(Double.parseDouble(point.substring(1)
								.split(",")[0]) , Double.parseDouble(point.substring(1)
								.split(",")[1]));
								pointsForPolygon.add(newPoint);
								standPunkt.setX(newPoint.getX());
								standPunkt.setY(newPoint.getY());
							} else {
								Point newPoint = new Point(Double.parseDouble(point.substring(1)
								.split(",")[0]) , Double.parseDouble(point.substring(1)
								.split(",")[1]));
								double newX = standPunkt.getX() + newPoint.getX();
								double newY = standPunkt.getY() + newPoint.getY();								
								standPunkt.setX(round(newX,3));
								standPunkt.setY(round(newY,3));								
								Point clonePoint = new Point(standPunkt.getX(), standPunkt.getY());
								pointsForPolygon.add(clonePoint);
							}
						}	
					}
				}
				ArrayList<Point> pointsForPolygonClone = (ArrayList<Point>) pointsForPolygon.clone();
				newPolygon = new Polygon(pointsForPolygonClone);		
				polygonForBundesland.add(newPolygon);
				pointsForPolygon.clear();
			}
			ArrayList<Polygon> polygonForBundeslandClone = (ArrayList<Polygon>) polygonForBundesland.clone();
			states.add(new Bundesland(bundeslandName, polygonForBundeslandClone));
			polygonForBundesland.clear();
		}
		scnr.close();
		return states;
	}
    
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
	  	return new String(encoded, encoding);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public static ArrayList<City> getCities(String path) throws FileNotFoundException {
		ArrayList<City> result = new ArrayList<City>();

		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
			String line = scnr.nextLine();
		   	if (line.startsWith("   id=")) {
				   result.add(new City(
					line.split("\"")[1],
					new Point(Double.parseDouble(scnr.nextLine().split("\"")[1]),
						Double.parseDouble(scnr.nextLine().split("\"")[1]))));
			   }
		}
		scnr.close();
		return result;
	}
}
