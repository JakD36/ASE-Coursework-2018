import java.util.HashMap;


public class PassengerList {
	private HashMap<String,Passenger> passengersCheckedIn;
	private HashMap<String,Passenger> passengersNotCheckedIn;
	
	/**
	 * Default constructor for the PassengerList.
	 */
	public PassengerList() {
		//instantiate HashMaps
		passengersCheckedIn = new HashMap<String,Passenger>();
		passengersNotCheckedIn =new  HashMap<String,Passenger>();
	}
	
	/**
	 * Retrieves the {@link Passenger} with the matching booking reference code.
	 * Checks the two hash maps, passengersCheckedIn and passengersNotCheckedIn for the passenger with matching booking reference code. 
	 * Starting with the checked in map, if that doesn't work moves to the notCheckedIn map.
	 * 
	 * @param	bookingRefCode	the booking reference code for the passenger wanted.
	 * @return	The passenger with the matching booking reference code.
	 * @throws	IllegalReferenceCodeException	If there is no passenger with a matching booking reference code in either of the two maps.
	 */
	public Passenger get(String bookingRefCode) throws IllegalReferenceCodeException {
		Passenger output;
		if((output = passengersCheckedIn.get(bookingRefCode)) != null){
			return output;
		}else if((output = passengersNotCheckedIn.get(bookingRefCode)) != null){
			return output;
		}else{
			throw new IllegalReferenceCodeException
			("There is no passenger with this reference code: "+bookingRefCode);
		}
	}

	// !!! IS THIS ALLOWED? !!! Im referring to passign hte entire hash map

	//
	public HashMap<String,Passenger> getNotCheckedIn(){
		return passengersNotCheckedIn;
	}

	public HashMap<String,Passenger> getCheckedIn(){
		return passengersCheckedIn;
	}

	/**
	 * Adds a passenger to the collection.
	 * Adds the passenger to the correct hash map based on if they haved been checked in or not.
	 * Checks to make sure there isn't already a passenger with the same booking reference code.
	 * 
	 * @param	thePassenger	The passenger that is to be added to the collection
	 * @param	checkedIn		Whether the passenger has been checked in yet or not.
	 * @return	boolean to say if the passenger was added to the list successfully.
	 */
	public boolean add(Passenger thePassenger, boolean checkedIn) {
		// Cant have two passengers with the same booking reference code, first need to check both hashmaps
		if(passengersCheckedIn.containsKey(thePassenger.getBookingRefCode()) 
		|| passengersNotCheckedIn.containsKey(thePassenger.getBookingRefCode())){
			// As one of the lists contains a passenger with the same key, we return false to show that it cannot be added.
			return false;
		}
		// if the method hasn't been stopped from the return above, then we can add the passenger to the right hashmap
		if(checkedIn){
			passengersCheckedIn.put(thePassenger.getBookingRefCode(),thePassenger);
			thePassenger.getFlight().addPassengerAndBaggage(0,0); // Add to the number of passengers on this flight, there is no information on the baggage for these passengers
		}
		else{
			passengersNotCheckedIn.put(thePassenger.getBookingRefCode(),thePassenger);
		}
		// With the passenger added to the collection we can return true to show operation was successful.
		return true;
	}
	
	/**
	 * Remove the passenger with the matching booking reference code from the collection.
	 * Checks both hashmaps to see if they contain a matching booking reference code, 
	 * if one does removes the passenger from the collection.
	 * 
	 * @param	bookingRefCode	The booking reference of the passenger that is to be removed
	 * @return	A boolean to say if a passenger was removed or if there is no matching passenger with this booking reference code.
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
	 * Retrieve the number of passengers that are left to be checked in.
	 * 
	 * @return	The number of passengers left to be checked in.
	 */
	public int getNumToCheckIn() {
		return passengersNotCheckedIn.size();
	}
	
	/**
	 * Process the passenger as being checked in.
	 * This moves them from the not checked in hash map to the checked in hashmap.
	 * 
	 * @param	bookingRefCode	The booking reference of the passenger to be checked in.
	 * @return	A boolean to show if the passenger was moved successfully, returns false when there is no matching bookingRefCode, in the collection for passengers to be checked in.
	 */
	public boolean checkInPassenger(String bookingRefCode){
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