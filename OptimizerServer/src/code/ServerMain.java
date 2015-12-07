package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

import structures.Lineup;
import structures.Player;

public class ServerMain {

	public static void main(String[] args) {
		try {
			ServerSocket s = new ServerSocket(1234);
			Socket so = s.accept();
			System.out.println("Connection made");
			BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			PrintWriter out = new PrintWriter(so.getOutputStream(), false);
			String message = in.readLine();
			
			if(message.equals("NBA")){
				Lineup lineup = Utils.createLineup(Utils.readCSV("CSV/DraftKings/NBADKSalaries.csv"),"NBA");
				ArrayList<Player> players = lineup.printLineup();
				for(Player p : players){
					out.print(p.getName()+" ");
				}
				out.flush();
			}
			
			else if(message.equals("NFL")){
				Lineup lineup = Utils.createLineup(Utils.readCSV("CSV/DraftKings/NFLDKSalaries.csv"),"NFL");
				ArrayList<Player> players = lineup.printLineup();
				for(Player p : players){
					out.print(p.getName()+" ");
				}
				out.flush();
			}
			
			else{
				System.out.println("Unintelligible message from the client: " +message);
				out.println("error");
			}
			s.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
