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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main2 {

    public static void main(String[] args) throws IOException {

    	String path = "C:\\Users\\Fabian\\Desktop\\Master\\Geometry\\02\\data\\DeutschlandMitStaedten.svg";
		
		ArrayList<Bundesland> bundeslaender = readFile(path);
		ArrayList<City> cities = getCities(path);

		//Get Area of state
		//!letztes element von bundeslaender und letztes polygon pro bundesland doppelt!
		for (Bundesland b:bundeslaender) {
			System.out.println("Name :" + b.getName());
			System.out.println("Anzahl Polygone :" + b.getPolygons().size());
			int i = 0;
			for(Polygon p: b.getPolygons()) {
				System.out.println("Size Polygon" + i+ ": " + p.getArea());
				i++;
			}
		}

		System.out.println("Size cities: " + cities.size());
		//Map cities to state -> mit while und zugewiesene stadt entfernen? 
		//nicht alle gefunden, außer München und Magdeburg ==> Bayern und Sachsen-Anhalt korrekt eingelesen
		Map <City, Bundesland> citeInstate = new HashMap<City, Bundesland>();
		for (City c: cities) {
			for(Bundesland b:bundeslaender) {
				if (b.bundeslandContainsCitey(c.getPoint())) {
					citeInstate.put(c, b);
					System.out.println("City: " + c.getName() + " | " + "State: " + b.getName());
				}
			}
		}

		Point berlin = new Point(477, 256);

		for (Point p:bundeslaender.get(13).getPolygons().get(1).getPoints()) {
			System.out.println(p.getX() + "," + p.getY());

		}


    	/*
		for (Bundesland n : bundeslaender){
			System.out.println(n.getName());
			System.out.println(n.getPoints());
		}
		*/
    	//System.out.println(bundeslaender.get(8).name);
    	//System.out.println(bundeslaender.get(8).polygons);
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(0).getX());
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(0).y);
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(bundeslaender.get(8).polygons.get(0).points.size()-1).getX());
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(bundeslaender.get(8).polygons.get(0).points.size()-1).y);
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(bundeslaender.get(8).polygons.get(0).points.size()-2).getX());
    	//System.out.println(bundeslaender.get(8).polygons.get(0).points.get(bundeslaender.get(8).polygons.get(0).points.size()-2).y);

		





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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fileString = fileString.substring(fileString.indexOf("<g>") );
		fileString = fileString.substring(0, fileString.indexOf("</g>"));
		bundeslandElements = fileString.split("\"/>");
		

		
		for (String bElements : bundeslandElements){
			if(bElements.contains("id=")){
				bundeslandName = bElements.substring(bElements.indexOf("id="));
				bundeslandName = bundeslandName.substring(0, bundeslandName.indexOf("fill"));
				bundeslandName = bundeslandName.split("\"")[1];
				//System.out.println(bundeslandName);
				
				polygons = bElements.split("\nz");
			}
			
			
			
			for (String poly : polygons){
				iteration +=1 ;
				if(poly.length()>3){
					
					if(poly.contains(" d=\"")){
						points = poly.substring(poly.indexOf(" d=\"")).split("\"")[1].split("\n");
					} else{
						points = poly.split("\n");

					}

				}
				
				if(points[0].isEmpty()){
					List<String> list = new ArrayList<String>(Arrays.asList(points));
					
					list.remove("");
					
					points = list.toArray(new String[0]);
				}
				
				/*
				Point startPunkt = pointsForPolygonClone.get(0);
				pointsForPolygonClone.remove(0);
				
				Point endPunkt = pointsForPolygonClone.get(pointsForPolygonClone.size()-1);
				pointsForPolygonClone.remove(pointsForPolygonClone.size()-1);
				
				Point standPunkt = startPunkt;
				
				for (Point point : pointsForPolygonClone) {
					
					double newX = standPunkt.getX() + point.getX();
					double newY = standPunkt.getY() + point.getY();
					
					standPunkt.setX(newX);
					standPunkt.setX(newY);
					
					System.out.println(point.x);
				}
				*/
				
				Point startPunkt = null;
				Point standPunkt = null;
				
				for (String point : points){
					if(!point.trim().isEmpty()){
						if(point.startsWith("M")) {
							startPunkt = new Point(Double.parseDouble(point.substring(1).split(",")[0]) , Double.parseDouble(point.substring(1).split(",")[1]));
							//System.out.println(bundeslandName);
							//System.out.println(startPunkt.x);
							pointsForPolygon.add(startPunkt);
							standPunkt = new Point(startPunkt.getX(), startPunkt.getY()); // startPunkt;
						}
						else {
							
							if(point.contains("H")) {
								point= point.split("H")[0];
								//System.out.println(point);
							}
							
							if(point.startsWith("L")) {
								Point newPoint = new Point(Double.parseDouble(point.substring(1).split(",")[0]) , Double.parseDouble(point.substring(1).split(",")[1]));
								pointsForPolygon.add(newPoint);

							} else {
								Point newPoint = new Point(Double.parseDouble(point.substring(1).split(",")[0]) , Double.parseDouble(point.substring(1).split(",")[1]));
								
								double newX = standPunkt.getX() + newPoint.getX();
								double newY = standPunkt.getY() + newPoint.getY();
								
								standPunkt.setX(round(newX,3));
								standPunkt.setY(round(newY,3));
								
								pointsForPolygon.add(standPunkt);
								
								/*
								if(bundeslandName.contains("Mecklenburg-Vorpommern") && pointsForPolygon.size() < 3) {
									System.out.println("startpunkt"+startPunkt.x);
									System.out.println(newX);
									System.out.println(newY);
									System.out.println(newPoint.x);
									System.out.println(newPoint.y);
								}
								*/
							}
						}
						
					}
				}
				
				//System.out.println(pointsForPolygon.get(1).getX());
				ArrayList<Point> pointsForPolygonClone = (ArrayList<Point>) pointsForPolygon.clone();
				
				newPolygon = new Polygon(pointsForPolygonClone);		
				pointsForPolygon.clear();
				polygonForBundesland.add(newPolygon);
				//System.out.println(newPolygon.getPoints());
			}
			
			ArrayList<Polygon> polygonForBundeslandClone = (ArrayList<Polygon>) polygonForBundesland.clone();
			states.add(new Bundesland(bundeslandName, polygonForBundeslandClone));
			polygonForBundesland.clear();
		}
		
		return states;
	}
    
	static String readFile(String path, Charset encoding)
			  throws IOException
			{
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