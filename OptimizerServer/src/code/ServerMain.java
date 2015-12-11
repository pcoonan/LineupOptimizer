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
		Controller control = new Controller();
		boolean loaded = false;
		try {
			ServerSocket s = new ServerSocket(1234);
			Socket so = s.accept();
			System.out.println("Connection made");
			BufferedReader in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			PrintWriter out = new PrintWriter(so.getOutputStream(), false);
			
			String message;
			while ((message = in.readLine()) != null) {
				if (message.equals("NBA")) {
					if(!loaded) {
						control.load("CSV/DraftKings/NBADKSalaries.csv");
						loaded = true;
					}
					control.setLineup("NBA");
					Lineup lineup = control.getLineup();
					ArrayList<Player> players = lineup.printLineup();
					for (Player p : players) {
						out.println(p.toString());
						out.flush();
					}
				} else if (message.equals("NFL")) {
					if(!loaded) {
						control.load("CSV/DraftKings/DKSalariesWeek14SM.csv");
						loaded = true;
					}
					control.setLineup("NFL");
					Lineup lineup = control.getLineup();
					ArrayList<Player> players = lineup.printLineup();
					for (Player p : players) {
						out.println(p.toString());
						out.flush();
					}
					out.println("end");
					out.flush();
				} else if (message.equals("players")) {
					ArrayList<Player> players = control.getPlayers();
					for (Player p : players) {
						out.println(p.toString());
						out.flush();
					}
					out.println("end");
					out.flush();
				} else if (message.startsWith("include")) {
					Player p = Utils.reconstructPlayer(message.replaceAll("include ", ""));
					control.include(p);
				} else if (message.startsWith("exclude")) {
					Player p = Utils.reconstructPlayer(message.replaceAll("exclude ", ""));
					control.exclude(p);
				} else if (message.equals("exit")) {
					break;
				} else {
					System.out.println("Unintelligible message from the client: " + message);
					out.println("error");
				}
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
