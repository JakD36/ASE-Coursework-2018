import static org.junit.Assert.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckInTest {
	
	public CheckInHandler desk;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		desk = new CheckInHandler(); 
	}

	@Test
	public void constructorTest() {
		
	}
	
	@Test
	public void processPassengerTest1() {
		assertFalse(desk.processPassenger(""));
	}
	
	@Test
	public void processPassengerTest2() {
		assertFalse(desk.processPassenger("jdfgadsga"));
	}
	
	@Test
	public void processPassengerTest3() {
		assertFalse(desk.processPassenger("dav123"));
	}
	
	@Test
	public void processPassengerTest4() {
		assertTrue(desk.processPassenger("dav001"));
	}
	
	@Test
	public void processBaggageTest1() {
		float[] dim = {1,2,3};
		float weight = 10;
		assertFalse(desk.processBaggage(dim,weight) < 0.0f);
	}
	
	@Test
	public void numToCheckInTest1() {
		assertFalse(desk.getNumToCheckIn() < 0);
	}
	
	@Test
	public void generateReportsTest1() {
		String regex = "Flight code: [a-z]{3}\\d{3}\nNumber of Passengers: \\d+\nTotal Baggage Weight: \\d+\nTotal Baggage Volume: \\d+\nTotal Excess Fees: \\d+\nCapacity Exceeded: (yes|no)";
		Matcher m = Pattern.compile(regex).matcher(desk.generateReport());
		assertEquals(true,m.matches());
	}
	
	@Test
	public void generateReportsTest2() {
		assertFalse(desk.generateReport() == null);
	}
	
	@Test
	public void generateReportsTest3() {
		assertFalse(desk.generateReport() == "");
	}
}
