package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {
	
	MainWindow _mw;

	public RestartListener(MainWindow mw){
		_mw = mw;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_mw.chooseSport();

	}

}
