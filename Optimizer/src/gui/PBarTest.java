package gui;

import javax.swing.JProgressBar;


//Completely ignore this file, I just made it to test the functionality of the progress bar.
public class PBarTest implements Runnable {
	
	private int _i = 0;
	private JProgressBar _p;
	
	

	public PBarTest(JProgressBar p){
		_p = p;

	}
	
	@Override
	public void run() {
		if(_i<101){
			_i++;
			_p.setValue(_i);
		}
		
	}

}
