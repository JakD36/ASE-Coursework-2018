import java.util.HashMap;


public class PassengerList {
	private HashMap<String,Passenger> passengersCheckedIn;
	private HashMap<String,Passenger> passengersNotCheckedIn;
	
	public PassengerList() {
		
	}
	
	public Passenger get(String bookingRefCode) {
		Passenger test = new Passenger();
		return test;
	}

	public boolean add(Passenger thePassenger, boolean checkedIn) {
		return true;
	}
	
	public boolean remove(String bookingRefCode) {
		return true;
	}
	
	public int getNumToCheckIn() {
		return 0;
	}
	
	public boolean checkInPassenger(String bookingRefCode) {
		return true;
	}
	
}
