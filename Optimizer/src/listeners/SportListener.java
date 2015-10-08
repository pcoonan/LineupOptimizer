package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SportListener implements ActionListener {
	
	MainWindow _mw;

	public SportListener(MainWindow mw, int sport){
		_mw = mw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		_mw.fileBrowser();
		
		//_jframe.setVisible(false);
		//_jframe.dispose();

	}

}
