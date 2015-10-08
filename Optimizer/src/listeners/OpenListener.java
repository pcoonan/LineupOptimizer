package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenListener implements ActionListener {
	
	MainWindow _mw;

	public OpenListener(MainWindow mw){
		_mw = mw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "CSV Files", "csv");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getPath());
	       		_mw.startAlgo(chooser.getSelectedFile().getPath());
	    }

	}

}
