import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main2 {

    public static void main(String[] args) throws IOException {
    	
		/*
		 * !!!!!!!!!!!!!!!!!!!!!!! Es gibt In Polygonen Koordinaten, die nicht relativ sind und nicht der Endpunkt bsp: L270.431,165!!!!!!!!!!! 
		 */

    	
		//readFile("");
    	ArrayList<Bundesland> bundeslaender = readFile("C:\\Users\\Cornelius\\Desktop\\Master Informatik Notebooks\\CompGeo\\DeutschlandMitStaedten.svg");
    	
		for (Bundesland n : bundeslaender){
			System.out.println(n.getName());
			System.out.println(n.getPoints());
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
				
				for (String point : points){
					if(!point.trim().isEmpty()){
						if(!point.contains("H")){
							Point newPoint = new Point(Double.parseDouble(point.substring(1).split(",")[0]) , Double.parseDouble(point.substring(1).split(",")[1]));
							pointsForPolygon.add(newPoint);
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
}
