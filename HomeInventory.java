/*package homeInventory;

//import com.sun.pisces.GradientColorMap;
//import com.toedter.calendar.*;
//import java.beans.*;
import java.io.*;
import java.sql.Date;
import com.toedter.calendar.*;
import java.beans.*;
import java.text.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class HomeInventory extends JFrame{

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		
		new HomeInventory().show();

	}
	
	JToolBar inventoryToolBar=new JToolBar();
	JButton newButton;
	JButton deleteButton;
	JButton saveButton;
	JButton previousButton;
	JButton nextButton;
	JButton printButton;
	JButton exitButton=new JButton();
	
	
	JLabel itemLabel;
	JTextField itemTextField;
	
	JLabel locationLabel;
	JComboBox<String> locationComboBox;
	JCheckBox markedCheckBox;
	
	JLabel serialLabel;
	JTextField serialTextField;
	
	JLabel priceLabel;
	JTextField priceTextField;
	
	JLabel dateLabel;
	JDateChooser dateDateChooser;
	
	JLabel storeLabel;
	JTextField storeTextField;
	
	JLabel noteLabel;
	JTextField noteTextField;
	
	JLabel photoLabel;
	static JTextArea photoTextArea;
	JButton photoButton;
	
	JPanel searchPanel;
	JButton[] searchButton;
	PhotoPanel photoPanel;
	
	static final int maximumEntries=300;
	static  int numberEntries;
	static InventoryItem[] myInventory=new InventoryItem[maximumEntries];
	public static int lastPage;
	public static int entriesPerPage=2;
	int currentEntry;
	
	
	
	
	public HomeInventory(){
		setTitle("Home Inventory Manager");
		setResizable(false);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				exitForm(evt);
			}			
		});
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;
		
		
		inventoryToolBar.setFloatable(false);
		inventoryToolBar.setBackground(new Color(191, 64, 64));
		inventoryToolBar.setOrientation(SwingConstants.VERTICAL);
		
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=0;
		gridConstraints.gridheight=8;
		gridConstraints.fill=GridBagConstraints.VERTICAL;
		getContentPane().add(inventoryToolBar,gridConstraints);
		
		inventoryToolBar.addSeparator();
		Dimension bSize=new Dimension(70,50);
		
		newButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\new.gif"));
		newButton.setText("New");
		sizeButton(newButton,bSize);
		newButton.setToolTipText("Add New Item");
		newButton.setHorizontalTextPosition(SwingConstants.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(newButton);
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				newButtonActionPerformed(e);
			}
		});
		
		deleteButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\delete.gif"));
		deleteButton.setText("Delete");
		sizeButton(deleteButton,bSize);
		deleteButton.setToolTipText("Delete Current Item");
		deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(deleteButton);
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				deleteButtonActionPerformed(e);
			}
		});
		
		saveButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\save.gif"));
		saveButton.setText("Save");
		sizeButton(saveButton,bSize);
		saveButton.setToolTipText("Save Current Item");
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(saveButton);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveButtonActionPerformed(e);
			}
		});
		
		previousButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\previous.gif"));
		previousButton.setText("Previous");
		sizeButton(previousButton,bSize);
		previousButton.setToolTipText("Display Previous Item");
		previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
		previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(previousButton);
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				previousButtonActionPerformed(e);
			}
		});
		
		nextButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\next.gif"));
		nextButton.setText("Next");
		sizeButton(nextButton,bSize);
		nextButton.setToolTipText("Display Next Item");
		nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
		nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(nextButton);
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextButtonActionPerformed(e);
			}	
		});
		
		
		printButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\print.gif"));
		printButton.setText("Print");
		sizeButton(printButton,bSize);
		printButton.setToolTipText("Print Inventory List");
		printButton.setHorizontalTextPosition(SwingConstants.CENTER);
		printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(printButton);
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printButtonActionPerformed(e);
			}
		});
		
		exitButton.setText("Exit");
		sizeButton(exitButton,bSize);
		inventoryToolBar.add(exitButton);
		exitButton.setToolTipText("Exit Program");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitButtonActionPerformed(e);
			}
		});
		
		itemLabel=new JLabel();
		itemLabel.setText("Inventory Item");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=0;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(itemLabel,gridConstraints);
		
		itemTextField=new JTextField();
		itemTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=0;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(itemTextField,gridConstraints);
		itemTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTextFieldAcionPerformed(e);
			}

			
		});
		
		locationLabel=new JLabel();
		locationLabel.setText("Location");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=1;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(locationLabel,gridConstraints);
		
		
		locationComboBox=new JComboBox<String>();
		locationComboBox.setPreferredSize(new Dimension(270,25));
		locationComboBox.setFont(new Font("Arial",Font.PLAIN,12));
		locationComboBox.setEditable(true);
		locationComboBox.setBackground(Color.WHITE);
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=1;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(locationComboBox,gridConstraints);
		locationComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				locationComboBoxActionPerformed(e);
			}

			
		});
		
		markedCheckBox=new JCheckBox();
		markedCheckBox.setText("Marked?");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=5;
		gridConstraints.gridy=1;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(markedCheckBox,gridConstraints);
		
		
		serialLabel=new JLabel();
		serialLabel.setText("Serial Number");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=2;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(serialLabel,gridConstraints);
		
		
		serialTextField=new JTextField();
		serialTextField.setPreferredSize(new Dimension(270,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=2;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(serialTextField,gridConstraints);
		serialTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serialTextFieldActionPerformed(e);
			}
		});
		
		
		priceLabel=new JLabel();
		priceLabel.setText("Purchase Price");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(priceLabel,gridConstraints);
		
		
		priceTextField=new JTextField();
		priceTextField.setPreferredSize(new Dimension(160,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=3;
		gridConstraints.gridwidth=1;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(priceTextField,gridConstraints);
		priceTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceTextFieldActionPerformed(e);
			}			
		});
		
		
		dateLabel=new JLabel();
		dateLabel.setText("Date Purchase");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=4;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(dateLabel,gridConstraints);
		
		
		dateDateChooser=new JDateChooser();
		dateDateChooser.setPreferredSize(new Dimension(120,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=5;
		gridConstraints.gridy=3;
		gridConstraints.gridwidth=2;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(dateDateChooser,gridConstraints);
		dateDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				dateDateChooserPropertyChange(e);
			}	
		});
		
		
		storeLabel=new JLabel();
		storeLabel.setText("Store/Website");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=4;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(storeLabel,gridConstraints);
		
		
		storeTextField=new JTextField();
		storeTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=4;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(storeTextField,gridConstraints);
		storeTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeTextFieldActionPerformed(e);
			}

			
		});
		
		
		
		noteLabel=new JLabel();
		noteLabel.setText("Note");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=5;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(noteLabel,gridConstraints);
		
		noteTextField=new JTextField();
		noteTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=5;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(noteTextField,gridConstraints);
		noteTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noteTextFieldActionPerformed(e);
			}
		});
		
		
		photoLabel=new JLabel();
		photoLabel.setText("Photo");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=6;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(photoLabel,gridConstraints);
		
		photoTextArea=new JTextArea();
		photoTextArea.setPreferredSize(new Dimension(350,35));
		photoTextArea.setFont(new Font("Arial",Font.PLAIN,12));
		photoTextArea.setEditable(false);
		photoTextArea.setLineWrap(true);
		photoTextArea.setWrapStyleWord(true);
		photoTextArea.setBackground(new Color(255,255,192));
		photoTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=6;
		gridConstraints.gridwidth=4;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(photoTextArea,gridConstraints);
		
		photoButton=new JButton();
		photoButton.setText("...");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=6;
		gridConstraints.gridy=6;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(photoButton,gridConstraints);
		photoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photoButtonActionPerformed(e);
			}

			
		});
		
		searchPanel=new JPanel();
		searchButton=new JButton[26];
		
		searchPanel.setPreferredSize(new Dimension(240,160));
		searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
		searchPanel.setLayout(new GridBagLayout());
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=7;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,10,0);
		gridConstraints.anchor=GridBagConstraints.CENTER;
		getContentPane().add(searchPanel,gridConstraints);
		
		int x=0;
		int y=0;
		//Create Position of 26 Button.
		for(int i=0;i<26;i++){
			searchButton[i]=new JButton(); //button
			searchButton[i].setText(String.valueOf((char)(65+i)));
			searchButton[i].setFont(new Font("Arial",Font.BOLD,12));
			searchButton[i].setMargin(new Insets(-10,-10,-10,-10));
			sizeButton(searchButton[i],new Dimension(37,27));
			searchButton[i].setBackground(Color.YELLOW);
			searchButton[i].setFocusable(false);
			gridConstraints=new GridBagConstraints();
			gridConstraints.gridx=x;
			gridConstraints.gridy=y;
			searchPanel.add(searchButton[i],gridConstraints);
			// Add method
			searchButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchButtonActionPerformed(e);
				}
				
			});
			x++;
			//six Button Per Row
			if(x%6==0) {
				x=0;
				y++;
			}
			
		}
		
		
		photoPanel=new PhotoPanel();
		photoPanel.setPreferredSize(new Dimension(240,160));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=4;
		gridConstraints.gridy=7;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,10,10);
		gridConstraints.anchor=GridBagConstraints.CENTER;
		getContentPane().add(photoPanel,gridConstraints);
		
		
		pack();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5*(screenSize.width-getWidth())),(int)(0.5*(screenSize.height-getHeight())),getWidth(),getHeight());
		
		
		
		int n;
		
	    try {	
		    BufferedReader inputFile=new BufferedReader(new FileReader("inventory.txt"));
		    numberEntries=Integer.valueOf(inputFile.readLine()).intValue();
		    n=Integer.valueOf(inputFile.readLine()).intValue();
		    if(numberEntries!=0) {
			    for(int i=0;i<numberEntries;i++) {
				    myInventory[i]=new InventoryItem();
				    myInventory[i].description=inputFile.readLine();
				    myInventory[i].location=inputFile.readLine();
	    			myInventory[i].serialNumber=inputFile.readLine();
		    		myInventory[i].markedIndicator=Boolean.valueOf(inputFile.readLine()).booleanValue();
			    	myInventory[i].purchasePrice=inputFile.readLine();
		    		myInventory[i].purchaseDate=inputFile.readLine();
		    		myInventory[i].purchaseLocation=inputFile.readLine();
			    	myInventory[i].note=inputFile.readLine();
		    		myInventory[i].photoFile=inputFile.readLine();
					
				}
			}
		    
			//Read in combo box elements.
			if(n!=0) {
				for(int i=0;i<n;i++) {
					String str;
					str=inputFile.readLine();
					locationComboBox.addItem(str);
				}
			}
			inputFile.close();
			currentEntry=1;
			showEntry(currentEntry);
			
		}catch(Exception ex) {
			numberEntries=0;
			currentEntry=0;
			
		}
		if(numberEntries==0) {
			newButton.setEnabled(false);
			deleteButton.setEnabled(false);
			nextButton.setEnabled(false);
			previousButton.setEnabled(false);
			printButton.setEnabled(false);
		}
		
	}
	
	private void exitForm(WindowEvent evt){
		System.exit(0);
		
    }
	
	private void sizeButton(JButton b, Dimension bSize){
		
		b.setMaximumSize(bSize);
		b.setPreferredSize(bSize);
		b.setMinimumSize(bSize);
	}
	private void newButtonActionPerformed(ActionEvent e){
		checkSave();
		blankvalues();
		
		
	}
	private void deleteButtonActionPerformed(ActionEvent e){
		if(JOptionPane.showConfirmDialog(null,"Are u sure u want to delete this item??","DeleteInventoryItem",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.NO_OPTION)
			return;
		deleteEntry(currentEntry);
		if(numberEntries==0) {
			currentEntry=0;
			blankvalues();
		}
		else {
			currentEntry--;
			if(currentEntry==0) 
				currentEntry=1;
				
			showEntry(currentEntry);
		}
	
		
	}
	private void saveButtonActionPerformed(ActionEvent e){
		itemTextField.setText(itemTextField.getText().trim());
		if(itemTextField.getText().equals(" ")){
			JOptionPane.showConfirmDialog(null, "Must have item description","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			itemTextField.requestFocus();
			return;
		}
		if(newButton.isEnabled()) {
			deleteEntry(currentEntry);
		}
		String s=itemTextField.getText();
		itemTextField.setText(s.substring(0,1).toUpperCase()+s.substring(0));
		numberEntries++;
		currentEntry=1;
		if(numberEntries!=1) {
			do {
				//if(itemTextField.getText().compareTo(myInventory[currentEntry-1].description<0))
					//break;
				currentEntry++;
			}while(currentEntry<numberEntries);
		}
		if(currentEntry!=numberEntries) {
			for(int i=numberEntries;i>=currentEntry;i--) {
				myInventory[i-1]=myInventory[i-2];
				myInventory[i-2]=new InventoryItem();
				
			}
		}
		
		myInventory[currentEntry-1]=new InventoryItem();
		myInventory[currentEntry-1].description=itemTextField.getText();
		myInventory[currentEntry-1].location=locationComboBox.getSelectedItem().toString();
		//myInventory[currentEntry-1].marked=markedCheckBox.isSelected();
		myInventory[currentEntry-1].serialNumber=serialTextField.getText();
		myInventory[currentEntry-1].purchasePrice=priceTextField.getText();
		myInventory[currentEntry-1].purchaseDate=dateToString(dateDateChooser.getDate());
		myInventory[currentEntry-1].purchaseLocation=storeTextField.getText();
		myInventory[currentEntry-1].photoFile=photoTextArea.getText();
		myInventory[currentEntry-1].note=noteTextField.getText();
		showEntry(currentEntry);
		if(numberEntries<maximumEntries)
			newButton.setEnabled(true);
		
		else
			newButton.setEnabled(false);
		deleteButton.setEnabled(true);
		printButton.setEnabled(true);
		
		
		
		
	}
	private void previousButtonActionPerformed(ActionEvent e){
		checkSave();
		currentEntry--;
		showEntry(currentEntry);
		
		
	}
	private void nextButtonActionPerformed(ActionEvent e){
		checkSave();
		currentEntry++;
		showEntry(currentEntry);
		
	}
	private void itemTextFieldAcionPerformed(ActionEvent e) {
		locationComboBox.requestFocus();
		
	}
	private void locationComboBoxActionPerformed(ActionEvent e) {
		serialTextField.requestFocus();
		
	}
	private void serialTextFieldActionPerformed(ActionEvent e) {
		priceTextField.requestFocus();
		
	}
	private void priceTextFieldActionPerformed(ActionEvent e) {
		dateDateChooser.requestFocus();
		
	}
	private void dateDateChooserPropertyChange(PropertyChangeEvent e) {
		storeTextField.requestFocus();
		
	}
	private void storeTextFieldActionPerformed(ActionEvent e) {
	     noteTextField.requestFocus();
		
	}
	private void noteTextFieldActionPerformed(ActionEvent e) {
		photoButton.requestFocus();
		
	}
	private void printButtonActionPerformed(ActionEvent e){
		//lastPage=(int)(1+(numberEntries-1)/entriesPerPage);
		PrinterJob InventoryPrinterJob=PrinterJob.getPrinterJob();
		//InventoryPrinterJob.setPrintable(new InventoryDocument());
		if(InventoryPrinterJob.printDialog()) {
			try {
				InventoryPrinterJob.print();
			}catch(PrinterException ex) {
				JOptionPane.showConfirmDialog(null,ex.getMessage(),"Print Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private void photoButtonActionPerformed(ActionEvent e) {
	    
		JFileChooser openChooser=new JFileChooser();
		openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		openChooser.setDialogTitle("Open Photo File");
		openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg"));
		if(openChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
          showPhoto(openChooser.getSelectedFile().toString());
	}
	private void searchButtonActionPerformed(ActionEvent e) {
		int i;
		if(numberEntries==0)
			return;
		String letterClicked=e.getActionCommand();
		i=0;
		do {
			if(myInventory[i].description.substring(0,1).equals(letterClicked)) {
				currentEntry=i+1;
				showEntry(currentEntry);
				return;
			}
			i++;
		}while(i<numberEntries);
		JOptionPane.showConfirmDialog(null,"No"+letterClicked+"inventory Item","None Found",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	
		
	}
	private void exitButtonActionPerformed(ActionEvent e){
		
		System.exit(0);
		
	}
	private void showEntry(int j) {
		// Display Entry j (1 to numberEntries)
		itemTextField.setText(myInventory[j-1].description);
		locationComboBox.setSelectedItem(myInventory[j-1].location);
		markedCheckBox.setSelected(myInventory[j-1].markedIndicator);
		serialTextField.setText(myInventory[j-1].serialNumber);
		priceTextField.setText(myInventory[j-1].purchasePrice);
		dateDateChooser.setDate(stringToDate(myInventory[j-1].purchaseDate));
		storeTextField.setText(myInventory[j-1].purchaseLocation);
		noteTextField.setText(myInventory[j-1].note);
		showPhoto(myInventory[j-1].photoFile);
		itemTextField.requestFocus();
		
	}
	private void showPhoto(String photoFile) {
		if(!photoFile.equals("")) {
			try {
				photoTextArea.setText(photoFile);
			}catch(Exception ex) {
				photoTextArea.setText("");
			}
		}
		else {
			photoTextArea.setText("");
		}
		photoPanel.repaint();
		
	}
	private void blankvalues() {
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
		saveButton.setEnabled(true);
		previousButton.setEnabled(false);
		nextButton.setEnabled(false);
		printButton.setEnabled(false);
		itemTextField.setText(" ");
		locationComboBox.setSelectedItem(" ");
		markedCheckBox.setSelected(false);
		serialTextField.setText(" ");
		priceTextField.setText(" ");
		dateDateChooser.setDate(new Date());
		storeTextField.setText(" ");
		noteTextField.setText(" ");
		photoTextArea.setText(" ");
		photoPanel.repaint();
		itemTextField.requestFocus();
		
	}

	private Date stringToDate(String s) {
		int m=Integer.valueOf(s.substring(0,2)).intValue()-1;
		int d=Integer.valueOf(s.substring(3,5)).intValue();
		int y=Integer.valueOf(s.substring(6)).intValue()-1900;
		return(new Date(y,m,d));
	}
	
	@SuppressWarnings("deprecation")
	private String dateToString(Date dd){
		String yString=String.valueOf(dd.getYear()+1900); 
		int m=dd.getMonth()+1;
		String mString=new DecimalFormat("00").format(m);
		@SuppressWarnings("deprecation")
		int d=dd.getDate();
		String dString=new DecimalFormat("00").format(d);
		return(mString + "/"+ dString+"/"+yString);	
	}

	private void deleteEntry(int j) {
		if(j!=numberEntries) {
			for(int i=j;i<numberEntries;i++) {
				myInventory[i-1]=new InventoryItem();
				myInventory[i-1]=myInventory[i];
				
			}
		}
		numberEntries--;
		
	}
	private void checkSave() {
		boolean edited=false;
		if(!myInventory[currentEntry-1].description.equals(itemTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].location.equals(locationComboBox.getSelectedItem().toString()))
			edited=true;
		//else if(myInventory[currentEntry-1].marked!=markedCheckBox.isSelected())
			//edited=true;
		else if(!myInventory[currentEntry-1].serialNumber.equals(serialTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].purchasePrice.equals(priceTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
			edited=true;
		else if(!myInventory[currentEntry-1].note.equals(noteTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].photoFile.equals(photoTextArea.getText()))
			edited=true;
		if(edited) {
			if(JOptionPane.showConfirmDialog(null,"You have edited this item.Do u want to save changes??","Save Item",JOptionPane. YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
		saveButton.doClick();
			}
		
	}
}
class PhotoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Graphics2D g2D=(Graphics2D)g;
		super.paintComponent(g2D);
		// Draw Border
		g2D.setPaint(Color.BLACK);
		g2D.draw(new Rectangle2D.Double(0, 0, getWidth()-1, getHeight()-1));
		
		//show photo
		Image photoImage=new ImageIcon(HomeInventory.photoTextArea.getText()).getImage();
		int w=getWidth();
		int h=getHeight();
		double rWidth=(double) getWidth()/(double)photoImage.getWidth(null);
		double rHeight=(double) getHeight()/(double)photoImage.getHeight(null);
		
		if(rWidth>rHeight) {
			// Leave Height at Display height change width by amount height is changed.
			w=(int)(photoImage.getWidth(null)*rHeight);
		}
		else {
			// Leave width at display width, change width by amount height is changed
			h=(int)(photoImage.getHeight(null)*rWidth);
		}
		// Center in panel
		g2D.drawImage(photoImage,(int)(0.5*(getWidth()-w)),(int)(0.5*(getHeight()-h)),w,h,null);
		
		//g2D.dispose();
	}
}

class InventoryDocument implements Printable{
	public int print(Graphics g,PageFormat pf,int pageIndex) {
		Graphics2D g2D=(Graphics2D)g;
		if((pageIndex+1)>HomeInventory.lastPage) {
			return NO_SUCH_PAGE;
		}
		int i,iEnd;
		
		g2D.setFont(new Font("Arail",Font.BOLD,14));
		g2D.drawString("Home Inventory Items - Page"+String.valueOf(pageIndex+1),(int)pf.getImageableX(),(int)(pf.getImageableY()+25));
		// get Starting Y
		
		int dy=(int)g2D.getFont().getStringBounds("S", g2D.getFontRenderContext()).getHeight();
		int y=(int)(pf.getImageableY()+4*dy);
		iEnd=HomeInventory.numberEntries;
		if(iEnd>HomeInventory.numberEntries) {
			iEnd=HomeInventory.numberEntries;
		}
		for(i=0+HomeInventory.entriesPerPage*pageIndex;i<iEnd;i++) {
			Line2D.Double dividingLine=new Line2D.Double(pf.getImageableX(),y,pf.getImageableX()+pf.getImageableWidth(),y);
			g2D.draw(dividingLine);
				
			y=y+dy;
			g2D.setFont(new Font("Arial",Font.BOLD,12));
			g2D.drawString(HomeInventory.myInventory[i].description,(int)pf.getImageableX(),y);
				
			y=y+dy;
			g2D.setFont(new Font("Arial",Font.BOLD,12));
			g2D.drawString("Location: "+HomeInventory.myInventory[i].location,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			if(HomeInventory.myInventory[i].markedIndicator) {
				g2D.drawString("Item is marked with identifying information.",(int)(pf.getImageableX()+25),y);
			}
			else {
				g2D.drawString("Item is NOT marked with indentifying information.",(int)(pf.getImageableX()+25),y);
			}
				
			y=y+dy;
			g2D.drawString("Serial Number: "+HomeInventory.myInventory[i].serialNumber,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			g2D.drawString("Price: $"+HomeInventory.myInventory[i].purchasePrice+", Purchased on: "+HomeInventory.myInventory[i].purchaseDate,(int)(pf.getImageableX()+25) ,y);
				
			y=y+dy;
			g2D.drawString("Purchased at: "+HomeInventory.myInventory[i].purchaseLocation,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			g2D.drawString("Note: "+HomeInventory.myInventory[i].note,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			try {
				// Maintain Original width/height ratio.
				Image InventoryImage=new ImageIcon(HomeInventory.myInventory[i].photoFile).getImage();
				double ratio=(double)(InventoryImage.getWidth(null))/(double)(InventoryImage.getHeight(null));
				g2D.drawImage(InventoryImage,(int)(pf.getImageableX()+25),y,(int)(100*ratio),100,null);
			}
			catch(Exception ex) {
			    // HAVE  place to go in case image file does not open		
			}
				
			y=y+2*dy+100;
		}
		return PAGE_EXISTS;
  }
}*/
package homeInventory;

//import com.sun.pisces.GradientColorMap;
//import com.toedter.calendar.*;
//import java.beans.*;
import java.io.*;
import java.sql.Date;
import com.toedter.calendar.*;
import java.beans.*;
import java.text.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class HomeInventory extends JFrame{

	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		
		new HomeInventory().show();

	}
	
	JToolBar inventoryToolBar=new JToolBar();
	JButton newButton;
	JButton deleteButton;
	JButton saveButton;
	JButton previousButton;
	JButton nextButton;
	JButton printButton;
	JButton exitButton=new JButton();
	
	
	JLabel itemLabel;
	JTextField itemTextField;
	
	JLabel locationLabel;
	JComboBox<String> locationComboBox;
	JCheckBox markedCheckBox;
	
	JLabel serialLabel;
	JTextField serialTextField;
	
	JLabel priceLabel;
	JTextField priceTextField;
	
	JLabel dateLabel;
	JDateChooser dateDateChooser;
	
	JLabel storeLabel;
	JTextField storeTextField;
	
	JLabel noteLabel;
	JTextField noteTextField;
	
	JLabel photoLabel;
	static JTextArea photoTextArea;
	JButton photoButton;
	
	JPanel searchPanel;
	JButton[] searchButton;
	PhotoPanel photoPanel;
	
	static final int maximumEntries=300;
	static  int numberEntries;
	static InventoryItem[] myInventory=new InventoryItem[maximumEntries];
	public static int lastPage;
	public static int entriesPerPage=2;
	int currentEntry;
	
	
	
	
	public HomeInventory(){
		setTitle("Home Inventory Manager");
		setResizable(false);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				exitForm(evt);
			}			
		});
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gridConstraints;
		
		
		inventoryToolBar.setFloatable(false);
		inventoryToolBar.setBackground(new Color(191, 64, 64));
		inventoryToolBar.setOrientation(SwingConstants.VERTICAL);
		
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=0;
		gridConstraints.gridy=0;
		gridConstraints.gridheight=8;
		gridConstraints.fill=GridBagConstraints.VERTICAL;
		getContentPane().add(inventoryToolBar,gridConstraints);
		
		inventoryToolBar.addSeparator();
		Dimension bSize=new Dimension(70,50);
		
		newButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\new.gif"));
		newButton.setText("New");
		sizeButton(newButton,bSize);
		newButton.setToolTipText("Add New Item");
		newButton.setHorizontalTextPosition(SwingConstants.CENTER);
		newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(newButton);
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					newButtonActionPerformed(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		deleteButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\delete.gif"));
		deleteButton.setText("Delete");
		sizeButton(deleteButton,bSize);
		deleteButton.setToolTipText("Delete Current Item");
		deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(deleteButton);
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					deleteButtonActionPerformed(e);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		saveButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\save.gif"));
		saveButton.setText("Save");
		sizeButton(saveButton,bSize);
		saveButton.setToolTipText("Save Current Item");
		saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
		saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(saveButton);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveButtonActionPerformed(e);
			}
		});
		
		previousButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\previous.gif"));
		previousButton.setText("Previous");
		sizeButton(previousButton,bSize);
		previousButton.setToolTipText("Display Previous Item");
		previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
		previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(previousButton);
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				previousButtonActionPerformed(e);
			}
		});
		
		nextButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\next.gif"));
		nextButton.setText("Next");
		sizeButton(nextButton,bSize);
		nextButton.setToolTipText("Display Next Item");
		nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
		nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(nextButton);
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextButtonActionPerformed(e);
			}	
		});
		
		
		printButton=new JButton(new ImageIcon("C:\\Users\\Shaha\\eclipse-workspace\\HomeInventory\\src\\homeinventory\\print.gif"));
		printButton.setText("Print");
		sizeButton(printButton,bSize);
		printButton.setToolTipText("Print Inventory List");
		printButton.setHorizontalTextPosition(SwingConstants.CENTER);
		printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		inventoryToolBar.add(printButton);
		printButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printButtonActionPerformed(e);
			}
		});
		
		exitButton.setText("Exit");
		sizeButton(exitButton,bSize);
		inventoryToolBar.add(exitButton);
		exitButton.setToolTipText("Exit Program");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitButtonActionPerformed(e);
			}
		});
		
		itemLabel=new JLabel();
		itemLabel.setText("Inventory Item");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=0;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(itemLabel,gridConstraints);
		
		itemTextField=new JTextField();
		itemTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=0;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(itemTextField,gridConstraints);
		itemTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemTextFieldAcionPerformed(e);
			}

			
		});
		
		locationLabel=new JLabel();
		locationLabel.setText("Location");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=1;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(locationLabel,gridConstraints);
		
		
		locationComboBox=new JComboBox<String>();
		locationComboBox.setPreferredSize(new Dimension(270,25));
		locationComboBox.setFont(new Font("Arial",Font.PLAIN,12));
		locationComboBox.setEditable(true);
		locationComboBox.setBackground(Color.WHITE);
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=1;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(locationComboBox,gridConstraints);
		locationComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				locationComboBoxActionPerformed(e);
			}

			
		});
		
		markedCheckBox=new JCheckBox();
		markedCheckBox.setText("Marked?");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=5;
		gridConstraints.gridy=1;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(markedCheckBox,gridConstraints);
		
		
		serialLabel=new JLabel();
		serialLabel.setText("Serial Number");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=2;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(serialLabel,gridConstraints);
		
		
		serialTextField=new JTextField();
		serialTextField.setPreferredSize(new Dimension(270,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=2;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(serialTextField,gridConstraints);
		serialTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serialTextFieldActionPerformed(e);
			}
		});
		
		
		priceLabel=new JLabel();
		priceLabel.setText("Purchase Price");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(priceLabel,gridConstraints);
		
		
		priceTextField=new JTextField();
		priceTextField.setPreferredSize(new Dimension(160,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=3;
		gridConstraints.gridwidth=1;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(priceTextField,gridConstraints);
		priceTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priceTextFieldActionPerformed(e);
			}			
		});
		
		
		dateLabel=new JLabel();
		dateLabel.setText("Date Purchase");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=4;
		gridConstraints.gridy=3;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(dateLabel,gridConstraints);
		
		
		dateDateChooser=new JDateChooser();
		dateDateChooser.setPreferredSize(new Dimension(120,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=5;
		gridConstraints.gridy=3;
		gridConstraints.gridwidth=2;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(dateDateChooser,gridConstraints);
		dateDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				dateDateChooserPropertyChange(e);
			}	
		});
		
		
		storeLabel=new JLabel();
		storeLabel.setText("Store/Website");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=4;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(storeLabel,gridConstraints);
		
		
		storeTextField=new JTextField();
		storeTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=4;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(storeTextField,gridConstraints);
		storeTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeTextFieldActionPerformed(e);
			}

			
		});
		
		
		
		noteLabel=new JLabel();
		noteLabel.setText("Note");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=5;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(noteLabel,gridConstraints);
		
		noteTextField=new JTextField();
		noteTextField.setPreferredSize(new Dimension(400,25));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=5;
		gridConstraints.gridwidth=5;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(noteTextField,gridConstraints);
		noteTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				noteTextFieldActionPerformed(e);
			}
		});
		
		
		photoLabel=new JLabel();
		photoLabel.setText("Photo");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=6;
		gridConstraints.insets=new Insets(10,10,0,10);
		gridConstraints.anchor=GridBagConstraints.EAST;
		getContentPane().add(photoLabel,gridConstraints);
		
		photoTextArea=new JTextArea();
		photoTextArea.setPreferredSize(new Dimension(350,35));
		photoTextArea.setFont(new Font("Arial",Font.PLAIN,12));
		photoTextArea.setEditable(false);
		photoTextArea.setLineWrap(true);
		photoTextArea.setWrapStyleWord(true);
		photoTextArea.setBackground(new Color(255,255,192));
		photoTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=2;
		gridConstraints.gridy=6;
		gridConstraints.gridwidth=4;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(photoTextArea,gridConstraints);
		
		photoButton=new JButton();
		photoButton.setText("...");
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=6;
		gridConstraints.gridy=6;
		gridConstraints.insets=new Insets(10,0,0,10);
		gridConstraints.anchor=GridBagConstraints.WEST;
		getContentPane().add(photoButton,gridConstraints);
		photoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photoButtonActionPerformed(e);
			}

			
		});
		
		searchPanel=new JPanel();
		searchButton=new JButton[26];
		
		searchPanel.setPreferredSize(new Dimension(240,160));
		searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
		searchPanel.setLayout(new GridBagLayout());
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=1;
		gridConstraints.gridy=7;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,10,0);
		gridConstraints.anchor=GridBagConstraints.CENTER;
		getContentPane().add(searchPanel,gridConstraints);
		
		int x=0;
		int y=0;
		//Create Position of 26 Button.
		for(int i=0;i<26;i++){
			searchButton[i]=new JButton(); //button
			searchButton[i].setText(String.valueOf((char)(65+i)));
			searchButton[i].setFont(new Font("Arial",Font.BOLD,12));
			searchButton[i].setMargin(new Insets(-10,-10,-10,-10));
			sizeButton(searchButton[i],new Dimension(37,27));
			searchButton[i].setBackground(Color.YELLOW);
			searchButton[i].setFocusable(false);
			gridConstraints=new GridBagConstraints();
			gridConstraints.gridx=x;
			gridConstraints.gridy=y;
			searchPanel.add(searchButton[i],gridConstraints);
			// Add method
			searchButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchButtonActionPerformed(e);
				}
				
			});
			x++;
			//six Button Per Row
			if(x%6==0) {
				x=0;
				y++;
			}
			
		}
		
		
		photoPanel=new PhotoPanel();
		photoPanel.setPreferredSize(new Dimension(240,160));
		gridConstraints=new GridBagConstraints();
		gridConstraints.gridx=4;
		gridConstraints.gridy=7;
		gridConstraints.gridwidth=3;
		gridConstraints.insets=new Insets(10,0,10,10);
		gridConstraints.anchor=GridBagConstraints.CENTER;
		getContentPane().add(photoPanel,gridConstraints);
		
		
		pack();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(0.5*(screenSize.width-getWidth())),(int)(0.5*(screenSize.height-getHeight())),getWidth(),getHeight());
		
		
		
		int n;
		
	    try {	
		    BufferedReader inputFile=new BufferedReader(new FileReader("inventory.txt"));
		    numberEntries=Integer.valueOf(inputFile.readLine()).intValue();
		    n=Integer.valueOf(inputFile.readLine()).intValue();
		    if(numberEntries!=0) {
			    for(int i=0;i<numberEntries;i++) {
				    myInventory[i]=new InventoryItem();
				    myInventory[i].description=inputFile.readLine();
				    myInventory[i].location=inputFile.readLine();
	    			myInventory[i].serialNumber=inputFile.readLine();
		    		myInventory[i].markedIndicator=Boolean.valueOf(inputFile.readLine()).booleanValue();
			    	myInventory[i].purchasePrice=inputFile.readLine();
		    		myInventory[i].purchaseDate=inputFile.readLine();
		    		myInventory[i].purchaseLocation=inputFile.readLine();
			    	myInventory[i].note=inputFile.readLine();
		    		myInventory[i].photoFile=inputFile.readLine();
					
				}
			}
		    
			//Read in combo box elements.
			if(n!=0) {
				for(int i=0;i<n;i++) {
					String str;
					str=inputFile.readLine();
					locationComboBox.addItem(str);
				}
			}
			inputFile.close();
			currentEntry=1;
			showEntry(currentEntry);
			
		}catch(Exception ex) {
			numberEntries=0;
			currentEntry=0;
			
		}
		if(numberEntries==0) {
			newButton.setEnabled(false);
			deleteButton.setEnabled(false);
			nextButton.setEnabled(false);
			previousButton.setEnabled(false);
			printButton.setEnabled(false);
		}
		
	}
	
	private void exitForm(WindowEvent evt){
		System.exit(0);
		
    }
	
	private void sizeButton(JButton b, Dimension bSize){
		
		b.setMaximumSize(bSize);
		b.setPreferredSize(bSize);
		b.setMinimumSize(bSize);
	}
	private void newButtonActionPerformed(ActionEvent e) throws ParseException{
		checkSave();
		blankvalues();
		
		
	}
	private void deleteButtonActionPerformed(ActionEvent e) throws ParseException{
		if(JOptionPane.showConfirmDialog(null,"Are u sure u want to delete this item??","DeleteInventoryItem",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.NO_OPTION)
			return;
		deleteEntry(currentEntry);
		if(numberEntries==0) {
			currentEntry=0;
			blankvalues();
		}
		else {
			currentEntry--;
			if(currentEntry==0) 
				currentEntry=1;
				
			showEntry(currentEntry);
		}
	
		
	}
	private void saveButtonActionPerformed(ActionEvent e){
		itemTextField.setText(itemTextField.getText().trim());
		if(itemTextField.getText().equals(" ")){
			JOptionPane.showConfirmDialog(null, "Must have item description","Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			itemTextField.requestFocus();
			return;
		}
		if(newButton.isEnabled()) {
			deleteEntry(currentEntry);
		}
		String s=itemTextField.getText();
		itemTextField.setText(s.substring(0,1).toUpperCase()+s.substring(0));
		numberEntries++;
		currentEntry=1;
		if(numberEntries!=1) {
			do {
				//if(itemTextField.getText().compareTo(myInventory[currentEntry-1].description<0))
					//break;
				currentEntry++;
			}while(currentEntry<numberEntries);
		}
		if(currentEntry!=numberEntries) {
			for(int i=numberEntries;i>=currentEntry;i--) {
				myInventory[i-1]=myInventory[i-2];
				myInventory[i-2]=new InventoryItem();
				
			}
		}
		
		myInventory[currentEntry-1]=new InventoryItem();
		myInventory[currentEntry-1].description=itemTextField.getText();
		myInventory[currentEntry-1].location=locationComboBox.getSelectedItem().toString();
		//myInventory[currentEntry-1].marked=markedCheckBox.isSelected();
		myInventory[currentEntry-1].serialNumber=serialTextField.getText();
		myInventory[currentEntry-1].purchasePrice=priceTextField.getText();
		myInventory[currentEntry-1].purchaseDate=(String) dateToString(dateDateChooser.getDate());
		myInventory[currentEntry-1].purchaseLocation=storeTextField.getText();
		myInventory[currentEntry-1].photoFile=photoTextArea.getText();
		myInventory[currentEntry-1].note=noteTextField.getText();
		showEntry(currentEntry);
		if(numberEntries<maximumEntries)
			newButton.setEnabled(true);
		
		else
			newButton.setEnabled(false);
		deleteButton.setEnabled(true);
		printButton.setEnabled(true);
		
		
		
		
	}
	private void previousButtonActionPerformed(ActionEvent e){
		checkSave();
		currentEntry--;
		showEntry(currentEntry);
		
		
	}
	private void nextButtonActionPerformed(ActionEvent e){
		checkSave();
		currentEntry++;
		showEntry(currentEntry);
		
	}
	private void itemTextFieldAcionPerformed(ActionEvent e) {
		locationComboBox.requestFocus();
		
	}
	private void locationComboBoxActionPerformed(ActionEvent e) {
		serialTextField.requestFocus();
		
	}
	private void serialTextFieldActionPerformed(ActionEvent e) {
		priceTextField.requestFocus();
		
	}
	private void priceTextFieldActionPerformed(ActionEvent e) {
		dateDateChooser.requestFocus();
		
	}
	private void dateDateChooserPropertyChange(PropertyChangeEvent e) {
		storeTextField.requestFocus();
		
	}
	private void storeTextFieldActionPerformed(ActionEvent e) {
	     noteTextField.requestFocus();
		
	}
	private void noteTextFieldActionPerformed(ActionEvent e) {
		photoButton.requestFocus();
		
	}
	private void printButtonActionPerformed(ActionEvent e){
		//lastPage=(int)(1+(numberEntries-1)/entriesPerPage);
		PrinterJob InventoryPrinterJob=PrinterJob.getPrinterJob();
		//InventoryPrinterJob.setPrintable(new InventoryDocument());
		if(InventoryPrinterJob.printDialog()) {
			try {
				InventoryPrinterJob.print();
			}catch(PrinterException ex) {
				JOptionPane.showConfirmDialog(null,ex.getMessage(),"Print Error",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private void photoButtonActionPerformed(ActionEvent e) {
	    
		JFileChooser openChooser=new JFileChooser();
		openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		openChooser.setDialogTitle("Open Photo File");
		openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg"));
		if(openChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
          showPhoto(openChooser.getSelectedFile().toString());
	}
	private void searchButtonActionPerformed(ActionEvent e) {
		int i;
		if(numberEntries==0)
			return;
		String letterClicked=e.getActionCommand();
		i=0;
		do {
			if(myInventory[i].description.substring(0,1).equals(letterClicked)) {
				currentEntry=i+1;
				showEntry(currentEntry);
				return;
			}
			i++;
		}while(i<numberEntries);
		JOptionPane.showConfirmDialog(null,"No"+letterClicked+"inventory Item","None Found",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	
		
	}
	private void exitButtonActionPerformed(ActionEvent e){
		
		System.exit(0);
		
	}
	private void showEntry(int j) {
		// Display Entry j (1 to numberEntries)
		itemTextField.setText(myInventory[j-1].description);
		locationComboBox.setSelectedItem(myInventory[j-1].location);
		markedCheckBox.setSelected(myInventory[j-1].markedIndicator);
		serialTextField.setText(myInventory[j-1].serialNumber);
		priceTextField.setText(myInventory[j-1].purchasePrice);
		dateDateChooser.setDate(stringToDate(myInventory[j-1].purchaseDate));
		storeTextField.setText(myInventory[j-1].purchaseLocation);
		noteTextField.setText(myInventory[j-1].note);
		showPhoto(myInventory[j-1].photoFile);
		itemTextField.requestFocus();
		
	}
	private void showPhoto(String photoFile) {
		if(!photoFile.equals("")) {
			try {
				photoTextArea.setText(photoFile);
			}catch(Exception ex) {
				photoTextArea.setText("");
			}
		}
		else {
			photoTextArea.setText("");
		}
		photoPanel.repaint();
		
	}
	private void blankvalues() throws ParseException {
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
		saveButton.setEnabled(true);
		previousButton.setEnabled(false);
		nextButton.setEnabled(false);
		printButton.setEnabled(false);
		itemTextField.setText(" ");
		locationComboBox.setSelectedItem(" ");
		markedCheckBox.setSelected(false);
		serialTextField.setText(" ");
		priceTextField.setText(" ");
		String date = " ";
		java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		dateDateChooser.setDate(date2);
		storeTextField.setText(" ");
		noteTextField.setText(" ");
		photoTextArea.setText(" ");
		photoPanel.repaint();
		itemTextField.requestFocus();
		
	}

	private Date stringToDate(String s) {
		int m=Integer.valueOf(s.substring(0,2)).intValue()-1;
		int d=Integer.valueOf(s.substring(3,5)).intValue();
		int y=Integer.valueOf(s.substring(6)).intValue()-1900;
		return(new Date(y,m,d));
	}
	
	@SuppressWarnings("deprecation")
	private String dateToString(Date dd){
		String yString=String.valueOf(dd.getYear()+1900); 
		int m=dd.getMonth()+1;
		String mString=new DecimalFormat("00").format(m);
		int d=dd.getDate();
		String dString=new DecimalFormat("00").format(d);
		return(mString + "/"+ dString+"/"+yString);	
	}

	private void deleteEntry(int j) {
		if(j!=numberEntries) {
			for(int i=j;i<numberEntries;i++) {
				myInventory[i-1]=new InventoryItem();
				myInventory[i-1]=myInventory[i];
				
			}
		}
		numberEntries--;
		
	}
	private void checkSave() {
		boolean edited=false;
		if(!myInventory[currentEntry-1].description.equals(itemTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].location.equals(locationComboBox.getSelectedItem().toString()))
			edited=true;
		//else if(myInventory[currentEntry-1].marked!=markedCheckBox.isSelected())
			//edited=true;
		else if(!myInventory[currentEntry-1].serialNumber.equals(serialTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].purchasePrice.equals(priceTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].purchaseDate.equals(dateToString(dateDateChooser.getDate())))
			edited=true;
		else if(!myInventory[currentEntry-1].note.equals(noteTextField.getText()))
			edited=true;
		else if(!myInventory[currentEntry-1].photoFile.equals(photoTextArea.getText()))
			edited=true;
		if(edited) {
			if(JOptionPane.showConfirmDialog(null,"You have edited this item.Do u want to save changes??","Save Item",JOptionPane. YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
		saveButton.doClick();
			}
		
	}

	private Object dateToString(java.util.Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}
class PhotoPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Graphics2D g2D=(Graphics2D)g;
		super.paintComponent(g2D);
		// Draw Border
		g2D.setPaint(Color.BLACK);
		g2D.draw(new Rectangle2D.Double(0, 0, getWidth()-1, getHeight()-1));
		
		//show photo
		Image photoImage=new ImageIcon(HomeInventory.photoTextArea.getText()).getImage();
		int w=getWidth();
		int h=getHeight();
		double rWidth=(double) getWidth()/(double)photoImage.getWidth(null);
		double rHeight=(double) getHeight()/(double)photoImage.getHeight(null);
		
		if(rWidth>rHeight) {
			// Leave Height at Display height change width by amount height is changed.
			w=(int)(photoImage.getWidth(null)*rHeight);
		}
		else {
			// Leave width at display width, change width by amount height is changed
			h=(int)(photoImage.getHeight(null)*rWidth);
		}
		// Center in panel
		g2D.drawImage(photoImage,(int)(0.5*(getWidth()-w)),(int)(0.5*(getHeight()-h)),w,h,null);
		
		//g2D.dispose();
	}
}

class InventoryDocument implements Printable{
	public int print(Graphics g,PageFormat pf,int pageIndex) {
		Graphics2D g2D=(Graphics2D)g;
		if((pageIndex+1)>HomeInventory.lastPage) {
			return NO_SUCH_PAGE;
		}
		int i,iEnd;
		
		g2D.setFont(new Font("Arail",Font.BOLD,14));
		g2D.drawString("Home Inventory Items - Page"+String.valueOf(pageIndex+1),(int)pf.getImageableX(),(int)(pf.getImageableY()+25));
		// get Starting Y
		
		int dy=(int)g2D.getFont().getStringBounds("S", g2D.getFontRenderContext()).getHeight();
		int y=(int)(pf.getImageableY()+4*dy);
		iEnd=HomeInventory.numberEntries;
		if(iEnd>HomeInventory.numberEntries) {
			iEnd=HomeInventory.numberEntries;
		}
		for(i=0+HomeInventory.entriesPerPage*pageIndex;i<iEnd;i++) {
			Line2D.Double dividingLine=new Line2D.Double(pf.getImageableX(),y,pf.getImageableX()+pf.getImageableWidth(),y);
			g2D.draw(dividingLine);
				
			y=y+dy;
			g2D.setFont(new Font("Arial",Font.BOLD,12));
			g2D.drawString(HomeInventory.myInventory[i].description,(int)pf.getImageableX(),y);
				
			y=y+dy;
			g2D.setFont(new Font("Arial",Font.BOLD,12));
			g2D.drawString("Location: "+HomeInventory.myInventory[i].location,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			if(HomeInventory.myInventory[i].markedIndicator) {
				g2D.drawString("Item is marked with identifying information.",(int)(pf.getImageableX()+25),y);
			}
			else {
				g2D.drawString("Item is NOT marked with indentifying information.",(int)(pf.getImageableX()+25),y);
			}
				
			y=y+dy;
			g2D.drawString("Serial Number: "+HomeInventory.myInventory[i].serialNumber,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			g2D.drawString("Price: $"+HomeInventory.myInventory[i].purchasePrice+", Purchased on: "+HomeInventory.myInventory[i].purchaseDate,(int)(pf.getImageableX()+25) ,y);
				
			y=y+dy;
			g2D.drawString("Purchased at: "+HomeInventory.myInventory[i].purchaseLocation,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			g2D.drawString("Note: "+HomeInventory.myInventory[i].note,(int)(pf.getImageableX()+25),y);
				
			y=y+dy;
			try {
				// Maintain Original width/height ratio.
				Image InventoryImage=new ImageIcon(HomeInventory.myInventory[i].photoFile).getImage();
				double ratio=(double)(InventoryImage.getWidth(null))/(double)(InventoryImage.getHeight(null));
				g2D.drawImage(InventoryImage,(int)(pf.getImageableX()+25),y,(int)(100*ratio),100,null);
			}
			catch(Exception ex) {
			    // HAVE  place to go in case image file does not open		
			}
				
			y=y+2*dy+100;
		}
		return PAGE_EXISTS;
  }
}
	


