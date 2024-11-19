import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.SwingConstants;

public class CalorimetryLabFrame {

	private JFrame frame; // main frame (base for the GUI)
	
	public static void main(String[] args) {
		
		FlatLightLaf.setup(); // Flatlightlaf is used to change the base look of the program
		
		EventQueue.invokeLater(new Runnable() { // attempt the "run()" method
			public void run() {
				try { // try catch to open frame
					CalorimetryLabFrame window = new CalorimetryLabFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//initiate flatlightlaf by changing look and feel of UI manager
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize FlatLightLaf" );
		}
	}

	public CalorimetryLabFrame() {
		initialize();
	}

	//initalization of frame (setup of GUI)
	private void initialize() {
		
		//basic frame setup
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//JTabbedPane used to allow for multiple page layout
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, -35, 1264, 716);
		frame.getContentPane().add(tabbedPane);
		
		//Instruction panel (page 1)
		JPanel instructionPanel = new JPanel();
		tabbedPane.addTab("", null, instructionPanel, null);
		instructionPanel.setLayout(null);
		
		//Instruction text
		JLabel lblNewLabel = new JLabel("Instructions");
		lblNewLabel.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
		lblNewLabel.setBounds(45, 76, 301, 44);
		instructionPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1. Drag the fuel under the calorimeter");
		lblNewLabel_1.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 117, 240, 25);
		instructionPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("2. Take note of temperature change");
		lblNewLabel_1_1.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(45, 140, 240, 25);
		instructionPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("3. Repeat until all fuels used");
		lblNewLabel_1_1_1.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(45, 163, 240, 25);
		instructionPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("4. Proceed to analysis");
		lblNewLabel_1_2.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(45, 187, 240, 25);
		instructionPanel.add(lblNewLabel_1_2);
		
		// Button used to switch to experiment panel
		JButton btnNewButton = new JButton("Proceed to Experiment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton.setBounds(1078, 629, 151, 31);
		instructionPanel.add(btnNewButton);
		
		//Cover calorimeter image
		JLabel coverImgLabel = new JLabel("");
		Image coverImg = new ImageIcon(this.getClass().getResource("/coverImg.png")).getImage();
		coverImgLabel.setIcon(new ImageIcon(coverImg));
		coverImgLabel.setBounds(-32, 47, 983, 637);
		instructionPanel.add(coverImgLabel);
		
		//Title
		JLabel lblCalorimetryLab = new JLabel("Calorimetry Lab");
		lblCalorimetryLab.setFont(new Font("Quicksand Medium", Font.BOLD, 40));
		lblCalorimetryLab.setBounds(42, 9, 367, 58);
		instructionPanel.add(lblCalorimetryLab);
		
		//Result panel (page 3) (it is neccessary to be second, because all of the variables need to be initialized for fillOutResults method)
		JPanel resultPanel = new JPanel();
		tabbedPane.addTab("", null, resultPanel, null);
		resultPanel.setLayout(null);
		
		//Cheeto Results Panel
		JPanel cheetoResultsPanel = new JPanel();
		cheetoResultsPanel.setBounds(0, 11, 420, 677);
		resultPanel.add(cheetoResultsPanel);
		cheetoResultsPanel.setLayout(null);
		
		Image cheetoResultsImg = new ImageIcon(this.getClass().getResource("cheetoBig.png")).getImage();
		JLabel cheetoResultsLbl = new JLabel(new ImageIcon(cheetoResultsImg));
		cheetoResultsLbl.setBounds(160, 35, 100, 100);
		cheetoResultsPanel.add(cheetoResultsLbl);
		
		JLabel lblCheeto = new JLabel("Cheeto");
		lblCheeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheeto.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
		lblCheeto.setBounds(0, 0, 420, 44);
		cheetoResultsPanel.add(lblCheeto);
		
		JLabel avgValueslbl1 = new JLabel("Average Values: ");
		avgValueslbl1.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		avgValueslbl1.setBounds(25, 105, 113, 25);
		cheetoResultsPanel.add(avgValueslbl1);
		
		JTextArea cheetoAvgs = new JTextArea();
		cheetoAvgs.setBackground(new Color(255, 255, 255));
		cheetoAvgs.setBounds(25, 135, 370, 180);
		cheetoAvgs.setEditable(false);
		cheetoResultsPanel.add(cheetoAvgs);
		
		JLabel enthalpyLabel1 = new JLabel("Calculating Enthalpy of Combustion:");
		enthalpyLabel1.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		enthalpyLabel1.setBounds(25, 326, 212, 25);
		cheetoResultsPanel.add(enthalpyLabel1);
		
		JTextArea cheetoEnthalpy = new JTextArea();
		cheetoEnthalpy.setBackground(new Color(255, 255, 255));
		cheetoEnthalpy.setBounds(25, 360, 370, 290);
		cheetoEnthalpy.setEditable(false);
		cheetoResultsPanel.add(cheetoEnthalpy);
		
		//Marshmallow Results Panel
		JPanel marshmallowResultsPanel = new JPanel();
		marshmallowResultsPanel.setBounds(419, 11, 420, 677);
		resultPanel.add(marshmallowResultsPanel);
		marshmallowResultsPanel.setLayout(null);
		
		Image marshmallowResultsImg = new ImageIcon(this.getClass().getResource("marshmallowBig.png")).getImage();
		JLabel marshmallowResultsLbl = new JLabel(new ImageIcon(marshmallowResultsImg));
		marshmallowResultsLbl.setBounds(160, 35, 100, 100);
		marshmallowResultsPanel.add(marshmallowResultsLbl);
		
		JLabel lblMarshmallow = new JLabel("Marshmallow");
		lblMarshmallow.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarshmallow.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
		lblMarshmallow.setBounds(0, 0, 420, 44);
		marshmallowResultsPanel.add(lblMarshmallow);
		
		JLabel avgValueslbl2 = new JLabel("Average Values: ");
		avgValueslbl2.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		avgValueslbl2.setBounds(25, 105, 113, 25);
		marshmallowResultsPanel.add(avgValueslbl2);
		
		JTextArea marshmallowAvgs = new JTextArea();
		marshmallowAvgs.setBackground(new Color(255, 255, 255));
		marshmallowAvgs.setBounds(25, 135, 370, 180);
		marshmallowAvgs.setEditable(false);
		marshmallowResultsPanel.add(marshmallowAvgs);
		
		JLabel enthalpyLabel2 = new JLabel("Calculating Enthalpy of Combustion:");
		enthalpyLabel2.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		enthalpyLabel2.setBounds(25, 326, 212, 25);
		marshmallowResultsPanel.add(enthalpyLabel2);
		
		JTextArea marshmallowEnthalpy = new JTextArea();
		marshmallowEnthalpy.setBackground(new Color(255, 255, 255));
		marshmallowEnthalpy.setBounds(25, 362, 370, 290);
		marshmallowEnthalpy.setEditable(false);
		marshmallowResultsPanel.add(marshmallowEnthalpy);
		
		//Peanut Results Panel
		JPanel peanutResultsPanel = new JPanel();
		peanutResultsPanel.setBounds(839, 11, 420, 677);
		resultPanel.add(peanutResultsPanel);
		peanutResultsPanel.setLayout(null);
		
		Image peanutResultsImg = new ImageIcon(this.getClass().getResource("peanutBig.png")).getImage();
		JLabel peanutResultsLbl = new JLabel(new ImageIcon(peanutResultsImg));
		peanutResultsLbl.setBounds(160, 35, 100, 100);
		peanutResultsPanel.add(peanutResultsLbl);
		
		JLabel lblPeanut = new JLabel("Peanut");
		lblPeanut.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeanut.setFont(new Font("Quicksand Medium", Font.BOLD, 25));
		lblPeanut.setBounds(0, 0, 420, 44);
		peanutResultsPanel.add(lblPeanut);
		
		JLabel avgValueslbl3 = new JLabel("Average Values: ");
		avgValueslbl3.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		avgValueslbl3.setBounds(25, 105, 113, 25);
		peanutResultsPanel.add(avgValueslbl3);
		
		JTextArea peanutAvgs = new JTextArea();
		peanutAvgs.setBackground(new Color(255, 255, 255));
		peanutAvgs.setBounds(25, 136, 370, 180);
		peanutAvgs.setEditable(false);
		peanutResultsPanel.add(peanutAvgs);
		
		JLabel enthalpyLabel3 = new JLabel("Calculating Enthalpy of Combustion:");
		enthalpyLabel3.setFont(new Font("Quicksand Medium", Font.PLAIN, 12));
		enthalpyLabel3.setBounds(25, 327, 212, 25);
		peanutResultsPanel.add(enthalpyLabel3);
		
		JTextArea peanutEnthalpy = new JTextArea();
		peanutEnthalpy.setBackground(new Color(255, 255, 255));
		peanutEnthalpy.setBounds(25, 363, 370, 290);
		peanutEnthalpy.setEditable(false);
		peanutResultsPanel.add(peanutEnthalpy);
		
		//Experiment Panel (page 2)
		JPanel experimentPanel = new JPanel();
		tabbedPane.addTab("", null, experimentPanel, null);
		experimentPanel.setLayout(null);
		
		//Glass pane to block input (activated when fire animation active)
		JPanel glassPane = new JPanel();
		glassPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {} //do nothing when glass is clicked
		});
		glassPane.setBounds(0, 11, 1259, 677);
		glassPane.setOpaque(false);
		experimentPanel.add(glassPane);
		
		//Fuels (cheeto, marshmallow, peanut)
		int xCheeto = 661;
		int yCheeto = 99;
		JPanel fuelPanel1 = new JPanel();
		fuelPanel1.setBounds(xCheeto, yCheeto, 85, 87);
		experimentPanel.add(fuelPanel1);
		
		int xMarshmallow = 850;
		int yMarshmallow = 99;
		JPanel fuelPanel2 = new JPanel();
		fuelPanel2.setBounds(xMarshmallow, yMarshmallow, 85, 87);
		experimentPanel.add(fuelPanel2);
		
		int xPeanut = 1031;
		int yPeanut = 99;
		JPanel fuelPanel3 = new JPanel();
		fuelPanel3.setBounds(xPeanut, yPeanut, 85, 87);
		experimentPanel.add(fuelPanel3);
		
		// Main Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(552, 286, 678, 220);
		experimentPanel.add(scrollPane);

		//DefaultTableModel used for simplicity
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"Trial #", "Fuel", "T\u2092", "T\u0562", "M\u2092", "M\u0562", "c"}) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //Set all cells uneditable
		       return false;
		    }
		};
		
		JTable table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setBackground(new Color(240, 240, 240));
		
		JLabel lblNewLabel_2_1 = new JLabel("Experiment Table: ");
		lblNewLabel_2_1.setFont(new Font("Quicksand Light", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(552, 238, 616, 47);
		experimentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pick Your Fuel:");
		lblNewLabel_2.setFont(new Font("Quicksand Light", Font.BOLD, 25));
		lblNewLabel_2.setBounds(552, 40, 616, 47);
		experimentPanel.add(lblNewLabel_2);
		
		
		//FUEL STUFF --> (int xLocation, int yLocation, String fuelName, double tFinal, double mInitial, double mFinal, String filename)
		JPanel calorimeterPanel = new JPanel();
		calorimeterPanel.setBounds(276, 413, 70, 70);
		calorimeterPanel.setOpaque(false);
		experimentPanel.add(calorimeterPanel);
		
		// Fire in calorimeter
		ImageIcon fireCalorimeterGif = new ImageIcon(this.getClass().getResource("calorimeterFireGif.gif"));
		JLabel fireCalorimeter = new JLabel(fireCalorimeterGif);
		fireCalorimeter.setBounds(0, 0, 70, 70);
		calorimeterPanel.add(fireCalorimeter);
		
		// Calorimeter cheeto
		Image calorimeterCheetoImg = new ImageIcon(this.getClass().getResource("cheetoSmall.png")).getImage();
		calorimeterPanel.setLayout(null);
		JLabel calorimeterCheeto = new JLabel(new ImageIcon(calorimeterCheetoImg));
		calorimeterCheeto.setBounds(0, 0, 70, 70);
		calorimeterPanel.add(calorimeterCheeto);
		
		// Calorimeter marshmallow
		Image calorimeterMarshmallowImg = new ImageIcon(this.getClass().getResource("marshmallowSmall.png")).getImage();
		JLabel calorimeterMarshmallow = new JLabel(new ImageIcon(calorimeterMarshmallowImg));
		calorimeterMarshmallow.setBounds(0, 0, 70, 70);
		calorimeterPanel.add(calorimeterMarshmallow);
		
		// Calorimeter marshmallow
		Image calorimeterPeanutImg = new ImageIcon(this.getClass().getResource("peanutSmall.png")).getImage();
		JLabel calorimeterPeanut = new JLabel(new ImageIcon(calorimeterPeanutImg));
		calorimeterPeanut.setBounds(0, 0, 70, 70);
		calorimeterPanel.add(calorimeterPeanut);

		// Create fuel objects for cheeto, marshmallow, and peanut
		
		// Cheeto object
		Fuel cheeto = new Fuel(xCheeto, yCheeto, "Cheeto", 37.0, 3.6, 2.2, "cheetoBig.png");
		cheeto.createFuel(fuelPanel1, calorimeterPanel, fireCalorimeter, calorimeterCheeto, glassPane, model);
		
		//marshmallow object
		Fuel marshmallow = new Fuel(xMarshmallow, yMarshmallow, "Marshmallow", 27.0, 6.0, 4.2, "marshmallowBig.png");
		marshmallow.createFuel(fuelPanel2, calorimeterPanel, fireCalorimeter, calorimeterMarshmallow, glassPane, model);
		
		//peanut object
		Fuel peanut = new Fuel(xPeanut, yPeanut, "Peanut", 52.5, 0.8, 0.2, "peanutBig.png");
		peanut.createFuel(fuelPanel3, calorimeterPanel, fireCalorimeter, calorimeterPeanut, glassPane, model);

		// Button that leads to Result page (3)
		JButton btnSeeResults = new JButton("See Results");
		btnSeeResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if (peanut.alreadyBurned && marshmallow.alreadyBurned && peanut.alreadyBurned) {
					// Fill out results for each fuel
					fillOutResults("Cheeto", model, cheetoAvgs, cheetoEnthalpy);
					fillOutResults("Marshmallow", model, marshmallowAvgs, marshmallowEnthalpy);
					fillOutResults("Peanut", model, peanutAvgs, peanutEnthalpy);
					tabbedPane.setSelectedIndex(1); // go to page 3 (1 - because of ordering)
				} else {
					JOptionPane.showMessageDialog(frame, "You have to burn all three fuels at least once before proceeding.");
				}

			}
		});
		btnSeeResults.setBounds(1082, 628, 151, 31);
		experimentPanel.add(btnSeeResults);

		// Main calorimeter image (background)
		Image calorimeterImg = new ImageIcon(this.getClass().getResource("/calorimeterFullProcessed.png")).getImage();
		JLabel calorimeterImgLabel = new JLabel("");
		calorimeterImgLabel.setIcon(new ImageIcon(calorimeterImg));
		calorimeterImgLabel.setBounds(0, -28, 1272, 743);
		experimentPanel.add(calorimeterImgLabel);
	}
	
	public static void fillOutResults(String fuelName, DefaultTableModel model, JTextArea avgsTextArea, JTextArea enthalpyTextArea) {
		
		//Averages
		int rowCount = model.getRowCount();
		int fuelCount = 0;
		
		//initialTemp --> liquidMass and C are constants
		final double liquidMass = 35; //(35 ml of water in this case)
		final double c = 4.18;
		final double initalTemp = 23.0;

		double finalTemp = 0;
		double initalMass = 0;
		double finalMass = 0;

		// Iterate through the table and get all of the values that match with fuel name
		for(int i = 0; i < rowCount; i++) {
			
			if (model.getValueAt(i, 1).equals(fuelName)) {
				finalTemp += Double.parseDouble(String.format("%s", model.getValueAt(i, 3)).replaceFirst(".$","")); //get finalTemp value from row 3
				initalMass += Double.parseDouble(String.format("%s", model.getValueAt(i, 4)).replaceFirst(".$","")); //get initalMass value from row 4
				finalMass += Double.parseDouble(String.format("%s", model.getValueAt(i, 5)).replaceFirst(".$","")); //get finalMass value from row 5
				
				fuelCount++; // necessary to calculate average (total value) / (amt of sub values)
			}
		}
		
		//calculate averages
		finalTemp /= fuelCount;
		initalMass /= fuelCount;
		finalMass /= fuelCount;
		
		//output to textarea 
		avgsTextArea.append(String.format("%nInital Temperature: %.1f° %n%n", initalTemp));
		
		avgsTextArea.append(String.format("Final Temperature: %.2f° %n%n", finalTemp));
		
		avgsTextArea.append(String.format("Initial Mass: %.2fg %n%n", initalMass));
		
		avgsTextArea.append(String.format("Final Mass: %.2fg %n%n", finalMass));
		
		avgsTextArea.append(String.format(String.format("Heat Capacity: %.2f J/g*C %n%n", c)));
		
		//Enthalpy
		// ΔH = q = mcΔT
		enthalpyTextArea.append(String.format("Total Enthalpy Change (%.1fg of liquid) : %nΔH = -q = mcΔT%n%n", liquidMass));
		
		enthalpyTextArea.append(String.format("ΔH = -q = (%.2f g )(%.2f (J/g*C°) )(%.2f C° - %.2f C°)%n%n", liquidMass, c, finalTemp, initalTemp));
		
		enthalpyTextArea.append(String.format("ΔH = -q = (%.2f g )(%.2f (J/g*C°) )(%.2f C°)%n%n", liquidMass, c, (finalTemp - initalTemp)));
		
		double enthalpyChange = -liquidMass * c * (finalTemp - initalTemp);
		enthalpyTextArea.append(String.format("ΔH = -q = %.2f J%n%n", enthalpyChange));
		
		enthalpyTextArea.append(String.format("Finding J/g:%n  = J/m%n%n", liquidMass * c * (finalTemp - initalTemp)));
		
		enthalpyTextArea.append(String.format("  = (%.2f J)/(%.2f g - %.2f g)%n%n", enthalpyChange, initalMass, finalMass));
		
		enthalpyTextArea.append(String.format("  = (%.2f J)/(%.2f g)%n%n", enthalpyChange, initalMass - finalMass));
		
		enthalpyTextArea.append(String.format("  = %.2f J/g or Kj/Kg%n%n", enthalpyChange / (initalMass - finalMass)));
	}
}
