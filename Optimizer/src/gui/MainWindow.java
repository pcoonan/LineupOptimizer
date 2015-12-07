package gui;

import java.awt.Color;
//import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.SwingConstants;

import code.Controller;
import listeners.OpenListener;
import listeners.RestartListener;
import listeners.SportListener;
import structures.Lineup;
import structures.Player;

public class MainWindow implements Runnable {

	private JFrame _title;
	private String _sport;
	private JMenuItem _restart;
	private JMenuItem _scraper;

	//Creates the JFrame and adds the menu bar
	public void run() {
		_title = new JFrame("Lineup Optimizer 2015");
		_title.setSize(700,300);
		_title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_title.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem restart = new JMenuItem("New Lineup");
//		JMenuItem scraper = new JMenuItem("Grab data from a website");
		menuBar.add(file);
		file.add(restart);
//		file.add(scraper);
		_title.setJMenuBar(menuBar);
		restart.addActionListener(new RestartListener(this));
		_restart = restart;
//		_scraper = scraper;

		this.chooseSport();
	}
	
	public void setSport(String str){	//I'm not sure if we need to select different sports but this could be a way of keeping track if need be
		_sport = str;
	}
	
	//sets window to the initial choose sport menu
	public void chooseSport(){
		_title.getContentPane().removeAll();
		_title.setSize(700,300);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#1a621c")); //Green
		panel.setLayout(new GridLayout(0, 1));
		_title.add(panel);
		
		JLabel label = new JLabel("DAILY FANTASY SPORTS LINEUP OPTIMIZER", SwingConstants.CENTER);
		label.setForeground(Color.decode("#ffffff")); //White
		label.setFont(new Font("Calibri", Font.PLAIN, 30));
		panel.add(label);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.decode("#efefe3")); //Dark Cream
		panel2.setLayout(new GridLayout(0, 3));
		panel.add(panel2);
		
		JLabel label2 = new JLabel("Select a Sport:", SwingConstants.CENTER);
		label2.setFont(new Font("Calibri", Font.PLAIN, 25)); 
		label2.setHorizontalTextPosition(panel.getWidth()/2);
		label2.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb"))); //Medium Cream
		panel2.add(label2);	
		
		JPanel bp1 = new JPanel(new GridBagLayout());
		JPanel bp2 = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		bp1.setBackground(Color.decode("#efefe3")); //Dark Cream
		bp2.setBackground(Color.decode("#efefe3")); 
		bp1.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb"))); //Medium Cream
		bp2.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
		panel2.add(bp1);
		panel2.add(bp2);
		
		JButton b1 = new JButton("NBA");
		JButton b2 = new JButton("NFL");
		bp1.add(b1);
		bp2.add(b2);
		
		b1.addActionListener(new SportListener(this, "NBA"));
		b2.addActionListener(new SportListener(this, "NFL"));
		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
	}
	
	//Window with a button that opens the file browser.
	public void fileBrowser(){
		_title.getContentPane().removeAll();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#1a621c")); //Green
		_title.add(panel);
		panel.setLayout(new GridLayout(0, 1));
		
		JLabel selectFile = new JLabel("Please select a CSV file to process.", SwingConstants.CENTER);
		selectFile.setFont(new Font("Calibri", Font.PLAIN, 25));
		selectFile.setForeground(Color.decode("#ffffff")); //White
		panel.add(selectFile);
		
		JButton b = new JButton("Open CSV");
		JPanel miniPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		miniPanel.setBackground(Color.decode("#efefe3")); 
		miniPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb"))); //Medium Cream
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
		_title.setSize(700,300);
		Controller.load(file);
		Controller.setLineup(_sport);
		Lineup lineup = Controller.getLineup(); //Utils.createLineup(Utils.readCSV(file),_sport, new HashMap<Player, Boolean>(), new HashMap<Player, Boolean>());
		ArrayList<Player> list = lineup.printLineup();
		JPanel panel = new JPanel();
		_title.add(panel);
		panel.setLayout(new GridLayout(0,5));
		JLabel j1 = new JLabel("  Name");
		JLabel j2 = new JLabel("  Position");
		JLabel j3 = new JLabel("  Salary");
		JLabel j4 = new JLabel("  Points Per Game");
		JLabel j5 = new JLabel("  Projected Points");
		j1.setFont(new Font("Default", Font.BOLD, 14));
		j2.setFont(new Font("Default", Font.BOLD, 14));
		j3.setFont(new Font("Default", Font.BOLD, 14));
		j4.setFont(new Font("Default", Font.BOLD, 14));
		j5.setFont(new Font("Default", Font.BOLD, 14));
		j1.setOpaque(true);
		j2.setOpaque(true);
		j3.setOpaque(true);
		j4.setOpaque(true);
		j5.setOpaque(true);
		j1.setForeground(Color.decode("#ffffff")); //White
		j2.setForeground(Color.decode("#ffffff"));
		j3.setForeground(Color.decode("#ffffff"));
		j4.setForeground(Color.decode("#ffffff"));
		j5.setForeground(Color.decode("#ffffff"));
		j1.setBackground(Color.decode("#1a621c")); //Green
		j2.setBackground(Color.decode("#1a621c"));
		j3.setBackground(Color.decode("#1a621c"));
		j4.setBackground(Color.decode("#1a621c"));
		j5.setBackground(Color.decode("#1a621c"));
		panel.add(j1);
		panel.add(j2);
		panel.add(j3);
		panel.add(j4);
		panel.add(j5);
		int count = -1;
		for(Player p: list){
			count++;
			JLabel jl1 = new JLabel("  " + p.getName());
			JLabel jl2;
//			if (count == 7){  //TODO un-hard-code this when projection update is implemented
//				jl2 = new JLabel("  FLEX"); 
//			}
//			else {
				jl2 = new JLabel("  " + p.getPosition());
//			}
			JLabel jl3 = new JLabel("  $"+(int) p.getSalary());
			JLabel jl4 = new JLabel("  " + (Math.round(p.getPPG() * 100.0) / 100.0));
			JLabel jl5 = new JLabel("  " + (Math.round(p.getProjection() * 100.0) / 100.0));
			jl1.setOpaque(true);
			jl2.setOpaque(true);
			jl3.setOpaque(true);
			jl4.setOpaque(true);
			jl5.setOpaque(true);
			if(count % 2 == 0){
				jl1.setBackground(Color.decode("#efefe3")); //Dark Cream
				jl2.setBackground(Color.decode("#efefe3"));
				jl3.setBackground(Color.decode("#efefe3"));
				jl4.setBackground(Color.decode("#efefe3"));
				jl5.setBackground(Color.decode("#efefe3"));
			}
			else{
				jl1.setBackground(Color.decode("#f6f7ef")); //Light Cream
				jl2.setBackground(Color.decode("#f6f7ef"));
				jl3.setBackground(Color.decode("#f6f7ef"));
				jl4.setBackground(Color.decode("#f6f7ef"));
				jl5.setBackground(Color.decode("#f6f7ef"));
			}
			jl1.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb"))); //Medium Cream
			jl2.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
			jl3.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
			jl4.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
			jl5.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
			panel.add(jl1);
			panel.add(jl2);
			panel.add(jl3);
			panel.add(jl4);
			panel.add(jl5);
			if (count == list.size() - 1){
				JLabel jlt1 = new JLabel("  TOTAL");
				JLabel jlt2 = new JLabel("  -");
				JLabel jlt3 = new JLabel("  $" + (int)(50000.0 - lineup.getSalary()) + "/$50000");
				JLabel jlt4 = new JLabel("  "+ Math.round(lineup.getScore() * 100.0) / 100.0);
				JLabel jlt5 = new JLabel("  "+ Math.round(lineup.getProjection() * 100.0) / 100.0);
				jlt1.setOpaque(true);
				jlt2.setOpaque(true);
				jlt3.setOpaque(true);
				jlt4.setOpaque(true);
				jlt5.setOpaque(true);
			    jlt1.setBackground(Color.decode("#5db062")); //Dark Cream
			    jlt2.setBackground(Color.decode("#5db062"));
			    jlt3.setBackground(Color.decode("#5db062"));
			    jlt4.setBackground(Color.decode("#5db062"));
			    jlt5.setBackground(Color.decode("#5db062"));
				jlt1.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb"))); //Medium Cream
				jlt2.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
				jlt3.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
				jlt4.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
				jlt5.setBorder(BorderFactory.createLineBorder(Color.decode("#e2e1cb")));
				panel.add(jlt1);
				panel.add(jlt2);
				panel.add(jlt3);
				panel.add(jlt4);
				panel.add(jlt5);
			}
		}

		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
	}
}
