import java.util.HashMap;


public class PassengerList {
	private HashMap<String,Passenger> passengersCheckedIn;
	private HashMap<String,Passenger> passengersNotCheckedIn;
	
	public PassengerList() {
		
	}
	
	/**
	 * Checks the two hash maps starting with the checked in map, if that doesn't work moves to the notCheckIn map.
	 * Returns the passenger related to the bookingRefCode, or throws an exception if there is no passenger in the list 
	 * with that bookingRefCode.
	 */
	public Passenger get(String bookingRefCode) throws IllegalReferenceCode {
		if(output = passengersCheckedIn.get(bookingRefCode) != null){
			return output;
		}else if(output = passengersNotCheckedIn.get(bookingRefCode) != null){
			return output;
		}else{
			throw new IllegalReferenceCode(bookingRefCode);
		}
	}


	/**
	 * 
	 */
	public boolean add(Passenger thePassenger, boolean checkedIn) {
		// Cant have two passengers with the same booking reference code, first need to check both hashmaps
		if(passengersCheckedIn.containsKey(thePassenger.getBookingRefCode()) || passengersNotCheckedIn.containsKey(thePassenger.getBookingRefCode())){
			// As one of the lists contains a passenger with the same key, we return false to show that it cannot be added.
			return false;
		}
		// if the method hasn't been stopped from the return above, then we can add the passenger to the right hashmap
		if(checkedIn){
			passengersCheckedIn.put(thePassenger.getBookingRefCode(),thePassenger);
		}
		else{
			passengersNotCheckedIn.put(thePassenger.getBookingRefCode(),thePassenger);
		}
		return true;
	}
	
	/**
	 * 
	 */
	public boolean remove(String bookingRefCode) { 
		if(passengersCheckedIn.containsKey(bookingRefCode)){ 		// If the checkIn map contains the booking reference
			passengersCheckedIn.remove(bookingRefCode); 			// remove from the hash map
			return true;											// And return true to show that this was successful
		}else if(passengersCheckedIn.containsKey(bookingRefCode)){	// Same as above
			passengersNotCheckedIn.remove(bookingRefCode);
			return true;
		}else{
			return false;											// If neither list contains the booking reference, return false to show remove was unsuccessful
		}
		
	}
	
	/**
	 * 
	 */
	public int getNumToCheckIn() {
		return passengersNotCheckedIn.size();
	}
	
	/**
	 * 
	 */
	public boolean checkInPassenger(String bookingRefCode) {
		if(passengersNotCheckedIn.containsKey(bookingRefCode)){
			passengersCheckedIn.put(bookingRefCode,passengersNotCheckedIn.get(bookingRefCode));
			passengersNotCheckedIn.remove(bookingRefCode);
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
