package code;
import java.util.ArrayList;

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
		return true;
	}
	
	/** Make sure to call checkCSVFile before calling this, and only call this if checkCSVFile returns true.
	 * Also, you might want to make sure that the size of the returned ArrayList was what you were expecting.
	 * I.e. if there are 700 lines in the .csv but the size of the ArrayList is like 14... oops.
	 * 
	 * @param path - a path to the .csv file
	 * @return an ArrayList of Player objects, one for each line in the file.
	 */
	public static ArrayList<Player> readCSVFile(String path){
		return null;
	}

}
