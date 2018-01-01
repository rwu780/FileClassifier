
import javax.swing.*;

public class FileClassifierView {

	JFrame frame;
	JPanel panel;
	
	public FileClassifierView(){
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("File Classifier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 200);
		
		panel = new JPanel();
		
		frame.add(panel);
		
		addComponent();
		
		frame.setVisible(true);
					
	}
	
	public void addComponent(){
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
		
		FolderBrowserActionListener sourceFB = new FolderBrowserActionListener(sourceText);
		sourceOpen.addActionListener(sourceFB);

		
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
		
		FolderBrowserActionListener destinationFB = new FolderBrowserActionListener(destinateText);
		destinateOpen.addActionListener(destinationFB);
		
		// Start Button
		JButton startButton = new JButton("Start");
		startButton.setBounds(10, 80, 80, 25);
		panel.add(startButton);
		
		FileMoverActionListener startFM = new FileMoverActionListener(sourceText, destinateText);
		startButton.addActionListener(startFM);
	
	}
	
}
