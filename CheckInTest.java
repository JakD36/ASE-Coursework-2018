import static org.junit.Assert.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.Mockito;

public class CheckInTest {
	
	public CheckInHandler desk;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	/**
	 * Instantiate the CheckInHandler object desk
	 */
	public void setUp() throws Exception {
		desk = new CheckInHandler(); 
	}

	@Test
	public void constructorTest() {
		
	}
	
	@Test
	/**
	 * Tests whether two emtpy strings as input to processPassenger will be accepted.
	 * The processPassenger method takes in the 
	 * booking reference and the last name of the passenger.
	 */
	public void processPassengerTest1() {
		assertFalse(desk.processPassenger("",""));
	}
	
	@Test
	/**
	 * Tests to see if an invalid booking reference does not
	 * return as successful.
	 */
	public void processPassengerTest2() {
		assertFalse(desk.processPassenger("jdfgadsga","Davidson"));
	}
	
	@Test
	/**
	 * Tests to see if an invalid surname with a valid booking
	 * reference returns does not return as successful.
	 */
	public void processPassengerTest3() {
		assertFalse(desk.processPassenger("dav0001","osdfijsdjg"));
	}
	
	@Test
	/**
	 * Test that providing a valid booking reference and surname,
	 * returns successful.
	 */
	public void processPassengerTest4() {
		assertTrue(desk.processPassenger("dav0001","Davidson"));
	}
	
	@Test
	/**
	 * Tests that the processBaggage method does not return a
	 * negative number as it returns the fee for any excess baggage
	 * or 0 if there is no fee.
	 */
	public void processBaggageTest1() {
		float[] dim = {1,2,3};
		float weight = 10;
		assertFalse(desk.processBaggage(dim,weight) < 0.0f);
	}
	
	@Test
	/**
	 * Attempts to check in the same passenger twice, when there is only one passenger on the list.
	 */
	public void numToCheckInTest1() {
		desk.processPassenger("dav0001","Davidson");
		desk.processPassenger("dav0001","Davidson");
		assertFalse(desk.getNumToCheckIn() < 0);
	}
	
}
