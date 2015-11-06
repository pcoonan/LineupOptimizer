package code;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// The scraper that will collect the data for our projections.
// Maintained by Mike Kostek.

public class Scraper {

    public static void main(String[] args) {
//    	GetTable("QB");  
//    	GetTable("RB"); 
//    	GetTable("WR");  
    	GetTable("TE");  
//    	GetTable("D/ST"); 
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
        		break;
        	case "RB":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=2").get();
        		break;
        	case "WR":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=4").get();
        		break;
        	case "TE":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=6").get();
        		break;
        	case "D/ST":
        		doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=16").get();
        		break;
            default:
            	doc = Jsoup.connect("http://games.espn.go.com/ffl/tools/projections?slotCategoryId=0").get();
            	break;
        	}
              
            Element table = doc.getElementById("playertable_0");
            Elements tds = table.getElementsByTag("td");
//            position = " " + position + "\n";
            for (Element td : tds) {
//            	System.out.println(td.text());
                if(td.text().contains(position) && !td.text().contains("TEAM")){
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
//            for(int i = 0; i < playerInfo.length; i++){
//            	boolean exit = false;
//            	if(playerInfo[i][0] == null) break;
//            	for(int k = 0; k < playerInfo[i].length; k++){
//            		if(playerInfo[i][k] != null){
//            			System.out.print(playerInfo[i][k] + " ");
//            		}
//            		else{
//            			break;
//            		}
//            	}
//            	if(exit) break;
//            	System.out.println();
//            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerInfo;
    }
}
