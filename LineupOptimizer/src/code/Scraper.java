import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// The scraper that will collect the data for our projections.
// Maintained by Mike Kostek.

public class Scraper {

    public static void main(String[] args) {
    	GetTable("QB");  
//    	GetTable("RB"); 
//    	GetTable("WR");  
//    	GetTable("TE");  
//    	GetTable("DST"); 
    }

    public static String[][] GetTable(String position){
        Document doc;
        String[][] playerInfo = new String[100][100];
        Boolean startDataEntry = false;
        int row = -1;
        int col = 0;
        
        try {
        	switch (position){
        	case "QB":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=0").get();
        	case "RB":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=2").get();
        	case "WR":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=4").get();
        	case "TE":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=6").get();
        	case "DST":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=16").get();
            default:
            	doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=0").get();
        	}
              
            Element table = doc.getElementById("playertable_0");
            Elements tds = table.getElementsByTag("td");
            
            for (Element td : tds) {
                if(td.text().contains(position)){
                	startDataEntry = true;
                }
                
                if(startDataEntry){
                	if(td.text().contains(position)){
//                		System.out.println();
                		col = 0;
                		row++;
                    }
                	playerInfo[row][col] = td.text();
                	col++;
//                	System.out.print(td.text() + " ");
                }
            }
            
            //print the 2D array
            for(int i = 0; i < 38; i++){
            	for(int k = 0; k < 14; k++){
            		if(!playerInfo[i][k].isEmpty()){
            			System.out.print(playerInfo[i][k] + " ");
            		}
            	}
            	System.out.println();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerInfo;
    }
}
