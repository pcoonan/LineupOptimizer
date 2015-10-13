package code;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.opencsv.CSVReader;

// A parser to collect the data from .csv files and create the appropriate data structures.
// Maintained by Yousuf Zubairi

public class Parser {

	/** Pretty self-explanatory. You should take the path from the user, and run it through this which will check
	 * if it's a proper .csv or not. If it isn't, there's a problem obviously.
	 * 
	 * @param path - a path to the .csv file
	 * @return whether or not the file exists and is a properly-formatted .csv file
	 */

	public static boolean checkCSVFile(String path){
		File f = new File(path);
		
		return f.isFile() && path.contains(".csv");
	}

	/** Make sure to call checkCSVFile before calling this, and only call this if checkCSVFile returns true.
	 * Also, you might want to make sure that the size of the returned ArrayList was what you were expecting.
	 * I.e. if there are 700 lines in the .csv but the size of the ArrayList is like 14... oops.
	 * 
	 * @param path - a path to the .csv file
	 * @return an ArrayList of Player objects, one for each line in the file.
	 */
	public static ArrayList<Player> readCSVFile(String path){
		try{
			ArrayList<Player> players = new ArrayList<Player>();
			CSVReader reader = new CSVReader(new FileReader(path));
			String [] nextLine;
			int curLine = 0;
			while ((nextLine = reader.readNext()) != null) {
				String name = nextLine[0];
				String pos = nextLine[1];
				double ppg = Double.parseDouble(nextLine[2]);
				double salary = Double.parseDouble(nextLine[3]);
				Player p = new Player(name, pos, ppg, salary);
				players.set(curLine, p);
				curLine++;
			}

			return players;
		}
		catch (Exception e){
			return null;
		}
	}

}
