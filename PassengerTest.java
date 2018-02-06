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
		String bookingRef = "A0A0A0A0A0";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);
		
		assertEquals("BookRef mismatch", bookingRef, 
				testPassenger.getBookingRefCode());
		assertEquals("firstName mismatch", firstName, 
				testPassenger.getFirstName());
		assertEquals("surname mismatch", surname, 
				testPassenger.getLastName());
		assertEquals("flight mismatch", surname, 
				testPassenger.getLastName());
	}
	
	@Test
	/**
	 * Test that hash of a Passenger is the same
	 * as the hash of its bookingRef
	 */
	public void testHash1() {
		String bookingRef = "A0A0A0A0A0";
		String firstName = "Gordon";
		String surname = "Brown";
		
		//will need modified once actual Flight
		//object is delivered
		Flight flight = new Flight();
		
		Passenger testPassenger = new Passenger(bookingRef, 
				firstName, surname, flight);
		
		assertEquals("Hash mismatch", bookingRef.hashCode(),
				testPassenger.hashCode());
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
		
		Passenger testPassenger1 = new Passenger(bookingRef, 
				firstName, surname, flight);
		Passenger testPassenger2 = new Passenger(bookingRef, 
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
		
		assertTrue("Passengers are identical so equals should remain true.",
				testPassenger.equals(testPassenger2));
		
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
	public void testBookingRef(Passenger testPassenger) {
		assertTrue("Passenger bookingRef should be 10 characters long,"
				+ "consisting of uppercase letters and numbers.",
				testPassenger.getBookingRefCode().matches("[A-Z0-9]{10}"));
	}
}
