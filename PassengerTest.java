import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerTest {

	@Test
	/**
	 * Test that Passenger's constructor assigns the correct
	 * values and that the correct values are returned by
	 * its get methods.
	 */
	public void testPassengerConstructor1() {
		String bookingRef = "A0A0A0A0A0";			// Jack - You can use the setup method to create variables for testing 
		String firstName = "Gordon";				// that will be used in the tests rather than assigning them each test
		String surname = "Brown";				// I believe my test class has use of this
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight(); 				// Jack - Should we be mocking this object rather than using the actual object
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);
		
									// Jack - Should we collect all the expected formats of the outputs of different methods somewherer
		assertEquals("BookRef mismatch", bookingRef, 		// Jack - I've read its good practice to have a new method for each test
				testPassenger.getBookingRefCode());	// as there can be an issue where if the test fails it stops going
		assertEquals("firstName mismatch", firstName, 		// through the method. so if the first assert fails then we dont get 
				testPassenger.getFirstName());		// to see the results of the rest.
		assertEquals("surname mismatch", surname, 
				testPassenger.getLastName());
		assertEquals("flight mismatch", surname, 		// Jack - you've got getLastName tested twice, I assume this second one 
				testPassenger.getLastName());		// is supposed to be getFlight?
	}
	
	@Test
	/**
	 * Test that hash of a Passenger is the same
	 * as the hash of its bookingRef
	 */
	public void testHash1() {
		String bookingRef = "A0A0A0A0A0";			// Jack - I'll just write it here again think we can use the setup method
		String firstName = "Gordon";				// to instantiate variables used in tests.
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);		// Jack - Everything up to this could be included in the setup I think
		
		assertEquals("Hash mismatch", bookingRef.hashCode(),	// Jack - One assert for the test looks good
				testPassenger.hashCode());		// Jack - Just so I know is the assertEquals in the format
									// (message to be returned,what we expect,what we actually get)?
	}
	
	@Test
	/**
	 * Test comparing a lower Passenger with a higher passenger.
	 */
	public void testCompareTo1() {
		String bookingRefLow = "A0A0A0A0A0";
		String bookingRefHigh = "B0A0A0A0A0";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassengerSmall = new Passenger(bookingRefLow, 
				firstName, surname, flight);
		Passenger testPassengerLarge = new Passenger(bookingRefHigh, 
				firstName, surname, flight);
		
		assertTrue("Calling passenger is < arg. Should return negative value",
				testPassengerSmall.compareTo(testPassengerLarge) < 0);
	}
	
	@Test
	/**
	 * Test comparing a higher Passenger with a lower one.
	 */
	public void testCompareTo2() {
		String bookingRefLow = "A0A0A0A0A0";
		String bookingRefHigh = "B0A0A0A0A0";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassengerSmall = new Passenger(bookingRefLow, 
				firstName, surname, flight);
		Passenger testPassengerLarge = new Passenger(bookingRefHigh, 
				firstName, surname, flight);
		
		assertTrue("Calling passenger is > arg. Should return positive value",
				testPassengerLarge.compareTo(testPassengerSmall) > 0);
	}
	
	@Test
	/**
	 * Test comparing Passenger to equal Passenger.
	 */
	public void testCompareTo3() {
		String bookingRef = "FFFFFFFFFF";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger1 = new Passenger(bookingRef, 			// Jack - Would it not make more sense to change up, the names and flight
				firstName, surname, flight);				// This way youre not just comparing the passenger to themselves, cause thats
		Passenger testPassenger2 = new Passenger(bookingRef, 			// effectively what youre doing in this test and the next.
				firstName, surname, flight);
		
		assertTrue("Calling passenger is == arg. Should return 0",
				testPassenger1.compareTo(testPassenger2) == 0);
	}
	
	@Test
	/**
	 * Test comparing Passenger with itself.
	 */
	public void testCompareTo4() {
		String bookingRef = "FFFFFFFFFF";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);

		
		assertTrue("Calling passenger is the same object as arg. Should return 0",
				testPassenger.compareTo(testPassenger) == 0);
	}
	
	@Test
	/**
	 * Test Passenger's equals function with two passengers with
	 * identical values.
	 */
	public void testEquals1() {
		String bookingRef = "FFFFFFFFFF";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);
		Passenger testPassenger2 = new Passenger(bookingRef, 
				firstName, surname, flight);
		
		assertTrue("Passengers are identical so equals should remain true.",		// Jack - So its bookingRef for comparison, what is it for equals?
				testPassenger.equals(testPassenger2));				// looks like its the booking ref again?
		
		assertTrue("Passengers are identical so equals should remain true.",
				testPassenger2.equals(testPassenger));
	}
	
	@Test
	/**
	 * Test Passenger's equals method with unequal argument.
	 */
	public void testUnequal() {
		String bookingRefLow = "A0A0A0A0A0";
		String bookingRefHigh = "B0A0A0A0A0";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassengerSmall = new Passenger(bookingRefLow, 
				firstName, surname, flight);
		Passenger testPassengerLarge = new Passenger(bookingRefHigh, 
				firstName, surname, flight);
		
		assertEquals("Passengers are not equal. Result should be false",
				testPassengerSmall.equals(testPassengerLarge), false);
		assertEquals("Passengers are not equal. Result should be false",
				testPassengerLarge.equals(testPassengerSmall), false);
	}
	
	
	@Test
	/**
	 * Test that Passenger converts chars to upper case
	 */
	public void testLower() {
		String bookingRef = "a0a0a0a0a0";					 
		System.out.println(bookingRef.length());
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);

		
		testBookingRef(testPassenger);
	}
	
	/**
	 * Test bookingRef is in a valid format.
	 * 
	 * Can be changed after bookingRef format is agreed.
	 * 
	 * For now tests that bookingRef is exactly 10 characters
	 * and that all characters are uppercase letters or numbers.
	 * 
	 * @param testPassenger Passenger whose bookingRef to test
	 */
	public void testBookingRef(Passenger testPassenger) {						// Is it worth having this as a seperate method, if used the once or 
		assertTrue("Passenger bookingRef should be 10 characters long,"				// were you plannng on adding more uses of it?
				+ "consisting of uppercase letters and numbers.",
				testPassenger.getBookingRefCode().matches("[A-Z0-9]{10}"));
	}
}
