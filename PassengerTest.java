import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;


public class PassengerTest {
	String bookingRef1, firstName1, lastName1,
		bookingRef2, firstName2, lastName2;
	
	Flight flight1, flight2;
	
	Passenger passenger1; //has a lower ref than passenger2
	Passenger passenger2; //has a higher ref than passenger1
	//equal ref to passenger1, but all other fields different
	Passenger passenger3;
	//different ref to passenger1, but all other fields equal
	Passenger passenger4;
	Passenger passenger5; //given lowercase booking ref
	
	@Before
	/**
	 * Set up there Passengers. First passenger has a high
	 * booking ref, the second a lower and the third has a
	 * booking ref equal to the first.
	 */
	public void setUp() {
		bookingRef1 = "abc1234";
		firstName1 = "Gordon";
		lastName1 = "Brown";
		flight1 = Mockito.mock(Flight.class);
		
		bookingRef2 = "bbb2222";
		firstName2 = "Walter";
		lastName2 = "White";
		flight2 = Mockito.mock(Flight.class);
		
		
		passenger1 = new Passenger(bookingRef1, 
				firstName1, lastName1, flight1);
		
		//higher ref than one
		passenger2 = new Passenger(bookingRef2, 
				firstName2, lastName2, flight2);
		
		//same booking ref as passenger1, but different name and flight
		passenger3 = new Passenger(bookingRef1, 
				firstName2, lastName2, flight2);
		
		//different ref to passenger1, but same name and flight
		passenger4 = new Passenger(bookingRef2, 
				firstName1, lastName1, flight1);
		
		//uppercase booking ref
		passenger5 = new Passenger("ABC1234", 
				firstName1, lastName1, flight1);
	}
	
	@Test
	/**
	 * Test that Passenger's constructor assigns the correct
	 * booking ref and that the correct value is returned by
	 * its getBookingRefCode method.
	 */
	public void testPassengerConstructorBookingRef() {
		assertEquals("Booking Ref mismatch", bookingRef1, 
				passenger1.getBookingRefCode());
	}
	
	@Test
	/**
	 * Test that Passenger has valid booking ref
	 */
	public void testPassengerConstructorBookingRef2() {
		assertTrue("Passenger booking ref should follow pattern of "
				+ "three lowercase letters followed by 4 numbers",
				passenger5.getBookingRefCode().matches("[a-z]{3}[0-9]{4}"));
	}
	
	@Test
	/**
	 * Test that Passenger's constructor assigns the correct
	 * first name and that the correct value is returned by
	 * its getFirstName method.
	 */
	public void testPassengerConstructorFirstName() {
		assertEquals("first name mismatch", firstName1, 
				passenger1.getFirstName());
	}
	
	@Test
	/**
	 * Test that Passenger's constructor assigns the correct
	 * last name and that the correct value is returned by
	 * its getLastName method.
	 */
	public void testPassengerConstructorLastName() {
		assertEquals("last name mismatch", lastName1, 
				passenger1.getLastName());
	}
	
	@Test
	/**
	 * Test that Passenger's constructor assigns the correct
	 * last name and that the correct value is returned by
	 * its getFlight method.
	 */
	public void testPassengerConstructorFlight() {
		assertEquals("flight mismatch", flight1, 
				passenger1.getFlight());
	}
	
	
	@Test
	/**
	 * Test that hash of a Passenger is the same
	 * as the hash of its bookingRef
	 */
	public void testHash1() {
		assertEquals("Hash mismatch", bookingRef1.hashCode(),
				passenger1.hashCode());
	}
	
	@Test
	/**
	 * Test comparing a lower Passenger with a higher passenger.
	 */
	public void testCompareTo1() {
		assertTrue("Calling passenger is < arg. Should return negative value",
				passenger1.compareTo(passenger2) < 0);
	}
	
	@Test
	/**
	 * Test comparing a higher Passenger with a lower one.
	 */
	public void testCompareTo2() {
		assertTrue("Calling passenger is > arg. Should return positive value",
				passenger2.compareTo(passenger1) > 0);
	}
	
	@Test
	/**
	 * Test comparing Passenger to Passenger with the same
	 * booking ref, but different name and flight
	 */
	public void testCompareTo3() {
		assertTrue("Calling passenger has same "
				+ "bookingRef as arg. Should return 0",
				passenger1.compareTo(passenger3) == 0);
	}
	
	@Test
	/**
	 * Test comparing Passenger with itself.
	 */
	public void testCompareTo4() {
		assertTrue("Calling passenger is the same "
				+ "object as arg. Should return 0",
				passenger2.compareTo(passenger2) == 0);
	}
	
	@Test
	/**
	 * Test equality between passengers with the same
	 * booking ref, but different name and flight
	 */
	public void testEquals1() {

		assertTrue("Passengers have same bookingRef "
				+ "so equals should remain true.",
				passenger3.equals(passenger1));
	}
	
	@Test
	/**
	 * Test inequality between passengers with the
	 * same name and flight but different booking ref
	 */
	public void testUnequal() {
		assertFalse("Passengers bookingRefs are"
				+ " not equal. Result should be false",
				passenger1.equals(passenger4));
	}
}
