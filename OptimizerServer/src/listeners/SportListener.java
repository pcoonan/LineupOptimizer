package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SportListener implements ActionListener {
	
	private MainWindow _mw;
	private String _sport;

	public SportListener(MainWindow mw, String sport){
		_mw = mw;
		_sport = sport;
	}
	
	//This will pass the sport int back into the main window
	// as well as changing the main window to the file browser.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_mw.setSport(_sport);
		_mw.fileBrowser();
	}

}
