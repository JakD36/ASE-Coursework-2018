import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightTest {
	List<Flight> flights = new ArrayList<Flight>();

	@BeforeEach
	void setUp() throws Exception {
		//set up flights for test
		//set 4 flights with 4 passengers each
		flights.add(new Flight("AZ1234","Rome","Alitalia",12,22,2.5f, 100, 100f,100f));
		flights.add(new Flight("FR1234","London Stansted","Ryanair",10,10,5,3,100f,100f));
		flights.add(new Flight("U21234","Edinburgh","Easyjet",13,12,1.25f,100,10f,100f));
		flights.add(new Flight("U24321","Milan Malpensa","Easyjet",13,12,1.25f,100,100f,10f));
		
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
			String regex = "Flight code: [a-zA-Z0-9]{2}[0-9]{4}\nNumber of Passengers: [0-9]+\nTotal Baggage Weight: [0-9]+\\.[0-9]+\nTotal Baggage Volume: [0-9]+\\.[0-9]+\nTotal Excess Fees: [0-9]+\\.[0-9]+\nExceeded: (yes|no)";
			String rep = f.generateReport().trim();
			assertTrue(rep.matches(regex));
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
			assertFalse(f.generateReport().equals(""));
		}
	}

	
	
	@Test
	void testTotalPassengers() {
		
		for (Flight f : flights)
		{
			assert(f.getCurrentTotalPassengers()==4);
		}
	}
	
	@Test
	void testTotalFees1() {
		assertEquals(flights.get(0).getCurrentTotalFees(),0f);
	}
	
	@Test
	void testTotalFees2() {
		assertEquals(flights.get(1).getCurrentTotalFees(),45f);
	}
	
	@Test
	void testTotalFees3() {
		assertEquals(flights.get(2).getCurrentTotalFees(),6.25f);
	}
	
	
	@Test
	void checkExceeding() {
		assertEquals(flights.get(0).hasExceeded(),false);
		assertEquals(flights.get(1).hasExceeded(),true);
		assertEquals(flights.get(2).hasExceeded(),true);
		assertEquals(flights.get(3).hasExceeded(),true);
	}

}
