import java.util.HashMap;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class CheckInHandler {
	
	private PassengerList passengers;
	private HashMap<String,Flight> flights;
	
	public CheckInHandler() {
		passengers = new PassengerList();
		loadFlights();
		loadPassengers();
	}
	
	public boolean processPassenger(String bookingReference, String lastName) throws IllegalReferenceCodeException{
		if(lastName == passengers.get(bookingReference).getLastName()){ // Checks if the last name provided matches the last name associated to bookingReference
			return passengers.checkInPassenger(bookingReference);
		}
		return false;
	}
	
	public float processBaggage(String bookingRefCode ,float[] dimensions, float weight) throws IllegalReferenceCodeException{
		float fee;
		float weightFee = 0, volFee = 0;
		float multiplier = 1;
		float vol = dimensions[0]*dimensions[1]*dimensions[2];
		Flight flight = passengers.get(bookingRefCode).getFlight();
		
		float maxWeight = ( flight.getMaxBaggageWeight() / flight.getPassengerCapacity() )*0.9;
		float maxVol = ( flight.getMaxBaggageVolume() / flight.getPassengerCapacity() )*0.9;
		
		weightFee = maxWeight-weight;
		volFee = maxVol-vol;
		flight.addPassengerAndBaggage(vol,weight);
		return (weightFee+volFee)*multiplier;
	}
	
	public int getNumToCheckIn() {
		return passengers.getNumToCheckIn();
	}
	
	public String generateReports() {
		// Follow this pattern
		//		Flight code: #
		//		Number of Passengers: #
		//		Total Baggage Weight: #
		//		Total Baggage Volume: #
		//		Total Excess Fees: #
		//		Capacity Exceeded: yes/no
		String finalReport;
		for(Flight f: flights.values()){
			finalReport += f.generateReport()+"\n";
		}
		return finalReport;
	}
	
	private void loadPassengers() {
		File f = new File("passengers.txt");
		Scanner scanner = new Scanner(f);
		while (scanner.hasNextLine()) {     
			String inputLine = scanner.nextLine();   //do something with this line     
			String parts[] = inputLine.split(",");
			passengers.add(new Passenger(parts[0],parts[1],parts[2],flights.get(parts[4])),Boolean.parseBoolean(parts[4]));
		}
	}
	
	private void loadFlights() {
		File f = new File("flight.txt");
		Scanner scanner = new Scanner(f);
		while (scanner.hasNextLine()) {     
			String inputLine = scanner.nextLine();   //do something with this line     
			String parts[] = inputLine.split(",");
			flights.add(new Flight(parts[0],
					parts[1],
					parts[2],
					Integer.parseInt(parts[4]),
					Float.parseFloat(parts[5]),
					Float.parseFloat(parts[6]),
					Float.parseFloat(parts[7])));
		}
	}
	
}
