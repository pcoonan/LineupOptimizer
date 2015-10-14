package gui;

//import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;

//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
//import javax.swing.JProgressBar;
//import javax.swing.border.Border;

//import code.Parser;
import code.Player;
import code.Utils;
import listeners.OpenListener;
import listeners.RestartListener;
import listeners.SportListener;

public class MainWindow implements Runnable {

	private JFrame _title;
	private int _sport;
	private JMenuItem _restart;
	private JMenuItem _scraper;

	
	//Creates the JFrame and adds the menu bar
	public void run() {
		_title = new JFrame("Lineup Optimizer 2015");
		_title.setSize(700,400);
		_title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_title.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem restart = new JMenuItem("Start a new a algorithm");
		JMenuItem scraper = new JMenuItem("Grab data from a website");
		menuBar.add(file);
		file.add(restart);
		file.add(scraper);
		_title.setJMenuBar(menuBar);
		restart.addActionListener(new RestartListener(this));
		_restart = restart;
		_scraper = scraper;

		
		this.chooseSport();

	}
	
	public void setSport(int i){	//I'm not sure if we need to select different sports but this could be a way of keeping track if need be
		_sport = i;
	}
	
	//sets window to the initial choose sport menu
	public void chooseSport(){
		_title.getContentPane().removeAll();
		_title.setSize(700,400);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		_title.add(panel);
		JLabel label = new JLabel("Welcome to getTeamName()'s Lineup Optimizer");
		label.setFont(new Font("Calibri", Font.PLAIN, 30)); 
		panel.add(label);
		JLabel label2 = new JLabel();
		label2.setText("Please select a sport that you'd like to make a team for:");
		label2.setFont(new Font("Calibri", Font.PLAIN, 25)); 
		panel.add(label2);	
		JPanel bp1 = new JPanel();
		JPanel bp2 = new JPanel();
		panel.add(bp1);
		panel.add(bp2);
		JButton b1 = new JButton("Basketball");
		JButton b2 = new JButton("Football");
		bp1.add(b1);
		bp2.add(b2);
		b1.addActionListener(new SportListener(this, 1));
		b2.addActionListener(new SportListener(this, 2));
		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
	}
	
	//Window with a button that opens the file browser.
	public void fileBrowser(){
		_title.getContentPane().removeAll();
		JPanel panel = new JPanel();
		_title.add(panel);
		panel.setLayout(new GridLayout(0, 1));
		JLabel selectFile = new JLabel("Please select a CSV file to process.");
		panel.add(selectFile);
		JButton b = new JButton("Open CSV");
		JPanel miniPanel = new JPanel();
		panel.add(miniPanel);
		miniPanel.add(b);
		b.addActionListener(new OpenListener(this));
		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
	}

	//This is where the algorithm will be executed.
//	public void startAlgo(String file){
//		_title.getContentPane().removeAll();
//		JPanel panel = new JPanel();
//		_title.add(panel);
//		panel.setLayout(new GridLayout(0,1));
//		JLabel l1 = new JLabel("You are running the algorithm on " + file + " Please wait...");
//		JLabel l2 = new JLabel("Just kidding, it's not implemented yet");
//		panel.add(l1);
//		panel.add(l2);
//		
//		if(_sport == 1){
//			//execute Basketball Algorithm
//		}
//		
//		if(_sport == 2){
//			//execute Football Algorithm
//		}
//		
//
//		//Make a progress bar
//		JPanel jp = new JPanel();
//		JProgressBar progressBar = new JProgressBar();
//		progressBar.setValue(0);
//		progressBar.setStringPainted(true);
//	    Border border = BorderFactory.createTitledBorder("Processing...");
//	    progressBar.setBorder(border);
//	    jp.add(progressBar);
//	    panel.add(jp);
//	
//	    
//	    //Ticks the progress bar up every .25 seconds (just a test)
//	    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//	    executor.scheduleAtFixedRate(new PBarTest(progressBar), 0, 250, TimeUnit.MILLISECONDS);
//
//	    
//	    //Set cursor to a waiting cursor
//		_title.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//		
//		//Disable the menu items
//		_restart.setEnabled(false);
//		_scraper.setEnabled(false);
//		
//		_title.getContentPane().revalidate();
//		_title.getContentPane().repaint();
//		
//	}
	
	public void startAlgo(String file){
		_title.getContentPane().removeAll();
		_title.setSize(700,700);
		ArrayList<Player> list = Utils.createLineup(Utils.readCSV(file));
		JPanel panel = new JPanel();
		_title.add(panel);
		panel.setLayout(new GridLayout(0,4));
		JLabel j1 = new JLabel("Name:");
		JLabel j2 = new JLabel("Position:");
		JLabel j3 = new JLabel("Salary:");
		JLabel j4 = new JLabel("Points per game:");
		j1.setFont(new Font("Default", Font.PLAIN, 20));
		j2.setFont(new Font("Default", Font.PLAIN, 20));
		j3.setFont(new Font("Default", Font.PLAIN, 20));
		j4.setFont(new Font("Default", Font.PLAIN, 20));
		panel.add(j1);
		panel.add(j2);
		panel.add(j3);
		panel.add(j4);
		for(Player p: list){
			JLabel jl1 = new JLabel(p.name);
			JLabel jl3 = new JLabel(""+p.sal);
			JLabel jl2 = new JLabel(p.pos);
			JLabel jl4 = new JLabel(p.ppg+"");
			panel.add(jl1);
			panel.add(jl2);
			panel.add(jl3);
			panel.add(jl4);
		}
		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
	}
}
