import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.*;

public class FileClassifier {
	
	private static File sourceFile;
	private static File destinateFile;
	private static File sourceDirectory;
	
	private static void placeComponent(JPanel panel){
		
		panel.setLayout(null);
		
		//Create Source Location Label
		JLabel sourceLabel = new JLabel("Source Location");
		sourceLabel.setBounds(10, 20, 80, 25);
		panel.add(sourceLabel);
		
		JTextField sourceText = new JTextField(20);
		sourceText.setBounds(100,20,165,25);
		panel.add(sourceText);
		
		JButton sourceOpen = new JButton("Browse");
		sourceOpen.setBounds(275, 20, 80, 25);
		panel.add(sourceOpen);
				
		sourceOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File selectedFile = selectDirectory();
				if(selectedFile != null){
					sourceText.setText(selectedFile.getAbsolutePath());
					sourceFile = selectedFile;
				}
			}
		});
		
		//Create Destination Location Label
		JLabel destinationLabel = new JLabel("Destinate Location");
		destinationLabel.setBounds(10, 50, 80, 25);
		panel.add(destinationLabel);
		
		JTextField destinateText = new JTextField(20);
		destinateText.setBounds(100, 50, 165, 25);
		panel.add(destinateText);

		JButton destinateOpen = new JButton("Browse");
		destinateOpen.setBounds(275, 50, 80, 25);
		panel.add(destinateOpen);
		
		destinateOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File selectedFile = selectDirectory();
				if(selectedFile != null){
					destinateText.setText(selectedFile.getAbsolutePath());
					destinateFile = selectedFile;
				}	
			}
		});
		
		// Start Button
		JButton startButton = new JButton("Start");
		startButton.setBounds(10, 80, 80, 25);
		panel.add(startButton);
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sourceFile != null && destinateFile != null){
					sortFile(sourceFile, destinateFile);
				}
				else{
					JOptionPane.showMessageDialog(null, "Source and Destinate Address must not be null");
				}
			}
		});
		
	}
	
	private static File selectDirectory(){
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
	
	private static void sortFile(File sourceLocation, File destinateLocation){
		File[] listOfFiles = sourceLocation.listFiles();
		
		File[] destinate = destinateLocation.listFiles();
		
		for (int i = 0; i< listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
				System.out.println("This is file " + listOfFiles[i].getName());
				moveFile(listOfFiles[i], destinate);
			}
			else if(listOfFiles[i].isDirectory()){
				continue;
			}
		}
		
		System.exit(0);
	}
	
	
	public static void main(String[] args) {
		
		// Look and Feel Decorated
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("File Classifier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 200);
		
		JPanel panel = new JPanel();
		
		frame.add(panel);
		
		placeComponent(panel);
		
		frame.setVisible(true);
	
	}
	
	public static void moveFile(File f, File[] destinate){
		String filename = f.getName();
		if(filename.contains(".mp3")){
			String[] parts = filename.split("\\.");
			String[] folders = parts[0].split("\\-");
			
			File foldername = new File(destinateFile.getAbsolutePath() + "/" + folders[0].trim());
			System.out.println(foldername.getAbsolutePath());
			System.out.println("foldername: " + foldername.getName());
			if(foldername.exists()){
				System.out.println("Yes");
			}else{
				System.out.println("No");
				foldername.mkdir();
			}

			try {
				Files.move(Paths.get(f.getAbsolutePath()), Paths.get(foldername.getAbsolutePath()+"/"+f.getName()), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Fail to move");
			}
//			if(f.renameTo(new File(foldername.getAbsolutePath() + "/" + f.getName()))){
//				System.out.println("success");
//			}
		}	
	}
}
