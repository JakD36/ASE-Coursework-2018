import java.util.HashMap;

public class CheckInHandler {
	
	private PassengerList passengers;
	private HashMap<String,Flight> flights;
	
	public CheckInHandler() {
		loadFlights();
		loadPassengers();
	}
	
	public boolean processPassenger(String bookingReference) {
		return true;
	}
	
	public float processBaggage(float[] dimensions, float weight) {
		return 0.0f;
	}
	
	public int getNumToCheckIn() {
		return 0;
	}
	
	public String generateReport() {
		// Follow this pattern
//		Flight code: #
//		Number of Passengers: #
//		Total Baggage Weight: #
//		Total Baggage Volume: #
//		Total Excess Fees: #
//		Capacity Exceeded: yes/no
		return "Flight code: abc123\nNumber of Passengers: 8\nTotal Baggage Weight: 5\nTotal Baggage Volume: 12\nTotal Excess Fees: 12\nCapacity Exceeded: yes";
	}
	
	private void loadPassengers() {
		
	}
	
	private void loadFlights() {
		
	}
	
}
