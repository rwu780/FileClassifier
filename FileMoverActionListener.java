import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FileMoverActionListener implements ActionListener{
	
	JTextField sourceTextField;
	JTextField destinationTextField;
	
	public FileMoverActionListener(JTextField s, JTextField d){
		this.sourceTextField = s;
		this.destinationTextField = d;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(sourceTextField.getText() != "" && destinationTextField.getText() != ""){
			File source = new File(sourceTextField.getText());
			File destination = new File(destinationTextField.getText());
			getFile(source, destination);
		}
		else{
			JOptionPane.showMessageDialog(null, "Source and Destinate Address must not be null");
		}
		
	}
	
	private void getFile(File sourceLocation, File destinateLocation){
		File[] listOfFiles = sourceLocation.listFiles();
				
		for (int i = 0; i< listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				System.out.println("This is file " + listOfFiles[i].getName());
				handleFile(listOfFiles[i], destinateLocation);
			}
			else if(listOfFiles[i].isDirectory()){
				continue;
			}
		}
		
		System.exit(0);
	}
	
	public void handleFile(File file, File destinateFolder){
		String filename = file.getName();
		
		if(filename.contains(".mp3")){
			moveFile(file, destinateFolder);
		}
	}
	
	public void moveFile(File file, File destinateFolder){
		String filename = file.getName();
		String[] parts = filename.split("\\.");
		String[] newFolderName = parts[0].split("\\-");
		
		File newFolder = new File(destinateFolder.getAbsolutePath() + "/" + newFolderName[0].trim());
		
		if(!newFolder.exists()){
			newFolder.mkdirs();
		}
		
		try{
			Path source = Paths.get(file.getAbsolutePath());
			Path target = Paths.get(newFolder.getAbsolutePath() + "/" +file.getName());
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e){
			System.out.println("Fail to move file");
		}
		
	}
}
