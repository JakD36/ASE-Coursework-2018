/**skeleton of passenger class**/
public class Passenger implements Comparable<Passenger> {
	public String getBookingRefCode() {
		return BookingRefCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Flight getFlight() {
		return flight;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	public Passenger() {
		
	}
	
	String BookingRefCode;
	String firstName;
	String lastName;
	Flight flight;
	public Passenger(String referenceCode, String firstName, 
			String lastName, Flight flight) {
		BookingRefCode = referenceCode.toUpperCase();
		this.firstName = firstName;
		this.lastName = lastName;
		this.flight = flight;
		
		System.out.println("LastName='" + this.lastName + "'");
	}
	

	@Override
	public int compareTo(Passenger other) {
		return 0;
	}
	
	@Override
	public boolean equals(Object other) {
		return false;
	}
}
