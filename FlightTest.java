import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightTest {
	List<Flight> flights = new ArrayList<Flight>();

	@BeforeEach
	void setUp() throws Exception {
		flights.add(new Flight("AZ1234","Rome","Alitalia",120,12,22,2.5f));
		flights.add(new Flight("FR1234","London Stansted","Ryanair",180,10,10,5));
		flights.add(new Flight("U21234","Edinburgh","Easyjet",160,13,12,1.25f));
		
		for (Flight f : flights)
		{
			f.addPassengerAndBaggage(9, 9);
			f.addPassengerAndBaggage(5, 15);
			f.addPassengerAndBaggage(50, 2);
			f.addPassengerAndBaggage(15, 14);
		}
	}

	
	@Test
	/**
	 * Test that the report generated is of the expected format,
	 * defined by the regular expression found below.
	 */
	public void generateReportsTest1() {
		
		for (Flight f : flights)
		{
		
		String regex = "Flight code: [a-z]{3}\\d{4}\nNumber of Passengers: \\d+\nTotal Baggage Weight: \\d+\nTotal Baggage Volume: \\d+\nTotal Excess Fees: \\d+\nCapacity Exceeded: (yes|no)";
		Matcher m = Pattern.compile(regex).matcher(f.generateReport());
		assertEquals(true,m.matches());
		}
	}
	
	@Test
	/**
	 * Tests that the generate report does return a String object,
	 * and that it doesn't return nothing.
	 */
	public void generateReportsTest2() {
		
		for (Flight f : flights) 
			{
		assertFalse(f.generateReport() == null);
			}
		}
	
	@Test
	/**
	 * Test to make sure that the generate report doesn't
	 * just return an empty string.
	 */
	public void generateReportsTest3() {
		for (Flight f : flights)
		{
		assertFalse(f.generateReport() == "");
		}
	}

	
	
	@Test
	void testTotalPassengers() {
		
		for (Flight f : flights)
		{
			assert(f.getTotalPassengers()==4);
		}
	}
	
	@Test
	void testTotalFees1() {
		assertEquals(flights.get(0).getTotalFees(),0f);
	}
	
	@Test
	void testTotalFees2() {
		assertEquals(flights.get(1).getTotalFees(),45f);
	}
	
	@Test
	void testTotalFees3() {
		assertEquals(flights.get(2).getTotalFees(),6.25f);
	}

}
