import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class window extends JFrame{
	private Primes primes;
	private Container mainLayout = this.getContentPane();
	private JLabel status = new JLabel("", SwingConstants.LEFT);
	private	JLabel LPrimedigits = new JLabel("", SwingConstants.CENTER);
	private JLabel LHexDigits = new JLabel("", SwingConstants.CENTER);
	private JLabel numberOfPrimes = new JLabel("", SwingConstants.CENTER);
	private JLabel numberOfHexnew  = new JLabel("",SwingConstants.CENTER);
	
	window(String Name, Primes p) {
		super(Name);
		primes = p;
		numberOfPrimes.setFont(new Font("Tahoma",Font.BOLD,17));
		numberOfHexnew.setFont(new Font("Tahoma",Font.BOLD,17));
		status.setFont(new Font("Tahoma",Font.PLAIN,20));
		updateValues("No Operation Completed");
		this.setSize(1000,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainLayout.setLayout(new GridLayout(4,1));
		mainLayout.setBackground( new Color(77, 0, 0));
		mainWindow();
		this.setVisible(true);
	}
	private void mainWindow() {
		primesLayout();
		hexFileLayout();
		generateLayout();
		statusLayout();
	}
	
	
	private void primesLayout() {
		JPanel primesPanel = new JPanel();
		primesPanel.setLayout(new GridLayout(2,1));
		primesPanel.setBorder(BorderFactory.createLineBorder(new Color(150,0,0),2));
		
		JLabel primeFile = new JLabel("Primes File", SwingConstants.LEFT);
		primeFile.setFont(new Font("Tahoma",Font.PLAIN,30));
		
		JButton primeSave = new JButton("Save");
		primeSave.setFont(new Font("Tahoma",Font.PLAIN,16));
		
		JButton primeLoad = new JButton("Load");
		primeLoad.setFont(new Font("Tahoma",Font.PLAIN,16));
		
		JTextField primeFileNameField = new JTextField("primesFile.txt",50);
		primeFileNameField.setFont(new Font("Tahoma",Font.PLAIN,15));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(1,2,0,0);
		
		JPanel innerPanelText = new JPanel();
		innerPanelText.setLayout(new GridBagLayout());
		innerPanelText.add(primeFileNameField, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		innerPanelText.add(numberOfPrimes,gbc);
		//innerPanelText.setBorder(BorderFactory.createLineBorder(new Color(150,0,0)));
		
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1,2));
		secondPanel.add(primeFile);
		
		JPanel ButtonPanel = new JPanel(new GridLayout(1,4,2,2));
		ButtonPanel.add(new JLabel());
		ButtonPanel.add(new JLabel());
		ButtonPanel.add(primeLoad);
		ButtonPanel.add(primeSave);
		
		secondPanel.add(ButtonPanel);
		
		primesPanel.add(innerPanelText);
		primesPanel.add(secondPanel);
		mainLayout.add(primesPanel);
		
		
		primeSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // display/center the jdialog when the button is pressed
				String fileName = primeFileNameField.getText();
				if(FileAccess.savePrimes(primes, fileName) != true) {
					updateValues("Unable to save Primes into file named: " + fileName);
				}else {
					updateValues("Successful Saved primes into file: " + fileName);
				}
			}
		});
		primeLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = primeFileNameField.getText();
				if(FileAccess.loadPrimes(primes, fileName) !=  true) {
					updateValues("Unable to load primes file: " + fileName + "check your file name or file's content");
				}else {
					updateValues("Succesful upload from file: " + fileName);
				}
			}
		});
		
	}
	private void hexFileLayout() {
		JPanel hexPanel = new JPanel();
		hexPanel.setLayout(new GridLayout(2,1));
		JLabel hexFile = new JLabel("Hexagon Cross File",SwingConstants.LEFT);
		
		//buttons to save hex and load hex
		JButton hexSave = new JButton("Save");
		hexSave.setFont(new Font("Tahoma",Font.PLAIN,16));
		JButton hexLoad = new JButton("Load");
		hexLoad.setFont(new Font("Tahoma",Font.PLAIN,16));
		JTextField hexFileNameField = new JTextField("hexCross.txt",50);
		hexFileNameField.setFont(new Font("Tahoma",Font.PLAIN,15));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.insets = new Insets(1,2,0,0);
		
		JPanel innerPanelText = new JPanel();
		innerPanelText.setLayout(new GridBagLayout());
		innerPanelText.add(hexFileNameField, gbc);
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		innerPanelText.add(numberOfHexnew,gbc);
		//innerPanelText.setBorder(BorderFactory.createLineBorder(new Color(150,0,0)));
		
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(1,2));
		hexFile.setFont(new Font("Tahoma",Font.PLAIN,30));
		secondPanel.add(hexFile);
		
		
		JPanel ButtonPanel = new JPanel(new GridLayout(1,4,2,2));
		ButtonPanel.add(new JLabel());
		ButtonPanel.add(new JLabel());
		ButtonPanel.add(hexLoad,gbc);
		ButtonPanel.add(hexSave,gbc);
		secondPanel.add(ButtonPanel);
		
		hexPanel.add(innerPanelText);
		hexPanel.add(secondPanel);
		hexPanel.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 2));
		mainLayout.add(hexPanel);
		
		hexSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // display/center the jdialog when the button is pressed
				String fileName = hexFileNameField.getText();
				if(FileAccess.saveCrosses(primes, fileName) != true) {
					updateValues("Unable to save Primes into file named: " + fileName);
				}else {
					updateValues("Successful Saved primes into file: " + fileName);
				}
			}
		});
		hexLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = hexFileNameField.getText();
				primes.clearCrosses();
				if(FileAccess.loadCrosses(primes, fileName) !=  true) {
					updateValues("Unable to load primes file: " + fileName + "check your file name or file's content");
				}else {
					updateValues("Succesful upload from file: " + fileName);
				}
			}
		});
	}
	
	
	// generate hex and primes layout buttons
	private void generateLayout() {
		JPanel generatePanel = new JPanel();
		generatePanel.setLayout(new GridBagLayout());
		JButton generatePrimes = new JButton("Generate Primes");
		generatePrimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePrimesPopup();
			}
		});
		JButton generateHex = new JButton("Generate Hex Cross");
		generateHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				primes.clearCrosses();
				primes.generateHexPrimes();
				updateValues("Succesful Hex Cross Prime Numbers Generated");
			}
		});
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(1,1,0,0);
		JPanel digitsPanel = new JPanel();
		digitsPanel.setLayout(new GridLayout(2,1));
		LPrimedigits.setFont(new Font("Tahoma", Font.BOLD,15));
		LHexDigits.setFont(new Font("Tahoma", Font.BOLD,15));
		digitsPanel.add(LPrimedigits);
		digitsPanel.add(LHexDigits);
		gbc.gridx = 0;
		gbc.weightx = 0.1;
		generatePanel.add(generatePrimes,gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		generatePanel.add(digitsPanel,gbc);
		gbc.weightx = 0.1;
		gbc.gridx = 2;
		generatePanel.add(generateHex,gbc);
		generatePanel.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 2));
		mainLayout.add(generatePanel);
	}
	private void statusLayout() {
		JPanel statusPanel =  new JPanel();
		statusPanel.setLayout(new GridLayout(2,1));
		statusPanel.add(new JLabel(""));
		JPanel newPanel = new JPanel(new GridLayout(1,1));
		
		newPanel.add(status);
		newPanel.setBorder(BorderFactory.createLineBorder(new Color(150,0,0)));
		statusPanel.add(newPanel);
		mainLayout.add(statusPanel);
	}
	
	private void generatePrimesPopup() {
		JDialog generatePanel = new JDialog(this, "Prime Number Generation");
		generatePanel.setSize(1000, 250);
		generatePanel.setLayout(new GridLayout(4,1));
		
		JLabel nPrimesLabel = new JLabel("Number of Primes to Generate: ");
		nPrimesLabel.setFont(new Font("Tahoma", Font.PLAIN,13));
		JLabel startLabel = new JLabel("Starting Number (does not have to be prime)");
		startLabel.setFont(new Font("Tahoma", Font.PLAIN,13));
		JTextField start = new JTextField("",58);
		JTextField count = new JTextField("",44);
		
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(2,1,5,5));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.weightx = 0.5;
		JPanel form1 = new JPanel(new GridBagLayout());
		JPanel form2 = new JPanel(new GridBagLayout());
		
		form1.add(nPrimesLabel,gbc);
		gbc.gridx = 1;
		form1.add(count,gbc);
		
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.weightx = 0.5;
		form2.add(startLabel,gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		form2.add(start,gbc);
		form1.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 1));	
		form2.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 1));
		JPanel generateButtons = new JPanel(new GridBagLayout());
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.fill =GridBagConstraints.CENTER;
		JButton generatePrimes = new JButton("Generate Primes");
		generatePrimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // display/center the jdialog when the button is pressed
				try {
					int thisCount = Integer.valueOf(count.getText());
					int thisStart = Integer.valueOf(start.getText());
					if(thisCount <0 || thisStart <0) {
						updateValues("Unsucessful Generation: Start number and Number of Primes cannot be negative");
						
					}else {
						updateValues("Generating Primes please wait...");
						primes.clearPrimes();
						primes.generatePrimes(thisStart, thisCount);
						updateValues("Successful Generation of Primes");
					}
				}catch(NumberFormatException ex) {
					updateValues("Bad input of Numbers please check Starting number and number of primes text box");
				}
			}
		});
		JButton cancelGeneration = new JButton("Cancel Genereation");
		cancelGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generatePanel.dispose();
			}
		});
		generateButtons.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 1));
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 0.4;
		gbc.gridx = 0;
		generateButtons.add(generatePrimes,gbc);
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		gbc.weightx = 0.4;
		gbc.gridx = 1;
		generateButtons.add(cancelGeneration,gbc);
		
		JLabel warning = new JLabel("Warning: Procces migth freeze or take a very long time");
		warning.setFont(new Font("Tahoma", Font.PLAIN,13));
		warning.setBorder(BorderFactory.createLineBorder(new Color(150,0,0), 1));
		JPanel warningPanel = new JPanel(new GridLayout(2,1));
		warningPanel.add(new JLabel());
		warningPanel.add(warning);
		generatePanel.add(form1);
		generatePanel.add(form2);
		generatePanel.add(generateButtons);
		generatePanel.add(warningPanel);
		generatePanel.setVisible(true);
		
	}
	private void updateValues(String s) {
		status.setText("Status: " + s);
		numberOfPrimes.setText(Integer.toString(primes.primeCount()));
		LPrimedigits.setText("The Largest Prime has " + Integer.toString(primes.sizeofLastPrime()) + " digits");
		LHexDigits.setText("The Largest Cross has " + primes.sizeofLastCross().left() + " and " +  primes.sizeofLastCross().right() + " digits");
		numberOfHexnew.setText(Integer.toString(primes.crossesCount()));
	}
	private GridBagConstraints getConstraints(int posX,int posY, int padX, int padY) {
		GridBagConstraints gbc = new GridBagConstraints();  
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = posX;
		gbc.gridy = posY;
		gbc.ipadx = padX;
		gbc.ipady = padY;
		return gbc;
	}
}
