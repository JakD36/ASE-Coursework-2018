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
	 * The processPassenger method takes in the 
	 * booking reference and the last name of the passenger.
	 * This test checks that empty strings cant be passed
	 * through and return as successful.
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
	 * Tries to remove two passengers when you only have 1 on
	 * the list. 
	 * 
	 * Unsure if this test is necessary please get back to me on your
	 * thoughts!
	 */
	public void numToCheckInTest1() {
		desk.processPassenger("dav0001","Davidson");
		desk.processPassenger("dav0001","Davidson");
		assertFalse(desk.getNumToCheckIn() < 0);
	}
	
	@Test
	/**
	 * Test that the report generated is of the expected format,
	 * defined by the regular expression found below.
	 */
	public void generateReportsTest1() {
		String regex = "Flight code: [a-z]{3}\\d{4}\nNumber of Passengers: \\d+\nTotal Baggage Weight: \\d+\nTotal Baggage Volume: \\d+\nTotal Excess Fees: \\d+\nCapacity Exceeded: (yes|no)";
		Matcher m = Pattern.compile(regex).matcher(desk.generateReport());
		assertEquals(true,m.matches());
	}
	
	@Test
	/**
	 * Tests that the generate report does return a String object,
	 * and that it doesn't return nothing.
	 */
	public void generateReportsTest2() {
		assertFalse(desk.generateReport() == null);
	}
	
	@Test
	/**
	 * Test to make sure that the generate report doesn't
	 * just return an empty string.
	 */
	public void generateReportsTest3() {
		assertFalse(desk.generateReport() == "");
	}
}
