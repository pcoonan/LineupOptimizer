package listeners;

import gui.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenListener implements ActionListener {
	
	MainWindow _mw;

	public OpenListener(MainWindow mw){
		_mw = mw;
	}
	
	
// This will open a file chooser that will only accept CSV files. 
// When one is selected then the file path will be passed back into the main window.
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		chooser.setFileFilter(filter);
		
		//set the default directory to the current directory of the program
		File workingDirectory = new File(System.getProperty("user.dir"));
		chooser.setCurrentDirectory(workingDirectory);
		
		
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getPath());
			_mw.startAlgo(chooser.getSelectedFile().getPath());
		}

	}

}
