import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 * Program entry point. Contains main method, which creates
 * an instance of this GUI class.
 * 
 * @cite https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
 * @cite https://docstore.mik.ua/orelly/java/exp/ch12_05.htm
 * @cite https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
 */
public class GUI extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	//GUI controls need class wide access
	JTextField txtBookingRef;
	JTextField txtSurname;
	JLabel lblBookingRef;
	JLabel lblSurname;
	JLabel lblResponse;
	JButton btnCheckIn;
	CheckInHandler checkInHandler;
	
	/**
	 * Entry point to program. Creates GUI window.
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI();
	}
	
	public GUI() {
		//prevent resizing
		this.setResizable(false);
		
		//tell program to end when this window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set the title
		this.setTitle("Passenger Check-In Application");
		
		//constraints for adding items to gridbag layout
		GridBagConstraints c = new GridBagConstraints();
		
		//create a panel for the input boxes and their labels
		JPanel panel = new JPanel();
		//set its layout to gridbaglayout
		panel.setLayout(new GridBagLayout());
		
		//add a border to the panel
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		//items should fill horizontally
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//add a title label
		//use constraint to span all columns
		//of the first row
		
		JLabel title = new JLabel("Passenger Check-In Application.");
		title.setHorizontalAlignment(JLabel.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		//set margins
		c.insets = new Insets(5,5,2,5);
		panel.add(title, c);
		
		
		//set margins
		c.insets = new Insets(2,5,2,5);
		
		//add separator to row
		c.gridy = 1;
		panel.add(new JSeparator(), c);
		
		
		
		//labels below should only be a single column wide
		c.gridwidth = 1;
		
		//create booking ref and surname labels
		lblBookingRef = new JLabel("Booking Ref No:");
		lblSurname = new JLabel("Surname:");
	
		//labels for input get 0.01 x weight
		c.weightx = 0.01;

		

		//add booking ref label to 0x0
		c.gridx = 0;
		c.gridy = 2;
		panel.add(lblBookingRef, c);
		
		//add surname label to 1x0
		c.gridx = 0;
		c.gridy = 3;
		panel.add(lblSurname, c);
		
		//create text fields for booking ref and surname
		txtBookingRef = new JTextField("");
		txtSurname = new JTextField("");
		
		//text fields get 0.99 x weight
		c.weightx = 0.99;
		
		//add booking ref text field to 0x1
		c.gridx = 1;
		c.gridy = 2;
		
		panel.add(txtBookingRef,c);
		
		//add surname text field to 1x1
		c.gridx = 1;
		c.gridy = 3;
		panel.add(txtSurname, c);
		
		//add separator to row
		c.gridwidth = 3; //fill all 3 columns
		c.gridx = 0;
		c.gridy = 4;
		panel.add(new JSeparator(), c);
		
		//create and add check in button
		btnCheckIn = new JButton("Check In");
		//should take up the entire row
		c.gridx = 0;
		c.gridy = 5;
		panel.add(btnCheckIn, c);
		
		//add separator to row
		c.gridy = 6;
		panel.add(new JSeparator(), c);
		
		//set margins
		c.insets = new Insets(2,5,10,5);
		
		//create status label
		lblResponse = new JLabel("Status: ");

		//should take up the entire row
		c.gridx = 0;
		c.gridy = 7;
		panel.add(lblResponse, c);
		
		//add ActionListener to btnCheckIn button
		btnCheckIn.addActionListener(this);
		
		//add input panel to the main window
		this.add(panel);
		
		//fit window to controls
		this.pack();
		//make window 300, but keep height
		this.setSize(300, this.getHeight());
		
		//centre window
		this.setLocationRelativeTo(null);
		
		//should stay as last line of GUI creation
		//to avoid werid behaviour on mac
		this.setVisible(true);
		
		//create a CheckInHandler to load and process
		//Passengers and their Flights
		checkInHandler = new CheckInHandler();
	}
	
	/**
	 * Requests a named value for an attribute of a passenger's baggage using
	 * a dialog box.
	 * 
	 * @throws NullPointerException if the user clicks cancel.
	 * @param name the name of the value to ask for
	 * @return the value taken from the user
	 *	 * 
	 * @cite http://hajsoftutorial.com/showinputdialogget-integer-value/
	 * @cite https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
	 */
	public float getFloat(String name) throws NullPointerException {
		//set dimensions to negative value as program loops
		//input request until positive value is entered
		float dim = -1;
		
		String input;
		
		//loop until user enters a valid positive value
		while(dim < 0) {
			try {
			input = JOptionPane.showInputDialog
					(this, "Enter Baggage " + name);
			dim = Float.parseFloat(input);
			if(dim < 0) throw new NumberFormatException();

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, 
						"Invalid Width. Please reenter.",
						"Invalid width",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return dim;
	}
		
	
	@Override
	/**
	 * Triggered upon any event whose source has this class as
	 * its ActionListener.
	 * 
	 * Deals with GUI events by determining the source and
	 * taking appropriate action.
	 * 
	 * @param arg0 the source event that triggered the method
	 */
	public void actionPerformed(ActionEvent arg0) {
		//determine source of event
		//if event is btnCheckIn click, attempt to check passenger in
		if(arg0.getSource() == btnCheckIn) {
			//make booking ref uppercase
			txtBookingRef.setText
				(txtBookingRef.getText().toUpperCase());
			
			//get and validate user input
			String bookingRef = txtBookingRef.getText();
			String lastName = txtSurname.getText();
			
			//if booking ref is invalid, inform user and return
			if(!bookingRef.matches("[A-Z]{3}[0-9]{4}")) {
				lblResponse.setText("<html><font color = 'red'>"
						+ "Invalid booking reference!</font>"
						+ "</html>");
				return;
			}
			
			if(lastName.length() < 1) {
				lblResponse.setText("<html><font color = 'red'>"
						+ "Please supply a last name!</font>"
						+ "</html>");
				return;
			}
			
			//try to check user in
			try {			
				//try to check in
				checkInHandler.processPassenger(bookingRef, lastName);
								
				//declare dimension floats
				float[] dimensions = new float[3];
				
				//populate floats with information from user
				dimensions[0] = getFloat("width");
				dimensions[1] = getFloat("height");
				dimensions[2] = getFloat("depth");
				
				float weight = getFloat("weight");
				
				//try to process baggage
				float fees = checkInHandler.processBaggage(dimensions, weight);
				
				//if there are no baggage fees, inform user
				if(fees <= 0)
					lblResponse.setText("User checked in. Baggage ok.");
				//if there are baggage fees, inform user
				else {
					//format string to 2dp and use red colouring
					String feeString = String.format("£%.2f", fees);
					
					lblResponse.setText("<html>User checked in. "
							+ "Collect baggage fee: <font color = 'red'>"
							+ feeString + ".</font></html>");
				}
			//if the user presses cancel on the input dialog, a 
			//NullPointerException is thrown
			} catch (NullPointerException e) {
				//user has cancelled check in, so update status
				lblResponse.setText("<html><font color = 'red'>"
						+ "Check In Cancelled!</font></html>");
			//this exception is thrown by CheckInHandler if check in
			//details are erroneous
			} catch(IllegalReferenceCodeException e) {
				//inform user
				lblResponse.setText("<html><font color = 'red'>"
						+ "Invalid Booking!</font></html>");
			}
		}
	}
}
