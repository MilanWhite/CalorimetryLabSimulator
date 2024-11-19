import java.awt.Color;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Fuel {
	//starting location
	final int xLocation;
	final int yLocation;
	
	String fuelName;
	String filename;
	double tInital = 23.0;
	double tFinal;
	double mInitial;
	double mFinal;

	// alreadyBurned used to check if the fuel has been burned
	// in order to limit access to next page
	boolean alreadyBurned;
	
	public Fuel(int xLocation, int yLocation, String fuelName, double tFinal, double mInitial, double mFinal, String filename) {
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.fuelName = fuelName;
		this.tFinal = tFinal;
		this.mInitial = mInitial;
		this.mFinal = mFinal;
		this.filename = filename;
		this.alreadyBurned = false;
	}
	
	public void createFuel(JPanel fuelPanel, JPanel calorimeterPanel, JLabel fireCalorimeter, JLabel calorimeterFuel, JPanel glassPane, DefaultTableModel model) {
		//make the fuel, fire in the calorimeter invisible as well as the glass pane until needed
		fuelPanel.setOpaque(false);
		glassPane.setVisible(false);
		calorimeterPanel.setBorder(null);
		fireCalorimeter.setVisible(false);
		calorimeterFuel.setVisible(false);
		
		Image fuelImg = new ImageIcon(this.getClass().getResource(this.filename)).getImage();
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(fuelImg));
		fuelPanel.add(imageLabel);

		// THREADGROUP (to limit amount of threads active)
		ThreadGroup threadGroup = new ThreadGroup("threadGroup");
		
		int offSet = 3000; //time offset
		// Multipliers used to randomize values
		double minMultiplier = 0.9;
		double maxMultiplier = 1.1;
		fuelPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				calorimeterPanel.setBorder(null);
				fuelPanel.setLocation(xLocation, yLocation); // reset to starting position when mouse released
			}
		});

		// listen for mouse drag
		fuelPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// make border around calorimeter panel visible to encourage contact
				calorimeterPanel.setBorder(new LineBorder(new Color(49, 49, 49), 2, true));
				
				// set fuel position to mouse position when dragged
				fuelPanel.setLocation((int)fuelPanel.getLocation().getX() + e.getX() - (fuelPanel.getWidth()/2),(int)fuelPanel.getLocation().getY() + e.getY() - (fuelPanel.getHeight()/2));
				
				// check if collision between calorimeterPanel and fuel
				if (withinDims(fuelPanel, calorimeterPanel)) {
					fuelPanel.setLocation(xLocation, yLocation);
					alreadyBurned = true; // make fuel already burned

					//limit number of threads to one (to limit amount of times thread is run to 1)
					if (threadGroup.activeCount() == 0) {
						// run thread of a few seconds to allow for fire to burn (refer to offSet)
						Thread fireAndTableThread = new Thread(threadGroup, new Runnable() {
						    public void run() {
						    	// make fuel, fire and glass pane active
								calorimeterFuel.setVisible(true);
								fireCalorimeter.setVisible(true);
								glassPane.setVisible(true);
								
								try { // stop processing to allow for animation
									Thread.sleep(offSet); // time offset
								} catch (InterruptedException e) {}
								
								// deactivate
								calorimeterFuel.setVisible(false);
								fireCalorimeter.setVisible(false);
								glassPane.setVisible(false);
								
								//add row to table --> "Trial #", "Fuel", "T\u2092", "T\u0562", "M\u2092", "M\u0562", "c"
								model.addRow(new String[]{"" + (model.getRowCount() + 1) , fuelName, tInital + "°", randomize(tFinal, minMultiplier, maxMultiplier) + "°", randomize(mInitial, minMultiplier, maxMultiplier) + "g", randomize(mFinal, minMultiplier, maxMultiplier) + "g", "4.18 J/g*C"});
						    }
						});
						fireAndTableThread.start();
					}
				}
			}
		});
	}
	
	// method to check for collision
	public static boolean withinDims(JPanel fuelPanel, JPanel calorimeterPanel) {
		if (fuelPanel.getBounds().intersects(calorimeterPanel.getBounds())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// method to randomize value based on percentage multipliers (refer to minMultiplier, maxMultiplier)
	public String randomize(double initValue, double minMultiplier, double maxMultiplier) {
		return String.format("%.2f", initValue * (double)((Math.random() * (maxMultiplier - minMultiplier)) + minMultiplier));
	}
}
