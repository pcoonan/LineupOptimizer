package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.OpenListener;
import listeners.SportListener;

public class MainWindow implements Runnable {

	private JFrame _title;
	private int _sport;

	
	//Creates the JFrame and adds buttons to select what sport you want to run the algorithm on.
	public void run() {
		_title = new JFrame("Lineup Optimizer 2015");
		_title.setSize(1000,800);
		_title.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_title.setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        _title.add(panel);
		JLabel label = new JLabel("Welcome to getTeamName()'s Lineup Optimizer");
		label.setFont(new Font("Calibri", Font.PLAIN, 40)); 
		label.setForeground(Color.BLUE);
		panel.add(label);
		JLabel label2 = new JLabel();
		label2.setText("Please select a sport that you'd like to make a team for:");
		label2.setFont(new Font("Calibri", Font.PLAIN, 25)); 
		label2.setForeground(Color.BLUE);
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
	}
	
	public void setSport(int i){	//I'm not sure if we need to select different sports but this could be a way of keeping track if need be
		_sport = i;
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
	public void startAlgo(String file){
		_title.getContentPane().removeAll();
		JPanel panel = new JPanel();
		_title.add(panel);
		panel.setLayout(new GridLayout(0,1));
		JLabel l1 = new JLabel("You are running the algorithm on " + file + " Please wait...");
		JLabel l2 = new JLabel("Just kidding, it's not implemented yet");
		panel.add(l1);
		panel.add(l2);
		
		if(_sport == 1){
			//execute Basketball Algorithm
		}
		
		if(_sport == 2){
			//execute Football Algorithm
		}
		
		_title.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		_title.getContentPane().revalidate();
		_title.getContentPane().repaint();
		
	}
}
