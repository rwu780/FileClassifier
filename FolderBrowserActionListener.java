import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class FolderBrowserActionListener implements ActionListener {
	
	JTextField textField;

	public FolderBrowserActionListener(JTextField t){
		textField = t;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selectedFile = selectDirectory();
		if(selectedFile != null){
			textField.setText(selectedFile.getAbsolutePath());			
		}
	}
	
	//Select Directory using JFileChooser and put it on textField
	private File selectDirectory(){
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File(""));
		chooser.setDialogTitle("Select Directory");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		chooser.setAcceptAllFileFilterUsed(false);
		
		if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION){
			System.out.println("Get Current Directory: " + chooser.getSelectedFile());
			return chooser.getSelectedFile();
		}
		else{
			System.out.println("No Action");
			return null;
		}

	}

}
