import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) throws FileNotFoundException {
		readFile("");
	}

	public static ArrayList<Bundesland> readFile(String path) throws FileNotFoundException{
		ArrayList<Bundesland> states = new ArrayList<Bundesland>();
		File file = new File(path);
		Scanner scnr = new Scanner(file);
		while(scnr.hasNextLine()){
		
		}

		return states;
	}
    
}
