public class Passenger {
	private String bookingRefCode;
	private String firstName;
	private String lastName;
	Flight flight;

<<<<<<< HEAD
	public Passenger(String bookingRefCode, String firstName, String lastName, Flight flight) 
		throws IllegalReferenceCodeException {
	//validate booking ref code
	if(!bookingRefCode.matches("[a-z]{3}[0-9]{4}")) {
		//if it fails throw an exception
		throw new IllegalReferenceCodeException
		("Illegal booking reference passed to constructor: " + bookingRefCode);
	}
		
	this.setBookingRefCode(bookingRefCode);
	this.setFirstName(firstName);
	this.setLastName(lastName);
	this.setFlight(flight);
}
=======
	public Passenger(String bookingRefCode, String firstName, String lastName, Flight flight) {
		this.setBookingRefCode(bookingRefCode);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setFlight(flight);
	}
>>>>>>> Jack

	public String getBookingRefCode() {
		return this.bookingRefCode;
		}
	
	public void setBookingRefCode(String bookingRefCode) {
		this.bookingRefCode = bookingRefCode;
		}
	
	public String getFirstName() {
		return this.firstName;
		}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		}
	
	public String getLastName() {
		return this.lastName;
		}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		}
	
	public Flight getFlight() {
		return this.flight;
		}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
		}
	
	public boolean equals(Object obj)
	{
		return (obj instanceof Passenger) && (((Passenger)obj).getBookingRefCode().toUpperCase().equals(this.getBookingRefCode().toUpperCase()));
	}

	public int compareTo(Passenger passenger)
	{
		return this.getBookingRefCode().toUpperCase().compareTo(passenger.getBookingRefCode().toUpperCase());
	}
	


}

	
	



