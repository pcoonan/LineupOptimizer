package code;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import structures.Lineup;
import structures.Player;

public class Server {
	
	private String url; //http url to the server
	private Socket server;
	
	public void startServer(){
		try{
			server = new Socket(url, 1234);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void connectToServer(){

	}
	
	/**
	 * sets the http url of the server. This does NOT check if the input is valid
	 * @param s
	 */
	public void setServerPath(String s){
		url = s;
	}
	
	/**
	 * returns the http url of the server
	 * @return
	 */
	public String getServerPath(){
		return url;
	}
	
	/***
	 * checks if the server is up and running
	 * @return
	 */
	public boolean checkServer(){
		return server != null;
	}
	
	
	/***
	 * tell the server to clear out its database
	 * @return
	 */
	public boolean clearServer(){
		return true;
	}
	
	/***
	 * serializes the input and pushes it to the server
	 * @param p
	 * @return
	 */
	public boolean addToServer(Player p){
		String output = "include " + p.toString();
		try {
			PrintWriter out = new PrintWriter(server.getOutputStream());
			out.println(output);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	/***
	 * serializes the input and removes it from the server
	 * @param p
	 * @return
	 */
	public boolean removeFromServer(Player p){
		String output = "exclude " + p.toString();
		try {
			PrintWriter out = new PrintWriter(server.getOutputStream());
			out.println(output);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * the server computes the algorithm based on its current database and returns the best-possible list of players
	 * this method de-serializes the server's output and returns it as an ArrayList of Players
	 * @return
	 */
	public Lineup getLineupFromServer(){
		return null;
	}
	
}
